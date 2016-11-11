package co.edu.udistrital.ingesoft;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import eu.trentorise.opendata.jackan.CkanClient;
import eu.trentorise.opendata.jackan.CkanQuery;
import eu.trentorise.opendata.jackan.model.CkanDataset;
import eu.trentorise.opendata.jackan.model.CkanResource;

@WebServlet(urlPatterns = { "/comet" }, displayName = "comet", asyncSupported = true)
public final class SearchCometServlet extends HttpServlet {

	private static int counter = 0;
	
	private static final long serialVersionUID = 1L;
	public static Map<String, String> users = new HashMap<String, String>();
	private static Map<String, List<String>> notifications = new HashMap<String, List<String>>();

	
	private final Queue<AsyncContext> queue = new ConcurrentLinkedQueue<AsyncContext>();
	private static Map<String, List<Object>> datasetsEncontrados = new HashMap<String, List<Object>>();

	private final Thread generator = new Thread() {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					while (!queue.isEmpty()) {
						try {
							Thread.sleep(10);
							// System.out.println("Procesa! ");
							process(queue.peek());
						} catch (Exception e) {
							System.out.println("freaking error");
							e.printStackTrace();
						}
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	};

	@Override
	public void init() throws ServletException {
		generator.start();
	}

	@Override
	public void destroy() {
		generator.interrupt();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

//		if (!userExist(req.getRequestedSessionId())){
			AsyncContext actx = req.startAsync();
			actx.setTimeout(3600000);
			queue.offer(actx);

			/**
			 * Listens for timeout requests and removes them from the queue
			 */
			actx.addListener(new AsyncListener() {
				public void onTimeout(AsyncEvent arg0) throws IOException {

					HttpServletResponse res = (HttpServletResponse) arg0.getAsyncContext().getResponse();
					PrintWriter pw = res.getWriter();
					pw.write("{}");
					res.setStatus(HttpServletResponse.SC_OK);
					res.setContentType("application/json");

					queue.remove(arg0.getAsyncContext());
				}

				public void onStartAsync(AsyncEvent arg0) throws IOException {
				}

				public void onError(AsyncEvent arg0) throws IOException {
					queue.remove(arg0.getAsyncContext());
				}

				public void onComplete(AsyncEvent arg0) throws IOException {
					queue.remove(arg0.getAsyncContext());
				}
			});
	//	}
		
		
	}

	private boolean userExist(String requestedSessionId) {
		for (Map.Entry<String, String> e : users.entrySet()) {
		    String key = e.getKey();
		    
		    if (key.contentEquals(requestedSessionId))
		    	return true;
		}
		return false;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String message = req.getParameter("notification");
		String username = req.getSession().getId();

		try (JsonReader reader = Json.createReader(new StringReader(message))) {
			JsonObject jsonMessage = reader.readObject();

			System.out.println("JSONMESSAGE " + jsonMessage);
			if ("buscar".equals(jsonMessage.getString("action"))) {
				JsonObject joDatos = jsonMessage.getJsonObject("datos");
				buscar(username, joDatos.getString("abuscar"), joDatos.getString("metodo"));
			}
		}
	}
	
	

	private void process(AsyncContext ctx) {
		if (ctx == null) {
			System.out.println("ctx null");
			return;
		}
		
		HttpServletRequest req = (HttpServletRequest) ctx.getRequest();
		HttpServletResponse res = (HttpServletResponse) ctx.getResponse();

		if (req == null) {
			System.out.println("req null");
			queue.remove(ctx);
			return;
		}

		HttpSession session = req.getSession();

		if (session == null) {
			queue.remove(ctx);
			System.out.println("session null");
			return;
		}

		String sessId = session.getId();

		if (sessId == null) {
			queue.remove(ctx);
			System.out.println("sessId null");
			return;
		}

		if (datasetsEncontrados.get(sessId) == null) {
			return;
		}

		if (!datasetsEncontrados.get(sessId).isEmpty()) {
			System.out.println("Mensajes no vacios enviados a: "+sessId);
			try {
				String s = "[";

				for (Object msg : datasetsEncontrados.get(sessId)) {
					String txt="";
					if (msg instanceof CkanDataset){
						Gson gson = new Gson();
						txt=gson.toJson((CkanDataset)msg);
					}
					
					s += txt + ", ";
				}

				s = s.substring(0, s.length() - 2);
				s += "]";

				datasetsEncontrados.get(sessId).clear();

				res.getWriter().write("" + s + "");
				res.setStatus(HttpServletResponse.SC_OK);
				res.setContentType("application/json");

				ctx.complete();

				queue.remove(ctx);
			} catch (IOException e) {
				System.out.println("msgs excepcion:");
				e.printStackTrace();
			}
		}
	}

	public static void sendMsg(String to, Object ckanDatasetGeneral) {
		List<Object> msgs = datasetsEncontrados.get(to);

		if (msgs == null) {
			datasetsEncontrados.put(to, new ArrayList<Object>());
			msgs = datasetsEncontrados.get(to);
		}
		
		if (!msgs.contains(ckanDatasetGeneral)){
			System.out.println("Agrega: "+ckanDatasetGeneral.hashCode()+" al usuario "+to);
			msgs.add(ckanDatasetGeneral);
		}
	}

	public void buscar(String username, String busqueda, String lugar) {
		System.out.println("Buscar " + busqueda+" para el usuario: "+username);

		if (lugar.contains("geografia")) {
			System.out.println("Buscar 2" + lugar);

			CKANQuery ckanq = new CKANQuery();
			try {
				String a = ckanq.queryOnRepository(busqueda);
				List<CkanDataset> rtd = RestToCkan.parse(a);

				for (int i = 0; i < rtd.size(); i++) {
					sendMsg(username, rtd.get(i));
				}
			} catch (Exception ex) {
				Logger.getLogger(CKANQuery.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else if (lugar.contains("gobierno")) {
			// String BASE_DATASET_API = "https://datahub.io/dataset/";
			// CkanClient cc = new CkanClient(BASE_DATASET_API);
		}

		else {
			CkanClient cc = new CkanClient(lugar);
			CkanQuery query = CkanQuery.filter().byText(busqueda);
			List<CkanDataset> filteredDatasets = cc.searchDatasets(query, 100, 0).getResults();

			System.out.println("CKAN DATASETS: " + filteredDatasets.size());

			for (CkanDataset d : filteredDatasets) {

				CkanDataset cdg = (CkanDataset) d;

				if (d.getResources().size() > 0) {

					boolean av = false;

					for (CkanResource res : d.getResources()) {

						sendMsg(username, cdg);

						// try {
						// URL u = new URL(res.getUrl());
						// HttpURLConnection huc = (HttpURLConnection)
						// u.openConnection();
						//// huc.setConnectTimeout(20000);
						//// huc.setReadTimeout(20000);
						// huc.setRequestMethod("GET"); // OR
						// huc.setRequestMethod
						// // ("HEAD");
						// huc.connect();
						// int code = huc.getResponseCode();
						//
						// if (code == 200) {
						// av = true;
						// }
						// } catch (Exception e) {
						// e.printStackTrace();
						// }
					}

					if (av) {

					}

				}
			}

		}

	}
	
	public static String addUser(String sessionId) {
		String user = "User" + counter++;
		users.put(sessionId, user);
		
		System.out.println("Usuarios activos: ");
		
		for (Map.Entry<String, String> e : users.entrySet()) {
		    String key = e.getKey();
		    String value = e.getValue();
		    System.out.println(key+" "+value);
		}
		
		return user;
	}
}
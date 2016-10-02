package co.edu.udistrital.ingesoft;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

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

@WebServlet(urlPatterns = {"/comet"}, displayName = "comet", asyncSupported = true)
public final class SearchCometServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final Queue<AsyncContext> queue = new ConcurrentLinkedQueue<AsyncContext>();
	private static Map<String, List<String>> datasetsEncontrados = new HashMap<String, List<String>>();

	private final Thread generator = new Thread() {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					//Thread.sleep(10);
					
					while (!queue.isEmpty()) {
						try {
							Thread.sleep(10);
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

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		System.out.println("HACE UN GET "+req.getContextPath());
		
		/**
		 * it turns the request to an ascyn request and sets its timeout to 40
		 * seconds
		 */
		AsyncContext actx = req.startAsync();
		actx.setTimeout(40000);
		queue.offer(actx);	

		/**
		 * Listens for timeout requests and removes them from the queue
		 */
		actx.addListener(new AsyncListener() {
			public void onTimeout(AsyncEvent arg0) throws IOException {

				HttpServletResponse res = (HttpServletResponse) arg0
						.getAsyncContext().getResponse();
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
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		/**
		 * it gets a message from chat, gets username and sends a broadcast
		 */
		String message = req.getParameter("notification");
		String username = req.getSession().getId();
//		sendBroadcast(username + ": " + msg);
		
		System.out.println(username + ": " + message);
		
		try (JsonReader reader = Json.createReader(new StringReader(message))) {
			JsonObject jsonMessage = reader.readObject();

			System.out.println("JSONMESSAGE " + jsonMessage);
			if ("buscar".equals(jsonMessage.getString("action"))) {
				JsonObject joDatos=jsonMessage.getJsonObject("datos");
				buscar(username, joDatos.getString("abuscar"), joDatos.getString("metodo"));
			}
		}
	}

	private void process(AsyncContext ctx) {
		if (ctx == null){
			return;
		}
		
		HttpServletRequest req = (HttpServletRequest) ctx.getRequest();
		HttpServletResponse res = (HttpServletResponse) ctx.getResponse();
		HttpSession session = req.getSession();

		if (session == null) {
			return;
		}

		String sessId = session.getId();
		
		if (sessId == null) {
			return;
		}
		
		List<String> msgs = datasetsEncontrados.get(sessId);
		
		if (msgs == null) {
			return;
		}
		

		if (!msgs.isEmpty()) {
			System.out.println("Mensajes no vacios ");
			
			try {
				String s = "[";

				for (String msg : msgs) {
					s += msg+", ";
				}

				s = s.substring(0, s.length() - 2);
				s += "]";

				msgs.removeAll(msgs);

				res.getWriter().write("" + s + "");
				res.setStatus(HttpServletResponse.SC_OK);
				res.setContentType("application/json");

				ctx.complete();
				queue.remove(ctx);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void sendMsg(String to, String msg) {
		List<String> msgs = datasetsEncontrados.get(to);

		if (msgs == null) {
			datasetsEncontrados.put(to, new ArrayList<String>());
			msgs = datasetsEncontrados.get(to);
		}

		msgs.add(msg);
	}
	
	public void buscar(String username, String busqueda, String lugar) {
		System.out.println("Buscar");
		
		CkanClient cc = new CkanClient(lugar);
    	CkanQuery query = CkanQuery.filter().byText(busqueda);
        List<CkanDataset> filteredDatasets = cc.searchDatasets(query, 100, 0).getResults();

        System.out.println("CKAN DATASETS: "+filteredDatasets.size());
        
        for (CkanDataset d : filteredDatasets) {
        	Gson gson = new Gson();            
            	if (d.getResources().size()>0){
            		
            		boolean av=false;
            		
            		for ( CkanResource res : d.getResources()){
            			sendMsg(username, gson.toJson(d));
            			try {
            				URL u = new URL ( res.getUrl());
                			HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection (); 
                			huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD"); 
                			huc.connect () ; 
                			int code = huc.getResponseCode() ;
                			
                			if(code==200){
                				av=true;
                			}
            			}
            			catch (Exception e) {
            				e.printStackTrace();
            			}
            			
            		}
            		
            		if (av){
            			
            		}
            		
            		
            	}
        }    	
	}
}
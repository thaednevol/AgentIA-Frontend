package co.edu.udistrital.ingesoft;

import java.io.IOException;
import java.io.StringReader;
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
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.glassfish.grizzly.comet.CometContext;
import org.glassfish.grizzly.comet.CometEngine;
import org.glassfish.grizzly.comet.CometEvent;
import org.glassfish.grizzly.http.server.Response;

import com.google.gson.Gson;

import eu.trentorise.opendata.jackan.CkanClient;
import eu.trentorise.opendata.jackan.CkanQuery;
import eu.trentorise.opendata.jackan.model.CkanDataset;
import eu.trentorise.opendata.jackan.model.CkanResource;

@WebServlet(asyncSupported = true)
public final class SearchCometServlet extends HttpServlet {

	private String contextPath = null;
	
	private static final long serialVersionUID = 1L;
	public static Map<String, String> users = new HashMap<String, String>();
		
	SearchHandler handler = new SearchHandler();
	
//	private final Thread generator = new Thread() {
//		@Override
//		public void run() {
//			while (!Thread.currentThread().isInterrupted()) {
//				try {
//					while (!queue.isEmpty()) {
//						try {
//							Thread.sleep(10);
//							// System.out.println("Procesa! ");
//							process(queue.peek());
//						} catch (Exception e) {
//							System.out.println("freaking error");
//							e.printStackTrace();
//						}
//					}
//
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
//	};

	 @Override
	    public void init(ServletConfig config) throws ServletException {
	    	ServletContext context = config.getServletContext();
	    	contextPath = context.getContextPath() + "/comet";
	    	 CometEngine engine = CometEngine.getEngine();
	    	 
	    	 CometContext cometContext = engine.register(contextPath);
	    	 cometContext.setExpirationDelay(30 * 1000);
	    }

	@Override
	public void destroy() {
		//generator.interrupt();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("LLAMA DOGET SI? "+contextPath+" "+req.getMethod());
//		CometEngine engine = CometEngine.getEngine();
//    	engine.getCometContext(contextPath).notify(null);
//    	
		CometEvent<Object> cometEvent = new CometEvent<Object>(CometEvent.Type.NOTIFY);
		handler.setRequest(req);
		handler.attach(res);
		handler.onEvent(cometEvent);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		String message = req.getParameter("notification");
//		String username = req.getSession().getId();
//
//		try (JsonReader reader = Json.createReader(new StringReader(message))) {
//			JsonObject jsonMessage = reader.readObject();
//
//			System.out.println("JSONMESSAGE " + jsonMessage);
//			if ("buscar".equals(jsonMessage.getString("action"))) {
//				JsonObject joDatos = jsonMessage.getJsonObject("datos");
//				buscar(username, joDatos.getString("abuscar"), joDatos.getString("metodo"));
//			}
//		}
		
		handler.setRequest(req);
    	handler.attach(res);
    	
    	CometEngine engine = CometEngine.getEngine();
    	CometContext context = engine.getCometContext(contextPath);
    	
    	context.addCometHandler(handler);
		
    	System.out.println("CONTEXTO: "+contextPath);
    	
		context.notify(null);
		
	}
	
	

//	private void process(AsyncContext ctx) {
//		if (ctx == null) {
//			System.out.println("ctx null");
//			return;
//		}
//		
//		HttpServletRequest req = (HttpServletRequest) ctx.getRequest();
//		HttpServletResponse res = (HttpServletResponse) ctx.getResponse();
//
//		if (req == null) {
//			System.out.println("req null");
//			queue.remove(ctx);
//			return;
//		}
//
//		HttpSession session = req.getSession();
//
//		if (session == null) {
//			queue.remove(ctx);
//			System.out.println("session null");
//			return;
//		}
//
//		String sessId = session.getId();
//
//		if (sessId == null) {
//			queue.remove(ctx);
//			System.out.println("sessId null");
//			return;
//		}
//
//		if (datasetsEncontrados.get(sessId) == null) {
//			return;
//		}
//
//		if (!datasetsEncontrados.get(sessId).isEmpty()) {
//			System.out.println("Mensajes no vacios enviados a: "+sessId);
//			try {
//				String s = "[";
//
//				for (Object msg : datasetsEncontrados.get(sessId)) {
//					String txt="";
//					if (msg instanceof CkanDataset){
//						Gson gson = new Gson();
//						txt=gson.toJson((CkanDataset)msg);
//					}
//					
//					s += txt + ", ";
//				}
//
//				s = s.substring(0, s.length() - 2);
//				s += "]";
//
//				datasetsEncontrados.get(sessId).clear();
//
//				res.getWriter().write("" + s + "");
//				res.setStatus(HttpServletResponse.SC_OK);
//				res.setContentType("application/json");
//
//				ctx.complete();
//
//				queue.remove(ctx);
//			} catch (IOException e) {
//				System.out.println("msgs excepcion:");
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public static void sendMsg(String to, Object ckanDatasetGeneral) {
//		List<Object> msgs = datasetsEncontrados.get(to);
//
//		if (msgs == null) {
//			datasetsEncontrados.put(to, new ArrayList<Object>());
//			msgs = datasetsEncontrados.get(to);
//		}
//		
//		if (!msgs.contains(ckanDatasetGeneral)){
//			System.out.println("Agrega: "+ckanDatasetGeneral.hashCode()+" al usuario "+to);
//			msgs.add(ckanDatasetGeneral);
//		}
//	}

	
	
	
}
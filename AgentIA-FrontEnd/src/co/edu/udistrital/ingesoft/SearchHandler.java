package co.edu.udistrital.ingesoft;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.glassfish.grizzly.comet.CometContext;
import org.glassfish.grizzly.comet.CometEngine;
import org.glassfish.grizzly.comet.CometEvent;
import org.glassfish.grizzly.comet.CometHandler;
import org.glassfish.grizzly.http.server.Response;

import com.google.gson.Gson;

import eu.trentorise.opendata.jackan.CkanClient;
import eu.trentorise.opendata.jackan.CkanQuery;
import eu.trentorise.opendata.jackan.model.CkanDataset;
import eu.trentorise.opendata.jackan.model.CkanResource;

public class SearchHandler implements CometHandler<HttpServletResponse> {

	private HttpServletResponse response;
	private HttpServletRequest request;

	static void inicializar() {
		// System.setProperty("http.proxyHost", "172.31.239.53");
		// System.setProperty("http.proxyPort", "3128");
		// System.setProperty("https.proxyHost", "172.31.239.53");
		// System.setProperty("https.proxyPort", "3128");
	}

	private final Queue<CkanDataset> queue = new ConcurrentLinkedQueue<CkanDataset>();

	private String contextPath = null;
	private final AtomicInteger counter = new AtomicInteger();

	private ArrayList<CkanDataset> resultados = new ArrayList<CkanDataset>();

	public void onInitialize(CometEvent event) throws IOException {

	}

	public void onInterrupt(CometEvent event) throws IOException {
		removeThisFromContext();
	}

	public void onTerminate(CometEvent event) throws IOException {
		removeThisFromContext();
	}

	public void attach(HttpServletResponse attachment) {
		this.response = attachment;
	}

	private void removeThisFromContext() throws IOException {
		response.getWriter().close();
		CometContext context = CometEngine.getEngine().getCometContext(contextPath);
		context.removeCometHandler(this);
	}

	@Override
	public void onEvent(CometEvent event) throws IOException {
		SSLUtilities.trustAllHostnames();
		SSLUtilities.trustAllHttpsCertificates();
		//

		System.out.println(
				"LLAMA ONEVENT " + event.getType() + " " + request.getSession().getId() + " " + request.getMethod());

		if ("GET".equals(request.getMethod())) {
			if (!queue.isEmpty()) {

				sendMsg(null, null);

			}
		} else if ("POST".equals(request.getMethod())) {
			final String message = request.getParameter("notification");

			if (message != null) {
				queue.clear();
				resultados.clear();

				final String username = request.getSession().getId();

				Thread thread = new Thread() {
					public void run() {

						try (JsonReader reader = Json.createReader(new StringReader(message))) {
							JsonObject jsonMessage = reader.readObject();

							System.out.println("JSONMESSAGE " + jsonMessage);
							if ("buscar".equals(jsonMessage.getString("action"))) {
								JsonObject joDatos = jsonMessage.getJsonObject("datos");
								buscar(username, joDatos.getString("abuscar"), joDatos.getString("metodo"));
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};

				thread.start();
				try {
					request.getRequestDispatcher("buscar.jsp").forward(request, response);
					event.getCometContext().resumeCometHandler(this);

				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public void buscar(String username, String busqueda, String lugar) {
		System.out.println("Buscar " + busqueda + " para el usuario: " + username);

		if (lugar.contains("geografia")) {// JULIAN
											// http://ec2-35-162-125-10.us-west-2.compute.amazonaws.com:8080/WsConsulta/wsConsulta?wsdl
			System.out.println("Buscar 2" + lugar);

			CKANGeografia ckanGeografia = new CKANGeografia();

			try {
				String respuesta = ckanGeografia.queryOnRepository(busqueda);
				List<CkanDataset> rtd = RestToCkan.parseGeografia(respuesta);
				for (int i = 0; i < rtd.size(); i++) {
					queue.offer(rtd.get(i));
					System.out.println("AGREGA A LA COLA " + rtd.get(i).getId());
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (lugar.contains("gobierno")) {

			CkanClient cc = new CkanClient("http://datahub.io/");
			CkanQuery query = CkanQuery.filter().byText(busqueda).byTagNames("government");
			List<CkanDataset> filteredDatasets = cc.searchDatasets(query, 100, 0).getResults();

			System.out.println("CKAN DATASETS: " + filteredDatasets.size());

			for (CkanDataset d : filteredDatasets) {

				CkanDataset cdg = (CkanDataset) d;

				queue.offer(cdg);

				if (d.getResources().size() > 0) {

					boolean av = false;

					for (CkanResource res : d.getResources()) {

						// sendMsg(username, cdg);
						// queue.add(cdg);

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

		} else if (lugar.contains("educacion")) {// CRISTIAN Y GABRIEL
			System.out.println("Buscar 2" + lugar);

			CKANEducacion ckanq = new CKANEducacion();
			try {
				String a = ckanq.queryOnRepository(busqueda);

				List<CkanDataset> rtd = RestToCkan.parseEducacion(a);

				for (int i = 0; i < rtd.size(); i++) {
					queue.offer(rtd.get(i));
					System.out.println("AGREGA A LA COLA " + rtd.get(i).getId());
				}
			} catch (Exception ex) {
				Logger.getLogger(CKANQuery.class.getName()).log(Level.SEVERE, null, ex);
			}

		}

		else {

		}

	}

	private void sendMsg(String username, CkanDataset ckanDataset) throws IOException {

		while (!queue.isEmpty()) {
			try {
				CkanDataset cd = queue.peek();
				resultados.add(cd);
				queue.remove(cd);
			} catch (Exception e) {
				System.out.println("freaking error");
				e.printStackTrace();
			}
		}

		PrintWriter writer = response.getWriter();

		Gson gson = new Gson();

		Respuesta r = new Respuesta();
		r.setError("0");
		r.setResultados(resultados);

		writer.write(gson.toJson(r));
		writer.flush();

		// writer.write("<script type='text/javascript'>" +
		// "parent.counter.updateCount('" + i + "')" +
		// "</script>\n");

		// writer.write("<script type='text/javascript'>"
		// + "console.log('" + username + "')"+
		// "</script>\n");

		// String s = "";

		// for (Object msg : datasetsEncontrados.get(sessId)) {
		// String txt="";
		// if (msg instanceof CkanDataset){
		// Gson gson = new Gson();
		// txt=gson.toJson((CkanDataset)msg);
		// }
		//
		// s += txt + ", ";
		// }
		//
		// s = s.substring(0, s.length() - 2);
		// Gson gson = new Gson();
		// String txt = gson.toJson(ckanDataset);
		// s += txt;
		// s += "";

		// writer.write("[" + s + "],");

		// writer.flush();

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");

		// System.out.println("ENVIA ESTE MENSAJE "+s);
	}

	@Override
	public CometContext<HttpServletResponse> getCometContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getResponse() {
		// TODO Auto-generated method stub
		System.out.println("LLAMA getResponse");
		return null;
	}

	@Override
	public void setCometContext(CometContext<HttpServletResponse> arg0) {
		// TODO Auto-generated method stub
		System.out.println("LLAMA setCometContext");

	}

	@Override
	public void setResponse(Response arg0) {
		System.out.println("LLAMA setResponse");
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}

// TESTS
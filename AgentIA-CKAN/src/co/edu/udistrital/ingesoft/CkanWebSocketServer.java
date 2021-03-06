//http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/HomeWebsocket/WebsocketHome.html

package co.edu.udistrital.ingesoft;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/actions")
public class CkanWebSocketServer {

	@Inject
	private CkanSessionHandler sessionHandler = new CkanSessionHandler();

	@OnOpen
	public void open(Session session) {
		System.out.println("abre CkanWebSocketServer");
		sessionHandler.addSession(session);
	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		System.out.println("Mensaje recibido en ckan:" + message);

		try (JsonReader reader = Json.createReader(new StringReader(message))) {
			JsonObject jsonMessage = reader.readObject();

			System.out.println("JSONMESSAGE " + jsonMessage);

			if ("buscar".equals(jsonMessage.getString("action"))) {
				JsonObject joDatos=jsonMessage.getJsonObject("datos");
				sessionHandler.buscar(joDatos.getString("abuscar"), joDatos.getString("metodo"));
			}

		}
	}

	@OnClose
	public void close(Session session) {
		System.out.println("cierra CkanWebSocketServer");
		sessionHandler.removeSession(session);
	}

	@OnError
	public void onError(Throwable error) {
		Logger.getLogger(CkanWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
	}
}

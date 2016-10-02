package co.edu.udistrital.ingesoft;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ApplicationScoped;
import javax.websocket.Session;

import com.google.gson.Gson;

import eu.trentorise.opendata.jackan.CkanClient;
import eu.trentorise.opendata.jackan.CkanQuery;
import eu.trentorise.opendata.jackan.model.CkanDataset;

@ApplicationScoped
public class CkanSessionHandler {
	private final Set<Session> sessions = new HashSet<>();
    
    public void addSession(Session session) {
        sessions.add(session);
    
    }
    
    public void buscar(String busqueda, String lugar){
    	Buscador b = new Buscador(sessions);
    	b.setBusqueda(busqueda);
    	b.setLugar(lugar);
    	b.buscar();
    }
    
    public void removeSession(Session session) {
        sessions.remove(session);
    }

    private void sendToAllConnectedSessions(String message) {
    	for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, String message) {
    	try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(CkanSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
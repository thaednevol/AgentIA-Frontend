package co.edu.udistrital.ingesoft;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ApplicationScoped;
import javax.websocket.Session;

//import eu.trentorise.opendata.jackan.CkanClient;


@ApplicationScoped
public class SearchSessionHandler {
	private final Set<Session> sessions = new HashSet<>();
    
    public void addSession(Session session) {
        sessions.add(session);
    }
    
    public void removeSession(Session session) {
        sessions.remove(session);
    }


    private void sendToSession(Session session, String message) {
    	try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(SearchSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	public void buscar(String busqueda, String lugar) {
		System.out.println("Buscar");
//		CkanClient cc = new CkanClient(lugar);
//    	CkanQuery query = CkanQuery.filter().byText(busqueda);
//        List<CkanDataset> filteredDatasets = cc.searchDatasets(query, 100, 0).getResults();
//
//        System.out.println("CKAN DATASETS: "+filteredDatasets.size());
//        
//        for (CkanDataset d : filteredDatasets) {
//        	Gson gson = new Gson();
//            
//            	if (d.getResources().size()>0){
//            		sendToAllConnectedSessions(gson.toJson(d));
//            	}
//        }    	
	}
	
	private void sendToAllConnectedSessions(String message) {
    	for (Session session : sessions) {
            sendToSession(session, message);
        }
    }
}
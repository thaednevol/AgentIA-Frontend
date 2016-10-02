package co.edu.udistrital.ingesoft;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.Session;

import com.google.gson.Gson;

import eu.trentorise.opendata.jackan.CkanClient;
import eu.trentorise.opendata.jackan.CkanQuery;
import eu.trentorise.opendata.jackan.model.CkanDataset;

public class Buscador {
	
	private String busqueda;
	private String lugar;
	
	private Set<Session> sessions = new HashSet<>();
	
	public Buscador(Set<Session> sessions2) {
		this.sessions=sessions2;
	}

	public void buscar(){
    	CkanClient cc = new CkanClient(lugar);
    	CkanQuery query = CkanQuery.filter().byText(busqueda);
        List<CkanDataset> filteredDatasets = cc.searchDatasets(query, 100, 0).getResults();

        System.out.println("CKAN DATASETS: "+filteredDatasets.size());
        
        for (CkanDataset d : filteredDatasets) {
        
        	Gson gson = new Gson();
            
            	if (d.getResources().size()>0){
            		sendToAllConnectedSessions(gson.toJson(d));
            	}
    		
        }
	}
	
	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Set<Session> getSessions() {
		return sessions;
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
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

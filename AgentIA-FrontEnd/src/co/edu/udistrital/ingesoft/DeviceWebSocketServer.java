//http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/HomeWebsocket/WebsocketHome.html

package co.edu.udistrital.ingesoft;

import java.io.File;
import java.io.IOException;
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

import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import bbejeck.nio.util.*;

@ApplicationScoped    
@ServerEndpoint("/actions")
public class DeviceWebSocketServer {
	
	@Inject
    private DeviceSessionHandler sessionHandler = new DeviceSessionHandler();

	@OnOpen
    public void open(Session session) {
		System.out.println("open");
    	sessionHandler.addSession(session);
    }
    
    @OnMessage
    public void handleMessage(String message, Session session) {
    	
    	Proceso p = new Proceso();
    	p.setMensaje(message);
    	p.run();
    	
    	
//        try (JsonReader reader = Json.createReader(new StringReader(message))) {
//            JsonObject jsonMessage = reader.readObject();
//            
//            System.out.println("JSONMESSAGE "+jsonMessage);
//
//            if ("add".equals(jsonMessage.getString("action"))) {
//                Device device = new Device();
//                device.setName(jsonMessage.getString("name"));
//                device.setDescription(jsonMessage.getString("description"));
//                device.setType(jsonMessage.getString("type"));
//                device.setStatus("Off");
//                sessionHandler.addDevice(device);
//            }
//
//            if ("remove".equals(jsonMessage.getString("action"))) {
//                int id = (int) jsonMessage.getInt("id");
//                sessionHandler.removeDevice(id);
//            }
//
//            if ("toggle".equals(jsonMessage.getString("action"))) {
//                int id = (int) jsonMessage.getInt("id");
//                sessionHandler.toggleDevice(id);
//            }
//        }
    }
    
    @OnClose
    public void close(Session session) {
    	System.out.println("close");
    	sessionHandler.removeSession(session);
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(DeviceWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
    }

    class Proceso extends Thread{
    	
    	String mensaje;
    	
    	public void run()
    	{
    		String busqueda="";
        	String carpeta="";
        	
        	try (JsonReader reader = Json.createReader(new StringReader(mensaje))) {
                JsonObject jsonMessage = reader.readObject();
              
                System.out.println("JSONMESSAGE 2 "+jsonMessage);
              if ("buscar".equals(jsonMessage.getString("action"))) {
            	  
            	  JsonObject jo_datos=jsonMessage.getJsonObject("datos");
            	  
            	  busqueda=jo_datos.getString("abuscar");
            	  carpeta=jo_datos.getString("metodo");
              }
            }
        	catch(Exception e){
        		e.toString();
        	}
        	
        	
        	System.out.println("LLAMA HANDLEMESSAGE: "+busqueda+" "+carpeta);
        	
        	Path path = Paths.get(carpeta);

        	
        	
        	try (DirectoryStream<Path> directoryStream = DirUtils.glob(path,busqueda)){
        		for(Path p : directoryStream){
        			File f = p.toFile();
        			Device device = new Device();
        			
        			device.setName(f.getName());
        			device.setDescription(f.getAbsolutePath());
                    device.setType(f.length()+"");
                    device.setStatus("Off");
                    sessionHandler.addDevice(device);
          			System.out.println(f.getAbsolutePath());
        		}
    		} catch (IOException e) {
    			System.out.println("HANDLEMESSAGE "+e.toString());
    			e.printStackTrace();
    		}
        	
        	System.out.println("SALE HANDLEMESSAGE");
    	}
    	
    	public void setMensaje(String msj)
    	{
    		this.mensaje = msj;
    	}

    }
	
}    

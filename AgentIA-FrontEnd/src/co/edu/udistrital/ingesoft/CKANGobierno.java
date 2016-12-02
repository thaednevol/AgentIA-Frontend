package co.edu.udistrital.ingesoft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

import ws.WsConsultaProxy;
import ws.WsConsulta_ServiceLocator;

/**
 *
 * @author Normalito
 */
public class CKANGobierno {
    
    private final String USER_AGENT = "Mozilla/5.0";
    private final String URL = "http://104.236.16.165:8000/search?keyword=";
    
    public String queryOnRepository(String patternToSeach) throws Exception{
    	WsConsultaProxy wsProxy = new WsConsultaProxy();
    	
    	String test = wsProxy.patterToSearch(patternToSeach);
    	
    	System.out.println("Prueba esde CKAN Gobierno");
    	System.out.println(test);
    	
    	return test;
    	
//    	o=wsLocator.
//    	
//    	
//        String url = URL+patternToSeach;
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        con.setRequestMethod("GET");
//
//        //add request header
//        con.setRequestProperty("User-Agent", USER_AGENT);
//
//        int responseCode = con.getResponseCode();
//        if (responseCode != 400){
//            System.out.println("\nSending 'GET' request to URL : " + url);
//            System.out.println("Response Code : " + responseCode);              
//
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//            }
//            in.close();
//            JSONObject objetoJSON =new JSONObject(response.toString());
//            System.out.println(objetoJSON.toString());
//            return String.valueOf(objetoJSON);
//        }else{
//            return null;
//        }
    }
    
    public static void main(String[] args) {
        CKANGobierno ckanq= new CKANGobierno();
        try {
            System.out.println(ckanq.queryOnRepository("Colombia"));
        } catch (Exception ex) {
            Logger.getLogger(CKANGobierno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
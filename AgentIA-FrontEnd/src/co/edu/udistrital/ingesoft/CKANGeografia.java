package co.edu.udistrital.ingesoft;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

import com.github.jsonldjava.core.JsonLdError;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import com.google.gson.JsonObject;

import ws.WsConsultaProxy;
import ws.WsConsulta_ServiceLocator;

/**
 *
 * @author Normalito
 */
public class CKANGeografia {
	
	  private static final String RDF_TYPE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
	  
	  private int maxRank;
    
    public String queryOnRepository(String patternToSeach) throws Exception{
//    	System.out.println("Llama queryOnRepository()");
//    	WsConsultaProxy wsProxy = new WsConsultaProxy();
//    	
//    	String test = wsProxy.patterToSearch(patternToSeach);
//    	
//    	System.out.println("Prueba desde CKAN Gobierno");
//    	System.out.println(test);
//    
    	InputStream inputStream = new FileInputStream("/tmp/json-ld.txt");
    	
    	parse(inputStream);
    	
    	Object jsonObject = JsonUtils.fromInputStream(inputStream);
    	
    	// Create a context JSON map containing prefixes and definitions
    	Map context = new HashMap();
    	// Customise context...
    	// Create an instance of JsonLdOptions with the standard JSON-LD options
    	JsonLdOptions options = new JsonLdOptions();
    	// Customise options...
    	// Call whichever JSONLD function you want! (e.g. compact)
    	Object compact = JsonLdProcessor.compact(jsonObject, context, options);
    	// Print out the result (or don't, it's your call!)
    	//System.out.println(JsonUtils.toPrettyString(compact));

    	System.out.println("RESPONSITA: "+jsonObject);
    	
    	return JsonUtils.toPrettyString(compact);
    	
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
    
    
    public void parse(InputStream inputStream) throws IOException, JsonLdError {
    		Object frame = JsonUtils.fromInputStream(inputStream);
    		
    		if (frame instanceof Map) {
    			Map<String, Object> a = (Map<String, Object>) frame;
    			
    			for (Entry<String, Object> entry : a.entrySet()) {
        		    String key = entry.getKey();
        		    Object x=entry.getValue();
        		    
        		    System.out.println("PASO");	
        		    
        		    System.out.println(key+" "+x);
        		    
        		    ArrayList<?> jo = (ArrayList<?>) x;
        		    
        		    for (Object l : jo){
        		    	Map<String, Object> k = (Map<String, Object>) l;
        		    	for (Entry<String, Object> s: k.entrySet()) {
        		    		if (s.getKey().contentEquals("http://www.geonames.org/ontology#name")){
        		    			System.out.println("ASI SI!!! "+s.getValue());
        		    		}
        		    		
        		    		
        		    	}
        		    	
        		    }
        		    
        		    //System.out.println(jo.);
        		}
    			
    		}
    		
    		
    		
    		Set<String> a = findMappedPredicates(frame);
    		
    		for(String i : a){
        		System.out.println("FRAME: "+i);
    		}

    		

    	  JsonLdOptions options = new JsonLdOptions();
//    	  options.setExplicit(false);
//    	  options.setEmbed(true);
//    	  options.setExpandContext(frame);
    	  try {
    	    Object expanded = JsonLdProcessor.expand(frame, options);
    	    
    	    //Object flattened = JsonLdProcessor.flatten(frame, options);
//
//    	    TreeSet<String> mappedTypes = new TreeSet<String>();
//    	    for (Map<String, Object> flatObj : (List<Map<String, Object>>) flattened) {
//    	      if (flatObj.containsKey("@type")) {
//    	        String type = ((List) flatObj.get("@type")).get(0).toString();
//    	        mappedTypes.add(type);
//    	      }
//    	    }
//
//    	    Set<String> mappedPredicates = findMappedPredicates(flattened);
//    	    mappedPredicates.remove("@id");
//    	    mappedPredicates.remove("@type");
//    	    mappedPredicates.add(RDF_TYPE);
//
    	    TreeMap<Integer, Set<String>> pivotsByRank = new TreeMap<Integer, Set<String>>();
    	    findPivots(expanded, pivotsByRank);

    	    System.out.println("FIND PIVOTS");
    	    
    	    for (Map.Entry<Integer, Set<String>> entry : pivotsByRank.entrySet()) {
    	      for (String pred : entry.getValue()) {
    	    	  System.out.println(pred);
    	        //mappedPredicates.add("!" + pred);
    	      }
    	    }
    	  } catch (JsonLdError e) {
    	    e.printStackTrace();
    	  }
    	}
    
    
    
    private Set<String> findMappedPredicates(Object node) {
        Set<String> mappedPredicates = new TreeSet<String>();
        if (node instanceof Map) {
          Map<String, Object> map = (Map<String, Object>) node;
          mappedPredicates.addAll(map.keySet());
          for (Object value : map.values()) {
            mappedPredicates.addAll(findMappedPredicates(value));
          }
        } else if (node instanceof List) {
          for (Object element : (List<Object>) node) {
            mappedPredicates.addAll(findMappedPredicates(element));
          }
        }
        return mappedPredicates;
      }
    
    private int findPivots(Object node, Map<Integer, Set<String>> pivotsByRank) {
        if (node instanceof Map) {
          int maxRank = -1;
          if (!((Map<String, Object>) node).isEmpty()) {
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) node).entrySet()) {
              int subRank = findPivots(entry.getValue(), pivotsByRank);
              if (subRank >= 0) {
                Set<String> pivots;
                if (pivotsByRank.containsKey(subRank)) {
                  pivots = pivotsByRank.get(subRank);
                } else {
                  pivots = new TreeSet<String>();
                  pivotsByRank.put(subRank, pivots);
                }
                pivots.add(entry.getKey());
              }
              maxRank = Math.max(maxRank, subRank);
              this.maxRank = Math.max(maxRank, this.maxRank);
            }
            maxRank++;
          }
          return maxRank;
        } else if (node instanceof List) {
          int maxRank = -1;
          for (Object element : (List<Object>) node) {
            int subRank = findPivots(element, pivotsByRank);
            maxRank = Math.max(maxRank, subRank);
            this.maxRank = Math.max(maxRank, this.maxRank);
          }
          return maxRank;
        } else {
          return -1;
        }
      }
    
    public static void main(String[] args) {
        CKANGeografia ckanq= new CKANGeografia();
        try {
            System.out.println(ckanq.queryOnRepository("Colombia"));
        } catch (Exception ex) {
            Logger.getLogger(CKANGeografia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
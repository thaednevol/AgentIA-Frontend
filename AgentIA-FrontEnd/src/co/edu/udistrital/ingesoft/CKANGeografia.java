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
@SuppressWarnings("unchecked")
public class CKANGeografia {
	
	  private static final String RDF_TYPE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";  
	  private int maxRank;
    
    public String queryOnRepository(String patternToSeach) throws Exception{
    	WsConsultaProxy wsProxy = new WsConsultaProxy();
    	return wsProxy.patterToSearch(patternToSeach);
    }
    
    
    public static void parse(InputStream inputStream) throws IOException, JsonLdError {
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
        		    		System.out.println(s.getKey()+" "+s.getValue());
        		    		
        		    		if (s.getKey().contentEquals("id")){
        		    			System.out.println("ASI SI!!! "+s.getValue());
        		    		}
        		    		
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
    
    
    
    private static Set<String> findMappedPredicates(Object node) {
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
    
    private static int findPivots(Object node, Map<Integer, Set<String>> pivotsByRank) {
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
              //this.maxRank = Math.max(maxRank, this.maxRank);
            }
            maxRank++;
          }
          return maxRank;
        } else if (node instanceof List) {
          int maxRank = -1;
          for (Object element : (List<Object>) node) {
            int subRank = findPivots(element, pivotsByRank);
            maxRank = Math.max(maxRank, subRank);
            //this.maxRank = Math.max(maxRank, this.maxRank);
          }
          return maxRank;
        } else {
          return -1;
        }
      }
    
    public static void main(String[] args) {
        CKANGeografia ckanq= new CKANGeografia();
        try {
        	InputStream inputStream = new FileInputStream("/tmp/json-ld.txt");
        	
        	parse(inputStream);
        	
        } catch (Exception ex) {
            Logger.getLogger(CKANGeografia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
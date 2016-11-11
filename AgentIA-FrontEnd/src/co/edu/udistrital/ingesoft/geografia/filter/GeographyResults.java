/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.ingesoft.geografia.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.udistrital.ingesoft.geografia.structure.GeographyDataset;
import co.edu.udistrital.ingesoft.geografia.structure.Resource;

/**
 *
 * @author Normalito
 */
public class GeographyResults {
    public static JSONObject getResults(List<GeographyDataset> listGeographyDataset){
        List<Resource> listResources = new ArrayList<>();
        for(GeographyDataset geo:listGeographyDataset){
            for (Resource res:geo.getResources()) {
                listResources.add(res);
            }
        }
        return javaListResourcesToJSON(listResources);
    }
    
    private static JSONObject javaListResourcesToJSON(List<Resource> listResources){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("resource", listResources);
            return jsonObject;
        } catch (JSONException ex) {
            Logger.getLogger(GeographyResults.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

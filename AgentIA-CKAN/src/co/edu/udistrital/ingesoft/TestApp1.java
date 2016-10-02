package co.edu.udistrital.ingesoft;

import eu.trentorise.opendata.jackan.CkanClient;
import eu.trentorise.opendata.jackan.CkanQuery;
import eu.trentorise.opendata.jackan.model.CkanDataset;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class TestApp1 {

    public static void main(String[] args) {

       // CkanClient cc = new CkanClient("http://demo.ckan.org/api/3/action/resource_search?query=name:cat");
       // System.out.println(cc.getDatasetList());
    	
    	CkanClient cc = new CkanClient("http://demo.ckan.org");
    	CkanQuery query = CkanQuery.filter().byText("cats");//.byTagNames("restaurants");
        List<CkanDataset> filteredDatasets = cc.searchDatasets(query, 100, 0).getResults();

        System.out.println("CKAN DATASETS: "+filteredDatasets.size());
        
        for (CkanDataset d : filteredDatasets) {
        
        	Gson gson = new Gson();
            
            try {
            	if (d.getResources().size()>0){
            		gson.toJson(d, new FileWriter("/tmp/test"+d.getId()+".json"));
            	}
    			System.out.println(gson.toJson(d));
    		} catch (JsonIOException | IOException e) {
    			e.printStackTrace();
    		}
        	
        	
        	System.out.println("DATASET: "+d.getId());
            System.out.println("DATASET: " + d.getName());
            System.out.println("DATASET: " + d.getNumResources());
            
        }

    }
}
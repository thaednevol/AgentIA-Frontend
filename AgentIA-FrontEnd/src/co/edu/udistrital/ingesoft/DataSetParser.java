package co.edu.udistrital.ingesoft;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import eu.trentorise.opendata.jackan.model.CkanDataset;
import eu.trentorise.opendata.jackan.model.CkanDatasetBase;

public class DataSetParser implements DataSetVisitadorInterface{

	private CkanDataset dataset;
	
	public void visit(MyCKANDataset ds) {
		this.dataset=ds;
	}
	
	public void visit(CkanJulian ds) {
		CkanDataset cks = new CkanDataset();
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject) jsonParser.parse(ds.getJsonObject());
		
		cks.setTitle(jo.get("title").getAsString());
		this.dataset=cks;
		
	}
	

	public CkanDataset getDataset() {
		return dataset;
	}
	
}

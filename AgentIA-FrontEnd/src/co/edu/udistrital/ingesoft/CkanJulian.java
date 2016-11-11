package co.edu.udistrital.ingesoft;

import eu.trentorise.opendata.jackan.model.CkanDataset;

public class CkanJulian extends CkanDataset implements DataSetInterface{

	private String jsonObject;
	
	
	@Override
	public void accept(DataSetParser d) {
		d.visit(this);
	}


	public String getJsonObject() {
		return jsonObject;
	}


	public void setJsonObject(String jsonObject) {
		this.jsonObject = jsonObject;
	}
	
}

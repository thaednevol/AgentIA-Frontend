package co.edu.udistrital.ingesoft;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import eu.trentorise.opendata.jackan.model.CkanDataset;

public class RestToCkan {

	public static List<CkanDataset> parse(String a) {
		
		List<CkanDataset> listaCkan = new ArrayList<>();
		
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(a);
		JsonObject jo_Result = jsonObject.getAsJsonObject("result");
		JsonArray ja_Results = jo_Result.getAsJsonArray("results");
		
		DataSetParser dsp = new DataSetParser();
		
		for (int i=0; i<ja_Results.size(); i++){
			CkanJulian ckanJulian = new CkanJulian();
			
			ckanJulian.setJsonObject(ja_Results.get(i).toString());
			
			dsp.visit(ckanJulian);
			
			listaCkan.add((CkanDataset) dsp.getDataset());
		}
		return listaCkan;
	}

}

package co.edu.udistrital.ingesoft;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.utils.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import eu.trentorise.opendata.jackan.model.CkanDataset;

@SuppressWarnings("unchecked")
public class RestToCkan {

	public static List<CkanDataset> parse(String a) {

		List<CkanDataset> listaCkan = new ArrayList<>();

		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(a);
		JsonObject jo_Result = jsonObject.getAsJsonObject("result");
		JsonArray ja_Results = jo_Result.getAsJsonArray("results");

		DataSetParser dsp = new DataSetParser();

		for (int i = 0; i < ja_Results.size(); i++) {
			CkanJulian ckanJulian = new CkanJulian();

			ckanJulian.setJsonObject(ja_Results.get(i).toString());

			dsp.visit(ckanJulian);

			listaCkan.add((CkanDataset) dsp.getDataset());
		}
		return listaCkan;
	}

	public static List<CkanDataset> parseEducacion(String a) {

		System.out.println("Respuesta de Cristian " + a);

		return null;
	}

	public static List<CkanDataset> parseGeografia(String jsonString) throws JsonParseException, IOException {
		List<CkanDataset> resultList = new ArrayList<>();
		Object frame = JsonUtils.fromString(jsonString);

		if (frame instanceof Map) {
			
			Map<String, Object> a = (Map<String, Object>) frame;

			for (Entry<String, Object> entry : a.entrySet()) {
				String key = entry.getKey();
				Object x = entry.getValue();

				System.out.println("PASO");

				System.out.println(key + " " + x);

				ArrayList<?> jo = (ArrayList<?>) x;

				for (Object l : jo) {
					Map<String, Object> k = (Map<String, Object>) l;
					
					CkanDataset c = new CkanDataset();
					
					for (Entry<String, Object> s : k.entrySet()) {
						if (s.getKey().contentEquals("id")) {
							c.setId((String) s.getValue());
						}
						if (s.getKey().contentEquals("http://www.geonames.org/ontology#name")) {
							c.setTitle((String) s.getValue());
						}
						
					}
					resultList.add(c);
				}
			}

		}

		return resultList;
	}

}

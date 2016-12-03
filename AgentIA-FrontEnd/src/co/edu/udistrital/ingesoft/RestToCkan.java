package co.edu.udistrital.ingesoft;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import eu.trentorise.opendata.jackan.model.CkanDataset;
import eu.trentorise.opendata.jackan.model.CkanOrganization;
import eu.trentorise.opendata.jackan.model.CkanPair;
import eu.trentorise.opendata.jackan.model.CkanResource;
import org.apache.commons.codec.binary.Base64;

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

	public static List<CkanDataset> parseEducacion(String jsonString) {
		List<CkanDataset> resultList = new ArrayList<>();
		JsonParser parser = new JsonParser();
		JsonObject jo = parser.parse(jsonString).getAsJsonObject();
		
		JsonArray ja = jo.getAsJsonArray("data");
		
		for (int i=0; i<ja.size(); i++){
			
			System.out.println("jasize"+ja.get(i));
			CkanDataset ckanDataset = new CkanDataset();
			JsonArray res = ja.get(i).getAsJsonArray();
			ckanDataset.setTitle(res.get(0).getAsString());
			ckanDataset.setUrl(res.get(1).getAsString());
			
			String id = res.get(1).getAsString();
			byte[] encodedBytes = Base64.encodeBase64(id.getBytes());
			ckanDataset.setId(new String(encodedBytes));
			
			resultList.add(ckanDataset);
		}
		return resultList;
	}

	public static List<CkanDataset> parseGeografia(String jsonString) throws JsonParseException, IOException {
		List<CkanDataset> resultList = new ArrayList<>();
		Object frame = JsonUtils.fromString(jsonString);

		if (frame instanceof Map) {
			
			Map<String, Object> graph = (Map<String, Object>) frame;

			for (Entry<String, Object> entry : graph.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();

				System.out.println("PASO");

				System.out.println(key + " " + value);

				ArrayList<?> joResultado = (ArrayList<?>) value;

				for (Object item : joResultado) {
					Map<String, Object> mItem = (Map<String, Object>) item;
					
					CkanDataset ckanDataset = new CkanDataset();
					CkanOrganization ckanOrganization = new CkanOrganization();
					List<CkanPair> extras = new ArrayList<CkanPair>();
					Position position = new Position();
					List<CkanResource> ckanResources = new ArrayList<CkanResource>();
					CkanResource ckanResource = new CkanResource();
					
					for (Entry<String, Object> nodo : mItem.entrySet()) {
						
						if (nodo.getKey().contentEquals("id")) {
							String id = (String) nodo.getValue();
							byte[] encodedBytes = Base64.encodeBase64(id.getBytes());
							ckanDataset.setId(new String(encodedBytes));
							ckanDataset.setName(id);
						}
						if (nodo.getKey().contentEquals("http://www.geonames.org/ontology#name")) {
							ckanDataset.setTitle((String) nodo.getValue());
						}
						if (nodo.getKey().contentEquals("type")) {
							ckanDataset.setType((String) nodo.getValue());
						}
						if (nodo.getKey().contentEquals("http://www.geonames.org/ontology#population")) {
							String str = (String)nodo.getValue();
							str = str.replaceAll("[^0-9.]", "");
							
							ckanOrganization.setNumFollowers(Integer.parseInt(str));
						}
						if (nodo.getKey().contentEquals("http://www.geonames.org/ontology#postalCode")) {
							String str = (String)nodo.getValue();
							str = str.replaceAll("[^0-9.]", "");
							ckanOrganization.setPackageCount(Integer.parseInt(str));
						}
						if (nodo.getKey().contentEquals("http://www.geonames.org/ontology#wikipediaArticle")) {
							Map<String, Object> wikipediaArticle = (Map<String, Object>) nodo.getValue();
							ckanOrganization.setDescription((String) wikipediaArticle.get("id"));
						}
						if (nodo.getKey().contentEquals("http://www.geonames.org/ontology#locationMap")) {
							Map<String, Object> locationMap = (Map<String, Object>) nodo.getValue();
							ckanOrganization.setImageUrl((String) locationMap.get("id"));
						}
						if (nodo.getKey().contentEquals("http://www.geonames.org/ontology#officialName")) {
							String str = "";
							if (nodo.getValue() instanceof ArrayList){
								List<Map<String, Object>> officialName = (ArrayList<Map<String, Object>>) nodo.getValue();
								
								for (Map<String, Object> off : officialName){
									for (Entry<String, Object> a : off.entrySet()){
										if (((String)a.getKey()).contains("es")){
											str=(String)a.getValue();
										}
									}
								}
								if (str.contentEquals("")){
									for (Map<String, Object> off : officialName){
										for (Entry<String, Object> a : off.entrySet()){
											if (((String)a.getKey()).contains("en")){
												str=(String)a.getValue();
											}
										}
									}
								}
							}
							if (nodo.getValue() instanceof LinkedHashMap){
								Map<String, Object> officialName = (Map<String, Object>) nodo.getValue();
								for (Entry<String, Object> a : officialName.entrySet()){
									if (((String)a.getKey()).contains("es")){
										str=(String)a.getValue();
									}
								}
								if (str.contentEquals("")){
									for (Entry<String, Object> a : officialName.entrySet()){
										if (((String)a.getKey()).contains("en")){
											str=(String)a.getValue();
										}
									}
								}
							}
							
							ckanOrganization.setName(str);
							
						}
						if (nodo.getKey().contentEquals("http://www.geonames.org/ontology#alternateName")) {
							List<Map<String, Object>> alternateName = (ArrayList<Map<String, Object>>)nodo.getValue();
							
							String str = "";
							String temp = "";
							
							for (Map<String, Object> a : alternateName){
								if (a.get("@language").toString().contentEquals("es")){
									str=a.get("@value").toString();
								}
								if (a.get("@language").toString().contentEquals("en")){
									temp=a.get("@value").toString();
								}
							}
							if (str.contentEquals("")){
								CkanPair ckanPair = new CkanPair("alternateName", temp);
								extras.add(ckanPair);
							}
							else {
								CkanPair ckanPair = new CkanPair("alternateName", str);
								extras.add(ckanPair);
							}
							
							
						}
						if (nodo.getKey().contentEquals("http://www.geonames.org/ontology#countryCode")) {
							CkanPair ckanPair = new CkanPair("countryCode", (String) nodo.getValue());
							extras.add(ckanPair);
						}
						if (nodo.getKey().contentEquals("http://www.geonames.org/ontology#featureClass")) {
							Map<String, Object> featureClass = (Map<String, Object>) nodo.getValue();
							CkanPair ckanPair = new CkanPair("featureClass", (String) featureClass.get("id"));
							extras.add(ckanPair);
						}
						
						if (nodo.getKey().contains("seeAlso")) {
							Map<String, Object> rdfs = (Map<String, Object>) nodo.getValue();
							ckanDataset.setNotes((String) rdfs.get("id"));
						}
						
						if (nodo.getKey().contains("http://www.w3.org/2003/01/geo/wgs84_pos#lat")) {
							position.setLatitud((String) nodo.getValue());
						}
						
						if (nodo.getKey().contains("http://www.w3.org/2003/01/geo/wgs84_pos#alt")) {
							position.setAltitud((String) nodo.getValue());
						}
						
						if (nodo.getKey().contains("http://www.w3.org/2003/01/geo/wgs84_pos#long")) {
							position.setLongitud((String) nodo.getValue());
						}
						
						ckanDataset.setExtras(extras);
						ckanDataset.setOrganization(ckanOrganization);
						
					}
					Gson gson = new Gson();
					ckanResource.setDescription(gson.toJson(position));
					String id = ckanDataset.toString().split("@")[1];
					ckanDataset.setCreatorUserId(id);
					
					ckanResources.add(ckanResource);
					
					ckanDataset.setResources(ckanResources);
					
					
					resultList.add(ckanDataset);
				}
			}

		}

		return resultList;
	}
	
	public static class Position {
		private String latitud;
		private String longitud;
		private String altitud;
		
		public String getLatitud() {
			return latitud;
		}
		public void setLatitud(String latitud) {
			this.latitud = latitud;
		}
		public String getLongitud() {
			return longitud;
		}
		public void setLongitud(String longitud) {
			this.longitud = longitud;
		}
		public String getAltitud() {
			return altitud;
		}
		public void setAltitud(String altitud) {
			this.altitud = altitud;
		}
		
		
	}

}
	
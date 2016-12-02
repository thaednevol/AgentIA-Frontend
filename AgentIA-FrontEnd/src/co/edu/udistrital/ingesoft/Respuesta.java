package co.edu.udistrital.ingesoft;

import java.util.ArrayList;

import eu.trentorise.opendata.jackan.model.CkanDataset;

public class Respuesta {
	
	private ArrayList<CkanDataset> resultados = new ArrayList<CkanDataset>();
	private String error; 


	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public ArrayList<CkanDataset> getResultados() {
		return resultados;
	}

	public void setResultados(ArrayList<CkanDataset> resultados) {
		this.resultados = resultados;
	}

}

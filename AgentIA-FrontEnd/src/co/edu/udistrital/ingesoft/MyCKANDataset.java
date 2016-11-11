package co.edu.udistrital.ingesoft;

import eu.trentorise.opendata.jackan.model.CkanDataset;

public class MyCKANDataset extends CkanDataset implements DataSetInterface{

	@Override
	public void accept(DataSetParser d) {
		d.visit(this);
	}
}

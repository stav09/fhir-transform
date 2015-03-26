package cti.fhir;

import java.util.List;

import cti.fhir.model.DataElement;


public class Main {

	public static void main(String ... args) throws Exception {
		
		List<DataElement> elements = 
				CsvFile.process("~/Downloads/Haematology.csv", Transform::toDataElement);
		
		
	}

}

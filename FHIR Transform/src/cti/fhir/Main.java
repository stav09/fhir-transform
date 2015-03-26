package cti.fhir;

import java.util.List;

import cti.fhir.model.Bundle;
import cti.fhir.model.DataElement;


public class Main {

	public static final String INPUT_FILE_PATH = "~/Downloads/Haematology.csv";
	public static final String OUTPUT_FILE_PATH = "Haematology.json";
	
	public static void main(String ... args) throws Exception {
		
		List<DataElement> elements = 
				CsvFile.process(INPUT_FILE_PATH, Transform::toDataElement);
		
		Bundle bundle = new Bundle();
		bundle.addEntries(elements);
		
		JsonFile.write(OUTPUT_FILE_PATH, bundle);
	}

}

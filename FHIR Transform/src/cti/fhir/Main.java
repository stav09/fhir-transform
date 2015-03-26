package cti.fhir;

import java.util.List;

import cti.fhir.model.Bundle;
import cti.fhir.model.DataElement;


public class Main {

	public static void main(String ... args) throws Exception {
		
		List<DataElement> elements = 
				CsvFile.process(args[0], args[1], args[2]);
		
		Bundle bundle = new Bundle();
		bundle.addEntries(elements);
		
		JsonFile.write(args[3], bundle);
	}

}

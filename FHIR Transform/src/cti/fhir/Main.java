package cti.fhir;

import java.util.List;

import cti.fhir.model.Element;

public class Main {

	public static void main(String ... args) throws Exception {
		List<Element> elements =
				CsvFile.process("~/Downloads/Haematology.csv", Transform::toElement);
	}

}

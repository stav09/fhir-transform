package cti.fhir;

public class Main {

	public static void main(String ... args) throws Exception {
		CsvFile.toJson("~/Downloads/Haematology.csv", Transform::toFhirJson);
	}

}

package cti.fhir;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import cti.fhir.model.DataElement;

public class CsvFile {

	public static String LOINC_CODE = "LOINC";
	public static String PREFERRED_NAME = "Preferred Name";
	public static String SYNONYM = "Synonym";
	public static String UCUM_CODE = "UCUM";
	public static String COMMENTS = "Comments";
	
	public static List<DataElement> process(String filePath, String version, String date) throws IOException {
		Reader in = new FileReader(filePath);
		File file = new File(filePath);
		Iterable<CSVRecord> records = CSVFormat.TDF.parse(in);
		ArrayList<DataElement> rows = new ArrayList<>();
		for (CSVRecord record : records) {
			rows.add(Transform.toDataElement(file.getName(), version, date, record.toMap()));
		}
		return rows;
	}
	
}

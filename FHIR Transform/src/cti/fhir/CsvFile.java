package cti.fhir;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CsvFile {

	public static <T> List<T> process(String filePath, Function<Map<String,String>, T> processor) throws IOException {
		Reader in = new FileReader(filePath);
		Iterable<CSVRecord> records = CSVFormat.TDF.parse(in);
		ArrayList<T> rows = new ArrayList<T>();
		for (CSVRecord record : records) {
			rows.add(processor.apply(record.toMap()));
		}
		return rows;
	}
	
}

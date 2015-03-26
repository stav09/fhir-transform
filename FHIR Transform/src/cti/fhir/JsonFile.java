package cti.fhir;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class JsonFile {

	public static void write(String filePath, Object obj) throws IOException {
		
		String json = new Gson().toJson(obj);
			
		try (FileWriter writer = new FileWriter(filePath)) {
			 writer.write(json);
		}
	}
	
}

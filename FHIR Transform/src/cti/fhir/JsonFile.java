package cti.fhir;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonFile {

	public static void write(String filePath, Object obj) throws IOException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(obj);
			
		try (FileWriter writer = new FileWriter(filePath)) {
			 writer.write(json);
		}
	}
	
}

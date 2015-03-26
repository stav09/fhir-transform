package cti.fhir;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Mojo(name = "template")
public class TemplateMojo extends AbstractMojo {

	private static Logger logger = Logger.getLogger(TemplateMojo.class
			.getName());

	@Parameter(defaultValue = "src/main/resources/input_data_element_collections")
	String dataElementCollectionPath;

	@Parameter(defaultValue = "src/main/resources/input_value_sets")
	String valueSetPath;

	@Parameter(defaultValue = "src/main/resources/templates/dataElementBundle.template")
	String dataElementBundleTemplatePath;

	@Parameter(defaultValue = "src/main/resources/templates/dataElement.template")
	String dataElementTemplatePath;

	@Parameter(defaultValue = "src/main/resources/templates/valueSet.template")
	String valueSetTemplatePath;

	@Parameter(defaultValue = "src/main/resources/templates/valueSetCode.template")
	String valueSetCodeTemplatePath;

	@Parameter(defaultValue = "src/main/resources/templates/unitExtension.template")
	String unitExtensionTemplatePath;

	public void execute() throws MojoExecutionException, MojoFailureException {
		processFiles(dataElementCollectionPath, "dataElementCollection-",
				dataElementBundleTemplatePath, dataElementTemplatePath);
		processFiles(valueSetPath, "valueSet-", valueSetTemplatePath,
				valueSetCodeTemplatePath);
	}

	private void processFiles(String sheetPath, String filePrefix,
			String outerTemplatePath, String innerTemplatePath)
			throws MojoExecutionException {
		String outerTemplate = loadTemplate(outerTemplatePath);
		String innerTemplate = loadTemplate(innerTemplatePath);

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(
				Paths.get(sheetPath), "*.csv")) {
			for (Path entry : stream) {
				logger.info("Processing " + entry.toString());
				String elements = getEntries(entry, innerTemplate);

				String[] names = entry.getFileName().toString()
						.replace(".csv", "").split("_");
				String name = names[0];
				String version = names[1];
				String date = names[2];

				writeJson(
						entry,
						filePrefix,
						outerTemplate.replace("%%name%%", name)
								.replace("%%version%%", version)
								.replace("%%date%%", date)
								.replace("%%elements%%", elements));

			}
		} catch (DirectoryIteratorException | IOException ex) {
			throw new MojoExecutionException("Failed", ex);
		}
	}

	private void writeJson(Path entry, String filePrefix, String data)
			throws IOException {
		String targetFilename = entry.getFileName().toString()
				.replace(".csv", ".json");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(data);
		String prettyJsonString = gson.toJson(je);

		try (FileWriter writer = new FileWriter("target/classes/" + filePrefix
				+ targetFilename)) {
			writer.write(prettyJsonString);
		}
	}

	private String getEntries(Path entry, String template) throws IOException,
			MojoExecutionException {
		String baseFileName = entry.getFileName().toString()
				.replace(".csv", "");
		String[] names = baseFileName.split("_");
		String name = names[0];
		String version = names[1];
		String date = names[2];

		String unitExtensionTemplate = loadTemplate(unitExtensionTemplatePath);
		String localTemplate = template.replace("%%name%%", name)
				.replace("%%version%%", version).replace("%%date%%", date);

		StringBuffer entries = new StringBuffer();

		Iterable<CSVRecord> records;
		try (Reader in = new FileReader(entry.toFile())) {
			records = CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord(true)
					.parse(in);

			Iterator<CSVRecord> iterator = records.iterator();
			while (iterator.hasNext()) {
				CSVRecord record = iterator.next();
				String elementString = localTemplate;
				Map<String, String> map = record.toMap();
				for (String key : map.keySet()) {
					String value = map.get(key).trim();
					if (key.equals("UCUM") && !value.equals("")
							&& !value.equalsIgnoreCase("no unit")) {
						elementString = elementString.replace(
								"%%unitExtension%%", unitExtensionTemplate
										.replace("%%UCUM%%", value));
					} else {
						elementString = elementString.replace(
								"%%" + key + "%%", value);
					}
				}
				elementString = elementString.replace("%%unitExtension%%", "");
				elementString = elementString.replaceAll("[\"]comments[\"][:] [\"][\"],",
						"").replace("\"alias\": \"ESR\",", "");
				entries.append(elementString);
				if (iterator.hasNext()) {
					entries.append(",");
					entries.append(System.getProperty("line.separator"));
				}
			}
		}
		return entries.toString();
	}

	private String loadTemplate(String path) throws MojoExecutionException {
		try {
			logger.info("Loading value set template " + valueSetTemplatePath);
			return new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			throw new MojoExecutionException("Failed loading template "
					+ valueSetCodeTemplatePath + ": " + e.getMessage(), e);
		}
	}

}

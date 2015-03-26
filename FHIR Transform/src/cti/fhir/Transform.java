package cti.fhir;

import java.util.Map;

import cti.fhir.model.Coding;
import cti.fhir.model.DataElement;
import cti.fhir.model.Element;
import cti.fhir.model.Type;

public class Transform {
	
	private static final String RCPA_NAME = "RCPA";
	private static final String DATA_ELEMENT_TYPE_CODE = "Quantity";
	private static final String DATA_ELEMENT_PATH = "FIXME";
	private static final String ALLOWED_UNITS_EXTENSION_URL = "http://hl7.org/fhir/StructureDefinition/elementdefinition-allowedUnits";
	private static final String LOINC_SYSTEM_URL = "http://loinc.org";
	private static final String RCPA_URL = "http://rcpa.edu.au";
	private static final String COPYRIGHT = "FIXME";
	private static final String STATUS = "draft";

	public static DataElement toDataElement(String sheetName, String version, String date, Map<String, String> fields) {
		
		DataElement de = new DataElement();

		de.getContact().getTelecom().setSystem("url");
		de.getContact().getTelecom().setValue(RCPA_URL);;
		
		de.setVersion(version);
		de.setCopyright(COPYRIGHT);
		de.setDate(date);
		de.setName(fields.get(CsvFile.PREFERRED_NAME));
		de.setPublisher(RCPA_NAME);
		de.setStatus(STATUS);
		de.setUrl("http://hl7.org/au/pitus/" + sheetName + "-" + fields.get(CsvFile.LOINC_CODE));
		
		Element e = new Element();
		
		de.getElement().add(e);

		e.setAlias(fields.get(CsvFile.SYNONYM));
		e.setComments(fields.get(CsvFile.COMMENTS));
		
		Type t = new Type();
		t.setCode(DATA_ELEMENT_TYPE_CODE);
		e.getType().add(t);
		
		e.getExtension().setUrl(ALLOWED_UNITS_EXTENSION_URL);
		e.getExtension().getValueCodeableConcept().setText(fields.get(CsvFile.UCUM_CODE));
		
		e.setPath(DATA_ELEMENT_PATH);
		Coding c = new Coding();
		c.setCode(fields.get(CsvFile.LOINC_CODE));
		c.setSystem(LOINC_SYSTEM_URL);
		e.getCode().add(c);
		
		return de;
	}
}

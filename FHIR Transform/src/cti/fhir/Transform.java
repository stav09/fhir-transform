package cti.fhir;

import java.util.Map;

import cti.fhir.model.Coding;
import cti.fhir.model.DataElement;
import cti.fhir.model.Element;
import cti.fhir.model.Type;

public class Transform {
	
	private static final String DATA_ELEMENT_TYPE_CODE = "Quantity";
	private static final String DATA_ELEMENT_PATH = "FIXME";
	private static final String ALLOWED_UNITS_EXTENSION_URL = "http://hl7.org/fhir/StructureDefinition/elementdefinition-allowedUnits";
	private static final String LOINC_SYSTEM_URL = "http://loinc.org";

	public static DataElement toDataElement(Map<String, String> fields) {
		
		DataElement de = new DataElement();
		
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

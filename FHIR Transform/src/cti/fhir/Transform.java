package cti.fhir;

import java.util.Map;

import cti.fhir.model.Element;

public class Transform {
	
	public static Element toElement(Map<String, String> fields) {
		
		Element e = new Element();
		e.setAlias(fields.get("alias"));
		
		return e;
	}
}

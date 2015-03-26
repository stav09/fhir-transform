package cti.fhir.model;

public class Extension {
	
	private String url;
	private ValueCodeableConcept valueCodeableConcept;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ValueCodeableConcept getValueCodeableConcept() {
		return valueCodeableConcept;
	}

	public void setValueCodeableConcept(
			ValueCodeableConcept valueCodeableConcept) {
		this.valueCodeableConcept = valueCodeableConcept;
	}
}

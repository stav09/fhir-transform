package cti.fhir.model;

public class Contact {

	private Telecom telecom = new Telecom();

	public Telecom getTelecom() {
		return telecom;
	}

	public void setTelecom(Telecom telecom) {
		this.telecom = telecom;
	}

}

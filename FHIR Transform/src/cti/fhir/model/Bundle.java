package cti.fhir.model;

import java.util.ArrayList;
import java.util.List;

public class Bundle {

	private String resourceType = "Bundle";

	public String getResourceType() {
		return resourceType;
	}

	private String type = "collection";
	private List<Entry> entry = new ArrayList<>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Entry> getEntry() {
		return entry;
	}

	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}

	public void addEntries(List<DataElement> elements) {
		for (DataElement element : elements) {
			Entry e = new Entry();
			e.setResource(element);
			entry.add(e);
		}
	}
}

package cti.fhir.model;

public class Element {
	
	private Extension extension;
	private String path;
	private String alias;
	private String comments;
	private String type;

	public Extension getExtension() {
		return extension;
	}

	public void setExtension(Extension extension) {
		this.extension = extension;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
}

package cti.fhir.model;

import java.util.ArrayList;
import java.util.List;

public class Element {
	
	private Extension extension = new Extension();
	private String path;
	private String alias;
	private String comments;
	private List<Coding> code = new ArrayList<>();
	private List<Type> type = new ArrayList<>();

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

	public List<Type> getType() {
		return type;
	}

	public void setType(List<Type> type) {
		this.type = type;
	}

	public List<Coding> getCode() {
		return code;
	}

	public void setCode(List<Coding> code) {
		this.code = code;
	}	
}

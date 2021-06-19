package br.com.ourmind.sistemavendas.resources.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends ResponseError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<FieldError> fields = new ArrayList<FieldError>();
	
	
	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}


	public List<FieldError> getFields() {
		return fields;
	}


	public void setFields(List<FieldError> fields) {
		this.fields = fields;
	}



	public void addError(String field, String message) {
		this.fields.add(new FieldError(field, message));
	}
	

}

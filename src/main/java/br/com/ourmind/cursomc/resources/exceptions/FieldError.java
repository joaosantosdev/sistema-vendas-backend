package br.com.ourmind.cursomc.resources.exceptions;

import java.io.Serializable;

public class FieldError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String field;
	private String message;
	
	public FieldError() {}

	public FieldError(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

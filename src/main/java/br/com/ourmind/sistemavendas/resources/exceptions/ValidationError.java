package br.com.ourmind.sistemavendas.resources.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private List<FieldError> fields = new ArrayList();
	private Long timestamp;
	
	
	public ValidationError(Integer status, Long timestamp) {
		super();
		this.status = status;
		this.timestamp = timestamp;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<FieldError> getFields() {
		return this.fields;
	}
	public void setFields(List<FieldError> fields) {
		this.fields = fields;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	public void addError(String field, String message) {
		this.fields.add(new FieldError(field, message));
	}
	

}

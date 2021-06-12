package br.com.ourmind.cursomc.resources.exceptions;

import java.io.Serializable;

public class ResponseError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String message;
	private Long timestamp;
	
	
	public ResponseError(Integer status, String message, Long timestamp) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	

}

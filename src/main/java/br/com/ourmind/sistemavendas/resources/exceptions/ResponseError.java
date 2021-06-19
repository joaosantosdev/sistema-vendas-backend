package br.com.ourmind.sistemavendas.resources.exceptions;


import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

public class ResponseError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	public ResponseError(Long timestamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	static ResponseError error(HttpStatus status, Exception exception, HttpServletRequest request) {
		return new ResponseError(System.currentTimeMillis(), status.value(), status.name(),
				exception.getMessage(), request.getRequestURI());
	}
	
	static ValidationError errorValidation(HttpStatus status, Exception exception, HttpServletRequest request) {
		return new ValidationError(System.currentTimeMillis(), status.value(), status.name(),
				exception.getMessage(), request.getRequestURI());
	}

}

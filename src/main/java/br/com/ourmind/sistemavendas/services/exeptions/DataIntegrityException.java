package br.com.ourmind.sistemavendas.services.exeptions;

import java.io.Serializable;

public class DataIntegrityException extends RuntimeException implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String message) {
		super(message);
	}
	
	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
	}


}

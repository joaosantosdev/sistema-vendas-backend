package br.com.ourmind.sistemavendas.services.exeptions;

import java.io.Serializable;

public class NotFoundResourceException extends RuntimeException implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public NotFoundResourceException(String message) {
		super(message);
	}
	
	public NotFoundResourceException(String message, Throwable cause) {
		super(message, cause);
	}


}

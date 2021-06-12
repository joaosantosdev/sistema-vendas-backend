package br.com.ourmind.cursomc.services.exeptions;

import java.io.Serializable;

public class NotFoundResourceExeption extends RuntimeException implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public NotFoundResourceExeption(String message) {
		super(message);
	}
	
	public NotFoundResourceExeption(String message, Throwable cause) {
		super(message, cause);
	}


}

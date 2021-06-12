package br.com.ourmind.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ourmind.cursomc.services.exeptions.NotFoundResourceExeption;

@ControllerAdvice
public class ResourceExeptionHandler {
	
	@ExceptionHandler(NotFoundResourceExeption.class)
	public ResponseEntity<ResponseError> notFoundResourceExeption(NotFoundResourceExeption exception, HttpServletRequest request){
		
		ResponseError error = new ResponseError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}

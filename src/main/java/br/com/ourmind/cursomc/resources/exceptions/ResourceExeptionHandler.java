package br.com.ourmind.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ourmind.cursomc.services.exeptions.DataIntegrityException;
import br.com.ourmind.cursomc.services.exeptions.NotFoundResourceException;

@ControllerAdvice
public class ResourceExeptionHandler {
	
	@ExceptionHandler(NotFoundResourceException.class)
	public ResponseEntity<ResponseError> notFoundResourceExeption(NotFoundResourceException exception, HttpServletRequest request){
		ResponseError error = new ResponseError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ResponseError> dataIntegrityException(DataIntegrityException exception, HttpServletRequest request){
		ResponseError error = new ResponseError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> methodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		exception.getBindingResult().getFieldErrors().forEach(item->{
			error.addError(item.getField(), item.getDefaultMessage());
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}

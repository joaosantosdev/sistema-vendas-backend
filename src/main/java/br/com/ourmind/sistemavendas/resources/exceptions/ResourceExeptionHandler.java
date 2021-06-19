package br.com.ourmind.sistemavendas.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ourmind.sistemavendas.services.exeptions.AuthorizationException;
import br.com.ourmind.sistemavendas.services.exeptions.DataIntegrityException;
import br.com.ourmind.sistemavendas.services.exeptions.FileException;
import br.com.ourmind.sistemavendas.services.exeptions.NotFoundResourceException;

@ControllerAdvice
public class ResourceExeptionHandler {


	@ExceptionHandler(NotFoundResourceException.class)
	public ResponseEntity<ResponseError> notFoundResourceExeption(NotFoundResourceException exception,
			HttpServletRequest request) {
		ResponseError error = ResponseError.error(HttpStatus.NOT_FOUND, exception, request);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ResponseError> dataIntegrityException(DataIntegrityException exception,
			HttpServletRequest request) {
		ResponseError error = ResponseError.error(HttpStatus.BAD_REQUEST, exception, request);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> methodArgumentNotValidException(MethodArgumentNotValidException exception,
			HttpServletRequest request) {
		ValidationError error = ResponseError.errorValidation(HttpStatus.UNPROCESSABLE_ENTITY, exception, request);
		exception.getBindingResult().getFieldErrors().forEach(item -> {
			error.addError(item.getField(), item.getDefaultMessage());
		});
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ResponseError> authorizationException(AuthorizationException exception,
			HttpServletRequest request) {
		ResponseError error = ResponseError.error(HttpStatus.FORBIDDEN, exception, request);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}

	@ExceptionHandler(FileException.class)
	public ResponseEntity<ResponseError> fileException(FileException exception, HttpServletRequest request) {
		ResponseError error = ResponseError.error(HttpStatus.BAD_REQUEST, exception, request);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}

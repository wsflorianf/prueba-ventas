package com.prueba.backend.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// Maneja excepciones para recursos no encontrados - 404
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(NotFoundException ex, WebRequest webRequest) {
		
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(),ex.getMessage(),webRequest.getDescription(false),"NOT FOUND");
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
    }
	
	// Maneja excepciones para peticiones mal realizadas - 400
	@ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> handleBadRequestException(BadRequestException ex, WebRequest webRequest) {
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(),ex.getMessage(),webRequest.getDescription(false),"BAD REQUEST");
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }

    // Maneja excepciones generales que lanzan error 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception ex, WebRequest webRequest) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(),ex.getMessage(),webRequest.getDescription(false),"ERROR");
        
        ex.printStackTrace();
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
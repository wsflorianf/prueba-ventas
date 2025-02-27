package com.prueba.backend.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ErrorDetails {

	public LocalDateTime timeSpan;
	public String message;
	public String path;
	public String errorCode;
	
	
}

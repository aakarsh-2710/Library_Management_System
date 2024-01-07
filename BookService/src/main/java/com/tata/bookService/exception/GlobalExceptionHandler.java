package com.tata.bookService.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.tata.bookService.constant.MessageConstant;
import com.tata.bookService.util.CustomHttpResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgsNotValidException(MethodArgumentNotValidException exception,
			WebRequest request) {

		String url = request.getDescription(false).split("=")[1];
		String message = "";

		for (ObjectError error : exception.getBindingResult().getAllErrors()) {

			String fieldName = ((FieldError) error).getField();
			message = fieldName + " " + error.getDefaultMessage();
			break;
		}

		CustomHttpResponse customClassError = new CustomHttpResponse(url, message, MessageConstant.exception_handled,
				MessageConstant.exception_handled_code, new Date());

		return new ResponseEntity<>(customClassError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception exception, WebRequest request) {

		String url = request.getDescription(false).split("=")[1];

		CustomHttpResponse customClassError = new CustomHttpResponse(url, MessageConstant.technicalIssueOccured,
				MessageConstant.exception_handled, MessageConstant.exception_handled_code, new Date());

		return new ResponseEntity<>(customClassError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

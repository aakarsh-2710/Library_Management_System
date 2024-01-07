package com.tata.bookService.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import com.tata.bookService.constant.MessageConstant;

@Component
public class CommonMethods {

	@Autowired
	private WebRequest webRequest;

	public ResponseEntity<?> successMsg(String message) {

		String url = webRequest.getDescription(false).split("=")[1];

		CustomHttpResponse customClassError = new CustomHttpResponse(url, message, MessageConstant.created_successfully,
				MessageConstant.created_successfully_code, new Date());

		return new ResponseEntity<>(customClassError, HttpStatus.ACCEPTED);

	}

	public ResponseEntity<?> errorMsg(String message) {

		String url = webRequest.getDescription(false).split("=")[1];

		CustomHttpResponse customClassError = new CustomHttpResponse(url, message, MessageConstant.error_occurred,
				MessageConstant.error_occurred_code, new Date());

		return new ResponseEntity<>(customClassError, HttpStatus.ACCEPTED);

	}
}

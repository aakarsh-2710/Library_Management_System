package com.tata.bookService.util;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CustomHttpResponse {

	private String endPoint;
	private String message;
	private String status;
	private int code;
	private Date timestamp;

	public CustomHttpResponse(String endPoint, String message, String status, int code, Date timestamp) {
		super();
		this.endPoint = endPoint;
		this.message = message;
		this.status = status;
		this.code = code;
		this.timestamp = timestamp;
	}

	public CustomHttpResponse() {
		super();
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}

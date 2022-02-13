package com.david.rest.webservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {
	private Date timestamp;
	private String details;
	private String message;
	
	public ExceptionResponse(Date timestamp, String details, String message) {
		super();
		this.timestamp = timestamp;
		this.details = details;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

}

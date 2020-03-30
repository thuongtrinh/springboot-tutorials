package com.txt.service;

public class ServiceResult {

	public enum Status {
		SUCCESS, FAILED
	}

	private Status status = Status.SUCCESS;
	private String message;
	private Object data;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

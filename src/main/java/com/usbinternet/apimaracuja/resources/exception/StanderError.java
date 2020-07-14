package com.usbinternet.apimaracuja.resources.exception;

import java.io.Serializable;

public class StanderError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long timeStamp;
	private Integer status;
	private String error;
	private String msg;
	private String path;

	public StanderError(Long timeStamp, Integer status, String error, String msg, String path) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.error = error;
		this.msg = msg;
		this.path = path;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

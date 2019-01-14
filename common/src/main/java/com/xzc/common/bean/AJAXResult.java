package com.xzc.common.bean;

public class AJAXResult {

	private boolean success;
	private Object data;
	
	
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public AJAXResult(boolean success) {
		super();
		this.success = success;
	}

	public AJAXResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

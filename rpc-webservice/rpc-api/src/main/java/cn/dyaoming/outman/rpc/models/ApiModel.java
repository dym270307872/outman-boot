package cn.dyaoming.outman.rpc.models;

import java.io.Serializable;

public class ApiModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String flag;
	
	private String message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

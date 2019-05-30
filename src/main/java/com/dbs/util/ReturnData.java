package com.dbs.util;

import java.io.Serializable;
import java.util.List;

public class ReturnData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 695916939497450086L;
	public final String SUCCESS = "success";
	public final String FAIL = "fail";
	public String key;
	public String msg;
	public List<Object> body;
	public List<Object> subbody;
	public List<Object> threebody;

	public String getSuccess() {
		return SUCCESS;
	}

	public String getFail() {
		return FAIL;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Object> getBody() {
		return body;
	}

	public void setBody(List<Object> body) {
		this.body = body;
	}

	public List<Object> getSubbody() {
		return subbody;
	}

	public void setSubbody(List<Object> subbody) {
		this.subbody = subbody;
	}

	public List<Object> getThreebody() {
		return threebody;
	}

	public void setThreebody(List<Object> threebody) {
		this.threebody = threebody;
	}

	@Override
	public String toString() {
		return "ReturnData [key=" + key + ", msg=" + msg + ", body=" + body + ", subbody=" + subbody + ", threebody="
				+ threebody + "]";
	}

	public ReturnData(String key, String msg, List<Object> body, List<Object> subbody, List<Object> threebody) {
		super();
		this.key = key;
		this.msg = msg;
		this.body = body;
		this.subbody = subbody;
		this.threebody = threebody;
	}

	public ReturnData() {
	}
}

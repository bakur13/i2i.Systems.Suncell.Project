package com.suncell.rest.api.dbHelper;

import java.io.Serializable;

public class ObjectValue implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String msisdn;
	private int partKey;
	private String actDate;
	
	public ObjectValue(String msisdn, int partKey, String actDate) {
		super();
		this.msisdn = msisdn;
		this.partKey = partKey;
		this.actDate = actDate;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public int getPartKey() {
		return partKey;
	}

	public void setPartKey(int partKey) {
		this.partKey = partKey;
	}

	public String getActDate() {
		return actDate;
	}

	public void setActDate(String actDate) {
		this.actDate = actDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

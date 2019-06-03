package com.tgi.sd.domain;

import java.util.Date;

import com.tgi.sd.common.domain.GenericDomainObject;


public class TestVO extends GenericDomainObject {
	
	private static final long serialVersionUID = 33952065980716126L;
	private String name;
	private String address;
	private String pincode;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


}

package com.tgi.sd.domain;

import java.io.Serializable;
import java.util.Date;


import com.tgi.sd.common.domain.GenericDomainObject;

public class TokenVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	private String token;
	private Date tokenExpire;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpire() {
		return tokenExpire;
	}

	public void setTokenExpire(Date tokenExpire) {
		this.tokenExpire = tokenExpire;
	}

}
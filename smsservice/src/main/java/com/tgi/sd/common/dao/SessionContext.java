package com.tgi.sd.common.dao;


import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Class holding the contextual information for a user session.
 * 
 * 
 * */
/**
 * @author Jegatheesan
 *
 */
public class SessionContext implements Serializable{

	private static final long serialVersionUID = -1367427476207407958L;

	private Long userId;
	
	private String userName;
	

	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}

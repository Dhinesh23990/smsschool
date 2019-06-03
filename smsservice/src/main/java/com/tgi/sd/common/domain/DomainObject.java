package com.tgi.sd.common.domain;

import java.util.Date;

/**
 * 
 * 
 * @author Jegatheesan
 * */
public interface DomainObject {

	String getId();
    void setDateCreated(Date currentTimeStamp);
    void setDateModified(Date currentTimeStamp);
    void setUserCreated(String userName);
    void setUserModified(String userName);
}

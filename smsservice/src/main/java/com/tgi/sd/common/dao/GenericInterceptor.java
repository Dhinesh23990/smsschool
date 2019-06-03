package com.tgi.sd.common.dao;

import java.io.Serializable;
import java.util.Arrays;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.tgi.sd.common.domain.GenericDomainObject;



/**
 * An interceptor for adding the static informations like
 * Created Date, Created By etc.
 * 
 */
@SuppressWarnings("serial")
public class GenericInterceptor extends EmptyInterceptor {
	
	private SessionContext sessionContext;
	private static final String UPDATED_DATE = "dateModified";
	private static final String UPDATED_BY_USER_NAME= "userModified";
	private static final String CREATED_DATE= "dateCreated";
	private static final String CREATED_BY_USER_NAME = "userCreated";
	
	public GenericInterceptor(SessionContext sessionContext){
		this.sessionContext = sessionContext;
	}
	@Override
	public boolean onFlushDirty(Object entity,Serializable id,Object[] currentState,Object[] previousState,String[] propertyNames,Type[] types)
	{
		if(entity instanceof GenericDomainObject){
			setValue(currentState, propertyNames, UPDATED_DATE, DateUtil.getCurrentTimeStamp());
			setValue(currentState, propertyNames, UPDATED_BY_USER_NAME, sessionContext.getUserName());
		}
		return true;
	}
	@Override
	public boolean onSave(Object entity,Serializable id,Object[] state,String[] propertyNames,Type[] types)
	{
		if(entity instanceof GenericDomainObject){
			//setValue(state, propertyNames, CARRIERID, sessionContext.getCarrierId());
			setValue(state, propertyNames, CREATED_DATE, DateUtil.getCurrentTimeStamp());
			setValue(state, propertyNames, CREATED_BY_USER_NAME, sessionContext.getUserName());
			setValue(state, propertyNames, UPDATED_DATE, DateUtil.getCurrentTimeStamp());
			setValue(state, propertyNames, UPDATED_BY_USER_NAME, sessionContext.getUserName());
		}
		
		return true;
	}
	private void setValue(Object[] currentState, String[] propertyNames,
	                     String propertyToSet, Object value) 
	{
	    int index = Arrays.asList(propertyNames).indexOf(propertyToSet);
		if (index >= 0) {
			  currentState[index] = value;
		}
	}
	
	/**
	 * @return the sessionContext
	 */
	public SessionContext getSessionContext() {
		return sessionContext;
	}
	/**
	 * @param sessionContext the sessionContext to set
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
}

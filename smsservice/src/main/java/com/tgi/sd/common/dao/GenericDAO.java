package com.tgi.sd.common.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;



/**
 * Common Interface for all the Data Access Object Implementations.
 * 
 * @author Jegatheesan
 * 
 * */
public interface GenericDAO<T, ID extends Serializable> {

	T findById(ID id, boolean lock);

	/**
	 * Retrieves the domain object using the session.get method.
	 * 
	 * If the object needs to be passed back to the client layer then
	 * this method should be used to retrieve the object otherwise use the 
	 * findById method. 
	 **/
	T getById(ID id);

/*	*//**
	 * Get all the objects of the type.
	 * @param carrierId The carrier id for which the domain objects will be fetched.
	 * *//*
	List<T> findAll(Long carrierId);
*/	
	/**
	 * Get all the objects of the type.  
	 * */
	List<T> findAll();

	List<T> findByExample(T exampleInstance, String[] excludeProperty);
	
	/**
	 * Saves the passed in object to the data store.
	 * */
	void saveOrUpdate(T entity) throws DataAccessException;

	/**
	 * Saves the passed in object to the data store.
	 * */
	T update(SessionContext sessionContext, T entity);
	
	T update(SessionContext sessionContext, T entity, boolean merge);

	void delete(T entity);
	
	void delete(Long entityId);

	ID insert(SessionContext sessionContext, T instance);

	/**
	 * @param codePropertyName 
	 * @param code
	 * @param carrierId 
	 * @return List<T> 
	 */
	//public List<T> findByCode(String codePropertyName, String code, Long carrierId);

	public String getKeyColumnValues(Long[] entityIds);
	
	public Class getPersistentClass();
}

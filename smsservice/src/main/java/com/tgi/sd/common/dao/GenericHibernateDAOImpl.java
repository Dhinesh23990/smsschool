package com.tgi.sd.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import com.tgi.sd.common.domain.DomainObject;
import com.tgi.sd.common.domain.GenericDomainObject;



/***
 * 
 * The generic implementation for DAO using hibernate.
 * 
 * @author Jegatheesan
 * 
 * */
public abstract class GenericHibernateDAOImpl<T extends DomainObject, ID extends Serializable>
		implements GenericDAO<T, ID> {
Logger logger =Logger.getLogger(GenericHibernateDAOImpl.class);
	private Class<T> persistentClass;
	
	private SessionFactory sessionFactory;
	//public static final String CARRIER_ID="carrierId";
	public GenericHibernateDAOImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
    @SuppressWarnings("unchecked")
	public T findById(ID id, boolean lock) {
        T entity;
        if (lock)
            entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
        else
            entity = (T) getSession().load(getPersistentClass(), id);

        return entity;
    }
    
    public T getById(ID id) {
    	T entity = (T) getSession().get(getPersistentClass(), id);
    	//getSession().close();
    	//getSession().disconnect();
    	return entity;
    }
        
    public List<T> findAll() {
    	return findByCriteria();
    }
    
    protected Session getSession() {
    	//it is assumed that the session is opened and bound to the thread
    	//by either OSIV filter or MDB or scheduler before any service methods are called
    	return getSessionFactory().getCurrentSession();
    	
    }
    
    protected void closeSession() {
    	getSessionFactory().getCurrentSession().close();
    }    

    public Class<T> getPersistentClass() {
        return persistentClass;
    }
    
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        
        }
        return crit.list();
   }
    
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        List<T> results = new ArrayList<T>();
    	Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example =  Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        try {
        	results = crit.list();
        } catch (RuntimeException runtimeException) {
        	throw new GenericSystemException(runtimeException.getMessage(), runtimeException);
        }
        return results;
    }
    
    @SuppressWarnings("unchecked")
    public ID insert(SessionContext sessionContext, T entity) {
    	Timestamp currentTimeStamp = DateUtil.getCurrentTimeStamp();
        entity.setDateCreated(currentTimeStamp);
        entity.setDateModified(currentTimeStamp);
        entity.setUserCreated(sessionContext.getUserName());
        entity.setUserModified(sessionContext.getUserName());
        //entity.setCarrierId(sessionContext.getCarrierId());
        getSession().saveOrUpdate(entity);
        getSession().flush();
		return (ID)getSession().getIdentifier(entity);
    }
    
    public void saveOrUpdate(T entity) {
    	try{
        getSession().saveOrUpdate(entity);
    	}catch (HibernateException e){
    		logger.error("Hibernate Exception : " + e.getMessage() +  ": Caused By :" + e.getCause());
    	}catch (RuntimeException runtimeException) {
        	throw new GenericSystemException(runtimeException.getMessage(), runtimeException);
        }
    }
    
    public T update(SessionContext sessionContext,T entity) {
        return update(sessionContext, entity, false);
    }

    public T update(SessionContext sessionContext,T entity, boolean merge) {
    	entity.setDateModified(DateUtil.getCurrentTimeStamp());
    	if(sessionContext != null) {
    		entity.setUserModified(sessionContext.getUserName());
    	}
    	if(merge) {
    		entity = (T)getSession().merge(entity);
    	} else {
    		getSession().saveOrUpdate(entity);
    	}
    	getSession().flush();
        return entity;
    }
    
    public void delete(T entity) {
        getSession().delete(entity);
        getSession().flush();
    }
    
    public void delete(Long objectId) {
    	StringBuffer deleteQueryBuffer = new StringBuffer("delete from ").append(this.persistentClass.getName()).append(" where id=:id");
    	Query deleteQuery = getSession().createQuery(deleteQueryBuffer.toString());
    	deleteQuery.setLong("id", objectId);
    	deleteQuery.executeUpdate();
    }
    
    public String getKeyColumnValues(Long[] entityIds) {
    	StringBuffer keyColumnValues = new StringBuffer();
    	String codeColumn = getCodeProperty();
    	getSession().clear();
    	Criteria criteria = getSession().createCriteria(getPersistentClass());
    	criteria.add(Restrictions.in("id",entityIds ));
    	criteria.setProjection(Projections.property(codeColumn));
    	List list = criteria.list();
    	int count = list.size();
    	for(int i = 0; i < count ; i++) {
    		keyColumnValues.append(list.get(i));
    		if( i < count-1)
    			keyColumnValues.append(",");
    	}
    	return keyColumnValues.toString();
    }
    
    protected String getCodeProperty() {
    	String className = getPersistentClass().getName();
    	return new StringBuffer(StringUtils.uncapitalize(className.substring(className.lastIndexOf(".")+1))).append("Code").toString();
    }
        
    public List<T> findByHQL(String queryString) {
    	return this.getSession().createQuery(queryString).list();
	}    
    
    public List<T> findByCacheHQL(String queryString, boolean cacheFlag) {
    	return this.getSession().createQuery(queryString).list();
	}
 
    public List createSQLQuery(String queryString) {
    	return this.getSession().createSQLQuery(queryString).list();
	}
    public void save(GenericDomainObject entity) {
    	this.getSession().save(entity);
    	this.getSession().flush();
	}    
}
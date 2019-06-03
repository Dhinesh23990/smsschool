package com.tgi.sd.common.domain;

import java.io.Serializable;
import java.util.Date;

import com.tgi.sd.common.dao.IProxy;


/**
 * The base object for all domain objects 
 * 
 * @author Jegatheesan
 * 
 * */
public abstract class GenericDomainObject implements DomainObject, Serializable,IProxy  {

	private static final long serialVersionUID = -4064538236026860414L;
	protected String id;
	protected String userCreated;
	protected String userModified;
	protected Date dateCreated;
	protected Date dateModified;
	protected Long version;
		
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the userCreated
	 */
	public String getUserCreated() {
		return userCreated;
	}

	/**
	 * @param userCreated the userCreated to set
	 */
	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	/**
	 * @return the userModified
	 */
	public String getUserModified() {
		return userModified;
	}

	/**
	 * @param userModified the userModified to set
	 */
	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the dateModified
	 */
	public Date getDateModified() {
		return dateModified;
	}

	/**
	 * @param dateModified the dateModified to set
	 */
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Long version) {
		this.version = version;
	}
	
	@Override
	public String toString() {
		return "GenericDomainObject [name=" + "" + ", description=" + "" + ", id=" + id + ", userCreated=" + userCreated + ", userModified=" + userModified + ", dateCreated=" + dateCreated + ", dateModified=" + dateModified + ", version=" + version + ", proxyKey=" + proxyKey + ", proxyInitialized="
				+ proxyInitialized + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		//result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	/**
	 * @return the name
	 */
//	public String getName() {
//		return name;
//	}
//	/**
//	 * @param name the name to set
//	 */
//	public void setName(String name) {
//		this.name = name;
//	}
//	
	/**
	 * @return the description
	 */
//	public String getDescription() {
//		return description;
//	}
//	/**
//	 * @param description the description to set
//	 */
//	public void setDescription(String description) {
//		this.description = description;
//	}
	
	//
	// Implementation of IProxy --> IHibernateProxy
	//
	
	//TODO - find out iProxy
	
	public Object proxyKey;
	public Boolean proxyInitialized = true;

	public Object getProxyKey() {
		return proxyKey;
	}

	public void setProxyKey(Object proxyKey) {
		this.proxyKey = proxyKey;
	}

	public Boolean getProxyInitialized() {
		return proxyInitialized;
	}

	public void setProxyInitialized(Boolean proxyInitialized) {
		this.proxyInitialized = proxyInitialized;
	}

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericDomainObject other = (GenericDomainObject) obj;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (dateModified == null) {
			if (other.dateModified != null)
				return false;
		} else if (!dateModified.equals(other.dateModified))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (proxyInitialized == null) {
			if (other.proxyInitialized != null)
				return false;
		} else if (!proxyInitialized.equals(other.proxyInitialized))
			return false;
		if (proxyKey == null) {
			if (other.proxyKey != null)
				return false;
		} else if (!proxyKey.equals(other.proxyKey))
			return false;
		if (userCreated == null) {
			if (other.userCreated != null)
				return false;
		} else if (!userCreated.equals(other.userCreated))
			return false;
		if (userModified == null) {
			if (other.userModified != null)
				return false;
		} else if (!userModified.equals(other.userModified))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}*/
	
}

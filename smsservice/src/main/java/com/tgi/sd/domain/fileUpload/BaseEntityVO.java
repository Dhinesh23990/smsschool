package com.tgi.sd.domain.fileUpload;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This is the base class for all value objects. It facilitates to access the
 * updated property and its values of particular value object.
 * 
 * @author SGSAuthor
 */
public abstract class BaseEntityVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private String operationName = null;

	@Transient
	private BaseEntityVO oldEntity = null;

	@Transient
	private List<String> updatedFieldNames = null;
	
	private String createdBy;
	
	private Date createdOn;
	
	private String updatedBy;
	
	private Date updatedOn;

	/**
	 * Returns the operation name.
	 * 
	 * @return <code>String</code> operationName
	 */
	@JsonIgnore
	public String getOperationName() {
		return operationName;
	}

	/**
	 * Sets the operation name.
	 * 
	 * @param operationName
	 *            - Operation name to set.
	 */
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	/**
	 * @return the oldEntity
	 */
	@JsonIgnore
	public BaseEntityVO getOldEntity() {
		return oldEntity;
	}

	/**
	 * @param oldEntity
	 *            the oldEntity to set
	 */
	public void setOldEntity(BaseEntityVO oldEntity) {
		this.oldEntity = oldEntity;
	}

	public List<String> getUpdatedFieldNames() {
		return updatedFieldNames;
	}

	public void addUpdatedField(String updatedFieldName) {
		if (null == updatedFieldNames) {
			updatedFieldNames = new ArrayList<String>();
		}
		updatedFieldNames.add(updatedFieldName);
	}

	public void setUpdatedFieldNames(List<String> updatedFieldNames) {
		this.updatedFieldNames = updatedFieldNames;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	public String getId() {
		
		if(oldEntity != null) {
			
		 return	oldEntity.getId();
			
		}
		
		return null;
	}

}
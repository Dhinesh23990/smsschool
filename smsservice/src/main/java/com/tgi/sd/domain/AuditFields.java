package com.tgi.sd.domain;

import java.util.Date;

import com.tgi.sd.common.domain.GenericDomainObject;

public class AuditFields extends GenericDomainObject  {

	private static final long serialVersionUID = 8705876696553491580L;
	
	private String createdBy;
	private String updateBy;
	private Date createdDate;
	private Date updatedDate;

	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

}

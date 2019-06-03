package com.tgi.sd.domain;

import java.io.Serializable;

public class RoleAccessVO extends AuditFields implements BaseVO,Serializable {

	private static final long serialVersionUID = 1L;

	private String roleId;
	private String companyId;
	private String moduleName;
	private String fullAccess;
	private String viewAccess;
	private String writeAccess;
	private String deleteAccess;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getFullAccess() {
		return fullAccess;
	}
	public void setFullAccess(String fullAccess) {
		this.fullAccess = fullAccess;
	}
	public String getViewAccess() {
		return viewAccess;
	}
	public void setViewAccess(String viewAccess) {
		this.viewAccess = viewAccess;
	}
	public String getWriteAccess() {
		return writeAccess;
	}
	public void setWriteAccess(String writeAccess) {
		this.writeAccess = writeAccess;
	}
	public String getDeleteAccess() {
		return deleteAccess;
	}
	public void setDeleteAccess(String deleteAccess) {
		this.deleteAccess = deleteAccess;
	}

}
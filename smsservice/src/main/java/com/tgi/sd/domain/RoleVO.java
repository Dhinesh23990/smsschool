package com.tgi.sd.domain;

//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.security.core.GrantedAuthority;

import com.tgi.sd.common.domain.GenericDomainObject;

public class RoleVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String roleName;
	private String roleDesc;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	/*
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}
	*/

}

package com.tgi.sd.dao;

import java.util.List;

import org.apache.commons.collections.MultiMap;

import com.tgi.sd.domain.LookupVO;
import com.tgi.sd.domain.RoleAccessVO;
import com.tgi.sd.domain.RoleVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface LookupDAO {

	public List<LookupVO> getLookupByTypeId(String type, String value, String keyParam) throws SMSBusinessException;

	public MultiMap getLookupList() throws SMSBusinessException;

	public List<RoleVO> getRoleList() throws SMSBusinessException;

	public List<RoleAccessVO> getRoleAccessByRoleId(String companyId, String roleId) throws SMSBusinessException;

}

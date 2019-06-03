package com.tgi.sd.domain.config;

import java.io.Serializable;
import com.tgi.sd.common.domain.GenericDomainObject;
import com.tgi.sd.domain.AuditFields;
import com.tgi.sd.domain.BaseVO;


public class BatchVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String batchName;
	private String batchCode;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	
}
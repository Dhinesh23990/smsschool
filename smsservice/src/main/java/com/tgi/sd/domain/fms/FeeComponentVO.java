package com.tgi.sd.domain.fms;

import org.springframework.data.annotation.Transient;
import com.tgi.sd.common.domain.GenericDomainObject;


public class FeeComponentVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String feeTypeId;
	private String feeComponent;
	private String schoolId;
	
	@Transient
	private String feeTypeName;
	
	
	public String getFeeTypeName() {
		return feeTypeName;
	}
	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
	}
	public String getFeeTypeId() {
		return feeTypeId;
	}
	public void setFeeTypeId(String feeTypeId) {
		this.feeTypeId = feeTypeId;
	}
	public String getFeeComponent() {
		return feeComponent;
	}
	public void setFeeComponent(String feeComponent) {
		this.feeComponent = feeComponent;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	
}
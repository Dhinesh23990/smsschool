
package com.tgi.sd.domain;

import java.util.Date;
import java.util.List;
import com.tgi.sd.common.domain.GenericDomainObject;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class SmsLogVO extends GenericDomainObject {


	private static final long serialVersionUID = 5035617648452725550L;


	//@NotNull(message = ErrorConstants.STAFFNAME_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.STAFFNAME_IS_REQUIRED)
    //@Length(max=50,message = ErrorConstants.STAFFNAME_INVALID_LENGTH)
	private String smsDescription;
	
	private String smsType;
	
	private Integer smsCount;
	
	private List<String> sendTo;
	
	@Temporal(TemporalType.DATE)
	private Date sendOn;
	
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}


	/**
	 * @return the smsDescription
	 */
	public String getSmsDescription() {
		return smsDescription;
	}

	/**
	 * @param smsDescription the smsDescription to set
	 */
	public void setSmsDescription(String smsDescription) {
		this.smsDescription = smsDescription;
	}

	/**
	 * @return the smsType
	 */
	public String getSmsType() {
		return smsType;
	}

	/**
	 * @param smsType the smsType to set
	 */
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	/**
	 * @return the smsCount
	 */
	public Integer getSmsCount() {
		return smsCount;
	}

	/**
	 * @param smsCount the smsCount to set
	 */
	public void setSmsCount(Integer smsCount) {
		this.smsCount = smsCount;
	}

	/**
	 * @return the sendTo
	 */
	public List<String> getSendTo() {
		return sendTo;
	}

	/**
	 * @param sendTo the sendTo to set
	 */
	public void setSendTo(List<String> sendTo) {
		this.sendTo = sendTo;
	}

	/**
	 * @return the sendOn
	 */
	public Date getSendOn() {
		return sendOn;
	}

	/**
	 * @param sendOn the sendOn to set
	 */
	public void setSendOn(Date sendOn) {
		this.sendOn = sendOn;
	}

}

package com.tgi.sd.domain;

import java.util.Date;
import com.tgi.sd.common.domain.GenericDomainObject;


public class SmsVO extends GenericDomainObject {

	private static final long serialVersionUID = 5035617648452725550L;


	private int smsTotalCount;
	
	private int smsBalanceCount;
	
	private int smsValidityDays;
	
	private int smsTotalSentCount;
	
	private int smsTodaySentCount;
	
	private Date smsStartDate;
	
	private Date smsEndDate;


	public int getSmsTotalCount() {
		return smsTotalCount;
	}

	public void setSmsTotalCount(int smsTotalCount) {
		this.smsTotalCount = smsTotalCount;
	}

	public int getSmsBalanceCount() {
		return smsBalanceCount;
	}

	public void setSmsBalanceCount(int smsBalanceCount) {
		this.smsBalanceCount = smsBalanceCount;
	}

	public int getSmsValidityDays() {
		return smsValidityDays;
	}

	public void setSmsValidityDays(int smsValidityDays) {
		this.smsValidityDays = smsValidityDays;
	}

	public int getSmsTotalSentCount() {
		return smsTotalSentCount;
	}

	public void setSmsTotalSentCount(int smsTotalSentCount) {
		this.smsTotalSentCount = smsTotalSentCount;
	}

	public int getSmsTodaySentCount() {
		return smsTodaySentCount;
	}

	public void setSmsTodaySentCount(int smsTodaySentCount) {
		this.smsTodaySentCount = smsTodaySentCount;
	}

	public Date getSmsStartDate() {
		return smsStartDate;
	}

	public void setSmsStartDate(Date smsStartDate) {
		this.smsStartDate = smsStartDate;
	}

	public Date getSmsEndDate() {
		return smsEndDate;
	}

	public void setSmsEndDate(Date smsEndDate) {
		this.smsEndDate = smsEndDate;
	}



}

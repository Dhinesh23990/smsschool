package com.tgi.sd.domain;

import com.tgi.sd.common.domain.GenericDomainObject;


public class MailVO  extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String mailType;
	
	private String fromAddress;

	private String toAddress;

	private String ccAddress;

	private String bccAddress;

	private String subject;

	private String mailContent;

	private String sentOn;

	private int retryCount;
	
	private String errorMailReport;
	
	private String status;

	
	/**
	 * @return the mailType
	 */
	public String getMailType() {
		return mailType;
	}

	/**
	 * @param mailType
	 *            the mailType to set
	 */
	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	/**
	 * @return the toAddress
	 */
	public String getToAddress() {
		return toAddress;
	}

	/**
	 * @param toAddress
	 *            the toAddress to set
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	
	
	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * @return the ccAddress
	 */
	public String getCcAddress() {
		return ccAddress;
	}

	/**
	 * @param ccAddress
	 *            the ccAddress to set
	 */
	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	/**
	 * @return the bccAddress
	 */
	public String getBccAddress() {
		return bccAddress;
	}

	/**
	 * @param bccAddress
	 *            the bccAddress to set
	 */
	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the mailContent
	 */
	public String getMailContent() {
		return mailContent;
	}

	/**
	 * @param mailContent
	 *            the mailContent to set
	 */
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	

	public String getSentOn() {
		return sentOn;
	}

	public void setSentOn(String sentOn) {
		this.sentOn = sentOn;
	}

	/**
	 * @return the retryCount
	 */
	public int getRetryCount() {
		return retryCount;
	}

	/**
	 * @param retryCount
	 *            the retryCount to set
	 */
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	
	
	public String getErrorMailReport() {
		return errorMailReport;
	}

	public void setErrorMailReport(String errorMailReport) {
		this.errorMailReport = errorMailReport;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}
package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;
import com.tgi.sd.domain.MailVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface MailDAO {
	 
	List<MailVO> getMailListByType(String mailType, String status) throws SMSBusinessException;

	void updateMailStatus(String id, String status, Date date, int maxRetryCount) throws SMSBusinessException;

	MailVO saveMail(MailVO mailVO) throws SMSBusinessException;
	
	MailVO updateMail(MailVO mailVO) throws SMSBusinessException;

	List<MailVO> getAllQueueMail(String sendSmsQueue) throws SMSBusinessException;
	
}

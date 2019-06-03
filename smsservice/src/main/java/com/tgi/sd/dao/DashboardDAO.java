package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.SmsVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface DashboardDAO {
	
	public long getAllStudentCount(String schoolId) throws SMSBusinessException;

	public long getAllTeacherCount(String schoolId) throws SMSBusinessException;

	public long getAllAdminCount(String schoolId) throws SMSBusinessException;

	public long getAllParentCount() throws SMSBusinessException;

	public long getAllGenderCount(String genderId, String schoolId) throws SMSBusinessException;

	public long getAllBoysCountByClassSection(String classId, String sectionId,
			String genderId,String schoolId) throws SMSBusinessException;

	public long getAllSchoolCount() throws SMSBusinessException;

	public long getAllActiveSchoolCount(String schoolActiveStatus) throws SMSBusinessException;

	public List<SmsVO> getAllSmsCount() throws SMSBusinessException;

	
 }

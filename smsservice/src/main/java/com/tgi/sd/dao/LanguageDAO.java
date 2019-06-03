package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.LanguageVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface LanguageDAO {

	LanguageVO saveLanguage(LanguageVO languageVO) throws SMSBusinessException;

	LanguageVO getLanguageById(String languageId) throws SMSBusinessException;

	List<LanguageVO> getAllLanguageBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getLanguageCountBySchoolId(String schoolId) throws SMSBusinessException;

	LanguageVO updateLanguage(LanguageVO languageVO) throws SMSBusinessException;

	boolean deleteLanguage(String languageId) throws SMSBusinessException;

	LanguageVO getLanguageByName(String languageName, String id, String schoolId)
			throws SMSBusinessException;

}

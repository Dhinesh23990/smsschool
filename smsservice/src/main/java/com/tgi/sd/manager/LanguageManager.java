 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.LanguageVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface LanguageManager {

	LanguageVO saveLanguage(LanguageVO languageVO) throws SMSBusinessException;

	LanguageVO getLanguageById(String languageId) throws SMSBusinessException;

	LanguageVO updateLanguage(LanguageVO languageVO) throws SMSBusinessException;

	boolean deleteLanguage(String languageId) throws SMSBusinessException;

	long getLanguageCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllLanguageBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}

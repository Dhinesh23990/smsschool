 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.SectionVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface SectionManager {

	SectionVO saveSection(SectionVO sectionVO) throws SMSBusinessException;

	SectionVO getSectionById(String sectionId) throws SMSBusinessException;

	SectionVO updateSection(SectionVO sectionVO) throws SMSBusinessException;

	boolean deleteSection(String sectionId) throws SMSBusinessException;

	long getSectionCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllSectionBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}

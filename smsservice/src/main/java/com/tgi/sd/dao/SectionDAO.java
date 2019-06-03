package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.SectionVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface SectionDAO {

	SectionVO saveSection(SectionVO sectionVO) throws SMSBusinessException;

	SectionVO getSectionById(String sectionId) throws SMSBusinessException;

	List<SectionVO> getAllSectionBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getSectionCountBySchoolId(String schoolId) throws SMSBusinessException;

	SectionVO updateSection(SectionVO sectionVO) throws SMSBusinessException;

	boolean deleteSection(String sectionId) throws SMSBusinessException;

	SectionVO getSectionByName(String sectionName, String id, String schoolId)
			throws SMSBusinessException;

}

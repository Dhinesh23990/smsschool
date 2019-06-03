package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.CasteVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface CasteDAO {

	CasteVO saveCaste(CasteVO casteVO) throws SMSBusinessException;

	CasteVO getCasteById(String casteId) throws SMSBusinessException;

	List<CasteVO> getAllCasteBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getCasteCountBySchoolId(String schoolId) throws SMSBusinessException;

	CasteVO updateCaste(CasteVO casteVO) throws SMSBusinessException;

	boolean deleteCaste(String casteId) throws SMSBusinessException;

	CasteVO getCasteByName(String casteName, String id, String schoolId)
			throws SMSBusinessException;

}

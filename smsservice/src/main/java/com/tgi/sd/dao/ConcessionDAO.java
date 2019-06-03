package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.fms.ConcessionVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface ConcessionDAO {

	ConcessionVO saveConcession(ConcessionVO concessionVO) throws SMSBusinessException;

	ConcessionVO getConcessionById(String concessionId) throws SMSBusinessException;

	List<ConcessionVO> getAllConcessionBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getConcessionCountBySchoolId(String schoolId) throws SMSBusinessException;

	ConcessionVO updateConcession(ConcessionVO concessionVO) throws SMSBusinessException;

	boolean deleteConcession(String concessionId) throws SMSBusinessException;

	ConcessionVO getConcessionByName(String concessionName, String id, String schoolId)
			throws SMSBusinessException;

}

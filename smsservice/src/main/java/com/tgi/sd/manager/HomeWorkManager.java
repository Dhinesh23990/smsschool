 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.HomeWorkVO;
import com.tgi.sd.domain.FilterVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface HomeWorkManager {

	HomeWorkVO saveHomeWork(HomeWorkVO homeworkVO) throws SMSBusinessException;

	HomeWorkVO getHomeWorkById(String homeworkId) throws SMSBusinessException;

	HomeWorkVO updateHomeWork(HomeWorkVO homeworkVO) throws SMSBusinessException;

	boolean deleteHomeWork(String homeworkId) throws SMSBusinessException;

	long getHomeWorkCountBySchoolId(String schoolId,String studentId) throws SMSBusinessException;

	Map<String, Object> getAllHomeWorkBySchoolId(String schoolId,String studentId,String mobileNo, int pageIndex,
			int pageSize) throws SMSBusinessException;

	Map<String, Object> getHomeWorkByFilter(FilterVO filterVO) throws SMSBusinessException;
	
	
}


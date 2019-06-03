package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;

import com.tgi.sd.domain.HomeWorkVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface HomeWorkDAO {

	HomeWorkVO saveHomeWork(HomeWorkVO homeworkVO) throws SMSBusinessException;

	HomeWorkVO getHomeWorkById(String homeworkId) throws SMSBusinessException;

	List<HomeWorkVO> getAllHomeWorkBySchoolId(String schoolId,String studentId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getHomeWorkCountBySchoolId(String schoolId,String studentId) throws SMSBusinessException;

	HomeWorkVO updateHomeWork(HomeWorkVO homeworkVO) throws SMSBusinessException;

	boolean deleteHomeWork(String homeworkId) throws SMSBusinessException;

	HomeWorkVO getHomeWorkByName(String homeworkName, String id, String schoolId, String studentId)
			throws SMSBusinessException;

	List<HomeWorkVO> getHomeWorkBySchoolIdAndDate(String schoolId, Date startDate, Date endDate) throws SMSBusinessException;

}

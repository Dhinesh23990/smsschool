/**
 * 
 */
package com.tgi.sd.manager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tgi.sd.dao.ClassDAO;
import com.tgi.sd.dao.LeaveEntitlementDAO;
import com.tgi.sd.dao.LeaveMasterDAO;
import com.tgi.sd.dao.SectionDAO;
import com.tgi.sd.dao.StaffAttendanceDAO;
import com.tgi.sd.dao.TeacherDAO;
import com.tgi.sd.domain.LeaveEntitlementVO;
import com.tgi.sd.domain.LeaveMasterVO;
import com.tgi.sd.domain.StaffAttendanceSummaryVO;
import com.tgi.sd.domain.StaffAttendanceVO;
import com.tgi.sd.domain.TeacherVO;
import com.tgi.sd.exception.ErrorConstants;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

/**
 * @author SGSAuthour
 *
 */
public class StaffAttendanceManagerImpl implements StaffAttendanceManager {
	
	private static Logger logger = Logger.getLogger(StaffAttendanceManagerImpl.class);

	StaffAttendanceDAO staffAttendanceDAO;

	TeacherDAO teacherDAO;
	
	SectionDAO sectionDAO;
	
	private ClassDAO classDAO;
	
	private LeaveEntitlementDAO leaveEntitlementDAO;
	
	private LeaveMasterDAO leaveMasterDAO;
	
	public LeaveMasterDAO getLeaveMasterDAO() {
		return leaveMasterDAO;
	}
	public void setLeaveMasterDAO(LeaveMasterDAO leaveMasterDAO) {
		this.leaveMasterDAO = leaveMasterDAO;
	}
	
	public StaffAttendanceDAO getStaffAttendanceDAO() {
		return staffAttendanceDAO;
	}

	public void setStaffAttendanceDAO(StaffAttendanceDAO staffAttendanceDAO) {
		this.staffAttendanceDAO = staffAttendanceDAO;
	}

	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}

	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}
	
	public ClassDAO getClassDAO() {
		return classDAO;
	}
	public void setClassDAO(ClassDAO classDAO) {
		this.classDAO = classDAO;
	}
	
	public SectionDAO getSectionDAO() {
		return sectionDAO;
	}
	public void setSectionDAO(SectionDAO sectionDAO) {
		this.sectionDAO = sectionDAO;
	}
	
	public LeaveEntitlementDAO getLeaveEntitlementDAO() {
		return leaveEntitlementDAO;
	}
	public void setLeaveEntitlementDAO(LeaveEntitlementDAO leaveEntitlementDAO) {
		this.leaveEntitlementDAO = leaveEntitlementDAO;
	}


	public StaffAttendanceVO saveStaffAttendance(StaffAttendanceVO staffStaffAttendance) throws SMSBusinessException {
		staffStaffAttendance.setId(UUID.randomUUID().toString());
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveStaffAttendance :::");
		}
		staffStaffAttendance = staffAttendanceDAO.saveStaffAttendance(staffStaffAttendance);
		if (logger.isDebugEnabled()) {
			logger.debug("End saveStaffAttendance :::" + staffStaffAttendance.getId());
		}
		return staffStaffAttendance;
	}

	public StaffAttendanceVO getStaffAttendance(String id) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStaffAttendance Id:::" + id);
		}
		
		StaffAttendanceVO staffStaffAttendance =  staffAttendanceDAO.getStaffAttendance(id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getStaffAttendance :::" + staffStaffAttendance.getId());
		}
		return staffStaffAttendance;
	}
	
	@Override
	public StaffAttendanceVO updateStaffAttendance(StaffAttendanceVO staffStaffAttendance) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateStaffAttendance :::");
		}
		if(StringUtils.isBlank(staffStaffAttendance.getId())){
    		throw new SMSBusinessException(ErrorConstants.ID_NOT_EXISTS_WHILE_UPDATING);
		}
		staffStaffAttendance = staffAttendanceDAO.updateStaffAttendance(staffStaffAttendance);
		if (logger.isDebugEnabled()) {
			logger.debug("End updateStaffAttendance :::" + staffStaffAttendance.getId());
		}
		return staffStaffAttendance;
	}

	@Override
	public List<StaffAttendanceSummaryVO>  getTeachersByClassAndSection(String schoolId, Date date,String staffId,String attendanceDayAfter) throws SMSBusinessException {
		List<StaffAttendanceVO> staffStaffAttendanceList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getTeachersByClassAndSection");
		}
		List<StaffAttendanceSummaryVO> staffStaffAttendanceSummaryVOs = new ArrayList<StaffAttendanceSummaryVO>();

		Map<String,StaffAttendanceVO> staffStaffAttendanceIdMap = new HashMap<String,StaffAttendanceVO>();
		staffStaffAttendanceList =  (List<StaffAttendanceVO>) staffAttendanceDAO.getStaffAttendanceByClassAndSection(schoolId,date,staffId,attendanceDayAfter,null);
		if(staffStaffAttendanceList != null && staffStaffAttendanceList.size() > 0){
			for (StaffAttendanceVO staffStaffAttendanceVO : staffStaffAttendanceList) {
				staffStaffAttendanceIdMap.put(staffStaffAttendanceVO.getStaffId(),staffStaffAttendanceVO);
			}
		}
		
		List<TeacherVO> teacherList = new ArrayList<TeacherVO>();
		if(!staffId.equals(SMSConstants.ALL_TEACHERS)){
			TeacherVO teacherVO = teacherDAO.getTeacherByStaffId(schoolId,staffId);
			teacherList.add(teacherVO);
		}else{
			teacherList =  teacherDAO.getAllTeacher(schoolId,null,null);
 		}
		
		
		for (TeacherVO teacherVO : teacherList) {
			StaffAttendanceSummaryVO summaryVO = new StaffAttendanceSummaryVO();
			summaryVO.setStaffId(teacherVO.getStaffId());
			summaryVO.setAdmissionNumber(teacherVO.getEmployeeNumber());
			summaryVO.setStaffName(teacherVO.getStaffName());
			summaryVO.setDayAfter(attendanceDayAfter);
			summaryVO.setPresentDays(teacherVO.getPresentDays());
			summaryVO.setTotalDays(teacherVO.getTotalDays());
			DecimalFormat df=new DecimalFormat("0.00");
			String formate = df.format(teacherVO.getAttendancePercenatge()); 
			double value = Double.parseDouble(formate);
			summaryVO.setAttendancePercenatge(value);
			List<LeaveMasterVO> LeaveMasterVOs = new ArrayList<LeaveMasterVO>();
			List<LeaveEntitlementVO> leaveEntitlementVOs = leaveEntitlementDAO.getAllLeaveEntitlementBySchoolIdAndStaffId(schoolId,teacherVO.getId());
			for(LeaveEntitlementVO leaveEntitlementVO : leaveEntitlementVOs){
				LeaveMasterVO leaveMasterVO = leaveMasterDAO.getLeaveMasterById(leaveEntitlementVO.getLeaveMasterId());
				LeaveMasterVOs.add(leaveMasterVO);
			}
			summaryVO.setLeaveMasterVO(LeaveMasterVOs);
			if(staffStaffAttendanceIdMap.get(teacherVO.getStaffId()) != null){
				StaffAttendanceVO staffStaffAttendanceVO = staffStaffAttendanceIdMap.get(teacherVO.getStaffId());
				summaryVO.setId(staffStaffAttendanceVO.getId());
				summaryVO.setAfternoon(staffStaffAttendanceVO.getAfternoon());
				summaryVO.setFullDay(staffStaffAttendanceVO.getFullDay());
				summaryVO.setMorning(staffStaffAttendanceVO.getMorning());
				summaryVO.setNote(staffStaffAttendanceVO.getNote());
				summaryVO.setSchoolId(staffStaffAttendanceVO.getSchoolId());
			}
			staffStaffAttendanceSummaryVOs.add(summaryVO);
		}
 
		if (logger.isDebugEnabled()) {
			logger.debug("End getTeachersByClassAndSection");
		}
		return staffStaffAttendanceSummaryVOs;	
	}
	
	@Override
	public List<StaffAttendanceSummaryVO> getTeachersAttentance(String schoolId,Date startdate,String staffId,String dayAfter,Date endDate) throws SMSBusinessException {
		List<StaffAttendanceVO> staffAttendanceList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getTeachersAttentance");
		}
		List<StaffAttendanceSummaryVO> staffAttendanceSummaryVOs = new ArrayList<StaffAttendanceSummaryVO>();

		Map<String,StaffAttendanceVO> staffAttendanceIdMap = new HashMap<String,StaffAttendanceVO>();
		staffAttendanceList =  (List<StaffAttendanceVO>) staffAttendanceDAO.getStaffAttendanceByClassAndSection(schoolId,startdate,staffId,dayAfter,endDate);
		if(staffAttendanceList != null && staffAttendanceList.size() > 0){
			for (StaffAttendanceVO staffAttendanceVO : staffAttendanceList) {
				staffAttendanceIdMap.put(staffAttendanceVO.getStaffId(),staffAttendanceVO);
			}
			
			List<TeacherVO> teacherList = new ArrayList<TeacherVO>();
			if(!staffId.equals(SMSConstants.ALL_TEACHERS)){
				TeacherVO teacherVO = teacherDAO.getTeacherByStaffId(schoolId,staffId);
				teacherList.add(teacherVO);
			}else{
				teacherList =  teacherDAO.getAllTeacher(schoolId,null,null);
	 		}
			
			for (TeacherVO teacherVO : teacherList) {
				StaffAttendanceSummaryVO summaryVO = new StaffAttendanceSummaryVO();
				summaryVO.setStaffId(teacherVO.getStaffId());
				summaryVO.setAdmissionNumber(teacherVO.getEmployeeNumber());
				
				/*if(StringUtils.isNotBlank(teacherVO.getClassName())){
					ClassVO classVO = classDAO.getClassById(teacherVO.getClassName());
					summaryVO.setStandard(classVO.getClassName());
				}
				if(StringUtils.isNotBlank(teacherVO.getSection())){
					SectionVO sectionVO = sectionDAO.getSectionById(teacherVO.getSection());
					summaryVO.setSection(sectionVO.getSectionName());
				}*/
				summaryVO.setStaffName(teacherVO.getStaffName());
				if(staffAttendanceIdMap.get(teacherVO.getStaffId()) != null){
					StaffAttendanceVO staffAttendanceVO = staffAttendanceIdMap.get(teacherVO.getStaffId());
					summaryVO.setId(staffAttendanceVO.getId());
					summaryVO.setAfternoon(staffAttendanceVO.getAfternoon());
					summaryVO.setFullDay(staffAttendanceVO.getFullDay());
					summaryVO.setMorning(staffAttendanceVO.getMorning());
					summaryVO.setNote(staffAttendanceVO.getNote());
					summaryVO.setSchoolId(staffAttendanceVO.getSchoolId());
					summaryVO.setDayAfter(staffAttendanceVO.getDayAfter());
					summaryVO.setSelectLeaveMasterId(staffAttendanceVO.getSelectLeaveMasterId());
					if(staffAttendanceVO.getSelectLeaveMasterId() != null){
						LeaveMasterVO leaveMasterVO = leaveMasterDAO.getLeaveMasterById(staffAttendanceVO.getSelectLeaveMasterId());
						summaryVO.setLeaveMasterShortName(leaveMasterVO.getLeaveShortName());
					}
					DecimalFormat df=new DecimalFormat("0.00");
					String formate = df.format(teacherVO.getAttendancePercenatge()); 
					double value = Double.parseDouble(formate);
					summaryVO.setAttendancePercenatge(value);
					summaryVO.setPresentDays(teacherVO.getPresentDays());
					summaryVO.setTotalDays(teacherVO.getTotalDays());
					//summaryVO.setStaffAttendancePercenatge(staffStaffAttendanceVO.getStaffAttendancePercenatge());
				}
				staffAttendanceSummaryVOs.add(summaryVO);
			}
	 
		}else{
			staffAttendanceSummaryVOs.isEmpty();
		}

		
		if (logger.isDebugEnabled()) {
			logger.debug("End getTeachersAttentance");
		}
		return staffAttendanceSummaryVOs;	
	}

	@Override
	public void saveBulkStaffAttendance(List<StaffAttendanceVO> staffStaffAttendanceList) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveBulkStaffAttendance :::");
		}
		for (StaffAttendanceVO staffAttendanceVO : staffStaffAttendanceList) {
			if(StringUtils.isNotBlank(staffAttendanceVO.getId())){
				staffAttendanceDAO.updateStaffAttendance(staffAttendanceVO);
			}else{
				staffAttendanceVO.setId(UUID.randomUUID().toString());
				TeacherVO teacherVO = teacherDAO.getTeacherByStaffId(staffAttendanceVO.getSchoolId(),staffAttendanceVO.getStaffId());
				teacherVO.setAttendancePercenatge(staffAttendanceVO.getAttendancePercenatge());
				teacherVO.setTotalDays(staffAttendanceVO.getTotalDays());
				teacherVO.setPresentDays(staffAttendanceVO.getPresentDays());
				teacherDAO.updateTeacher(teacherVO);
				staffAttendanceDAO.saveStaffAttendance(staffAttendanceVO);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End saveBulkStaffAttendance :::");
		}
	}

	@Override
	public List<StaffAttendanceSummaryVO> getAllStaffAttendance() throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllStaffAttendance :::");
		}
		List<StaffAttendanceSummaryVO> staffStaffAttendanceSummaryVOs = new ArrayList<StaffAttendanceSummaryVO>();
		List<StaffAttendanceVO> staffStaffAttendanceList = null;
		staffStaffAttendanceList =  (List<StaffAttendanceVO>) staffAttendanceDAO.getAllStaffAttendance();
		
		for (StaffAttendanceVO staffStaffAttendanceVO : staffStaffAttendanceList) {
			StaffAttendanceSummaryVO summaryVO = new StaffAttendanceSummaryVO();
			TeacherVO teacher =  teacherDAO.getTeacher(staffStaffAttendanceVO.getStaffId());
			summaryVO.setStaffId(teacher.getId());
			summaryVO.setAdmissionNumber(teacher.getEmployeeNumber());
			//summaryVO.setStandard(teacher.getClassName());
			summaryVO.setStaffName(teacher.getStaffName());
			//summaryVO.setSection(teacher.getSection());
			summaryVO.setId(staffStaffAttendanceVO.getId());
			summaryVO.setAfternoon(staffStaffAttendanceVO.getAfternoon());
			summaryVO.setFullDay(staffStaffAttendanceVO.getFullDay());
			summaryVO.setMorning(staffStaffAttendanceVO.getMorning());
			summaryVO.setNote(staffStaffAttendanceVO.getNote());
			//summaryVO.setStaffAttendancePercenatge(staffStaffAttendanceVO.getStaffAttendancePercenatge());
		
			staffStaffAttendanceSummaryVOs.add(summaryVO);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllStaffAttendance :::");
		}
		return staffStaffAttendanceSummaryVOs;
	}
 
	
}

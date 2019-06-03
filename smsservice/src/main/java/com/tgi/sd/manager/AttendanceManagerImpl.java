/**
 * 
 */
package com.tgi.sd.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tgi.sd.dao.AttendanceDAO;
import com.tgi.sd.dao.BatchDAO;
import com.tgi.sd.dao.ClassDAO;
import com.tgi.sd.dao.SectionDAO;
import com.tgi.sd.dao.StudentDAO;
import com.tgi.sd.domain.AttendanceSummaryVO;
import com.tgi.sd.domain.AttendanceVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.config.BatchVO;
import com.tgi.sd.domain.config.ClassVO;
import com.tgi.sd.domain.config.SectionVO;
import com.tgi.sd.exception.ErrorConstants;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public class AttendanceManagerImpl implements AttendanceManager {
	
	private static Logger logger = Logger.getLogger(AttendanceManagerImpl.class);

	AttendanceDAO attendanceDAO;

	StudentDAO studentDAO;
	
	SectionDAO sectionDAO;
	
	private ClassDAO classDAO;
	
	private BatchDAO batchDAO;
	
	public BatchDAO getBatchDAO() {
		return batchDAO;
	}
	public void setBatchDAO(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}
	
	public AttendanceDAO getAttendanceDAO() {
		return attendanceDAO;
	}

	public void setAttendanceDAO(AttendanceDAO attendanceDAO) {
		this.attendanceDAO = attendanceDAO;
	}

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
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


	public AttendanceVO saveAttendance(AttendanceVO attendance) throws SMSBusinessException {
		attendance.setId(UUID.randomUUID().toString());
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveAttendance :::");
		}
		attendance = attendanceDAO.saveAttendance(attendance);
	
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveAttendance :::" + attendance.getId());
		}
		return attendance;
	}

	public AttendanceVO getAttendance(String id) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAttendance Id:::" + id);
		}
		
		AttendanceVO attendance =  attendanceDAO.getAttendance(id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAttendance :::" + attendance.getId());
		}
		return attendance;
	}
	
	@Override
	public AttendanceVO updateAttendance(AttendanceVO attendance) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateAttendance :::");
		}
		if(StringUtils.isBlank(attendance.getId())){
    		throw new SMSBusinessException(ErrorConstants.ID_NOT_EXISTS_WHILE_UPDATING);
		}
		attendance = attendanceDAO.updateAttendance(attendance);
		if (logger.isDebugEnabled()) {
			logger.debug("End updateAttendance :::" + attendance.getId());
		}
		return attendance;
	}

	@Override
	public List<AttendanceSummaryVO> getStudentsByClassAndSection(String schoolId, Date date,String standard,String section) throws SMSBusinessException {
		List<AttendanceVO> attendanceList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentsByClassAndSection");
		}
		List<AttendanceSummaryVO> attendanceSummaryVOs = new ArrayList<AttendanceSummaryVO>();

		Map<String,AttendanceVO> attendanceIdMap = new HashMap<String,AttendanceVO>();
		attendanceList =  (List<AttendanceVO>) attendanceDAO.getAttendanceByClassAndSection(schoolId,date,standard,section,null);
		if(attendanceList != null && attendanceList.size() > 0){
			for (AttendanceVO attendanceVO : attendanceList) {
				attendanceIdMap.put(attendanceVO.getStudentId(),attendanceVO);
			}
		}

		List<StudentVO> studentList =  studentDAO.getStudentsByClassAndSection(schoolId,standard,section); 
		for (StudentVO studentVO : studentList) {
			AttendanceSummaryVO summaryVO = new AttendanceSummaryVO();
			summaryVO.setStudentId(studentVO.getStudentId());
			summaryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
			summaryVO.setStandard(studentVO.getClassId());
			summaryVO.setSection(studentVO.getSection());
			summaryVO.setStudentName(studentVO.getStudentName());
			
			/*if(StringUtils.isNotBlank(studentVO.getClassName())){
				//ClassVO classVO = classDAO.getClassById(studentVO.getClassName());
				summaryVO.setStandard(studentVO.getClassName());
			}
			if(StringUtils.isNotBlank(studentVO.getSection())){
				//SectionVO sectionVO = sectionDAO.getSectionById(studentVO.getSection());
				summaryVO.setSection(studentVO.getSection());
			}*/
			//StudentVO student = studentDAO.getStudentByStudentId(studentVO.getStudentId(),studentVO.getSchoolId());
			summaryVO.setPresentDays(studentVO.getPresentDays());
			summaryVO.setTotalDays(studentVO.getTotalDays());
			summaryVO.setAttendancePercenatge(studentVO.getAttendancePercenatge());
			
			if(attendanceIdMap.get(studentVO.getStudentId()) != null){
				AttendanceVO attendanceVO = attendanceIdMap.get(studentVO.getStudentId());
				summaryVO.setId(attendanceVO.getId());
				summaryVO.setAfternoon(attendanceVO.getAfternoon());
				summaryVO.setFullDay(attendanceVO.getFullDay());
				summaryVO.setMorning(attendanceVO.getMorning());
				summaryVO.setNote(attendanceVO.getNote());
				summaryVO.setSchoolId(attendanceVO.getSchoolId());
			}
			attendanceSummaryVOs.add(summaryVO);
		}
 
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentsByClassAndSection");
		}
		return attendanceSummaryVOs;	
	}
	
	@Override
	public Map<String, Object> getStudentsByStudentIdAndDate(String schoolId,String studentId,Date date,Date endDate) throws SMSBusinessException {
		List<AttendanceVO> attendanceList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentsByClassAndSection");
		}
	
		attendanceList =  (List<AttendanceVO>) attendanceDAO.getStudentsByStudentIdAndDate(schoolId,studentId,date,endDate);
		StudentVO studentVO =  studentDAO.getStudentByStudentId(studentId,schoolId);
		
		if(studentVO != null){
		if(StringUtils.isNotBlank(studentVO.getBatchId())){
			BatchVO batchVO = batchDAO.getBatchById(studentVO.getBatchId());
			studentVO.setBatchName(batchVO.getBatchName());
		}
		if(StringUtils.isNotBlank(studentVO.getClassId())){
			ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
			studentVO.setClassName(classVO.getClassName());
		}
		if(StringUtils.isNotBlank(studentVO.getSection())){
			SectionVO sectionVO = sectionDAO.getSectionById(studentVO.getSection());
			studentVO.setSectionName(sectionVO.getSectionName());
		}
		}
		
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	responseObjectsMap.put("attendanceList", attendanceList);
    	responseObjectsMap.put("studentVO", studentVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentsByClassAndSection");
		}
		return responseObjectsMap;	
	}
	
	@Override
	public List<AttendanceSummaryVO> getStudentsByClassAndSectionAndDate(String schoolId,Date date,String standard,String section,Date endDate) throws SMSBusinessException {
		List<AttendanceVO> attendanceList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentsByClassAndSection");
		}
		List<AttendanceSummaryVO> attendanceSummaryVOs = new ArrayList<AttendanceSummaryVO>();

		Map<String,AttendanceVO> attendanceIdMap = new HashMap<String,AttendanceVO>();
		attendanceList =  (List<AttendanceVO>) attendanceDAO.getAttendanceByClassAndSection(schoolId,date,standard,section,endDate);
		if(attendanceList != null && attendanceList.size() > 0){
				
			for (AttendanceVO attendanceVO : attendanceList) {
				attendanceIdMap.put(attendanceVO.getStudentId(),attendanceVO);
			}
			
			List<StudentVO> studentList =  studentDAO.getStudentsByClassAndSection(schoolId,standard,section);
			
			for (StudentVO studentVO : studentList) {
				AttendanceSummaryVO summaryVO = new AttendanceSummaryVO();
				summaryVO.setStudentId(studentVO.getStudentId());
				summaryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
				
				if(StringUtils.isNotBlank(studentVO.getClassName())){
					ClassVO classVO = classDAO.getClassById(studentVO.getClassName());
					summaryVO.setStandard(classVO.getClassName());
				}
				if(StringUtils.isNotBlank(studentVO.getSection())){
					SectionVO sectionVO = sectionDAO.getSectionById(studentVO.getSection());
					summaryVO.setSection(sectionVO.getSectionName());
				}
				summaryVO.setStudentName(studentVO.getStudentName());
				if(attendanceIdMap.get(studentVO.getStudentId()) != null){
					AttendanceVO attendanceVO = attendanceIdMap.get(studentVO.getStudentId());
					summaryVO.setId(attendanceVO.getId());
					summaryVO.setAfternoon(attendanceVO.getAfternoon());
					summaryVO.setFullDay(attendanceVO.getFullDay());
					summaryVO.setMorning(attendanceVO.getMorning());
					summaryVO.setNote(attendanceVO.getNote());
					summaryVO.setSchoolId(attendanceVO.getSchoolId());
					StudentVO student = studentDAO.getStudentByStudentId(attendanceVO.getStudentId(),attendanceVO.getSchoolId());
					summaryVO.setAttendancePercenatge(student.getAttendancePercenatge());
					summaryVO.setPresentDays(student.getPresentDays());
					summaryVO.setTotalDays(student.getTotalDays());
				}
				attendanceSummaryVOs.add(summaryVO);
			}
			
			/*for(AttendanceSummaryVO attendanceSummaryVO : attendanceSummaryVOs){
				attendanceSummaryVO.setTotalDays(attendanceSummaryVOs.size());
			}*/
	 
		}else{
			attendanceSummaryVOs.isEmpty();
		}

		
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentsByClassAndSection");
		}
		return attendanceSummaryVOs;	
	}

	@Override
	public void saveBulkAttendance(List<AttendanceVO> attendanceList) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveBulkAttendance :::");
		}
		for (AttendanceVO attendanceVO : attendanceList) {
			if(StringUtils.isNotBlank(attendanceVO.getId())){
				attendanceDAO.updateAttendance(attendanceVO);
			}else{
				attendanceVO.setId(UUID.randomUUID().toString());
				attendanceDAO.saveAttendance(attendanceVO);
				StudentVO studentVO = studentDAO.getStudentByStudentId(attendanceVO.getStudentId(),attendanceVO.getSchoolId());
				studentVO.setAttendancePercenatge(attendanceVO.getAttendancePercenatge());
				studentVO.setTotalDays(attendanceVO.getTotalDays());
				studentVO.setPresentDays(attendanceVO.getPresentDays());
				studentDAO.updateStudent(studentVO);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End saveBulkAttendance :::");
		}
	}

	@Override
	public List<AttendanceSummaryVO> getAllAttendance() throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllAttendance :::");
		}
		List<AttendanceSummaryVO> attendanceSummaryVOs = new ArrayList<AttendanceSummaryVO>();
		List<AttendanceVO> attendanceList = null;
		attendanceList =  (List<AttendanceVO>) attendanceDAO.getAllAttendance();
		
		for (AttendanceVO attendanceVO : attendanceList) {
			AttendanceSummaryVO summaryVO = new AttendanceSummaryVO();
			StudentVO student =  studentDAO.getStudent(attendanceVO.getStudentId());
			summaryVO.setStudentId(student.getId());
			summaryVO.setAdmissionNumber(student.getAdmissionNumber());
			summaryVO.setStandard(student.getClassName());
			summaryVO.setStudentName(student.getStudentName());
			summaryVO.setSection(student.getSection());
			summaryVO.setId(attendanceVO.getId());
			summaryVO.setAfternoon(attendanceVO.getAfternoon());
			summaryVO.setFullDay(attendanceVO.getFullDay());
			summaryVO.setMorning(attendanceVO.getMorning());
			summaryVO.setNote(attendanceVO.getNote());
			summaryVO.setAttendancePercenatge(attendanceVO.getAttendancePercenatge());
		
			attendanceSummaryVOs.add(summaryVO);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllAttendance :::");
		}
		return attendanceSummaryVOs;
	}
 
	
}

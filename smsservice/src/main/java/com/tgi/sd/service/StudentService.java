package com.tgi.sd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tgi.sd.domain.BulkImportStudentDetailVO;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.TeacherVO;
import com.tgi.sd.manager.StudentManager;
import com.tgi.sd.service.base.SMSBaseService;
import com.tgi.sd.util.EndPointConstants;

@RestController
public class StudentService extends SMSBaseService  {

	private static Logger logger = Logger.getLogger(StudentService.class);

	StudentManager studentManager;
	
	public StudentManager getStudentManager() {
		return studentManager;
	}

	public void setStudentManager(StudentManager studentManager) {
		this.studentManager = studentManager;
	}

	@RequestMapping(value = EndPointConstants.SAVE_STUDENT, method = RequestMethod.POST)
	public ResponseVO saveStudent(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveStudent :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			StudentVO studentVO = (StudentVO) parseObjectFromRequest(requestVO,StudentVO.class);
			if(null != studentVO) {
				studentVO = studentManager.saveStudent(studentVO);
				responseObjectMap.put("studentVO", studentVO);
				responseVO = createSuccessResponseVO(responseObjectMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveStudent :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_STUDENT, method = RequestMethod.GET)
	public ResponseVO getStudent(@RequestParam String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudent Id:::" + id);
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			StudentVO studentVO =  studentManager.getStudent(id);
			responseObjectMap.put("studentVO", studentVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudent :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GED_VIEW_STUDENT, method = RequestMethod.GET)
	public ResponseVO getviewStudent(@RequestParam String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start viewStudent Id:::" + id);
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			StudentVO studentVO =  studentManager.getviewStudent(id);
			responseObjectMap.put("studentVO", studentVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End viewStudent :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.VIEW_STUDENT, method = RequestMethod.GET)
	public ResponseVO viewStudent(@RequestParam String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start viewStudent Id:::" + id);
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			StudentVO studentVO =  studentManager.viewStudent(id);
			responseObjectMap.put("studentVO", studentVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End viewStudent :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.UPDATE_STUDENT, method = RequestMethod.POST)
	public ResponseVO updateStudent(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateStudent :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			StudentVO studentVO = (StudentVO) parseObjectFromRequest(requestVO,StudentVO.class);
			if(null != studentVO) {
				studentVO = studentManager.updateStudent(studentVO);
				responseObjectMap.put("studentVO", studentVO);
				responseVO = createSuccessResponseVO(responseObjectMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateStudent :::");
		}
		return responseVO;	
	}	
	
	@RequestMapping(value = EndPointConstants.GET_ALL_STUDENT, method = RequestMethod.GET)
	public ResponseVO getAllStudent(@RequestParam String schoolId, int pageIndex, int pageSize) {
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllStudent");
		}
		ResponseVO responseVO = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		try {
			responseObjectsMap =  studentManager.getAllStudent(schoolId,pageIndex,pageSize);
			//responseObjectMap.put("studentList", studentList);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllStudent");
		}
		return responseVO;
	}	
	
	@RequestMapping(value = EndPointConstants.SEARCH_STUDENT, method = RequestMethod.POST)
	public ResponseVO searchStudent(@RequestParam String searchString,@RequestParam int skip,@RequestParam int pageSize) {
		
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start searchStudent");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			studentList =  studentManager.getStudentBySearchString(searchString, skip, pageSize);
			responseObjectMap.put("studentList", studentList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End searchStudent");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_STUDENTBYTDORNO, method = RequestMethod.GET)
	public ResponseVO getStudentByIdOrNo(@RequestParam String schoolId,@RequestParam String studentId) {
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentByIdOrNo");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			StudentVO studentVO =  studentManager.getStudentByIdOrNo(schoolId,studentId);
			responseObjectMap.put("StudentVO", studentVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentByIdOrNo");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_ALLSTUDENTS_BY_CLASS_AND_SECTION, method = RequestMethod.GET)
	public @ResponseBody ResponseVO getALLStudentsByClassAndSection(@RequestParam String schoolId,@RequestParam String standard,
			@RequestParam String section) {
		
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getALLStudentsByClassAndSection");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			studentList =  studentManager.getALLStudentsByClassAndSection(schoolId,standard,section);
			responseObjectMap.put("studentList", studentList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getALLStudentsByClassAndSection");
		}
		return responseVO;
	}	
	
	
	@RequestMapping(value = EndPointConstants.GET_ALLSTUDENTS_BY_CLASS_AND_SECTION_AND_GENDER, method = RequestMethod.GET)
	public @ResponseBody ResponseVO getALLStudentsByClassAndSectionAndGender(@RequestParam String schoolId,
			@RequestParam String standard, String section, String gender) {
		
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getALLStudentsByClassAndSectionAndGender");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			studentList =  studentManager.getALLStudentsByClassAndSectionAndGender(schoolId,standard,section,gender);
			responseObjectMap.put("studentList", studentList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getALLStudentsByClassAndSectionAndGender");
		}
		return responseVO;
	}	
	
	
	@RequestMapping(value = EndPointConstants.DELETE_STUDENT, method = RequestMethod.DELETE)
	public ResponseVO deleteStudent(@RequestParam String studentId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteStudent Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = studentManager.deleteStudent(studentId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteStudent Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/rest/student/bulkImportStudent", method = RequestMethod.POST)
	public ResponseVO bulkImportStudent(@RequestParam("schoolId") String schoolId,@RequestParam("classId") String classId,
			@RequestParam("sectionId") String sectionId,@RequestParam("file")MultipartFile[] file) {
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			responseObjectMap = studentManager.bulkImportStudentDetails(schoolId,classId,sectionId,file);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch(Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		return responseVO;	
	}
	
	
	@RequestMapping(value = EndPointConstants.UPDATE_BULKIMPORTSTUDENT, method = RequestMethod.POST)
	public ResponseVO updateBulkImportStudent(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateBulkImportStudent :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			BulkImportStudentDetailVO bulkImportStudentDetailVO = (BulkImportStudentDetailVO) parseObjectFromRequest(requestVO,BulkImportStudentDetailVO.class);
			if(null != bulkImportStudentDetailVO) {
				bulkImportStudentDetailVO = studentManager.updateBulkImportStudent(bulkImportStudentDetailVO);
				responseObjectMap.put("BulkImportStudentDetailVO", bulkImportStudentDetailVO);
				responseVO = createSuccessResponseVO(responseObjectMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateBulkImportStudent :::");
		}
		return responseVO;	
	}
	
	@RequestMapping(value = EndPointConstants.GET_UPLOADSTUDENTS_BYCLASSSECTION, method = RequestMethod.GET)
	public @ResponseBody ResponseVO getUploadStudentsByClassAndSection(@RequestParam String schoolId,
			@RequestParam String classId, String sectionId, String batchId) {
		
		List<BulkImportStudentDetailVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getUploadStudentsByClassAndSection");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			studentList =  studentManager.getUploadStudentsByClassAndSection(schoolId,classId,sectionId,batchId);
			responseObjectMap.put("studentList", studentList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getUploadStudentsByClassAndSection");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_STUDENT_BY_MOBILENO_AND_SCHOOLID, method = RequestMethod.GET)
	public ResponseVO getStudentsByMobileNoAndSchoolId(@RequestParam String schoolId,@RequestParam String mobileNo) {
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentsByMobileNoAndSchoolId");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			List<StudentVO> studentVOs =  studentManager.getStudentsByMobileNoAndSchoolId(schoolId,mobileNo);
			responseObjectMap.put("StudentVOs", studentVOs);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentsByMobileNoAndSchoolId");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_STUDENTS_ALLOCATED_TEACHER, method = RequestMethod.GET)
	public @ResponseBody ResponseVO getStudentsAllocateTeacher(@RequestParam String studentId,String schoolId) {
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentsAllocateTeacher");
			
			}
		ResponseVO responseVO = null;
		
		try {
			List<TeacherVO> teacherVOs =  studentManager.getStudentsAllocateTeacher(studentId,schoolId);
			Map<String,Object> responseObjectMap = new HashMap<String,Object>(); 
			responseObjectMap.put("TeacherVOs", teacherVOs);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentsAllocateTeacher");
		}
		return responseVO;
	}	
	
	
	
}

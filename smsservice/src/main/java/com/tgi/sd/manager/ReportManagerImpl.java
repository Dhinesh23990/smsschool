/**
 * 
 */
package com.tgi.sd.manager;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.ParentDAO;
import com.tgi.sd.dao.StudentDAO;
import com.tgi.sd.dao.TeacherDAO;
import com.tgi.sd.dao.UserDAO;
import com.tgi.sd.domain.UserCountVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public class ReportManagerImpl implements ReportManager {
	
	private static Logger logger = Logger.getLogger(ReportManagerImpl.class);

	StudentDAO studentDAO;

	UserDAO userDAO;
	
	TeacherDAO teacherDAO;
	
	ParentDAO parentDAO;

	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}


	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}


	public UserDAO getUserDAO() {
		return userDAO;
	}


	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}


	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}


	public ParentDAO getParentDAO() {
		return parentDAO;
	}


	public void setParentDAO(ParentDAO parentDAO) {
		this.parentDAO = parentDAO;
	}


	public UserCountVO getUsersCount() throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getUsersCount Id:::");
		}
		
		UserCountVO userCountVO =  new UserCountVO();
		//userCountVO.setAdminCount(userDAO.getAllListCountEntity("schoolId"));//TODO CODE
		userCountVO.setStudentCount(studentDAO.getStudentCount().toString());
		userCountVO.setParentCount(parentDAO.getAllParent().size()+"");
	//	userCountVO.setTeacherCount(teacherDAO.getAllTeacher().size()+"");

		if (logger.isDebugEnabled()) {
			logger.debug("End getUsersCount :::");
		}
		return userCountVO;
	}
	
	 
}

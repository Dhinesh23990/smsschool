package com.tgi.sd.util;

/**
 * 
 * @author SGSAuthour
 * 
 */
public class EndPointConstants {

	private EndPointConstants() {
	}

	public static final String SAVE_EMPLOYEE = "/rest/employee/saveEmployee";
	public static final String GET_EMPLOYEE = "/rest/employee/getEmployee";
	public static final String SAVE_STUDENT = "/rest/student/saveStudent";
	public static final String GET_STUDENT = "/rest/student/getStudent";
	public static final String VIEW_STUDENT = "/rest/student/viewStudent";
	public static final String GED_VIEW_STUDENT = "/rest/student/getviewStudent";
	public static final String UPDATE_STUDENT = "/rest/student/updateStudent";
	public static final String GET_ALL_STUDENT = "/rest/student/getAllStudent";
	public static final String SEARCH_STUDENT = "/rest/student/searchStudent";
	public static final String GET_STUDENTBYTDORNO = "/rest/student/getStudentByIdOrNo";
	public static final String GET_ALLSTUDENTS_BY_CLASS_AND_SECTION = "/rest/student/getAllStudentsByClassAndSection";
	public static final String GET_ALLSTUDENTS_BY_CLASS_AND_SECTION_AND_GENDER = "/rest/student/getAllStudentsByClassAndSectionAndGender";
	public static final String GET_UPLOADSTUDENTS_BYCLASSSECTION = "/rest/student/getUploadStudentsByClassAndSection";
	public static final String UPDATE_BULKIMPORTSTUDENT = "/rest/student/updateBulkImportStudent";
	public static final String DELETE_STUDENT = "/rest/student/deleteStudent";
	public static final String GET_STUDENT_BY_MOBILENO_AND_SCHOOLID = "/rest/student/getStudentsByMobileNoAndSchoolId";
	public static final String GET_STUDENTS_ALLOCATED_TEACHER = "/rest/student/getStudentsAllocatedTeacher";
	
	
	public static final String SAVE_PARENT = "/rest/parent/saveParent";
	public static final String GET_PARENT = "/rest/parent/getParent";
	public static final String UPDATE_PARENT = "/rest/parent/updateParent";
	public static final String GET_ALL_PARENTT = "/rest/parent/getAllParent";
	public static final String REGISTER_PARENT_BY_MOBILENO = "/parent/registerParentByMobileNo";
	public static final String GET_SCHOOL_LIST_BY_PARENT_MOBILENO = "/rest/parent/getSchoolListByParentMobileNo";
	
	public static final String SAVE_TEACHER= "/rest/teacher/saveTeacher";
	public static final String GET_TEACHER = "/rest/teacher/getTeacher";
	public static final String VIEW_TEACHER = "/rest/teacher/viewTeacher";
	public static final String UPDATE_TEACHER = "/rest/teacher/updateTeacher";
	public static final String GET_ALL_TEACHER = "/rest/teacher/getAllTeacher";
	public static final String DELETE_TEACHER = "/rest/teacher/deleteTeacher";
	public static final String GET_ALL_TEACHER_BY_GENDER = "/rest/teacher/getAllTeacherByGender";
	
	public static final String SAVE_ADMIN = "/rest/admin/saveAdmin";
	public static final String GET_ADMIN = "/rest/admin/getAdmin";
	public static final String VIEW_ADMIN = "/rest/admin/viewAdmin";
	public static final String UPDATE_ADMIN = "/rest/admin/updateAdmin";
	public static final String GET_ALL_ADMIN = "/rest/admin/getAllAdmin";
	public static final String DELETE_ADMIN = "/rest/admin/deleteAdmin";

	public static final String GET_USER_COUNT = "/rest/report/getUsersCount";

	public static final String CHANGE_PASSWORD = "/rest/user/changePassword";
	public static final String RESET_PASSWORD = "/rest/user/resetPassword";
	public static final String UPDATE_USER = "/rest/user/updateUser";
	public static final String GET_USER = "/rest/user/getUser";
	public static final String GET_USER_PROFILE_ENHANCE = "/rest/user/getUserProfileEnhance";
	
	public static final String SAVE_SMSTEMPLATE= "/rest/smstemplate/saveSmsTemplate";
	public static final String GET_SMSTEMPLATE = "/rest/smstemplate/getSmsTemplate";
	public static final String UPDATE_SMSTEMPLATE = "/rest/smstemplate/updateSmsTemplate";
	public static final String GET_ALL_SMSTEMPLATE = "/rest/smstemplate/getAllSmsTemplate";
	public static final String DELETE_SMSTEMPLATE = "/rest/smstemplate/deleteSmsTemplate";
	
	public static final String SAVE_ATTENDANCE = "/rest/attendance/saveAttendance";
	public static final String SAVE_BULK_ATTENDANCE = "/rest/attendance/saveBulkAttendance";
	public static final String UPDATE_ATTENDANCE = "/rest/attendance/updateAttendance";
	public static final String GET_ALL_ATTENDANCE = "/rest/attendance/getAllAttendance";

	public static final String SAVE_CONFIGURATION = "/rest/configuration/saveConfiguration";
	public static final String GET_CONFIGURATION= "/rest/configuration/getConfiguration";
	public static final String UPDATE_CONFIGURATION = "/rest/configuration/updateConfiguration";
	public static final String GET_ALL_CONFIGURATION = "/rest/configuration/getAllConfiguration";
	public static final String DELETE_CONFIGURATION = "/rest/configuration/deleteConfiguration";
	public static final String GET_STUDENTS_BY_CLASS_AND_SECTION = "/rest/attendance/getStudentsByClassAndSection";
	public static final String GET_STUDENTS_BY_CLASS_SECTION_DATE = "/rest/attendance/getAllStudentsByClassAndSectionAndDate";
	public static final String GET_STUDENTS_BY_STUDENTID_DATE = "/rest/attendance/getAllStudentsByStudentIdAndDate";
	
	public static final String SAVE_TEACHER_ATTENDANCE = "/rest/staffAttendance/saveAttendance";
	public static final String SAVE_TEACHER_BULK_ATTENDANCE = "/rest/staffAttendance/saveBulkAttendance";
	public static final String UPDATE_TEACHER_ATTENDANCE = "/rest/staffAttendance/updateAttendance";
	public static final String GET_ALL_TEACHER_ATTENDANCE = "/rest/staffAttendance/getAllAttendance";
	public static final String GET_TEACHERS_BY_CLASS_AND_SECTION = "/rest/staffAttendance/getTeachersByClassAndSection";
	public static final String GET_TEACHERS_BY_CLASS_SECTION_DATE = "/rest/staffAttendance/getAllTeachersByFromToDate";
	
	public static final String SEND_SMS = "/rest/sms/sendSMS";
	public static final String GET_ALL_SMS_LOG = "/rest/sms/getAllSmsLog";
	public static final String GET_ALL_SMS_HISTORY_BY_LOG_ID = "/rest/sms/getAllSmsHistoryBySmsLogId";
	
}

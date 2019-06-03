package com.tgi.sd.util;

public class SMSConstants {

	// Rest Request key
	public static final String REST_REQUEST_KEY = "jsonRequest";

	public static final String FAILED_REASON = "MANDATORY FIELDS ARE MISSING";
	public static final String PIPE_SEPARATOR = "|";
	public static final String STATUS_ACTIVE = "ACT";
	public static final String STATUS_IN_ACTIVE = "INACT";
	public static final String HEADER_TOKEN_KEY = "authorization";
	public static final String HEADER_AUTH_TYPE_KEY = "authType";
	public static final String HEADER_BASIC_AUTH = "BASIC";
	public static final String HEADER_BASIC_TOKEN = "TOKEN";
	public static final String REQUEST_PARAM_TYPE = "type";
	//created_by and updated_by user
	public static final String CREATED_BY = "BATCH_USER";
	public static final String UPDATED_BY = "BATCH_USER";

	public static final String SMS_GATEWAY_USERID = "sms.gateway.user";
	public static final String SMS_GATEWAY_PASSWORD = "sms.gateway.password";
	public static final String SMS_GATEWAY_SENDERID = "sms.gateway.senderId";
	public static final String SMS_PARENT_REG_MESSAGE = "sms.parent.registration.message";
	public static final String SMS_RESET_PASSWORD_MESSAGE = "sms.reset.password.message";
	public static final String SMS_CHANGE_PASSWORD_MESSAGE = "sms.change.password.message";
	
	public static final String FILEUPLOAD_COMPLETED = "COMPLETED";
	public static final String FILEUPLOAD_REASON = "STUDENT ALREADY EXITS";
	public static final String FILEUPLOAD_FAILURE = "FAILURE";
	public static final String STATUS_SUCCESS = "SUCCESS";

	public static final String EXT = "EXT";
	
	public static final String ADMIN="ADMIN";
	public static final String TEACHER="TEACHER";
	public static final String SCHOOLADMIN="roleSchoolAdmin";
	public static final String PARENT="PARENT";
	public static final String STUDENT="STUDENT";

	public static final String REQUEST_PARAM_USER_NAME = "userName";

	public static final String REQUEST_PARAM_PASSWORD = "password";
	
	public static final String REQUEST_ROLE_ID = "roleId";
	
	public static final String REQUEST_EMAIL_ADDRESS = "emailAddress";
	
	public final static String FOLDER_SEPARATOR = "/";

	public static final String TYPE = "type";
	
	public static final String ROLE_ID = "roleId";
	
	public static final String SCHOOL_ID = "schoolId";
	
	public static final String STUDENT_ID = "studentId";
	
	public static final String LEAVEMASTER_ID = "leaveMasterId";
	
	public static final String PARENT_NUMBER_ONE = "parentMobileNumber1";
	
	public static final String CLASS_ID = "classId";
	
	public static final String CLASS_TEACHER_ID = "classTeacherId";
	
	public static final String BATCH_ID = "batchId";
	
	public static final String TERM = "term";
	
	public static final String MEDIUM_ID = "mediumId";
	
	public static final String SECTION_ID = "sectionId";
	
	public static final String FEE_CATEGORY = "feeCategory";

	public static final String USER_NAME = "userName";
	
	public static final String CONTACTEMAIL = "contactEmail";

	public static final String TOKEN = "token";

	public static final String MONGO_ID = "_id";

	public static final String CREATED_ON = "createdDate";
	
	public static final String DAY = "day";

	public static final String TOTAL_RECORDS = "totalRecords";

	public static final String COURSE_ALREADY_EXIST = "COURSE ALREADY EXIST" ;
	
	public static final String Education_ALREADY_EXIST = "EDUCATION Name ALREADY EXIST" ;
	
	public static final String BATCHNAME_ALREADY_EXIST = "BATCH NAME ALREADY EXIST" ;
	
	public static final String FEECONFIG_ALREADY_EXIST = "FEE CONFIGURATION ALREADY EXIST" ;
	
	public static final String FEE_CONFIG_EMPTY = "FEE CONFIGURATION EMPTY";
	
	public static final String PAYMENT_PAID = "PAYMENT ALREADY PAID STUDENT";
	
	public static final String BATCHCONFIG_ALREADY_EXIST = "BATCH CONFIG ALREADY EXIST" ;

	public static final String COURSE_NAME = "courseName";
	
	public static final String Education_NAME = "educationName";

	public static final String BATCH_NAME = "batchName";
	
	public static final String BATCH_CODE = "batchCode";

	public static final String BLOOD_GROUP_NAME = "bloodgroupName";

	public static final String CASTE_NAME = "casteName";
	
	public static final String SCHOOL_NAME = "schoolName";

	public static final String CITY_NAME = "cityName";

	public static final String CLASS_NAME = "className";

	public static final String LANGUAGE_NAME = "languageName";

	public static final String COUNTRY_NAME = "countryName";

	public static final String COUNTRY = "countryId";
	
	public static final String STATE = "stateId";

	public static final String MEDIUM_NAME = "mediumName";

	public static final String MOTHER_TONGUE_NAME = "motherTongueName";

	public static final String NATIONALITY_NAME = "nationalityName";

	public static final String RELIGION_NAME = "religionName";
	
	public static final String GENDER = "gender";

	public static final String SECTION_NAME = "sectionName";

	public static final String STATE_NAME = "stateName";

	public static final String SUBJECT_NAME = "subjectName";

	public static final String SUBJECT_TYPE_NAME = "subjectType";

	public static final String BLOOD_GROUP_ALREADY_EXIST = "BloodGroup Already Exists";

	public static final String CASTE_ALREADY_EXISTS = "Caste Already Exists";

	public static final String CITY_ALREADY_EXISTS = "City Already Exists";
	
	public static final String CLASS_ALREADY_EXISTS = "Class Already Exists";
	
	public static final String COUNTRY_ALREADY_EXISTS = "Country Already Exists";
	
	public static final String LANAGUAGE_ALREADY_EXISTS = "Language Already Exists";
	
	public static final String MEDIUM_ALREADY_EXISTS = "Medium Already Exists";
	
	public static final String MOTHERTONGUE_ALREADY_EXISTS = "MotherTongoue Already Exists";
	
	public static final String NATIONALITY_ALREADY_EXISTS = "Nationality Already Exists";
	
	public static final String RELIGION_ALREADY_EXISTS = "Religion Already Exists";
	
	public static final String GENDER_ALREADY_EXISTS = "Gender Already Exists";
	
	public static final String SECTION_ALREADY_EXISTS = "SECTION Already Exists";
	
	public static final String STATE_ALREADY_EXISTS = "State Already Exists";
	
	public static final String STATE_LIST_EMPTY = "State List is Empty";
	
	public static final String CITY_LIST_EMPTY = "City List is Empty";
	
	public static final String SUBJECT_ALREADY_EXISTS = "Subject Already Exists";
	
	public static final String SUBJECTTYPE_ALREADY_EXISTS = "Subject Type Already Exists";

	public static final String GRADE_NAME = "gradeName";

	public static final String GRADENAME_ALREADY_EXIST = "Grade Name Already Exists";

	public static final String EXAM_NAME = "examName";

	public static final String EXAMSESSION_NAME = "examSessionName";

	public static final String EXAM_SESSION_ALREADY_EXISTS = "Exam Session Already Exists";
	
	public static final String EXAM_ALREADY_EXISTS = "Exam Already Exists";

	public static final String FEE_TYPE_ALREADY_EXISTS = "Fee Type Already Exists.";

	public static final String FEE_TYPE = "feeType";

	public static final String FEE_COMPONENT = "feeComponent";

	public static final String CONCESSION_NAME = "concessionName";

	public static final String PAY_NAME = "payName";
	
	public static final String SCHOOL_ACTIVE_STATUS = "ACTIVE";
	
	public static final String SCHOOL_INACTIVE_STATUS = "INACTIVE";
	
	public static final String SCHOOL_STATUS = "status";

	public static final String PAY_NAME_ALREADY_EXISTS = "Pay Name Already Exists.";
	
	public static final String STUDENT_DETAILS_EMPTY = "Student List Is Empty.";
	
	public static final String SEND_MESSAGE_NETWORK_ISSUE = "NETWORK ISSUE IS NOT SEND MESSAGE.";
	
	public static final String SEND_SMS_DELIVERED = "DELIVERED";
	
	public static final String SEND_SMS_UNDELIVERED = "UNDELIVERED";
	
	public static final String SEND_SMS_SENDING = "SENDING";
	
	public static final String SEND_SMS_QUEUE = "QUEUE";
	
	public static final String SEND_SMS_FAILURE = "FAILURE";

	
	public static final String DASHBOARD_MALE = "Male";
	
	public static final String DASHBOARD_FEMALE = "Female";
	
	public static final String DASHBOARD_TRANSGENDER = "Transgender";

	public static final String FEE_COMPONENT_ALREADY_EXISTS = "Fee Component Already Exists.";

	public static final String CONCESSION_NAME_ALREADY_EXISTS = "Concession Name Already Exists.";

	public static final String SCHOOL_ALREADY_EXISTS = "School Already Exists";
	
	public static final String USERNAME_ALREADY_EXISTS = "User Name Already Exists";
	
	public static final String EMAILID_ALREADY_EXISTS = "Email Id Already Exists";
	
	public static final String STUDENT_IDS_EMPTY = "Student Details are Empty";
	
	public static final String STUDENT_ADMISSIONNO_EXITS = "STUDENT ADMISSION NUMBER EXISTS";
	
	public static final String STUDENT_ALREADY_EXITS = "STUDENT ALREADY EXISTS";
	
	public static final String TEACHER_IDS_ARE_EMPTY = "Teacher Details are Empty";
	
	public static final String ALL_TEACHER_EMPTY = "All Teacher Empty";
	
	public static final String ALL_STUDENT_EMPTY = "All Student Empty";
	
	public static final String ALL_ADMIN_EMPTY = "Admin Details are Empty";

	public static final Object SCHOOL_STUDENT = "Student";
	
	public static final Object SCHOOL_PARENT1 = "Parent1";
	
	public static final Object SCHOOL_PARENT2 = "Parent2";
	
	public static final Object SCHOOL_ADMIN = "Admin";
	
	public static final Object STUDENT_WISE = "STUDENT_WISE";
	
	public static final Object CLASS_WISE = "CLASS_WISE";
	
	public static final Object SECTION_WISE = "SECTION_WISE";
	
	public static final Object ALL_STUDENTS = "ALL_STUDENTS";
	
	public static final Object INDIVIDUAL_STUDENTS = "INDIVIDUAL_STUDENTS";
	
	public static final Object ALL_TEACHERS = "ALL_TEACHERS";
	
	public static final Object INDIVIDUAL_TEACHERS = "INDIVIDUAL_TEACHERS";
	
	public static final Object ALL_ADMIN = "ALL_ADMIN";

	public static final Object SCHOOL_TEACHER = "Teacher";

	public static final String LEAVE_NAME_ALREADY_EXISTS = "Leave Name Already Exists.";

	public static final String PERIOD_NAME_ALREADY_EXISTS = "Period Name Already Exists.";
	
	public static final String EMAIL_PURPOSE_FORGET_CONTENT = "Your password has been reset & your Account Details are,";
	
	public static final String EMAIL_PURPOSE_LOGIN_CONTENT = "Thank you for Joining a your account at SchoolsDiary";
	
	public static final String EMAIL_HEADER_CONTENT = "Your Login Account details";
	
	public static final String EMAIL_FORGET_HEADER_CONTENT = "Your Forget Password details";
	
	public static final Object SUCCESS_MSG_RESET_EMAIL = "Your Password Successfully Reset";
	
	public static final String EMAIL_SUBJECT_CONTENT = "Schools Diary Login Details";
	
	public static final String EMAIL_FORGET_SUBJECT_CONTENT = "Schools Diary Forget Password Details";
	
	public static final String EMAIL_ID = "schoolsdiary17@gmail.com";
	
	public static final String EMAIL_PASSWORD = "Diary2017";
	
	public static final String EMAIL_STARTTLS_ENABLE = "true";
	
	public static final String EMAIL_SMTP_HOST = "smtp.gmail.com";
	
	public static final String EMAIL_SMTP_PORT = "587";
	
	public static final String MAIL_TYPE = "REGISTER";
	
	public static final String MAIL_FORGET_TYPE = "FORGET_PASSWORD";

	public static final String LEAVE_PERIOD_ALREADY_ASSIGNED = "Period Period Already Assigned.";

	public static final String STAFF_ID = "staffId";

	public static final String LEAVE_PERIOD_ID = "leavePeriodId";
	
	public static final String TEACHER_ALREADY_EXISTS = "TEACHER ALREADY EXISTS";
	
	public static final String TEACHER_EMPLOYEENO_ALREADY_EXISTS = "TEACHER EMPLOYEE NUMBER EXISTS";
	
	public static final String ADMIN_EMPLOYEENO_ALREADY_EXISTS = "ADMIN EMPLOYEE NUMBER EXISTS";

	public static final String EVENT_ALREADY_EXISTS = "Event Already Exists";

	public static final String STUDENT_IDS = "schoolIds";

	public static final String CLASS_IDS = "classIds";

	public static final String CLASS_LIST_EMPTY = "classList";

	public static final String  SECTION_LIST_EMPTY = "sectionList";
	
	public static final String  PAYMENT_STATUS = "Open";
}

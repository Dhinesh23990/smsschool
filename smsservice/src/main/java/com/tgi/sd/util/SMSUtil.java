package com.tgi.sd.util;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.UUID;

import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.TokenVO;
import com.tgi.sd.domain.UserVO;


public class SMSUtil {

	public static UserVO populateUserVO(String firstName, String userId, String password,String userAppType,String schoolId) {
		UserVO user = new UserVO();
	//	user.setFirstName(firstName);
		user.setUserName(firstName);
		user.setPassword(SMSUtil.getMD5(password));
		user.setContactEmail(userId);
		//user.setPasswordExpDate(new Date(2020, 01, 01));
		//user.setLoginId(userId);
		user.setId(UUID.randomUUID().toString());
		user.setSchoolId(schoolId);
		//user.setUserType(Constants.EXT);
		//user.setUserAppType(userAppType); 
		user.setRoleId(userAppType);
		return user;
	}
	
	public static UserVO populateUserVO(StudentVO studentVO) {
		UserVO user = new UserVO();
		user.setFirstName(studentVO.getParentName());
		user.setUserName(studentVO.getParentMobileNumber1());
		user.setPassword(SMSUtil.getMD5("123456"));
		user.setContactEmail(studentVO.getParentEmailId());
		user.setId(UUID.randomUUID().toString());
		user.setRoleId(SMSConstants.PARENT);
		user.setAddress1(studentVO.getAddressLine1());
		user.setAddress2(studentVO.getAddressLine2());
		user.setUserStatusCode(SMSConstants.SCHOOL_ACTIVE_STATUS);
		TokenVO token = SMSUtility.createTokenDetails(user.getId());
		user.setTokenVO(token);
		return user;
	}

	public static String getMD5(String data) {
		String result = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(data.getBytes(Charset.forName("UTF-8")));
			result = String.format(Locale.ROOT, "%032x", new BigInteger(1, md.digest()));
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
		return result;
	}
}

package com.tgi.sd.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to validate the given field.
 *
 * @author SSAuthour
 *
 */
public class ValidationUtil {

	public static final String VALID_EMAIL_REGEXP = "[-a-zA-Z0-9!#$%&'*+/=?^_'{|}~]+(?:\\.[-a-zA-Z0-9!#$%&'*+/=?^_'{|}~]+)*@([a-zA-Z0-9](?:[-a-zA-Z0-9]*[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[-a-zA-Z0-9]*[a-zA-Z0-9])?)*(?:\\.[a-zA-Z0-9]{2,}+)|\\[(?:\\d{1,3}(?:\\.\\d{1,3}){3}|IPv6:[0-9A-Fa-f:]{4,39})\\])";

	private static boolean validateString(String exp, String text) {
		Pattern pattern = Pattern.compile(exp);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}

	public static boolean validatePhoneNumber(String phoneNumber) {
		String phoneNumberPattern = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
		return phoneNumber.matches(phoneNumberPattern);
	}

	/**
	 * Validate the Integer Field. validation rule : compare the given Integer
	 * value with less than that of 9999 valid is 4 digit without decimal point
	 *
	 * @param level
	 * @return boolean
	 */
	public static boolean validateIntegerValue(Integer level) {

		Boolean isvalid = Boolean.FALSE;

		// if(Double.compare(9999999999.99, doubleValue)!=-1)
		if (Integer.compare(999, level) != -1 && level >= 0) {
			isvalid = Boolean.TRUE;
		}
		return isvalid;

	}

	// Nagapriya edited this code for special chars <>,{},[]
	public static boolean validateforAlphanumericwithFewSpecialcharacter(
			String text) {
		// String alphaOnlyExp ="[a-zA-Z0-9\\s-*&()!@#$%^|\":;?/_+=.,'~`]+";
		String alphaOnlyExp = "[a-zA-Z0-9\\s-*&()!@#$%^|\":;?/_+=.,'~`\\\\]+";

		if (!validateString(alphaOnlyExp, text)) {

			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		//String phonePattern="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
		//String phoneNo="987654321";System.out.println(phoneNo.matches(phonePattern));
		
		System.out.println(validateforAlphanumericwithFewSpecialcharacter("Selva()!@#$%^|\""));
	}
}

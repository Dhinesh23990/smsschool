package com.tgi.sd.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tgi.sd.exception.SMSBusinessException;

public class DateUtil {
	public static Timestamp changeDateFormat(String strDate)
			throws SMSBusinessException {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Date currDate;
		Date sqlDate = null;
		Timestamp timeStamp = null;
		try {
			currDate = dateFormat.parse(strDate);
			DateFormat sqlDateFormat = new SimpleDateFormat(
					"yyyy-dd-MM HH:mm:ss");
			sqlDate = sqlDateFormat.parse(sqlDateFormat.format(currDate));
			timeStamp = new Timestamp(sqlDate.getTime());
		} catch (ParseException e) {
			throw new SMSBusinessException("Invalid date format");
		}
		return timeStamp;
	}

	public static Timestamp getCurrentDateTime() {

		java.util.Date date = new java.util.Date();
		Timestamp currTimeStamp = new Timestamp(date.getTime());
		return currTimeStamp;

	}

}

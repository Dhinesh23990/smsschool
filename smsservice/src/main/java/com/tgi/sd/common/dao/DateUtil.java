package com.tgi.sd.common.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;

/**
 * The utility class for handling Date related operations.
 * 
 * @author Jegatheesan
 * */
public class DateUtil {

  private static final String UTC = "UTC";

  public static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd hh:mm:ss.SSS";

  private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

  private static final String DEFAULT_TIMESTAMP_FORMAT_24HOURS = "yyyy-MM-dd HH:mm:ss.SSS";

  public static final Timestamp getCurrentTimeStamp() {
    SimpleDateFormat dateFormatori = new SimpleDateFormat(DEFAULT_TIMESTAMP_FORMAT);
    TimeZone gmtTimeZone = TimeZone.getTimeZone(UTC);
    dateFormatori.setTimeZone(gmtTimeZone);
    Date date = GregorianCalendar.getInstance(gmtTimeZone).getTime();
    return java.sql.Timestamp.valueOf(dateFormatori.format(date));
  }

  public static final Timestamp getCurrentTimeStamp24HoursFormat() {
    SimpleDateFormat dateFormatori = new SimpleDateFormat(DEFAULT_TIMESTAMP_FORMAT_24HOURS);
    TimeZone gmtTimeZone = TimeZone.getTimeZone(UTC);
    dateFormatori.setTimeZone(gmtTimeZone);
    Date date = GregorianCalendar.getInstance(gmtTimeZone).getTime();
    return java.sql.Timestamp.valueOf(dateFormatori.format(date));
  }

  public static void main(String[] s) {
    /*
     * Calendar c = GregorianCalendar.getInstance(TimeZone.getTimeZone(UTC));
     * c.setTimeInMillis(getDate().getTime());
     * 
     * 
     * TimeZone tz = TimeZone.getTimeZone(UTC); Calendar mbCal = new
     * GregorianCalendar(TimeZone.getTimeZone(UTC)); mbCal.setTimeInMillis(getDate().getTime());
     * 
     * Calendar cal = Calendar.getInstance(); cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));
     * cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH)); cal.set(Calendar.DAY_OF_MONTH,
     * mbCal.get(Calendar.DAY_OF_MONTH)); cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));
     * cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE)); cal.set(Calendar.SECOND,
     * mbCal.get(Calendar.SECOND)); cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));
     * 
     * 
     * System.out.println(cal.getTime()); System.out.println(getDate());
     */
    Date dt = new Date();
    // System.out.println(getFormattedDate(dt, DEFAULT_TIMESTAMP_FORMAT));
  }

  public static final Date getDate() {
    return getGMTDate();
  }

  private static final Date getGMTDate() {
    TimeZone gmtTimeZone = TimeZone.getTimeZone(UTC);
    return getGMTDate(GregorianCalendar.getInstance(gmtTimeZone).getTime());
  }

  public static final Date getGMTDate(Date date) {
    SimpleDateFormat dateFormatori = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    TimeZone gmtTimeZone = TimeZone.getTimeZone(UTC);
    dateFormatori.setTimeZone(gmtTimeZone);
    return java.sql.Date.valueOf(dateFormatori.format(date));
  }

  /**
   * @param date1
   * @param date2
   * @return int
   */
  public static int compareDateWithoutTime(Date date1, Date date2) {
    if (date1 != null && date2 != null) {
      SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
      Date dateToCompare1 = null;
      Date dateToCompare2 = null;

      try {
        dateToCompare1 = sdf.parse(sdf.format(date1));
        dateToCompare2 = sdf.parse(sdf.format(date2));
      } catch (ParseException e) {
        dateToCompare1 = date1;
        dateToCompare2 = date2;
      }

      return dateToCompare1.compareTo(dateToCompare2);
    }
    return 0;
  }

  public static String fomatDate(Date date) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
    return dateFormat.format(date);
  }

  /**
   * Return the end date for an effective date passed.
   * 
   * When we need create a new version of a record we set the end date of the old record as one day less than
   * effective date of the new record End Date = Eff Date - 1
   * 
   * @return endDate The returned end date will be a new instance of date and will be represented in UTC
   *         timezone.
   * */
  public static Date getEndDate(Date effectiveDate) {
    effectiveDate = getGMTDate(effectiveDate);
    Date endDate = DateUtils.addDays(effectiveDate, -1);
    return endDate;
  }

  /**
   * @param noOfDaysAfterOrBefore
   * @return Date
   */
  public static Date getDateAfterOrBefore(int noOfDaysAfterOrBefore) {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DATE, noOfDaysAfterOrBefore);
    return c.getTime();
  }

  /**
   * @param date1
   * @param noOfDaysAfterOrBefore
   * @return Date
   */
  public static Date getDateAfterOrBefore(Date date1, int noOfDaysAfterOrBefore) {
    Calendar c = Calendar.getInstance();
    c.setTime(date1);
    c.add(Calendar.DATE, noOfDaysAfterOrBefore);
    return c.getTime();
  }

  public static Date addDays(Date date, int days) {
    long secs = date.getTime();
    secs += days * 24 * 60 * 60 * 1000;
    return new Date(secs);
  }

  public static int daysBetween(Date d1, Date d2) {
    return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
  }

  public static final String getFormattedDate(Date date, String dformat) {
    SimpleDateFormat dateFormatori = new SimpleDateFormat(dformat);

    return dateFormatori.format(date);
  }

}

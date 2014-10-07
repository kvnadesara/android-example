package com.digicorp.android.researchsamples.ex4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
	
	public static final String DB_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DB_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String COMMENT_DATE_FORMAT = "EEE, dd MMM 'at' HH:mm";
	public static final String MILESTONE_DATE_FORMAT = "EEE, dd MMM yyyy";
	public static final String DD_MM_YYYY = "dd-MM-yyyy";
	public static final String TIME_ENTRY_FORMAT = "MMM dd";
	
//	private static final SimpleDateFormat dateFormatWeekDay = new SimpleDateFormat("EEEE");
//	private static final SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM");
//	private static final SimpleDateFormat dateFormatLong = new SimpleDateFormat("EEE, dd-MMM-yy");
//	private static final SimpleDateFormat dateFormatLongTime = new SimpleDateFormat("EEE, dd-MMM-yy HH:mm");
//	private static final SimpleDateFormat dateFormatShort2 = new SimpleDateFormat("d MMMM yyyy");
//	private static final SimpleDateFormat dateFormatShort = new SimpleDateFormat("dd-MM-yyyy");
//	private static final SimpleDateFormat dateFormatDB = new SimpleDateFormat("yyyy-MM-dd");
//	private static final SimpleDateFormat dateFormatDBTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//	private static final SimpleDateFormat dateFormatDMY = new SimpleDateFormat("dd/MM/yyyy");
//	private static final SimpleDateFormat dateFormatDMYTime = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//	private static final SimpleDateFormat dateFormatTime_HH_MM = new SimpleDateFormat("HH:mm");

//	public static enum DateFormatEnum {
//		WEEK_DAY, 
//		MONTH, 
//		LONG_DATE, 
//		SHORT_DATE1, 
//		SHORT_DATE2, 
//		DB_FORMAT, 
//		DDMMYYYY, 
//		DBTIME_FORMAT, 
//		DDMMYYYY_Time,
//		LONG_DATE_TIME,
//		TIME_HH_MM
//	}

//	private static final HashMap<DateFormatEnum, SimpleDateFormat> dateFormatMap = new HashMap<DateFormatEnum, SimpleDateFormat>();
//
//	static {
//		dateFormatMap.put(DateFormatEnum.LONG_DATE, dateFormatLong);
//		dateFormatMap.put(DateFormatEnum.SHORT_DATE2, dateFormatShort2);
//		dateFormatMap.put(DateFormatEnum.DB_FORMAT, dateFormatDB);
//		dateFormatMap.put(DateFormatEnum.WEEK_DAY, dateFormatWeekDay);
//		dateFormatMap.put(DateFormatEnum.MONTH, dateFormatMonth);
//		dateFormatMap.put(DateFormatEnum.SHORT_DATE1, dateFormatShort);
//		dateFormatMap.put(DateFormatEnum.DDMMYYYY, dateFormatDMY);
//		dateFormatMap.put(DateFormatEnum.DDMMYYYY_Time, dateFormatDMYTime);
//		dateFormatMap.put(DateFormatEnum.DBTIME_FORMAT, dateFormatDBTime);
//		dateFormatMap.put(DateFormatEnum.LONG_DATE_TIME, dateFormatLongTime);
//		dateFormatMap.put(DateFormatEnum.TIME_HH_MM, dateFormatTime_HH_MM);
//	}
	
//	static {
//		//DB_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
//		DB_DATE_TIME_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
//		//COMMENT_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
//	}

	/**
	 * Parse date to specified format
	 * 
	 * @param dateFormat
	 *            - date format to parse from
	 * @param date
	 *            - date to parse
	 * @return Date if date is in give format, null otherwise
	 */
	public static Date parseDate(String dateFormat, String date) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		if(dateFormat.equalsIgnoreCase(DB_DATE_TIME_FORMAT))
			format.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Format date to give format
	 * 
	 * @param dateFormat
	 *            - date format to convert to
	 * @param date
	 *            - date to format
	 * @return formatted date as string into specified format
	 */
	public static String formatDate(String dateFormat, Date date) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		if(dateFormat.equalsIgnoreCase(DB_DATE_TIME_FORMAT))
			format.setTimeZone(TimeZone.getTimeZone("GMT"));
		return format.format(date);
	}

	/**
	 * Convert date to given format format
	 * 
	 * @param from
	 *            - current format of given date
	 * @param to
	 *            - format to convert to
	 * @param date
	 *            - date to convert
	 * @return formatted date as string to specified format
	 */
	public static String convertDateToFormat(String from, String to, String date) {
		SimpleDateFormat fromFormat = new SimpleDateFormat(from);
		if(from.equalsIgnoreCase(DB_DATE_TIME_FORMAT))
			fromFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat toFormat = new SimpleDateFormat(to);
		if(from == null || to == null || date == null)
			return "";
		
		if(date.trim().length() == 0)
			return "";
		
		String resultDate = "";
		try {
			Date d = fromFormat.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			if (d.getYear() != cal.get(Calendar.YEAR))
				d.setYear(cal.get(Calendar.YEAR) - 1900);
			resultDate = toFormat.format(d);
		} catch (ParseException e) {
			resultDate = date;
		}
		return resultDate;
	}

	public static int monthDiff(Date d1, Date d2) {
		int diff = 0;

		int totalMonths1 = d1.getYear() * 12 + d1.getMonth();
		int totalMonths2 = d2.getYear() * 12 + d2.getMonth();

		diff = Math.abs(totalMonths1 - totalMonths2);

		//Log.d("Date Diff in Total Months", "" + diff);

		return diff;
	}
}
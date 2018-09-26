package com.kingtech.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 
 *@Description:日期操作类
 *@Author:Dengj
 *@Since:2015年4月13日
 *@Version:1.1.0
 */
public class DateUtil {
	// 同步锁对象
	private static Object lock = new Object();
	private static int num = 1;
	private static int num2 = 1;

	/**
	 * 获得当天0点时间戳
	 * 
	 * @return 时间戳
	 */
	public static long getTimesMorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	/**
	 * 获得当天24点时间戳
	 * 
	 * @return 时间戳
	 */
	public static long getTimesNight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	/**
	 * 获得本周一0点时间戳
	 * 
	 * @return 时间戳
	 */
	public static long getTimesWeekMorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTimeInMillis();
	}

	/**
	 * 获得本周日24点时间戳
	 * 
	 * @return 时间戳
	 */
	public static long getTimesWeekNight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return (cal.getTime().getTime() + (7 * 24 * 60 * 60 * 1000));
	}

	/**
	 * 获得本月第一天0点时间戳
	 * 
	 * @return 时间戳
	 */
	public static long getTimesMonthMorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTimeInMillis();
	}

	/**
	 * 获得本月最后一天24点时间戳
	 * 
	 * @return 时间戳
	 * @author xsc
	 */
	public static long getTimesMonthNight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		return cal.getTimeInMillis();
	}

	/**
	 * 获取当前时间的年份
	 * 
	 * @return int 返回类型
	 */
	public static int getYearNumOfNow() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取当前时间的月份
	 * 
	 * @return int 返回类型
	 */
	public static int getMothNumOfNow() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH) + 1;// 加1是换算为当前的月，因为默认从0开始
	}

	/**
	 * 获取当前时间的号数
	 * 
	 * @return int 返回类型
	 */
	public static int getDateOfNow() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DATE);
	}

	/**
	 * 获取当前的小时数(24小时制)
	 * 
	 * @return int 返回类型
	 */
	public static int getHourOfNow() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取当前时刻的分钟数
	 * 
	 * @return int 返回类型
	 */
	public static int getMiniteOfNow() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 获取当前时刻的秒数
	 * 
	 * @return int 返回类型
	 */
	public static int getSecondOfNow() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 判断当前日期是星期几
	 * 
	 * @param pTime
	 *            修要判断的时间
	 * @return dayForWeek 判断结果
	 * @Exception 发生异常
	 */
	public static int getDayForWeek(String timeStr) throws Exception {
		Date date = getDateFromString(timeStr);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	public static Date dateFormate(String dateStr, String formate) {
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * */
	public static String getDateStr(Date date, String formate) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		return sdf.format(date);
	}

	/***
	 * 获取yyyyMMddHHmmssSSS+三位递增数的唯一序列号
	 * 
	 * @return String 返回类型
	 */
	public static String getUniqueNo() {

		synchronized (lock) {
			String firstStr = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(System.currentTimeMillis());
			String secondStr = "" + num;
			int currentLen = secondStr.length();
			int needLen = 3;
			int addLen = needLen - currentLen;
			for (int i = 0; i < addLen; i++) {
				secondStr = "0" + secondStr;
			}
			num++;
			currentLen = (num + "").length();
			if (currentLen > needLen) {
				num = 0;
			}
			return firstStr + secondStr;
		}
	}

	/***
	 * 获取时间戳+三位递增数的唯一序列号
	 * 
	 * @return String 返回类型
	 */
	public static String getSerialNo() {

		synchronized (lock) {
			String firstStr = System.currentTimeMillis() + "";
			String secondStr = "" + num2;
			int currentLen = secondStr.length();
			int needLen = 3;
			int addLen = needLen - currentLen;
			for (int i = 0; i < addLen; i++) {
				secondStr = "0" + secondStr;
			}
			num2++;
			currentLen = (num2 + "").length();
			if (currentLen > needLen) {
				num2 = 0;
			}
			return firstStr + secondStr;
		}
	}

	public static Date getDateFromString(String timeString) throws ParseException {
		if (timeString == null) {
			return null;
		}
		int len = timeString.length();
		SimpleDateFormat sdfTmp = null;
		Date date = null;
		switch (len) {
		case 23:// yyyy-MM-dd HH:mm:ss:SSS
			sdfTmp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			break;
		case 21:// yyyy-MM-dd HH:mm:ss:0
			sdfTmp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
			break;
		case 19:// yyyy-MM-dd HH:mm:ss
			sdfTmp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		case 14:// yyyyMMddHHmmss
			sdfTmp = new SimpleDateFormat("yyyyMMddHHmmss");
			break;
		case 13:// 时间戳
			date = new Date(Long.parseLong(timeString));
			break;
		case 10:// yyyyMMddHHmmss
			sdfTmp = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case 8:// yyyyMMddHHmmss
			sdfTmp = new SimpleDateFormat("yyyyMMdd");
			break;
		default:
			sdfTmp = new SimpleDateFormat();
			break;
		}
		if (date == null) {
			return sdfTmp.parse(timeString);
		} else {
			return date;
		}

	}

	public static String formate(Object timeObj, String pattern) throws ParseException{
		if (timeObj == null) {
			return null;
		}
		int len = timeObj.toString().length();
		SimpleDateFormat sdfTmp = null;
		SimpleDateFormat sdfDst = null;
		Date date = null;
		switch (len) {
		case 23:// yyyy-MM-dd HH:mm:ss:SSS
			sdfTmp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			break;
		case 21:// yyyy-MM-dd HH:mm:ss:0
			sdfTmp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
			break;
		case 19:// yyyy-MM-dd HH:mm:ss
			sdfTmp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		case 14:// yyyyMMddHHmmss
			sdfTmp = new SimpleDateFormat("yyyyMMddHHmmss");
			break;
		case 10:// yyyyMMddHHmmss
			sdfTmp = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case 8:// yyyyMMddHHmmss
			sdfTmp = new SimpleDateFormat("yyyyMMdd");
			break;
		case 13:// 时间戳
			date = new Date(Long.parseLong(timeObj.toString()));
			break;
		default:
			sdfTmp = new SimpleDateFormat();
			break;
		}
		if (date == null) {
			date = sdfTmp.parse(timeObj.toString());
		}
		sdfDst = new SimpleDateFormat(pattern);
		return sdfDst.format(date);
	}

	// public static void main(String[] args)
	// {
	// System.out.println(getYearNumOfNow());
	// System.out.println(getMothNumOfNow());
	// System.out.println(getDateOfNow());
	// System.out.println(getHourOfNow());
	// System.out.println(getMiniteOfNow());
	// System.out.println(getSecondOfNow());
	// }

	public static void getMyWeekMorning() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("y年M月d日 E H时m分s秒", Locale.CHINA);
			Calendar calendar = Calendar.getInstance(Locale.CHINA); // 当前时间，貌似多余，其实是为了所有可能的系统一致 日历
			calendar.setTimeInMillis(System.currentTimeMillis());
			System.out.println("当前时间:" + format.format(calendar.getTime()));
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			System.out.println("周一时间:" + format.format(calendar.getTime()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static String formateDate(long l) {
		String result = "";
		if (l > 86400000) {// 大于1天
			result += (l / 86400000) + "天";
			l = l % 86400000;
		}
		if (l > 3600000) {
			result += (l / 3600000) + "小时";
			l = l % 3600000;
		}
		if (l > 60000) {
			result += (l / 60000) + "分";
			l = l % 60000;
		}
		if (l > 1000) {
			result += (l / 1000) + "秒";
			l = l % 1000;
		}
		if ("".equals(result)) {
			result += "1秒";
		}
		return result;
	}
	
	public static int daysBetween(String startmdate,String enddate,String foramt) { 
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(foramt);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(startmdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(enddate));
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			return -1;
		}
         
    }  
	
	public static String getSimpleDate(Date  date){
		return getDateStr(date,"yyyy-MM-dd");
		
	}

	public static void main(String[] args) {
		try {
			System.err.println(daysBetween("20160616", "20170617","yyyyMMdd"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

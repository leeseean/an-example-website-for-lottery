package com.mh.ifc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


public class DateUtil {


	public static String todayZhBegin() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return formatter.format(new Date());
	}

	public static String todayZhEnd() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		return formatter.format(new Date());
	}

	public static String todayBegin() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Calendar c = Calendar.getInstance();
		Date date = getGMT_4_Date();// 当前美东时间
		c.setTime(date);
		return formatter.format(c.getTime());
	}

	public static String todayEnd() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar c = Calendar.getInstance();
		Date date = getGMT_4_Date();// 当前美东时间
		c.setTime(date);
		return formatter.format(c.getTime());
	}

	public static String yesterdayBegin() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Calendar yesterdate = Calendar.getInstance();
		Date date = getGMT_4_Date();
		yesterdate.setTime(date);
		yesterdate.add(Calendar.DATE, -1);
		return formatter.format(yesterdate.getTime());
	}

	public static String yesterdayEnd() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar yesterdate = Calendar.getInstance();
		Date date = getGMT_4_Date();
		yesterdate.setTime(date);
		yesterdate.add(Calendar.DATE, -1);
		return formatter.format(yesterdate.getTime());
	}

	public static String weekBegin() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Calendar c = Calendar.getInstance();

		Date date = getGMT_4_Date();
		c.setTime(date);

		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return formatter.format(c.getTime());
	}

	public static String weekEnd() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar c = Calendar.getInstance();

		Date date = getGMT_4_Date();
		c.setTime(date);

		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, 7 - day_of_week);
		return formatter.format(c.getTime());
	}

	public static String monthBegin() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Calendar c = Calendar.getInstance();
		Date date = getGMT_4_Date();
		c.setTime(date);

		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return formatter.format(c.getTime());
	}

	public static String monthEnd() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar c = Calendar.getInstance();
		Date date = getGMT_4_Date();
		c.setTime(date);

		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
		return formatter.format(c.getTime());
	}

	public static String preMonthBegin() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Calendar cal = Calendar.getInstance();// 获取当前日期
		Date date = getGMT_4_Date();
		cal.setTime(date);

		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return formatter.format(cal.getTime());
	}

	public static String preMonthEnd() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar cale = Calendar.getInstance();
		Date date = getGMT_4_Date();
		cale.setTime(date);

		cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
		return formatter.format(cale.getTime());
	}

	/** 获取时差对应的时间 **/

	/**
	 * AG 时差，参数gmt8 北京标准时间
	 */
	public static Date getAG_GMT_Time(Date gmt8) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(gmt8);
		cal.add(Calendar.HOUR, -12);
		return cal.getTime();
	}

	/**
	 * BBIN 时差，参数gmt8 北京标准时间
	 */
	public static Date getBBIN_GMT_Time(Date gmt8) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(gmt8);
		cal.add(Calendar.HOUR, -12);
		return cal.getTime();
	}

	/**
	 * 188 时差，参数gmt8 北京标准时间
	 */
	public static Date getJBB_GMT_Time(Date gmt8) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(gmt8);
		cal.add(Calendar.HOUR, -12);
		return cal.getTime();
	}

	/**
	 * MG 时差，参数gmt8 北京标准时间
	 */
	public static Date getMG_GMT_Time(Date gmt8) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(gmt8);
		cal.add(Calendar.HOUR, -8);
		return cal.getTime();
	}

	/** MG游戏记录时间 转成时间对象 **/
	public static Date getBetDateMG(String dateStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.contains(dateStr, "T")) {
			dateStr = dateStr.replace("T", " ");
		}
		return formatter.parse(dateStr);
	}

	/** BBIN游戏记录时间 转成时间对象 **/
	public static Date getBetDateBbin(String dateStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.parse(dateStr);
	}

	/** JBB游戏记录时间 转成时间对象 **/
	public static Date getBetDateJbb(String dateStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return formatter.parse(dateStr);
	}

	/** JBB游戏记录时间 转成时间对象2 **/
	public static Date getBetDateJbb2(String dateStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		return formatter.parse(dateStr);
	}

	/** JBB游戏记录时间 转成时间对象3 **/
	public static Date getBetDateJbb3(String dateStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		return formatter.parse(dateStr);
	}

	public static String getDateBeforeDays(Date date, int days) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return formatter.format(cal.getTime());
	}

	public static Date getDateTimeBeforeDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static String getDateBeforeMinutes(Date date, int minutes) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);
		return formatter.format(cal.getTime());
	}

	public static Date getDateTimeBeforeMinutes(Date date, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);
		return cal.getTime();
	}

	/**
	 * 前段时间
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Map<String, String> getDateTime1(Date date, int minutes) {
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dateStr1 = formatter.format(date);
		String[] dateStr1s = dateStr1.split(" ");

		String dateStr2 = DateUtil.getDateBeforeMinutes(date, minutes);
		String[] dateStr2s = dateStr2.split(" ");

		if (!StringUtils.equals(dateStr1s[0], dateStr2s[0])) {
			map.put("roundDate", dateStr2s[0]);
			map.put("startTime", dateStr2s[1]);
			map.put("endTime", dateStr2s[1].split(":")[0] + ":59:59");
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 后段时间
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Map<String, String> getDateTime2(Date date, int minutes) {
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dateStr1 = formatter.format(date);
		String[] dateStr1s = dateStr1.split(" ");

		String dateStr2 = DateUtil.getDateBeforeMinutes(date, minutes);
		String[] dateStr2s = dateStr2.split(" ");

		if (!StringUtils.equals(dateStr1s[0], dateStr2s[0])) {
			map.put("roundDate", dateStr1s[0]);
			map.put("startTime", "00:00:00");
			map.put("endTime", dateStr1s[1]);
			return map;
		} else {

			map.put("roundDate", dateStr1s[0]);
			map.put("startTime", dateStr2s[1]);
			map.put("endTime", dateStr1s[1]);
			return map;
		}
	}

	/** 时间对象转成标准时间 **/
	public static String getDateString(Date date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	/** 时间对象转成标准时间 **/
	public static Date getDate(String dateString) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.parse(dateString);
	}

	/**
	 * 获取美东时间
	 * 
	 * @return
	 *//*
	public static Date getGMT_4_Date() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -12);
		return cal.getTime();
	}*/
	
	public static Date getGMT_4_Date() {
		return new Date();
	}

	/**
	 * 获取美东时间
	 * 
	 * @return
	 */
	public static Date getGMT_4_Date_Date(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, -12);
		return cal.getTime();
	}

	/**
	 * 获取美东时间
	 * 
	 * @return
	 */
	public static String getGMT_4_String() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -12);
		return formatter.format(cal.getTime());
	}

	public static String getDateString(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	public static String getGMT_4_String_End() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -12);
		return formatter.format(cal.getTime());
	}

	public static Date getDate(String date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String todayDateStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date = getGMT_4_Date();// 当前美东时间
		c.setTime(date);
		return formatter.format(c.getTime());
	}

	public static String yesterdayDateStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar yesterdate = Calendar.getInstance();
		Date date = getGMT_4_Date();
		yesterdate.setTime(date);
		yesterdate.add(Calendar.DATE, -1);
		return formatter.format(yesterdate.getTime());
	}

	public static String beforeDate(String dateStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(dateStr);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		return formatter.format(c.getTime());
	}

	public static String afterDate(String dateStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(dateStr);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		return formatter.format(c.getTime());
	}

	public static Date getBeforNDays(int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getGMT_4_Date());
		cal.add(Calendar.DATE, -n);
		return cal.getTime();
	}
	
	public static String getGMT_4_BeginString(String year, String month){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.valueOf(year), Integer.valueOf(month)-1, Calendar.DATE);
		
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return formatter.format(calendar.getTime());
	}
	
	public static String getGMT_4_EndString(String year, String month){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.valueOf(year), Integer.valueOf(month)-1, Calendar.DATE);
		
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return formatter.format(calendar.getTime());
	}
	
	public static String getGMT_4_YearAndMonth(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		
		return formatter.format(getGMT_4_Date());
	}

}

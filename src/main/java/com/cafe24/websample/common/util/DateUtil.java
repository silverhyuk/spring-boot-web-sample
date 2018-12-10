package com.cafe24.websample.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Date Util
 * @author 최은혁
 * @date 2018.4.21
 */
public final class DateUtil{
	
	private static Log log                  = LogFactory.getLog(DateUtil.class);

    public static final String SEPARATOR_DATE       = "-";
    public static final String SEPARATOR_TIME       = ":";

    public static final String FMT_DATE_Y           = "yyyy";
    public static final String FMT_DATE_M           = "MM";
    public static final String FMT_DATE_D           = "dd";
    public static final String FMT_DATE_MD          = "MMdd";
    public static final String FMT_DATE_YM          = "yyyyMM";
    public static final String FMT_DATE_YM_SEP      = "yyyy-MM";
    public static final String FMT_DATE_YMD         = "yyyyMMdd";
    public static final String FMT_DATE_YMD_SEP     = "yyyy-MM-dd";
    public static final String FMT_DATETIME         = "yyyyMMddHHmmss";
    public static final String FMT_DATETIME_SEP     = "yyyy-MM-dd HH:mm:ss";
    public static final String FMT_DATETIME_Mil     = "yyyyMMddHHmmssSSS";
    public static final String FMT_DATETIME_Mil_SEP = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String FMT_TIME_H           = "HH";
    public static final String FMT_TIME_HM          = "HHmm";
    public static final String FMT_TIME_HM_SEP      = "HH:mm";
    public static final String FMT_TIME_HMS         = "HHmmss";
    public static final String FMT_TIME_HMS_SEP     = "HH:mm:ss";
    public static final String FMT_TIME_HMSMil      = "HHmmssSSS";
    public static final String FMT_TIME_HMSMil_SEP  = "HH:mm:ss:SSS";
    
    /**
     * 패턴으로 현재 날짜 가져오기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static String getCurrDate(String pattern) {
        return (new SimpleDateFormat(pattern, Locale.KOREA)).format(new Date());
    }
    
    /**
     * 말일 년월일 가져오기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static String getLastMonthDate(String pattern) {
    	Calendar cal = Calendar.getInstance();
    	cal.set( cal.get(Calendar.YEAR) , cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return (new SimpleDateFormat(pattern, Locale.KOREA)).format(cal.getTime());
    }
    
    /**
     * 말일 년월일 가져오기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static String getLastMonthDate(String pattern , Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.set( cal.get(Calendar.YEAR) , cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return (new SimpleDateFormat(pattern, Locale.KOREA)).format(cal.getTime());
    }
    
    /**
     * 파라미터의 월의 말일 가져오기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static int getLastMonthDay(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 월일 가져오기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static String getFirstMonthDate(String pattern) {
    	Calendar cal = Calendar.getInstance();
    	cal.set( cal.get(Calendar.YEAR) , cal.get(Calendar.MONTH), cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return (new SimpleDateFormat(pattern, Locale.KOREA)).format(cal.getTime());
    }
    
    /**
     * 현재 날짜 가져오기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static String getCurrDate() {
        return (new SimpleDateFormat(FMT_DATE_YMD, Locale.KOREA)).format(new Date());
    }
    
    /**
     * 현재 년 일자만 가져오기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static String getCurrYyyy() {
        return (new SimpleDateFormat(FMT_DATE_Y, Locale.KOREA)).format(new Date());
    }
    
    
    /**
     * 현재 월 일자만 가져오기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static String getCurrMm() {
        return (new SimpleDateFormat(FMT_DATE_M, Locale.KOREA)).format(new Date());
    }
    
    
    /**
     * 현재 날짜 일자만 가져오기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static String getCurrDd() {
        return (new SimpleDateFormat(FMT_DATE_D, Locale.KOREA)).format(new Date());
    }
   
    
    /**
     * 생일 날짜 옵션 가져오기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static String getBirthdayYyyy(int sAge , int endAge) {
    	int curYyyy = Integer.parseInt(getCurrDate("yyyy"));
    	int sYyyy = curYyyy - endAge;
    	int eYyyy = curYyyy - sAge;
    
    	StringBuilder birthdayYyyyOption = new StringBuilder();
    	for( ; sYyyy <= eYyyy ; sYyyy++){
    		birthdayYyyyOption.append("<option value = \"" ).append(String.valueOf(sYyyy)).append("\">").append(String.valueOf(sYyyy)).append("</option>"); 
    	}
    	
    	return birthdayYyyyOption.toString();
    }
    
    /**
     * Date 형으로 변환하기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static Date getDateFormat(String dateFormat , String strDate) throws Exception{
    	SimpleDateFormat format = new SimpleDateFormat(dateFormat); 
    	Date date = format.parse(strDate);
    	return date;
    }
    
    /**
     * 문자열형으로 변환하기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static String getDateStringFormat(String dateFormat , Date date) throws Exception{
    	return (new SimpleDateFormat(dateFormat, Locale.KOREA)).format(date);
    }
    
    /**
     * 날짜추가
     * 사용법!!~~~
     * 현재 날짜 + 며칠 할려고 한다면
     * 1. 데이타 포맷, 2. String 현재 날짜, 3 int 추가되는 날짜
     * DateUtil.getAddDateFormat("yyyy-MM-dd" , DateUtil.getCurrDate("yyyy-MM-dd"), 10);
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static Date getAddDateFormat(String dateFormat , String strDate, int datInt) throws Exception{

		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat toDay = new SimpleDateFormat(dateFormat);
        Date addDateDateType = null;
        cal.add(Calendar.DATE, datInt);
        addDateDateType = toDay.parse(toDay.format(cal.getTime()));
        
    	return addDateDateType;
    }
    
   /**
    * date 일 증감 차감.
    * @author 최은혁
	* @date 2018.4.21
    */
    public static Date getAddDateFormat(Date date, int add) throws Exception{

		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
        cal.add(Calendar.DATE, add);
        
    	return cal.getTime();
    }
    
    /**
     * date 월 증감 차감.
     * @author 최은혁
	 * @date 2018.4.21
     */
     public static Date getAddMonthFormat(Date date, int add) throws Exception{

 		Calendar cal = Calendar.getInstance();
 	    cal.setTime(date);
         cal.add(Calendar.MONTH, add);
         
     	return cal.getTime();
     }
    
    /**
     * 현재날짜 밀리세컨드
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static String getCurrentDateMiliTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return timeFormat.format(new Date());
    }
    
    /**
     * 현재일기준 주의 월요일 일자 구하기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static Date getWeekMondayDate(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		return cal.getTime(); 
    }
    
    /**
     * 현재일기준 차주의 일요일 일자 구하기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static Date getWeekNextSundayDate(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
		cal.add(Calendar.DAY_OF_WEEK, 6); //- 차주이기때문에 7이지만  해당일이 일요일경우 해당일이 일요일로 나오기때문에 6로 셋팅(예- 월~일) 인경우.
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cal.getTime(); 
    }
    
    /**
     * 현재일기준 주의 일요일 일자 구하기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static Date getWeekSundayDate(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cal.getTime(); 
    }
    
    /**
     * 월 마지막 주차수 구하기
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static int getLastWeek(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)  , cal.getActualMaximum(Calendar.DATE));
    	return cal.get(Calendar.WEEK_OF_MONTH);
    }
    
    /**
     * 해당일의 주차수 구하기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static int getWeek(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	return cal.get(Calendar.WEEK_OF_MONTH);
    }
    
    /**
     * 해당 년월의 주차수를 입력하여 월요일 날짜 가져오기.
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static Date getWeekDigitMonday(Date date , int weekDigit) {
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.set(Calendar.WEEK_OF_MONTH, weekDigit);
    	
    	return getWeekMondayDate(calendar.getTime());
    }
    
    /**
     * 해당년도기준으로 전년도 다음년도 가져오기
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static Date getAddYearDate(Date date , int year) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.set(cal.get(Calendar.YEAR) + year, cal.get(Calendar.MONTH)  , cal.get(Calendar.DATE));
    	return cal.getTime();
    }
    
    /**
     * 이전달 가져오기
     * @author 최은혁
	 * @date 2018.4.21
     */
    public static String getPrevMonth(String year , String month) throws ParseException {
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
    	String strDate = year+"-"+month;
    	Date dt = dateFormat.parse(strDate);
    	cal.setTime(dt);
    	cal.add(Calendar.MONTH,-1);
    	return dateFormat.format(cal.getTime());
    }

}

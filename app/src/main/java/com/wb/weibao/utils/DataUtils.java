package com.wb.weibao.utils;





import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
	public static int selectPosition =-1;

	public static int getSelectPosition() {
		return selectPosition;
	}



	/**
	 * 根据美式周末到周一 返回
	 * @param weekNum
	 * @return
	 */
	private static String getWeekName(int weekNum) {
		String name = "" ;
		switch (weekNum) {
			case 1:
				name = "星期日";
				break;
			case 2:
				name = "星期一";
				break;
			case 3:
				name = "星期二";
				break;
			case 4:
				name = "星期三";
				break;
			case 5:
				name = "星期四";
				break;
			case 6:
				name = "星期五";
				break;
			case 7:
				name = "星期六";
				break;
			default:
				break;
		}
		return name;
	}
	/**
	 * 是否是今天
	 * @param sdate
	 * @return
	 */
	public static boolean isToday(String sdate){
		boolean b = false;
		Date time = null ;
		try {
			time = dateFormat.parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date today = new Date();
		if(time != null){
			String nowDate = dateFormater.get().format(today);
			String timeDate = dateFormater.get().format(time);
			if(nowDate.equals(timeDate)){
				b = true;
			}
		}
		return b;
	}


	/**
	 * 是否是当月
	 * @param sdate
	 * @return
	 */
	public static boolean isToMonth(String sdate,String sdate2){
		boolean b = false;
		Date time = null ;
		Date time2 = null ;
		try {
			time = dateFormat.parse(sdate);
			time2 = dateFormat.parse(sdate2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date today = new Date();
		if(time != null&&time2!=null){
			String timeDate = dateFormater2.get().format(time);
			String nowDate = dateFormater2.get().format(time2);
			if(nowDate.equals(timeDate)){
				b = true;
			}
		}
		return b;
	}


	/**
	 * 是否是当月
	 * @param sdate
	 * @return
	 */
	public static boolean isToMonth2(String sdate,String sdate2){
		boolean b = false;
		Date time = null ;
		Date time2 = null ;
		try {
			time = dateFormat2.parse(sdate);
			time2 = dateFormat2.parse(sdate2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date today = new Date();
		if(time != null&&time2!=null){
			String timeDate = dateFormater2.get().format(time);
			String nowDate = dateFormater2.get().format(time2);
			if(nowDate.equals(timeDate)){
				b = true;
			}
		}
		return b;
	}


	/**
	 * 是否比当前日期大
	 * @param date
	 * @return
	 */
	public static boolean isdatesize(String date,String date2){
		boolean b = false;
		Date time = null ;
		Date time2 = null ;
		try {
			time = dateFormat.parse(date);
			time2 = dateFormat.parse(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(time != null&&time2!=null){
			if(time.getTime()<=time2.getTime())
			{
				b=true;
			}
		}
		return b;
	}


	/**
	 * 日期比较
	 * @param date
	 * @param date2
	 * @return
	 */
	public static boolean isdatesize2(String date,String date2){
		boolean b = false;
		Date time = null ;
		Date time2 = null ;
		try {
			time = dateFormat.parse(date);
			time2 = dateFormat.parse(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(time != null&&time2!=null){
			if(time.getTime()<=time2.getTime())
			{
				b=true;
			}
		}
		return b;
	}


	/**
	 * 日期比较
	 * @param date
	 * @param date2
	 * @return
	 */
	public static boolean isdatesize3(String date,String date2){

		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Calendar ca = Calendar.getInstance();
            ca.add(Calendar.MONTH,-6);



		boolean b = false;
		Date time = null ;
		Date time2 = null ;
		Date time3 = null ;
		try {
			time = dateFormat.parse(date);
			time2 = dateFormat.parse(date2);
			ca.setTime(time2);
			ca.add(Calendar.MONTH,-3);
			String time4 =sDateFormat.format(ca.getTime());

			time3=dateFormat.parse(sDateFormat.format(ca.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(time != null&&time2!=null){
			if(time.getTime()>=time3.getTime())
			{
				b=true;
			}
		}
		return b;
	}


	/**
	 * 日期比较
	 * @param date
	 * @param date2
	 * @return
	 */
	public static boolean isdatesize4(String date,String date2){

		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.MONTH,-6);



		boolean b = false;
		Date time = null ;
		Date time2 = null ;
		Date time3 = null ;
		try {
			time = dateFormat.parse(date);
			time2 = dateFormat.parse(date2);
			ca.setTime(time2);
			ca.add(Calendar.MONTH,-6);
			String time4 =sDateFormat.format(ca.getTime());

			time3=dateFormat.parse(sDateFormat.format(ca.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(time != null&&time2!=null){
			if(time.getTime()>=time3.getTime())
			{
				b=true;
			}
		}
		return b;
	}






	/**
	 * 个位数补0操作
	 * @param num
	 * @return
	 */
	public static String getValue(int num){
		return String.valueOf(num>9?num:("0"+num));
	}


	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM");
		}
	};

	/**
	 * 获取系统当前日期
	 */
	public static String getCurrDate(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间
		String str = formatter.format(curDate);
		return str;
	}
	/**
	 * 格式化日期
	 */
	public static String formatDate(String date ,String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date curDate = null;//获取当前时间
		try {
			curDate = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String str = formatter.format(curDate);
		return str;
	}

    /**
	 *  切换周的时候用
     * 获取前/后 几天的一个日期
	 * @param currentData
     * @param dayNum
     * @return
     */
	public static String getSomeDays(String currentData,int dayNum){
		Calendar c = Calendar.getInstance();
		//过去七天
		try {
			c.setTime(DataUtils.dateFormat.parse(currentData));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, dayNum);
		Date d = c.getTime();
		String day = DataUtils.dateFormat.format(d);
		return day ;
	}
	/**
	 * 获取前/后 几个月的一个日期  切换月的时候用
	 * @param currentData
	 * @param monthNum
	 * @return
	 */
	public static String getSomeMonthDay(String currentData,int monthNum){
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(new SimpleDateFormat("yyyy-MM").parse(currentData));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) +monthNum);
		Date day =  c.getTime();
		return   new SimpleDateFormat("yyyy-MM-dd").format(day);
	}


}

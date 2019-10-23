package com.yh.kuangjia.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

    /// <summary>
    /// 获取当前时区的时间戳（1979-1-1至今的毫秒数）
    /// </summary>
    /// <returns></returns>
    public static long GetCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /*
     * 将时间转换为时间戳
     */
    public static Long dateToStamp(Date date) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long ts = date.getTime();
        return ts;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(long lt) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /// <summary>
    /// 判断时间误差
    /// </summary>
    /// <param name="timeMillis">待判断的时间戳</param>
    /// <param name="disp">误差毫秒数</param>
    /// <returns>超过误差毫秒数，返回true</returns>
    public static boolean CompareTimeMillis(long timeMillis, long disp) {
        return Math.abs(GetCurrentTimeMillis() - timeMillis) > disp;
    }

    /**
     * 判断时间误差
     *
     * @param dateTime 待判断的时间戳
     * @param disp     误差毫秒数
     * @return 超过误差毫秒数，返回true
     */
    public static boolean CompareTimeMillis(Date dateTime, long disp) {
        return Math.abs(GetCurrentTimeMillis() - dateTime.getTime()) > disp;
    }

    /**
     * 字符串转日期
     * 错误返回最小日期
     *
     * @param strDate
     * @param format
     * @return
     */
    public static Date formatDate(String strDate, String format) {
        try {
            return new SimpleDateFormat(format).parse(String.valueOf(strDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(0);
    }

    /**
     * 根据格式获取当前时间
     *
     * @param dateFormat
     * @return
     */
    public static String GetCurrentDateByDateFormat(String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(new Date());
    }

    /**
     * 根据格式获取当前时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return
     */
    public static String GetCurrentDateByDateFormat() {
        return GetCurrentDateByDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日期增加天数
     *
     * @param date 日期
     * @param day  增加天数
     * @return
     * @throws ParseException
     */
    public static Date AddDays(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }


    /**
     * 日期减少天数
     *
     * @param date 日期
     * @param day  减少天数
     * @return
     * @throws ParseException
     */
    public static Integer SubtractDays(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time -= day; // 相加得到新的毫秒数
//        return new Date(time); // 将毫秒数转换成日期
        return Integer.parseInt(DateFormat(new Date(time), "yyyyMMdd"));

    }

    /**
     * 日期减少天数
     *
     * @param date 日期
     * @param day  减少天数
     * @return
     * @throws ParseException
     */
    public static Integer addDays(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
//        return new Date(time); // 将毫秒数转换成日期
        return Integer.parseInt(DateFormat(new Date(time), "yyyyMMdd"));

    }

    /**
     * 日期格式转换
     * 将yyyy-MM-dd的字符串日期 转换为  yyyyMMdd的整数日期
     * 转换错误则返回当前日期
     */
    public static Integer DateConvert(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //设置lenient为false. 否则会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(date);
            return Integer.valueOf(date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10));
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 时间格式转换
     * 将 HHmmss的整数日期转换为HH:mm:ss的字符串日期
     * 转换错误则返回当前时间
     */
    public static String TimeConvert(Integer time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if (time != null) {
            try {
                return sdf.format(new SimpleDateFormat("HHmmss").parse(JsonUtil.PadLeft(String.valueOf(time), 6, '0')));
            } catch (ParseException e) {
//            e.printStackTrace();
            }
        }
        return "";
    }


    /**
     * 日期格式转换,字符串转日期
     * 转换错误则返回当前日期
     */
    public static Date ConvertDateTime(String dataStr, String format) {

        try {

            return new SimpleDateFormat(format).parse(String.valueOf(dataStr));
        } catch (ParseException e) {
//            e.printStackTrace();
        }
        return new Date();
    }

    public static Date dealDateFormat(String date) {
        try {
            date = date.replace("Z", " UTC");//注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
            return format.parse(date);
        } catch (ParseException e) {
//            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 格式化日期
     */
    public static String DateFormat(Date dt, String format) {
        return new SimpleDateFormat(format).format(dt);
    }

    /**
     * 格式化日期
     */
    public static String DateFormat(Calendar calendar, String format) {
        return new SimpleDateFormat(format).format(calendar.getTime());
    }


    /**
     * 校验服务器时间是否合法
     *
     * @param server_time     时间字符串
     * @param time_format     时间格式
     * @param tome_out_minute 合法时间：分钟
     * @return
     */
    public static boolean checkServerTime(String server_time, String time_format, int tome_out_minute) {
        Date serTime = null;
        try {
            serTime = new SimpleDateFormat(time_format).parse(server_time);
        } catch (ParseException e) {
        }
        if (serTime != null) {
            return Math.abs(System.currentTimeMillis() - serTime.getTime()) < (tome_out_minute * 60 * 1000);
        }
        return false;
    }

    public static Date GetDate() {
        return new Date();
    }

    public static Integer GetDateInt() {
        return Integer.parseInt(DateFormat(GetDate(), "yyyyMMdd"));
    }

    public static Integer GetDateInt(Date date) {
        return Integer.parseInt(DateFormat(date, "yyyyMMdd"));
    }

    public static Integer GetTimeInt() {
        return Integer.parseInt(DateFormat(GetDate(), "HHmmss"));
    }

    public static Date GetIntToDate(int dateNum) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        try {
            return formatter.parse(dateNum + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String GetIntToDateString(int dateNum) {
        return GetIntToDateString(dateNum, "yyyy-MM-dd");
    }

    public static String GetIntToDateString(int dateNum, String format) {
        Date date = GetIntToDate(dateNum);
        SimpleDateFormat formatter2 = new SimpleDateFormat(format);
        return formatter2.format(date);
    }

    //将java.util.Date 转换为java8 的java.time.LocalDateTime,默认时区为东8区
    public static LocalDateTime dateConvertToLocalDateTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
    }


    //将java8 的 java.time.LocalDateTime 转换为 java.util.Date，默认时区为东8区
    public static Date localDateTimeConvertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
//            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2 - day1;
        }
    }
}

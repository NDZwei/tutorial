package com.ndz.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*
    author: NMDuc
    created_at: 3/16/2024
    github: https://github.com/NDZwei
*/
public class DateUtils {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date atStartOfDay(Date date) {
        if(date==null){
            return null;
        }
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    public static Date atEndOfDay(Date date) {
        if(date==null){
            return null;
        }
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    public static Date atStartOfYear(Integer year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return atStartOfDay(cal.getTime());
    }

    public static Date atEndOfYear(Integer year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH,11);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        return atEndOfDay(cal.getTime());
    }

    public static int getYear(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public  static int getMonth(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH)+1;
    }

    public  static  int getDay(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String formatDate(Date date, String format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

    public static String formatDate(String fromFormat, String toFormat, String date) {
        try {
            Date dateConvert = new SimpleDateFormat(fromFormat).parse(date);
            return formatDate(dateConvert, toFormat);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date getFirstDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 0);
        return cal.getTime();
    }

    public static Date convertToDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        SimpleDateFormat format;
        if (dateStr.length() == 12) {
            format = new SimpleDateFormat("yyyyMMddHHmm");
        } else if (dateStr.length() == 8) {
            format = new SimpleDateFormat("yyyyMMdd");
        } else {
            return null;
        }
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

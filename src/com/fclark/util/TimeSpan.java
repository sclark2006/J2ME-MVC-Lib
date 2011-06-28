/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.util;

import java.util.Date;

/**
 *
 * @author Frederick
 * @version 06/20/2011 11:42:33 PM
 */
public class TimeSpan {

    public static final long MILLIS_PER_SECOND = 1000;
    public static final long MILLIS_PER_MINUTE = 60000;
    public  static final long MILLIS_PER_HOUR = (long)(60000 * 60);
    public  static final long MILLIS_PER_DAY = (long)(60000 * 1440);
    public  static final long MILLIS_PER_YEAR = (long) ((long)60000 * 1440) * 365;
    
    private long elapsedTime;
    private int years;
    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;

    public TimeSpan(long elapsedTime) {
        this.elapsedTime = elapsedTime;
        calculateTimeSpan();
        System.out.println("TimeSpan: " + this.toString());
    }

    private void calculateTimeSpan() {
        long remainingTime =  Math.abs(elapsedTime);
        //years
        System.out.println("Millis per Year:" + MILLIS_PER_YEAR);
        System.out.println("Years opeartion :"+remainingTime / MILLIS_PER_YEAR);
        years = (int) Math.floor(remainingTime / MILLIS_PER_YEAR);
        //days
        remainingTime %= MILLIS_PER_YEAR;
        days = (int) Math.floor(remainingTime / MILLIS_PER_DAY);

        remainingTime %= MILLIS_PER_DAY;
        hours = (int) Math.floor(remainingTime / MILLIS_PER_HOUR);

        remainingTime %= MILLIS_PER_HOUR;
        minutes = (int) Math.floor(remainingTime / MILLIS_PER_MINUTE);

        remainingTime %= MILLIS_PER_MINUTE;
        seconds = (int) Math.floor(remainingTime / MILLIS_PER_SECOND);
        
        milliseconds = (int)remainingTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    
    public int getYears() {
        return years;
    }

    public int getDays() {
        return days;
    }

    public int getHours() {
        return hours;
    }
   
    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
    
    public int getMilliseconds() {
        return milliseconds;
    }
    
    
    public static TimeSpan between(Date d1, Date d2) {
        if(d1 == null || d2 == null)
            return null;
        return new TimeSpan(d1.getTime() - d2.getTime());
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[elapsed time :").append(this.elapsedTime).append(", ");
        sb.append("years: ").append(years).append(", ");
        sb.append("days: ").append(days).append(", ");
        sb.append("hous: ").append(hours).append(", ");
        sb.append("minutes: ").append(minutes).append(", ");
        sb.append("seconds: ").append(seconds).append(", ");
        sb.append("millis: ").append(milliseconds).append("]");

        return sb.toString();
    }

}

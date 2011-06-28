package com.fclark.util;

import java.util.Calendar;
import java.util.Date;


public class DateComparator implements Comparator {
	private boolean ignoreYear;
	private boolean ignoreTime;
	private static Date DEFAULT_DATE;
	
	static {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 1);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DATE, 1);		  
		DEFAULT_DATE = c.getTime();
		c = null;
	}
	
	private DateComparator() {
		this.ignoreYear = false;
		this.ignoreTime = true;
	}
	private DateComparator(boolean ignoreYear, boolean ignoreTime) {
		this.ignoreYear = ignoreYear;
		this.ignoreTime = ignoreTime;
	}

	private int timeDiff(Calendar c1, Calendar c2) {
	    int timeDiff = (c1.get(Calendar.MILLISECOND) - c2.get(Calendar.MILLISECOND));
		timeDiff += (c1.get(Calendar.SECOND) - c2.get(Calendar.SECOND)) * 1000;
		timeDiff += (c1.get(Calendar.MINUTE) - c2.get(Calendar.MINUTE)) * 60000;
		timeDiff += (c1.get(Calendar.HOUR) - c2.get(Calendar.HOUR)) * 3600000;
		return timeDiff;
	}
	
	public int compare(Object o1, Object o2) {		
		if(o1 == null) o1 = DEFAULT_DATE;
		if(o2 == null) o2 = DEFAULT_DATE;
		
      	Calendar c1 = Calendar.getInstance();
      	c1.setTime((Date)o1);
      	Calendar c2 = Calendar.getInstance();
      	c2.setTime((Date)o2);
      	  
      	int dateDiff = 0;
      	
      	  if(!ignoreTime) 
      		dateDiff = timeDiff(c1, c2);
      		          
          dateDiff += (c1.get(Calendar.DATE) - c2.get(Calendar.DATE)) * 3600000 * 24;
          dateDiff += (c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH)) * 3600000 * 24 * 30.42;
                    
          if(!ignoreYear) {
        	  dateDiff+= (c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR)) * 3600000 * 24 * 365;
          }
          
      	  return dateDiff;
	}
	
	public static Comparator getInstance() {
		return new DateComparator();		
	}
	
	public static Comparator getInstance(boolean ignoreYear, boolean ignoreTime) {
		return new DateComparator(ignoreYear, ignoreTime);
	}

}

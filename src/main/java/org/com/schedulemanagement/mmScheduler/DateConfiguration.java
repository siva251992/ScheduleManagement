package org.com.schedulemanagement.mmScheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConfiguration {
	public String dateRequired() {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern);
		String date = simpleDateFormat1.format(new Date());
		//System.out.println(date);
	//	return dateString();
		return date;
	}
	public String pastdateRequired() throws ParseException {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern);
		String date = simpleDateFormat1.format(new Date());
		Calendar c = Calendar.getInstance();
		c.setTime(simpleDateFormat1.parse(date));
		c.add(Calendar.DATE, -1); 
		String timeStamp=simpleDateFormat1.format(c.getTime());
        System.out.println(timeStamp);
		return date;
	}
	
	public String timeRequired() throws ParseException {
		String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
		String CurrenDate = simpleDateFormat.format(new Date());
		Calendar c = Calendar.getInstance();
		c.setTime(simpleDateFormat.parse(CurrenDate));
		c.add(Calendar.HOUR, 3); 
		String timeStamp=simpleDateFormat.format(c.getTime());
		return timeStamp;
	}
	
	public String pasttimeRequired() throws ParseException {
		String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
		String CurrenDate = simpleDateFormat.format(new Date());
		Calendar c = Calendar.getInstance();
		c.setTime(simpleDateFormat.parse(CurrenDate));
		c.add(Calendar.DATE, -1);  
		String timeStamp=simpleDateFormat.format(c.getTime());
		//System.out.println(timeStamp);
		return timeStamp;
	}
	public String futuretimeRequired() throws ParseException {
		String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
		String CurrenDate = simpleDateFormat.format(new Date());
		Calendar c = Calendar.getInstance();
		c.setTime(simpleDateFormat.parse(CurrenDate));
		c.add(Calendar.DATE, 4);  
		String timeStamp=simpleDateFormat.format(c.getTime());
		//System.out.println(timeStamp);
		return timeStamp;
	}
	
	public static void main(String[] args) {
		DateConfiguration d= new DateConfiguration();
		d.dateRequired();
	}
	

}

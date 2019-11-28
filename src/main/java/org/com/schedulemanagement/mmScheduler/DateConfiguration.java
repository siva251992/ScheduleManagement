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
	
	public String timeRequired() throws ParseException {
		String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
		String CurrenDate = simpleDateFormat.format(new Date());
		Calendar c = Calendar.getInstance();
		c.setTime(simpleDateFormat.parse(CurrenDate));
		c.add(Calendar.HOUR, 2); 
		String timeStamp=simpleDateFormat.format(c.getTime());
		return timeStamp;
	}

}

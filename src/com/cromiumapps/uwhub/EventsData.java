package com.cromiumapps.uwhub;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.util.Log;

public class EventsData {

	//private
	Context context;
	
	String id = null;
	String date = null;
	String name = null;
	String links = null;
	String description = null;
	String where = null;
	
	int type = 0;
	
	SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
	
	public EventsData (String inputID, String inputDate, String inputName, String inputLinks, String inputDescription, String inputWhere, int inputType) {
	    id = inputID;
	    date = inputDate;
	    name = inputName;
	    links = inputLinks;
	    description = inputDescription;
	    where = inputWhere;
	    type = inputType;
	}
	
	public String getId() {
		return id;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getLocation()
	{
		return this.where;
	}
	
	public long getDateTimeInMilis()
	{
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = day.getCalendar();
        cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)+1);
        cal.set(Calendar.YEAR,cal.get(Calendar.YEAR)+80);
        Log.d("CalendarQuery","returning calendar of day"+cal.get(Calendar.DATE)+","+cal.get(Calendar.MONTH)+","+cal.get(Calendar.YEAR));
        return cal.getTimeInMillis();
	}
	
	public String getName() {
		return StringCleaner.replaceHTML(name);
	}
	
	public String getLinks() {
		return StringCleaner.getLink(links);
	}
	
	public String getDescription() {
		return StringCleaner.cleanContent(description);
	}
	
	private String splitCalendarEventDate(String input)
	{
		input = input.split("-",5)[0];
		input = input.substring(input.indexOf(','),input.length());
		return input;
	}
	
	/*private String parseInputDate(String inputDate,int inputType)
	{
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
		if(inputType == 0)day = new SimpleDateFormat("yyyy-MM-dd");;
		if(inputType == 1)day = new SimpleDateFormat("dd MM yyyy");;
		if(inputType == 2)day = new SimpleDateFormat("yyyy-MM-dd");;
		
        Calendar cal = day.getCalendar();
        cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)+1);
        cal.set(Calendar.YEAR,cal.get(Calendar.YEAR)+80);
	}*/
}

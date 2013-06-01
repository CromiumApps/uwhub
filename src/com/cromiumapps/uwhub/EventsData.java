package com.cromiumapps.uwhub;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;

public class EventsData {

	//private
	Context context;
	
	String id = null;
	String date = null;
	String name = null;
	String links = null;
	String description = null;
	
	SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
	
	public EventsData (String inputID, String inputDate, String inputName, String inputLinks, String inputDescription) {
	    id = inputID;
	    date = inputDate;
	    name = inputName;
	    links = inputLinks;
	    description = inputDescription;
	}
	
	public String getId() {
		return id;
	}
	
	public String getDate() {
	    
	    try 
	    {
	        Date parsedDate = (Date) format.parse(date);
	        System.out.println(parsedDate);
	        SimpleDateFormat day = new SimpleDateFormat("EEEE, MMMMM dd, yyyy");
	        date = day.format(parsedDate).toString();
	    } 
	    catch (ParseException e) 
	    {
	        e.printStackTrace();
	    }
	    
		return date;
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
}

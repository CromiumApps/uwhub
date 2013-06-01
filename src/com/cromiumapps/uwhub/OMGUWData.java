package com.cromiumapps.uwhub;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;

public class OMGUWData {

	//private
	Context context;
	
	String id = null;
	String date = null;
	String content = null;
	String link = null;
	String type = null;
	SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
	
	public OMGUWData (String inputID, String inputDate, String inputContent, String inputLink, String inputType) {
	    id = inputID;
	    date = inputDate;
	    content = inputContent;
	    link = inputLink;
	    type = inputType;
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
	
	public String getContent() {
		return StringCleaner.cleanContent(content);
	}
	
	public String getLink() {
		return link;
	}
	
	public String getType() {
		return type;
	}
}

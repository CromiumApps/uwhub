package com.cromiumapps.uwhub;

import java.text.ParseException;

import android.content.Context;

public class CoursesInfoData {

	//private
	Context context;
	
	String dept = null;
	String number = null;
	String title = null;
	String description = null;
	
	public CoursesInfoData (String inDept, String inNumber, String inTitle, String inDesc) {
	    dept = inDept;
	    number = inNumber;
		title = inTitle;
	    description = inDesc;
	}
	
	public String getDept() {
		return dept;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	// May need this
	/*
	private String decode(String string) {
	    
	    string = string.replace("&quot;", "\"");
	    string = string.replace("&apos;", "'");
	    string = string.replace("&amp;", "&");
	    string = string.replace("&lt;", "<");
	    string = string.replace("&gt;", ">");
	    return string;
	}
	*/
}
package com.cromiumapps.uwhub;

import android.content.Context;

public class OMGUWData {

	//private
	Context context;
	
	String id = null;
	String date = null;
	String content = null;
	String link = null;
	String type = null;
	
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
		return date;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getLink() {
		return link;
	}
	
	public String getType() {
		return type;
	}

}

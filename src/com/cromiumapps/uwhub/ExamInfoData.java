package com.cromiumapps.uwhub;

import android.content.Context;

public class ExamInfoData {

	//private
	Context context;
	
	String courseID = null;
	String section = null;
	String date = null;
	String location = null;
	
	public ExamInfoData(String inCourseID, String inSection, String inDay, String inDate, String inStart, String inEnd, String inLoc) {
		courseID = inCourseID;
		section = inSection;
		date = createDate(inDay, inDate, inStart, inEnd);
		location = inLoc;
	}
	
	private String createDate(String weekDay, String mdy, String startTime, String endTime) {
		return weekDay + ", " + mdy + ". " + startTime + " - " + endTime;
	}

	public String getCourseID() {
		return courseID;
	}
	
	public String getSection() {
		return section;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getLocation() {
		return location;
	}
}
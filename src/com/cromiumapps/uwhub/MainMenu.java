package com.cromiumapps.uwhub;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainMenu extends ListActivity {
    
    // Names of pages for users
	String pages[] = {"API Test Caller", "Events", "Courses Information", "Examination Information", "Food Services", "Staff Information", "Weather", "OMG UW"};
	// Names of pages from manifest. Case sensitive.
	String values[] = {"APITestCaller", "Events", "CoursesInfo", "ExamInfo", "FoodServices", "StaffInfo", "Weather", "OMGUW"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(MainMenu.this, android.R.layout.simple_list_item_1, pages));
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String choice = values[position];
		super.onListItemClick(l, v, position, id);
		
		try{
			Class linkClass = Class.forName("com.cromiumapps.uwhub." + choice);
			Intent ourIntent = new Intent(MainMenu.this, linkClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

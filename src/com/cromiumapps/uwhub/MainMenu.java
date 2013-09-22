package com.cromiumapps.uwhub;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainMenu extends ListActivity {
    
    // Names of pages for users
	String pages[] = {/*"API Test Caller",*/ "Events", "Courses Information", "Examination Information", /*"Food Services",*/ /*"Staff Information",*/ "Weather", "OMG UW"};
	// Names of pages from manifest. Case sensitive.
	String values[] = {/*"APITestCaller",*/ "Events", "CoursesInfo", "ExamInfo", /*"FoodServices",*/ /*"StaffInfo",*/ "Weather", "OMGUW"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(MainMenu.this, android.R.layout.simple_list_item_1, pages));
		
		if(!isNetworkAvailable())
		{
			showNetworkDownDialog();
		}
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		if(!isNetworkAvailable())
		{
			showNetworkDownDialog();
		}
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		if(!isNetworkAvailable())
		{
			showNetworkDownDialog();
		}
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
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	
	private void showNetworkDownDialog()
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
 
			// set title
			alertDialogBuilder.setTitle("Internet");
 
			// set dialog message
			alertDialogBuilder
				.setMessage("UwHub requres an internet connection!")
				.setCancelable(false)
				.setPositiveButton("Exit",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						finish();
						dialog.cancel();
					}
				  })
				.setNegativeButton("Wifi",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)); 
						finish();
						dialog.cancel();
					}
				});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
	}
}

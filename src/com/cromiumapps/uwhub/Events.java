package com.cromiumapps.uwhub;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cromiumapps.uwhub.OMGUW.SectionFragment;
import com.cromiumapps.uwhub.OMGUW.SectionsPagerAdapter;
import com.cromiumapps.uwhub.UWAPIWrapper.UWAPIWrapperListener;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


public class Events extends FragmentActivity implements UWAPIWrapperListener, OnClickListener {
	
	//copy pasted from OMGUW-----------------------------------
	
	// API Key
    // Adrian's API. But this is to be used to avoid conflicts.
    private String API_KEY;
    
    // UWAPIWrapper
    private static UWAPIWrapper apiWrapper;
    
	// Context
	Context ctx = this;
    
    //UI Elements
    static ArrayList<ListView> lvContent = new ArrayList<ListView>();
    SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;
   
    // Data and List Adapter
    static ArrayList<EventsListAdapter> EventsListadapters = new ArrayList<EventsListAdapter>();
    ArrayList<ArrayList<EventsData>> EventsLists = new ArrayList<ArrayList<EventsData>>();
    ArrayList<EventsData> Events = new ArrayList<EventsData>();
    
    // Others
    private final String LOG_TAG = "Events";
    public int CURRENT_QUERY = 0;
    
    //ui
    Button buttonCalendar;
    //--------------------------------------------------
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
	
		// Initialize an API wrapper object using the API key and this activity
		// as the listener.
		API_KEY = ((UWHUB) this.getApplication()).getAPI_KEY();
		apiWrapper = new UWAPIWrapper(API_KEY, this, ctx);
		
		sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		viewPager = (ViewPager) findViewById(R.id.pEvents);
		
		if(savedInstanceState != null){
		    viewPager.setAdapter(sectionsPagerAdapter);
		} else {
		    // Call Service
			Log.d("Events","calling UWAPI");
		    apiWrapper.callService("Events", this);
		}
		
		//buttonCalendar = (Button) findViewById(R.id.CalendarButton);
		//buttonCalendar.setOnClickListener(this);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(savedInstanceState);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_events, menu);
		return true;
	}
	
	@Override
    public void onUWAPIRequestComplete(JSONObject jsonObject, boolean success) {
	Log.d("Events","UWAPI returned request succesfully");
	try {
		if(this.CURRENT_QUERY == 0)
		{
			Log.d("Events","Current query id is 0");
		    JSONObject jsonResponse = jsonObject.getJSONObject("response");
		    JSONObject jsonData = jsonResponse.getJSONObject("data");
		    String date =  jsonData.getString("Date");
		    JSONArray jsonEvents = (JSONArray) jsonData.getJSONArray("result");
		    
			ArrayList <EventsData> tempEventsData = new ArrayList<EventsData>();
		    
			for (int j = 0; j < jsonEvents.length(); j++) {
			    JSONObject EventsObject = jsonEvents.getJSONObject(j);
			    tempEventsData.add(j, new EventsData(EventsObject.getString("ID"),date, EventsObject.getString("Name"), EventsObject.getString("Links"), EventsObject.getString("Description"),"",0));
				//tempEventsData.add(j, new EventsData("0", "2013-19-9", "Name", "Links", "Description"));
			}
			
			this.EventsLists.add(tempEventsData);
			
			//call next query
			CURRENT_QUERY=1;
			apiWrapper.callService("CalendarEvents", this);
		}
		
		if(this.CURRENT_QUERY == 1)
		{
			Log.d("Events","Current query id is 1");
			JSONObject jsonResponse = jsonObject.getJSONObject("response");
		    JSONObject jsonData = jsonResponse.getJSONObject("data");
		    JSONArray jsonEvents = (JSONArray) jsonData.getJSONArray("result");
		    
			ArrayList <EventsData> tempEventsData = new ArrayList<EventsData>();
		    
			for (int j = 0; j < jsonEvents.length(); j++) {
			    JSONObject EventsObject = jsonEvents.getJSONObject(j);
			    tempEventsData.add(j, new EventsData(EventsObject.getString("ID"), EventsObject.getString("When"), EventsObject.getString("Title"), EventsObject.getString("Link"), EventsObject.getString("Description"),EventsObject.getString("Where"),1));
				//tempEventsData.add(j, new EventsData("0", "2013-19-9", "Name", "Links", "Description","Where"));
			}
			
			this.EventsLists.add(tempEventsData);
			
			//call next query
			CURRENT_QUERY=2;
			apiWrapper.callService("Holidays", this);
		}
		
		if(this.CURRENT_QUERY == 2)
		{
			Calendar calendar = Calendar.getInstance();
			int curYear = calendar.get(Calendar.YEAR);
			
			Log.d("Events","Current query id is 2");
			JSONObject jsonResponse = jsonObject.getJSONObject("response");
		    JSONObject jsonData = jsonResponse.getJSONObject("data");
		    JSONArray jsonResultsArray = (JSONArray) jsonData.getJSONArray("result");
		    
		    JSONObject jsonResult = jsonResult = (JSONObject) jsonResultsArray.get(0);;
		    JSONArray jsonHolidays = jsonResult.getJSONObject("Holidays").getJSONArray("result");
		    
		    for(int i =0 ; i<jsonResultsArray.length();i++)
		    {
			     jsonResult = (JSONObject) jsonResultsArray.get(i);
			     jsonHolidays = jsonResult.getJSONObject("Holidays").getJSONArray("result");
			     if(Integer.parseInt(jsonResult.getString("Year"))==curYear) break; 
		    }
		    
			ArrayList <EventsData> tempEventsData = new ArrayList<EventsData>();
		    
			for (int j = 0; j < jsonHolidays.length(); j++) {
				JSONObject tempHoliday = (JSONObject) jsonHolidays.get(j);
			    tempEventsData.add(j, new EventsData(Integer.toString(j), tempHoliday.getString("Day")+" "+jsonResult.getString("Year"), tempHoliday.getString("Name"), "", "","",2));
				//tempEventsData.add(j, new EventsData("0", "2013-19-9", "Name", "Links", "Description"));
			}
			
			this.EventsLists.add(tempEventsData);
			
			//display view
			
			Log.d("Events","finished calling UWAPI");
			
			if(EventsLists.size()>0)
			{
				Log.d("Events","adding EventsListadapters");
			    EventsListadapters.add(0,new EventsListAdapter(ctx, EventsLists.get(0)));
			    EventsListadapters.add(1,new EventsListAdapter(ctx, EventsLists.get(1)));
			    EventsListadapters.add(2,new EventsListAdapter(ctx, EventsLists.get(2)));
			    
			    Log.d("Events","Setting sectionsPagerAdapter to viewPager");
			    viewPager.setAdapter(sectionsPagerAdapter);
			}else{
				Log.d("Events","EventsLists is empty, no data was retrieved");
				//finish();
			}
		}
		} catch (JSONException e) {
			Log.v(LOG_TAG, e.getMessage());
			// For testing
		}
	}

	@Override
	public void onClick(View src) {
		// TODO Auto-generated method stub
		/*if (src.getId() == R.id.CalendarButton) {
			//syncCalendarEvents();
			//start calendar app
			Intent calendarIntent = new Intent() ;
			
			//Froyo or greater
			calendarIntent.setClassName("com.android.calendar","com.android.calendar.LaunchActivity");
			
			PackageManager packageManager = getPackageManager();
			List<ResolveInfo> activities = packageManager.queryIntentActivities(calendarIntent, 0);
			
			
			boolean isIntentSafe = activities.size() > 0;
			
			if(isIntentSafe)
			{
				startActivity(calendarIntent);
			}else{
				Toast.makeText(getBaseContext(),"Android Calendar app is not installed!",Toast.LENGTH_LONG).show();
			}
		}*/
	}
	
	/*private void syncCalendarEvents()
	{
		for(int i = 0 ; i < Events.size();i++)
		{
			EventsData tempEvent = Events.get(i);
			Calendar c = Calendar.getInstance(); 
			long mseconds = c.getTimeInMillis();
					  
					  
		    final ContentValues event = new ContentValues();
		    event.put("calendar_id", Integer.parseInt(getuserCalendar()));
	
		    event.put("title", tempEvent.getName());
		    event.put("description", tempEvent.getDescription());
		    event.put("eventLocation", tempEvent.getLinks());
	
		    event.put("dtstart", tempEvent.getDateTimeInMilis());
		    event.put("dtend", tempEvent.getDateTimeInMilis()+3600000);
		    event.put("allDay", 0);   // 0 for false, 1 for true
		    event.put("hasAlarm", 0); // 0 for false, 1 for true
	
		    String timeZone = TimeZone.getDefault().getID();
		    event.put("eventTimezone", timeZone);
	
		    Uri baseUri;
		    if (Build.VERSION.SDK_INT >= 8) {
		        baseUri = Uri.parse("content://com.android.calendar/events");
		    } else {
		        baseUri = Uri.parse("content://calendar/events");
		    }
		    Log.d("CalendarQuery",this.getContentResolver().insert(baseUri, event).toString());
		}
	}
	
	private String getuserCalendar()
	{
		
		String[] projection = new String[] { "_id", "name" };
		Uri calendars = Uri.parse("content://com.android.calendar/calendars");
		     
		Cursor managedCursor = getContentResolver().query(calendars, projection,"selected=1", null, null);
		
		String calId = ""; 
		
		if (managedCursor.moveToFirst()) {
			 String calName; 
			 int nameColumn = managedCursor.getColumnIndex("name"); 
			 int idColumn = managedCursor.getColumnIndex("_id");
			 do 
			 {
			    calName = managedCursor.getString(nameColumn);
			    calId = managedCursor.getString(idColumn);
			    Log.d("CalendarQuery","CalID: "+calId);
			 } while (managedCursor.moveToNext());
		}
		
		return calId;
	}*/
	
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public static class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			return new SectionFragment(position);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			
			if(position == 0)return "Today's Events";
			if(position == 1) return "Calendar Events";
			if(position == 2) return "Holidays";
			return "No Data";
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class SectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		public final int sectionID;

		public SectionFragment(int sectionID) {
			this.sectionID = sectionID;
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			if (savedInstanceState == null) {
				lvContent.add(sectionID, new ListView(getActivity()));
			}

			lvContent.get(sectionID).setAdapter(EventsListadapters.get(sectionID));

			setRetainInstance(true);
			return lvContent.get(sectionID);

		}
	}
}

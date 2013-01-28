package com.cromiumapps.uwhub;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cromiumapps.uwhub.OMGUW.SectionFragment;
import com.cromiumapps.uwhub.OMGUW.SectionsPagerAdapter;
import com.cromiumapps.uwhub.UWAPIWrapper.UWAPIWrapperListener;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class Events extends FragmentActivity implements UWAPIWrapperListener {
	
	//copy pasted from OMGUW-----------------------------------
	
	// API Key
    // Adrian's API. But this is to be used to avoid conflicts.
    private String API_KEY;
    
    // UWAPIWrapper
    private static UWAPIWrapper apiWrapper;
    
    //Context
    Context ctx = this;
    
  //UI Elements
    static ArrayList<ListView> lvContent = new ArrayList<ListView>();
    SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;
    
    // Data and List Adapter
    static ArrayList<EventsListAdapter> EventsListadapters = new ArrayList<EventsListAdapter>();
    ArrayList<ArrayList<EventsData>> EventsList = new ArrayList<ArrayList<EventsData>>();
    ArrayList<EventsData> Events;
    
 // Others
    private final String LOG_TAG = "Events";
    
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
		viewPager = (ViewPager) findViewById(R.id.pOMGUW);
		
		if(savedInstanceState != null){
		    viewPager.setAdapter(sectionsPagerAdapter);
		} else {
		    // Call Service
		    apiWrapper.callService("Events", ctx);	    
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_events, menu);
		return true;
	}
	
	//copied from OMGUW-------------------------------------
	@Override
    public void onUWAPIRequestComplete(JSONObject jsonObject, boolean success) {
	
	final String EVENTS = "Events";
	final String ILU = "ILUs";
	final String MCs = "MCs";
	final String OVERHEARDs = "OVERHEARDs";
	final String ASK = "ASK";
	
	try {
	    JSONObject jsonResponse = jsonObject.getJSONObject("response");
	    JSONObject jsonData = jsonResponse.getJSONObject("data");
	    JSONObject jsonEvents = null;
	    
	    for (int i = 0; i < jsonData.length(); i++) {
		switch (i) {
		    case 0: jsonEvents = jsonData.getJSONObject(EVENTS);
		    break;
		    
		    case 1: jsonEvents = jsonData.getJSONObject(ILU);
		    break; 
		    
		    case 2: jsonEvents = jsonData.getJSONObject(MCs);
		    break;

		    case 3: jsonEvents = jsonData.getJSONObject(OVERHEARDs);
		    break;

		    case 4: jsonEvents = jsonData.getJSONObject(ASK);
		    break;
		    
		}
		Log.i(LOG_TAG, Integer.toString(i));
		JSONArray jsonEventsResult = jsonEvents.getJSONArray("result");
			
		Events = new ArrayList<EventsData>();
		for (int j = 0; j < jsonEventsResult.length(); j++) {
		    JSONObject EventsObject = jsonEventsResult.getJSONObject(j);
		    Events.add(j, new EventsData(EventsObject.getString("ID"), EventsObject.getString("Date"), EventsObject.getString("Content"), EventsObject.getString("Link"), EventsObject.getString("Type")));
		}
		EventsList.add(i, Events);
			
		EventsListadapters.add(i, new EventsListAdapter(ctx, EventsList.get(i)));
	    }
	    
	    viewPager.setAdapter(sectionsPagerAdapter);
	    
		} catch (JSONException e) {
			Log.v(LOG_TAG, e.getMessage());
		}
	
    }
    
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
	    // Show 5 total pages.
	    return 5;
	}

	@Override
	public CharSequence getPageTitle(int position) {
	
	    return EventsListadapters.get(position).getData(position).getType();
	    
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

	    if(savedInstanceState==null){
		lvContent.add(sectionID, new ListView(getActivity()));
	    }

	    lvContent.get(sectionID).setAdapter(EventsListadapters.get(sectionID));
	    
	    setRetainInstance(true);
	    return lvContent.get(sectionID);

	}
    }
    //--------------------------------------------------------

}

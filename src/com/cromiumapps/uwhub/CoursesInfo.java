package com.cromiumapps.uwhub;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.cromiumapps.uwhub.UWAPIWrapper.UWAPIWrapperListener;

// For testing
import android.widget.Toast;

public class CoursesInfo extends FragmentActivity implements UWAPIWrapperListener {

	// API Key
	// Adrian's API. But this is to be used to avoid conflicts.
	private String API_KEY;
	
	// UWAPIWrapper
	private static UWAPIWrapper apiWrapper;
	
	// Context
	Context ctx = this;
	
	// UI Elements
	//static ArrayList<ListView> lvContent = new ArrayList<ListView>();
	//SectionsPagerAdapter sectionsPagerAdapter;
	//ViewPager viewPager;
	ListView lvCourseInfoContent;
	
	// Data and List Adapter
	//static ArrayList<CoursesInfoListAdapter> CoursesInfoListadapters = new ArrayList<CoursesInfoListAdapter>();
	//ArrayList<ArrayList<CoursesInfoData>> coursesList = new ArrayList<ArrayList<CoursesInfoData>>();

	CoursesInfoListAdapter CoursesInfoListadapter;
	ArrayList<CoursesInfoData> courses = new ArrayList<CoursesInfoData>();
	
	// Others
	private final String LOG_TAG = "CoursesInfo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coursesinfo);

		// Initialize an API wrapper object using the API key and this activity
		// as the listener.
		API_KEY = ((UWHUB) this.getApplication()).getAPI_KEY();
		apiWrapper = new UWAPIWrapper(API_KEY, this, ctx);

		//sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		//viewPager = (ViewPager) findViewById(R.id.pCoursesInfo);
		ListView lvCourseInfoContent = (ListView) findViewById(R.id.lvCourseInfoContent);
		
		CoursesInfoListadapter = new CoursesInfoListAdapter(ctx, courses);
		lvCourseInfoContent.setAdapter(CoursesInfoListadapter);
		
		if(savedInstanceState != null){
			//viewPager.setAdapter(sectionsPagerAdapter);
		} else {
			// Call Service
			// Need to get full list...
			apiWrapper.callService("CourseSearch", "CS", ctx);	
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_coursesinfo, menu);
		return true;
	}

	@Override
	public void onUWAPIRequestComplete(JSONObject jsonObject, boolean success) {	
		try {
			JSONObject jsonResponse = jsonObject.getJSONObject("response");
			JSONObject jsonData = jsonResponse.getJSONObject("data");
			JSONArray jsonCoursesInfoResult = jsonData.getJSONArray("result");
			
			for (int j = 0; j < jsonCoursesInfoResult.length(); j++) {
				JSONObject coursesObject = jsonCoursesInfoResult.getJSONObject(j);
				courses.add(j, new CoursesInfoData(coursesObject.getString("DeptAcronym"), coursesObject.getString("Number"), coursesObject.getString("Title"), coursesObject.getString("Description")));
			}
			CoursesInfoListadapter.notifyDataSetChanged();
		/*
		viewPager.setAdapter(sectionsPagerAdapter);
		*/
		} catch (JSONException e) {
			Log.v(LOG_TAG, e.getMessage());

			// For testing
			Toast.makeText(this, "Throws exception", Toast.LENGTH_SHORT).show();
		}
	}
    
    /**
	* A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	* one of the sections/tabs/pages.
	*/
	/*
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
			// temporary
			return "Course List";
			// For multipage
			// return CoursesInfoListadapters.get(position).getData(position).getType();
		}
	}
*/
	/**
	* A dummy fragment representing a section of the app, but that simply
	* displays dummy text.
	*/
	/*
    public static class SectionFragment extends Fragment {
    */
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
	/*
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

		    lvContent.get(sectionID).setAdapter(CoursesInfoListadapters.get(sectionID));
		    
		    setRetainInstance(true);
		    return lvContent.get(sectionID);
		}
		
    }
    */
}
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

			// Auto generated callService lines included for testing
			/*
			apiWrapper.callService("CourseSearch", "WS", ctx);
			apiWrapper.callService("CourseSearch", "WKRPT", ctx);
			apiWrapper.callService("CourseSearch", "WHMIS", ctx);
			apiWrapper.callService("CourseSearch", "VCULT", ctx);
			apiWrapper.callService("CourseSearch", "UNIV", ctx);
			apiWrapper.callService("CourseSearch", "UN", ctx);
			apiWrapper.callService("CourseSearch", "TS", ctx);
			apiWrapper.callService("CourseSearch", "TPM", ctx);
			apiWrapper.callService("CourseSearch", "TOUR", ctx);
			apiWrapper.callService("CourseSearch", "TN", ctx);
			apiWrapper.callService("CourseSearch", "TAX", ctx);
			apiWrapper.callService("CourseSearch", "SYDE", ctx);
			apiWrapper.callService("CourseSearch", "SWREN", ctx);
			apiWrapper.callService("CourseSearch", "SWK", ctx);
			apiWrapper.callService("CourseSearch", "STV", ctx);
			apiWrapper.callService("CourseSearch", "STAT", ctx);
			apiWrapper.callService("CourseSearch", "SPCOM", ctx);
			apiWrapper.callService("CourseSearch", "SPAN", ctx);
			apiWrapper.callService("CourseSearch", "SOCWK", ctx);
			apiWrapper.callService("CourseSearch", "SOCIN", ctx);
			apiWrapper.callService("CourseSearch", "SOC", ctx);
			apiWrapper.callService("CourseSearch", "SMF", ctx);
			apiWrapper.callService("CourseSearch", "SI", ctx);
			apiWrapper.callService("CourseSearch", "SEQ", ctx);
			apiWrapper.callService("CourseSearch", "SE", ctx);
			apiWrapper.callService("CourseSearch", "SDS", ctx);
			apiWrapper.callService("CourseSearch", "SCI", ctx);
			apiWrapper.callService("CourseSearch", "SCBUS", ctx);
			apiWrapper.callService("CourseSearch", "RUSS", ctx);
			apiWrapper.callService("CourseSearch", "RS", ctx);
			apiWrapper.callService("CourseSearch", "RELC", ctx);
			apiWrapper.callService("CourseSearch", "REES", ctx);
			apiWrapper.callService("CourseSearch", "REC", ctx);
			apiWrapper.callService("CourseSearch", "QIC", ctx);
			apiWrapper.callService("CourseSearch", "PSYCH", ctx);
			apiWrapper.callService("CourseSearch", "PSCI", ctx);
			apiWrapper.callService("CourseSearch", "PS", ctx);
			apiWrapper.callService("CourseSearch", "PORT", ctx);
			apiWrapper.callService("CourseSearch", "POLSH", ctx);
			apiWrapper.callService("CourseSearch", "PMATH", ctx);
			apiWrapper.callService("CourseSearch", "PLAN", ctx);
			apiWrapper.callService("CourseSearch", "PHYS", ctx);
			apiWrapper.callService("CourseSearch", "PHS", ctx);
			apiWrapper.callService("CourseSearch", "PHIL", ctx);
			apiWrapper.callService("CourseSearch", "PHARM", ctx);
			apiWrapper.callService("CourseSearch", "PDPHRM", ctx);
			apiWrapper.callService("CourseSearch", "PD", ctx);
			apiWrapper.callService("CourseSearch", "PACS", ctx);
			apiWrapper.callService("CourseSearch", "OPTOM", ctx);
			apiWrapper.callService("CourseSearch", "NES", ctx);
			apiWrapper.callService("CourseSearch", "NE", ctx);
			apiWrapper.callService("CourseSearch", "NATST", ctx);
			apiWrapper.callService("CourseSearch", "NANO", ctx);
			apiWrapper.callService("CourseSearch", "MUSIC", ctx);
			apiWrapper.callService("CourseSearch", "MTHEL", ctx);
			apiWrapper.callService("CourseSearch", "MTE", ctx);
			apiWrapper.callService("CourseSearch", "MSCI", ctx);
			apiWrapper.callService("CourseSearch", "MNS", ctx);
			apiWrapper.callService("CourseSearch", "MEDVL", ctx);
			apiWrapper.callService("CourseSearch", "ME", ctx);
			apiWrapper.callService("CourseSearch", "MATH", ctx);
			apiWrapper.callService("CourseSearch", "MATBUS", ctx);
			apiWrapper.callService("CourseSearch", "LS", ctx);
			apiWrapper.callService("CourseSearch", "LED", ctx);
			apiWrapper.callService("CourseSearch", "LAT", ctx);
			apiWrapper.callService("CourseSearch", "KPE", ctx);
			apiWrapper.callService("CourseSearch", "KOREA", ctx);
			apiWrapper.callService("CourseSearch", "KIN", ctx);
			apiWrapper.callService("CourseSearch", "JS", ctx);
			apiWrapper.callService("CourseSearch", "JAPAN", ctx);
			apiWrapper.callService("CourseSearch", "ITALST", ctx);
			apiWrapper.callService("CourseSearch", "ITAL", ctx);
			apiWrapper.callService("CourseSearch", "IS", ctx);
			apiWrapper.callService("CourseSearch", "INTTS", ctx);
			apiWrapper.callService("CourseSearch", "INTST", ctx);
			apiWrapper.callService("CourseSearch", "INTEG", ctx);
			apiWrapper.callService("CourseSearch", "INDEV", ctx);
			apiWrapper.callService("CourseSearch", "HUMSC", ctx);
			apiWrapper.callService("CourseSearch", "HSG", ctx);
			apiWrapper.callService("CourseSearch", "HRM", ctx);
			apiWrapper.callService("CourseSearch", "HLTH", ctx);
			apiWrapper.callService("CourseSearch", "HIST", ctx);
			apiWrapper.callService("CourseSearch", "GS", ctx);
			apiWrapper.callService("CourseSearch", "GRK", ctx);
			apiWrapper.callService("CourseSearch", "GLOBAL", ctx);
			apiWrapper.callService("CourseSearch", "GGOV", ctx);
			apiWrapper.callService("CourseSearch", "GERON", ctx);
			apiWrapper.callService("CourseSearch", "GER", ctx);
			apiWrapper.callService("CourseSearch", "GEOG", ctx);
			apiWrapper.callService("CourseSearch", "GEOE", ctx);
			apiWrapper.callService("CourseSearch", "GENE", ctx);
			apiWrapper.callService("CourseSearch", "GDBA", ctx);
			apiWrapper.callService("CourseSearch", "FR", ctx);
			apiWrapper.callService("CourseSearch", "FINE", ctx);
			apiWrapper.callService("CourseSearch", "ESL", ctx);
			apiWrapper.callService("CourseSearch", "ERS", ctx);
			apiWrapper.callService("CourseSearch", "ENVE", ctx);
			apiWrapper.callService("CourseSearch", "ENGL", ctx);
			apiWrapper.callService("CourseSearch", "ENBUS", ctx);
			apiWrapper.callService("CourseSearch", "ELPE", ctx);
			apiWrapper.callService("CourseSearch", "ECON", ctx);
			apiWrapper.callService("CourseSearch", "ECE", ctx);
			apiWrapper.callService("CourseSearch", "EASIA", ctx);
			apiWrapper.callService("CourseSearch", "EARTH", ctx);
			apiWrapper.callService("CourseSearch", "DUTCH", ctx);
			apiWrapper.callService("CourseSearch", "DRAMA", ctx);
			apiWrapper.callService("CourseSearch", "DM", ctx);
			apiWrapper.callService("CourseSearch", "DEI", ctx);
			apiWrapper.callService("CourseSearch", "DAC", ctx);
			apiWrapper.callService("CourseSearch", "CT", ctx);  */
			apiWrapper.callService("CourseSearch", "CS", ctx);  /*
			apiWrapper.callService("CourseSearch", "CROAT", ctx);  
			apiWrapper.callService("CourseSearch", "COOP", ctx);
			apiWrapper.callService("CourseSearch", "CONST", ctx);
			apiWrapper.callService("CourseSearch", "COMST", ctx);
			apiWrapper.callService("CourseSearch", "COMM", ctx);
			apiWrapper.callService("CourseSearch", "COGSCI", ctx);
			apiWrapper.callService("CourseSearch", "CO", ctx);
			apiWrapper.callService("CourseSearch", "CMW", ctx);
			apiWrapper.callService("CourseSearch", "CM", ctx);
			apiWrapper.callService("CourseSearch", "CLAS", ctx);
			apiWrapper.callService("CourseSearch", "CIVE", ctx);
			apiWrapper.callService("CourseSearch", "CHINA", ctx);
			apiWrapper.callService("CourseSearch", "CHE", ctx);
			apiWrapper.callService("CourseSearch", "CEDEV", ctx);
			apiWrapper.callService("CourseSearch", "BUS", ctx);
			apiWrapper.callService("CourseSearch", "BIOL", ctx);
			apiWrapper.callService("CourseSearch", "BET", ctx);
			apiWrapper.callService("CourseSearch", "AVIA", ctx);
			apiWrapper.callService("CourseSearch", "ASTRN", ctx);
			apiWrapper.callService("CourseSearch", "ARTS", ctx);
			apiWrapper.callService("CourseSearch", "ARCHL", ctx);
			apiWrapper.callService("CourseSearch", "ARCH", ctx);
			apiWrapper.callService("CourseSearch", "ARBUS", ctx);
			apiWrapper.callService("CourseSearch", "APPLS", ctx);
			apiWrapper.callService("CourseSearch", "ANTH", ctx);
			apiWrapper.callService("CourseSearch", "AMATH", ctx);
			apiWrapper.callService("CourseSearch", "AHS", ctx);
			apiWrapper.callService("CourseSearch", "AFM", ctx);
			apiWrapper.callService("CourseSearch", "ADMGT", ctx);
			apiWrapper.callService("CourseSearch", "ACTSC", ctx);
			apiWrapper.callService("CourseSearch", "ACC", ctx);   */
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
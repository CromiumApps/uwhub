package com.cromiumapps.uwhub;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.cromiumapps.uwhub.UWAPIWrapper.UWAPIWrapperListener;

// For testing
import android.widget.Toast;

public class CoursesInfo extends Activity implements UWAPIWrapperListener {

	// Context
	Context ctx = this;

	// API Key
	// Adrian's API. But this is to be used to avoid conflicts.
	private String API_KEY;

	// UWAPIWrapper
	private static UWAPIWrapper apiWrapper;

	// UI Elements
	private ListView coursesInfoContent;

	private CoursesInfoListAdapter coursesInfoListadapter;
	private ArrayList<CoursesInfoData> courses = new ArrayList<CoursesInfoData>();

	// Others
	private final String LOG_TAG = "CoursesInfo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coursesinfo);

		// UI Elements
		ListView coursesInfoContent = (ListView) findViewById(R.id.coursesInfoContent);

		coursesInfoListadapter = new CoursesInfoListAdapter(ctx, courses);
		coursesInfoContent.setAdapter(coursesInfoListadapter);

		// Initialize an API wrapper object using the API key and this activity
		// as the listener.
		API_KEY = ((UWHUB) this.getApplication()).getAPI_KEY();
		apiWrapper = new UWAPIWrapper(API_KEY, this, ctx);

		final String[] faculties = {"ACC","ACTSC","ADMGT","AFM","AHS","AMATH","ANTH","APPLS","ARBUS","ARCH","ARCHL","ARTS","ASTRN","AVIA","BET","BIOL","BUS","CEDEV","CHE","CHINA","CIVE","CLAS","CM","CMW","CO","COGSCI","COMM","COMST","CONST","COOP","CROAT","CS","CT","DAC","DEI","DM","DRAMA","DUTCH","EARTH","EASIA","ECE","ECON","ELPE","ENBUS","ENGL","ENVE","ERS","ESL","FINE","FR","GDBA","GENE","GEOE","GEOG","GER","GERON","GGOV","GLOBAL","GRK","GS","HIST","HLTH","HRM","HSG","HUMSC","INDEV","INTEG","INTST","INTTS","IS","ITAL","ITALST","JAPAN","JS","KIN","KOREA","KPE","LAT","LED","LS","MATBUS","MATH","ME","MEDVL","MNS","MSCI","MTE","MTHEL","MUSIC","NANO","NATST","NE","NES","OPTOM","PACS","PD","PDPHRM","PHARM","PHIL","PHS","PHYS","PLAN","PMATH","POLSH","PORT","PS","PSCI","PSYCH","QIC","REC","REES","RELC","RS","RUSS","SCBUS","SCI","SDS","SE","SEQ","SI","SMF","SOC","SOCIN","SOCWK","SPAN","SPCOM","STAT","STV","SWK","SWREN","SYDE","TAX","TN","TOUR","TPM","TS","UN","UNIV","VCULT","WHMIS","WKRPT","WS"};

		new AlertDialog.Builder(this)
			.setTitle("Select a Faculty")
			.setItems(faculties, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					apiWrapper.callService("CourseSearch", faculties[item], ctx);
				}
			}).show();
	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
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
			coursesInfoListadapter.notifyDataSetChanged();
		} catch (JSONException e) {
			Log.v(LOG_TAG, e.getMessage());
		}
	}
}
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
import android.widget.EditText;
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

		final EditText input = new EditText(this);

		new AlertDialog.Builder(this)
			.setTitle("Search")
			.setMessage("Please enter a Faculty or Course ID")
			.setView(input)
			.setPositiveButton("Go", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					apiWrapper.callService("CourseSearch", input.getText().toString(), ctx);
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

			// For testing
			Toast.makeText(this, "Throws exception", Toast.LENGTH_SHORT).show();
		}
	}
}
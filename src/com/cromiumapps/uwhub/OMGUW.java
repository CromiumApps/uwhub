package com.cromiumapps.uwhub;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.cromiumapps.uwhub.UWAPIWrapper.UWAPIWrapperListener;

public class OMGUW extends Activity implements UWAPIWrapperListener {

    // API Key
    // Adrian's API. But this is to be used to avoid conflicts.
    private final String API_KEY = ((UWHUB) this.getApplication()).getAPI_KEY();
    
    // UWAPIWrapper
    private UWAPIWrapper apiWrapper;
    
    //UI Elements
    ListView lvOMGUWContent;
    
    // Data and List Adapter
    OMGUWListAdapter OMGUWListadapter;
    ArrayList<OMGUWData> omguws = new ArrayList<OMGUWData>();
    
    // Others
    private final String LOG_TAG = "OMGUW";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_omguw);
	
		lvOMGUWContent = (ListView) findViewById(R.id.lvOMGUWContent);
	
		OMGUWListadapter = new OMGUWListAdapter(this, omguws);
		lvOMGUWContent.setAdapter(OMGUWListadapter);
	
		// Initialize an API wrapper object using the API key and this activity
		// as the listener.
		apiWrapper = new UWAPIWrapper(API_KEY, this);
	
		//Call Service
		apiWrapper.callService("OMGUW");
    }

    @Override
		public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_omguw, menu);
		return true;
    }

    @Override
    public void onUWAPIRequestComplete(JSONObject jsonObject, boolean success) {
		try {
			JSONObject jsonResponse = jsonObject.getJSONObject("response");
			JSONObject jsonData = jsonResponse.getJSONObject("data");
			JSONObject jsonOMG = jsonData.getJSONObject("OMG");
			JSONArray jsonResult = jsonOMG.getJSONArray("result");
			
			for (int i = 0; i < jsonResult.length(); i++) {
				JSONObject omguwObject = jsonResult.getJSONObject(i);
				omguws.add(new OMGUWData(omguwObject.getString("ID"), omguwObject.getString("Date"), omguwObject.getString("Content"), omguwObject.getString("Link"), omguwObject.getString("Type")));
			}
			
			OMGUWListadapter.setData(omguws);
			OMGUWListadapter.notifyDataSetChanged();
		} catch (JSONException e) {
			Log.v(LOG_TAG, e.getMessage());
		}
    }
}
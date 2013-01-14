package com.cromiumapps.uwhub;

import org.json.JSONObject;

import uwapi.UWAPIWrapper;
import uwapi.UWAPIWrapper.UWAPIWrapperListener;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class APITestCaller extends Activity implements UWAPIWrapperListener {

    //Context
    Context ctx = this;
    
    // API Key
    // Adrian's API. But this is to be used to avoid conflicts.
    private static final String API_KEY = "1d425dd8265304177d7731007388191f";

    // UI elements
    private EditText etService;
    private EditText etParameter;
    private ToggleButton tbParameter;
    private Button bServiceCall;
    private TextView tvService;

    // UWAPIWrapper
    private UWAPIWrapper apiWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_apitest_caller);

	// UI elements
	etService = (EditText) findViewById(R.id.etAPITestCallerPService);
	etParameter = (EditText) findViewById(R.id.etAPITestCallerParameter);
	tbParameter = (ToggleButton) findViewById(R.id.tbAPITestCallerParameter);
	bServiceCall = (Button) findViewById(R.id.bAPITestCallerServiceCall);
	tvService = (TextView) findViewById(R.id.tvAPITestCallerService);

	// Initialize an API wrapper object using the API key and this activity
	// as the listener.
	apiWrapper = new UWAPIWrapper(API_KEY, this);

    }

    public void serviceAction(View view) {
	
	// Hide keyboard
	InputMethodManager inputManager = (InputMethodManager) getSystemService(ctx.INPUT_METHOD_SERVICE);
	inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	
	// Call the Service with/without using parameters.
	if (tbParameter.isChecked() && !(etParameter.getText().toString().matches(""))) {
	    apiWrapper.callService(etService.getText().toString(), etParameter.getText().toString());
	} else if (!(etService.getText().toString().matches(""))) {
	    apiWrapper.callService(etService.getText().toString());
	} else {
	    tvService.setText("Please enter a Service");
	}
    }

    @Override
    public void onUWAPIRequestComplete(JSONObject jsonObject, boolean success) {
	if (success) {
	    // Got result JSON
	    // See output format at: http://api.uwaterloo.ca/#!/coursesearch
	    tvService.setText(jsonObject.toString());
	} else {
	    // Request failed (most likely network issue).
	    tvService.setText("Request Failed! Check your network.");
	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.activity_apitest_caller, menu);
	return true;
    }

}

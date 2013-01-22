package com.cromiumapps.uwhub;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Weather extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_weather);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.activity_weather, menu);
	return true;
    }

}

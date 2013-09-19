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

public class Weather extends Activity implements UWAPIWrapperListener {

    // Context
    Context ctx = this;

    // API Key
    // Adrian's API. But this is to be used to avoid conflicts.
    private String API_KEY;

    // UWAPIWrapper
    private static UWAPIWrapper apiWrapper;

    // UI Elements

    private WeatherListAdapter weatherListadapter;
    private ArrayList<WeatherData> weatherInfo = new ArrayList<WeatherData>();

    // Others
    private final String LOG_TAG = "Weather";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // UI Elements
        ListView weatherContent = (ListView) findViewById(R.id.weatherContent);

        weatherListadapter = new WeatherListAdapter(ctx, weatherInfo);
        weatherContent.setAdapter(weatherListadapter);

        // Initialize an API wrapper object using the API key and this activity
        // as the listener.
        API_KEY = ((UWHUB) this.getApplication()).getAPI_KEY();
        apiWrapper = new UWAPIWrapper(API_KEY, this, ctx);

        apiWrapper.callService("Weather", ctx);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        getMenuInflater().inflate(R.menu.activity_weather, menu);
        return true;
    }
    @Override
    public void onUWAPIRequestComplete(JSONObject jsonObject, boolean success) {
        try {
            JSONObject jsonResponse = jsonObject.getJSONObject("response");
            JSONObject jsonData = jsonResponse.getJSONObject("data");
            JSONObject jsonWeatherResult = jsonData.getJSONObject("Current");
            JSONObject weatherObject = jsonWeatherResult;
            weatherInfo.add(0, new WeatherData(weatherObject.getString("Day"), weatherObject.getString("Condition"), weatherObject.getString("Icon"), weatherObject.getString("Temp"), weatherObject.getString("Max"), weatherObject.getString("Min")));


            //testing
            JSONObject jsonWeatherWeek = jsonData.getJSONObject("Week");
            JSONArray jsonWeatherWeekday = jsonWeatherWeek.getJSONArray("result");
            for (int j = 1; j < 5; j++) { //so far the forecast is only 5 days it seems
                weatherObject = jsonWeatherWeekday.getJSONObject(j-1);
                weatherInfo.add(j, new WeatherData(weatherObject.getString("Day"), weatherObject.getString("Condition"), weatherObject.getString("Image"), weatherObject.getString("High"), weatherObject.getString("Low")));
            }

            weatherListadapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Log.v(LOG_TAG, e.getMessage());
        }
    }
}
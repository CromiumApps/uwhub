package com.cromiumapps.uwhub;


import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherListAdapter extends BaseAdapter {

    // context
    private Context context;

    // views
    private LayoutInflater inflater;

    // data
    private ArrayList<WeatherData> weatherInfo;

    public WeatherListAdapter(Context context, ArrayList<WeatherData> weatherInfo) {
        this.context = context;
        this.weatherInfo = weatherInfo;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return weatherInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return weatherInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(ArrayList<WeatherData> weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public WeatherData getData(int position) {
        return this.weatherInfo.get(position);
    }
    public int setImageBase(String stringBase){
        int intBase=Integer.parseInt(stringBase);
        if(intBase>=30) intBase-=1; //no wi29 for some reason

        return intBase+2130837505; //2130837505 is the id of drawable wi00.gif, which we use as base to increment
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;
        if (convertView != null) {
            v = convertView;
        } else {
            v = inflater.inflate(R.layout.layout_weather, parent, false);
        }

        WeatherData weatherInfo = (WeatherData) getItem(position);
        TextView tvLAYOUTWEATHERTypeID = (TextView) v.findViewById(R.id.tvLAYOUTWEATHERTypeID);
        TextView tvLAYOUTWEATHERDate = (TextView) v.findViewById(R.id.tvLAYOUTWEATHERDate);
        TextView tvLAYOUTWEATHERContent = (TextView) v.findViewById(R.id.tvLAYOUTWEATHERContent);
        ImageView tvLAYOUTWEATHERIcon = (ImageView) v.findViewById(R.id.tvLAYOUTWEATHERIcon);

        tvLAYOUTWEATHERTypeID.setText(weatherInfo.getDay() + ": " + weatherInfo.getCondition());
        tvLAYOUTWEATHERIcon.setImageResource(setImageBase(weatherInfo.getIcon()));

        if(weatherInfo.getDay().equals("Today")){
            tvLAYOUTWEATHERDate.setText("Current Temperature: " + weatherInfo.getTemp()+" °C");
            tvLAYOUTWEATHERContent.setText("Maximum Temperature: " + weatherInfo.getMax() + " °C" + "\n" + "Minimum Temperature: " + weatherInfo.getMin() +" °C");
        }else{
            tvLAYOUTWEATHERDate.setText("");
            tvLAYOUTWEATHERContent.setText("Maximum Temperature: " + weatherInfo.getHigh()+" °C" + "\n" + "Minimum Temperature: " + weatherInfo.getLow()+" °C");
        }

        return v;
    }

}

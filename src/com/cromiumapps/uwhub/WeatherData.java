package com.cromiumapps.uwhub;

import android.content.Context;

public class WeatherData {

    //private
    Context context;

    String day = null;
    String condition = null;
    String icon = null;
    String temp = null;
    String max = null;
    String min = null;
    String high = null;
    String low = null;

//    public WeatherData(String inDay, String inCondition,  String inTemp, String inMax, String inMin, String inHigh, String inLow) {
    public WeatherData(String inDay, String inCondition, String inIcon, String inTemp, String inMax, String inMin){
        day = inDay;
        condition = inCondition;
        inIcon = inIcon.replace("http://m.weatheroffice.gc.ca/weathericons/","").replace(".gif", "");
        icon=inIcon;
        temp = inTemp;
        max = inMax;
        min = inMin;
    }
    //Overload constructor for days in future with no current temp
    public WeatherData(String inDay, String inCondition, String inIcon, String inHigh, String inLow){
        day = inDay;
        condition = inCondition;
        inIcon = inIcon.replace("http://m.weatheroffice.gc.ca/weathericons/","").replace(".gif", "");
        icon=inIcon;
        high = inHigh;
        low = inLow;
    }

    public String getDay() {
        return day;
    }

    public String getCondition() {
        return condition;
    }
    public String getIcon() {
        return icon;
    }

    public String getTemp() {
        return temp;
    }

    public String getMax() {
        return max;
    }
    public String getMin() {
        return min;
    }
    public String getHigh() {
        return high;
    }
    public String getLow() {
        return low;
    }
}
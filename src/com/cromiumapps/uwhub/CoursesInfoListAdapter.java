package com.cromiumapps.uwhub;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CoursesInfoListAdapter extends BaseAdapter {

    // context
    private Context context;

    // views
    private LayoutInflater inflater;

    // data
    private ArrayList<CoursesInfoData> courses;

    public CoursesInfoListAdapter(Context context, ArrayList<CoursesInfoData> courses) {
		this.context = context;
		this.courses = courses;
		inflater = LayoutInflater.from(context);
    }

    @Override
		public int getCount() {
		return courses.size();
    }

    @Override
    	public Object getItem(int position) {
		return courses.get(position);
    }

    @Override
    	public long getItemId(int position) {
		return position;
    }

    public void setData(ArrayList<CoursesInfoData> courses) {
		this.courses = courses;
    }
    
    public CoursesInfoData getData(int position) {
		return this.courses.get(position);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
		View v = null;
		if (convertView != null) {
			v = convertView;
		} else {
		    v = inflater.inflate(R.layout.layout_coursesinfo, parent, false);
		}

		CoursesInfoData courses = (CoursesInfoData) getItem(position);

		TextView tvLAYOUTCoursesInfoCode = (TextView) v.findViewById(R.id.tvLAYOUTCoursesInfoCode);
		TextView tvLAYOUTCoursesInfoTitle = (TextView) v.findViewById(R.id.tvLAYOUTCoursesInfoTitle);
		TextView tvLAYOUTCoursesInfoDescription = (TextView) v.findViewById(R.id.tvLAYOUTCoursesInfoDescription);
	
		tvLAYOUTCoursesInfoCode.setText(courses.getDept() + ": " + courses.getNumber());
		tvLAYOUTCoursesInfoTitle.setText(courses.getTitle());
		tvLAYOUTCoursesInfoDescription.setText(courses.getDescription());
	
		return v;
    }

}

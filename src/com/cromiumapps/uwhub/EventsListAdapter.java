package com.cromiumapps.uwhub;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EventsListAdapter extends BaseAdapter {

    // context
    private Context context;

    // views
    private LayoutInflater inflater;

    // data
    private ArrayList<EventsData> Events;

    public EventsListAdapter(Context context, ArrayList<EventsData> Events) {
	this.context = context;
	this.Events = Events;
	inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
	return Events.size();
    }

    @Override
    public Object getItem(int position) {
	return Events.get(position);
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    public void setData(ArrayList<OMGUWData> omguws) {
	this.Events = Events;
    }
    
    public EventsData getData(int position) {
	return this.Events.get(position);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	View v = null;
	if (convertView != null) {
		v = convertView;
	} else {
	    v = inflater.inflate(R.layout.layout_omguw, parent, false);
	}

	OMGUWData omguws = (OMGUWData) getItem(position);
	TextView tvLAYOUTOMGUWTypeID = (TextView) v.findViewById(R.id.tvLAYOUTOMGUWTypeID);
	TextView tvLAYOUTOMGUWDate = (TextView) v.findViewById(R.id.tvLAYOUTOMGUWDate);
	TextView tvLAYOUTOMGUWContent = (TextView) v.findViewById(R.id.tvLAYOUTOMGUWContent);
	
	tvLAYOUTOMGUWTypeID.setText(omguws.getType() + ": " + omguws.getId());
	tvLAYOUTOMGUWDate.setText(omguws.getDate());
	tvLAYOUTOMGUWContent.setText(omguws.getContent());
	
	return v;
    }

}

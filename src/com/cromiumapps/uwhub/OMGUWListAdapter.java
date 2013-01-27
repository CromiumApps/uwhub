package com.cromiumapps.uwhub;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OMGUWListAdapter extends BaseAdapter {

    // context
    private Context context;

    // views
    private LayoutInflater inflater;

    // data
    private ArrayList<OMGUWData> omguws;

    public OMGUWListAdapter(Context context, ArrayList<OMGUWData> omguws) {
	this.context = context;
	this.omguws = omguws;
	inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
	return omguws.size();
    }

    @Override
    public Object getItem(int position) {
	return omguws.get(position);
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    public void setData(ArrayList<OMGUWData> omguws) {
	this.omguws = omguws;
    }
    
    public OMGUWData getData(int position) {
	return this.omguws.get(position);
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

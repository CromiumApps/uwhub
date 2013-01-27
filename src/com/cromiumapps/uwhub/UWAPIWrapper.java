package com.cromiumapps.uwhub;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.cromiumapps.uwhub.JSONFetcher.JSONFetcherOnCompleteListener;

/*
 * Wrapper class for the University of Waterloo Open Data API. Modified by Adrian Gaw.
 */
public class UWAPIWrapper implements JSONFetcherOnCompleteListener {
	/*
	 * Listener class that handles the callback.
	 */
	public interface UWAPIWrapperListener {
		public void onUWAPIRequestComplete(JSONObject result, boolean success);
	}

	private static final String TAG = "UWAPIWrapper";// debug/log tag
	private static final String URL_FORMAT = "http://api.uwaterloo.ca/public/v1/?key=%s&service=%s&output=json";
	private static final String URL_FORMAT_WITH_QUERY = "http://api.uwaterloo.ca/public/v1/?key=%s&service=%s&q=%s&output=json";

	private String apiKey_;
	private Context context_;
	private UWAPIWrapperListener listener_;

	public UWAPIWrapper(String apiKey, UWAPIWrapperListener listener, Context ctx) {
		apiKey_ = apiKey;
		listener_ = listener;
		context_ = ctx;
	}

	/*
	 * Calls a service without query.
	 * See http://api.uwaterloo.ca for list of services and result structure.
	 * The listener's onUWAPIRequestComplete method will be called once
	 * the request is complete.
	 */
	public void callService(String service, Context ctx) {
		try {
			String queryString = String.format(URL_FORMAT,
					URLEncoder.encode(apiKey_, "utf-8"),
					URLEncoder.encode(service, "utf-8"));
			callWithUrl(queryString, ctx);
		} catch (UnsupportedEncodingException e) {
			Log.d(TAG, e.toString());
		}
	}

	/*
	 * Calls a service with query.
	 * See http://api.uwaterloo.ca for list of services and result structure.
 	 * The listener's onUWAPIRequestComplete method will be called once
	 * the request is complete.
	 */
	public void callService(String service, String parameter, Context ctx) {
		try {
			String queryString = String.format(URL_FORMAT_WITH_QUERY,
					URLEncoder.encode(apiKey_, "utf-8"),
					URLEncoder.encode(service, "utf-8"),
					URLEncoder.encode(parameter, "utf-8"));
			callWithUrl(queryString, ctx);
		} catch (UnsupportedEncodingException e) {
			Log.d(TAG, e.toString());
		}
	}

	private void callWithUrl(String url, Context ctx) {
		JSONFetcher fetcher = new JSONFetcher(this, ctx);
		fetcher.execute(url);
	}

	public void onJsonFetcherComplete(JSONObject result, boolean success) {
		listener_.onUWAPIRequestComplete(result, success);
	}
}

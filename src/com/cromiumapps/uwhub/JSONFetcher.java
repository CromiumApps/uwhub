/*
 * Created 12/11/13
 * Author: Bo Yin (bo@uwmobile.ca)
 */
package com.cromiumapps.uwhub;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

/*
 * Async task that fetches a JSON object from HTTP.
 */
public class JSONFetcher extends AsyncTask<String, Boolean, JSONObject> {
	public interface JSONFetcherOnCompleteListener {
		public void onJsonFetcherComplete(JSONObject object, boolean success);
	}

	private static final String TAG = "UWAPIWrapper/JsonFetcher";// debug/log tag

	private JSONFetcherOnCompleteListener listener_;

	public JSONFetcher(JSONFetcherOnCompleteListener listener) {
		listener_ = listener;
	}

	private String requestJsonData(String requestUrl)
		throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(new HttpGet(requestUrl));
		StatusLine statusLine = response.getStatusLine();

		String jsonString;
		if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			response.getEntity().writeTo(out);
			out.close();
			jsonString = out.toString();
		} else {
			response.getEntity().getContent().close();
			throw new IOException(statusLine.getReasonPhrase());
		}
		return jsonString;
	}

	@Override
	protected JSONObject doInBackground(String... params) {
		try {
			String jsonString = requestJsonData(params[0]);
			return new JSONObject(jsonString);
		} catch (ClientProtocolException e) {
			Log.d(TAG, e.toString());
			return null;
		} catch (IOException e) {
			Log.d(TAG, e.toString());
			return null;
		} catch (JSONException e) {
			Log.d(TAG, e.toString());
			return null;
		}
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		if (result == null) {
			listener_.onJsonFetcherComplete(null, false);
		} else {
			listener_.onJsonFetcherComplete(result, true);
		}
	}
}

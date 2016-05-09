package com.nawab.develop.photogrid.network;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.toolbox.JsonObjectRequest;
import com.nawab.develop.photogrid.listener.UpdateJsonListener;

public class VolleyJsonRequest extends JsonObjectRequest {

	private VolleyJsonRequest(int method, String url, UpdateJsonListener updateListener, String requestJson) throws JSONException {
		
		super(url,new JSONObject(requestJson), updateListener, updateListener);
	}
	
	public static VolleyJsonRequest doPost(String url, UpdateJsonListener updateListener, String requestJson) throws JSONException {
		return new VolleyJsonRequest(Method.POST, url, updateListener, requestJson);
	}
	
	public static VolleyJsonRequest doget(String url, UpdateJsonListener updateListener) throws JSONException {
		return new VolleyJsonRequest(Method.GET, url, updateListener, null);
	}

}
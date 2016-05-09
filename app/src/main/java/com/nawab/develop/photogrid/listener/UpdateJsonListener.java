package com.nawab.develop.photogrid.listener;

import org.json.JSONObject;

import android.util.Log;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.nawab.develop.photogrid.BuildConfig;
import com.nawab.develop.photogrid.network.VolleyExceptionUtil;

public class UpdateJsonListener implements ErrorListener, Listener<JSONObject> {

	private int reqType;
	private onUpdateViewJsonListener onUpdateViewJsonListener;

	public interface onUpdateViewJsonListener {
		public void updateView(String responseString, boolean isSuccess,
							   int reqType);
	}

	public UpdateJsonListener(onUpdateViewJsonListener onUpdateView, int reqType) {
		this.reqType = reqType;
		this.onUpdateViewJsonListener = onUpdateView;
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		onUpdateViewJsonListener.updateView(
				VolleyExceptionUtil.getErrorMessage(error), false, reqType);
	}

	@Override
	public void onResponse(JSONObject jsonResponse) {
		String responseStr = jsonResponse.toString();
		if (BuildConfig.DEBUG) {
			Log.i("Respone-------------->", responseStr);
		}
		try {
			onUpdateViewJsonListener.updateView(responseStr, true, reqType);
		} catch (Exception ex) {
			ex.printStackTrace();
			onUpdateViewJsonListener.updateView(responseStr, false, reqType);
		}

	}

}

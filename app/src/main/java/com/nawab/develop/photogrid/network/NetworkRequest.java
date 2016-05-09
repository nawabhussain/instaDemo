package com.nawab.develop.photogrid.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nawab.develop.photogrid.PhotoGridApplication;
import com.nawab.develop.photogrid.listener.UpdateListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Nawab on 01-05-2016.
 */
public class NetworkRequest {
    Context mContext;

    public NetworkRequest(Context context) {
        mContext = context;
    }

    public void doPost(String url, final UpdateListener updateListener, final Map<String, String> params) {

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if (response != null) {
                    JSONObject jsonObject;
                    try {
                        jsonObject = new JSONObject(response);


                        if (jsonObject != null && jsonObject.getString("error_type") == null) {
                            updateListener.onResponse(response);
                        } else {
//                            updateListener.onErrorResponse(response);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                updateListener.onErrorResponse(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Log.d("In PARams", "");
                return params;
            }
        };
        ((PhotoGridApplication) mContext.getApplicationContext())
                .getVolleyManagerInstance()
                .addToRequestQueue(request, url);
    }
}

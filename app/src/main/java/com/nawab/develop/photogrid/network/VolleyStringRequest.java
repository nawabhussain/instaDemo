package com.nawab.develop.photogrid.network;

import java.util.Map;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;
import com.nawab.develop.photogrid.listener.UpdateListener;

public class VolleyStringRequest extends StringRequest {

    private Map<String, String> mRequestparams;

    private VolleyStringRequest(int method, String url,
                                UpdateListener updateListener, Map<String, String> params) {
        super(method, url, updateListener, updateListener);
        mRequestparams = params;
    }

    public static VolleyStringRequest doPost(String url,UpdateListener updateListener, Map<String, String> params) {
        return new VolleyStringRequest(Method.POST, url, updateListener, params);
    }

    public static VolleyStringRequest doGet(String url,UpdateListener updateListener) {
        return new VolleyStringRequest(Method.GET, url, updateListener, null);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mRequestparams;
    }

}

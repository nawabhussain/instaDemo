package com.nawab.develop.photogrid.listener;

import android.util.Log;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.nawab.develop.photogrid.BuildConfig;
import com.nawab.develop.photogrid.network.VolleyExceptionUtil;
import com.nawab.develop.photogrid.presenter.IHitApiCallBack;

public class UpdateListener implements Listener<String>, ErrorListener {

    private int reqType;
    private onUpdateViewListener onUpdateViewListener;

    public interface onUpdateViewListener {
        void updateView(String responseString, boolean isSuccess,
                        int reqType);

    }

    public UpdateListener(onUpdateViewListener onUpdateView, int reqType) {
        this.reqType = reqType;
        this.onUpdateViewListener = onUpdateView;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        onUpdateViewListener.updateView(
                VolleyExceptionUtil.getErrorMessage(error), false, reqType);
    }

    @Override
    public void onResponse(String responseStr) {
        if (BuildConfig.DEBUG) {
        }
        try {
            onUpdateViewListener.updateView(responseStr, true, reqType);
        } catch (Exception ex) {
            ex.printStackTrace();
            onUpdateViewListener.updateView(responseStr, false, reqType);
        }

    }

}

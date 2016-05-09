package com.nawab.develop.photogrid;

import android.app.Application;

import com.nawab.develop.photogrid.network.VolleyManager;

/**
 * Created by Nawab on 01-05-2016.
 */
public class PhotoGridApplication extends Application {
    public VolleyManager getVolleyManagerInstance() {
        return VolleyManager.getInstance(getApplicationContext());
    }
}

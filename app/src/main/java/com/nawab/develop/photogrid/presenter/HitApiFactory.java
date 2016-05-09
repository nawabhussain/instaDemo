package com.nawab.develop.photogrid.presenter;

import android.content.Context;

/**
 * Created by nawab.hussain on 5/6/2016.
 */
public class HitApiFactory {
    public IHitApi getAccessHitApiLibrary(Context context){
        IHitApi iHitApi=HitApiImplementation.getInstance(context);
        return iHitApi;
    }
}

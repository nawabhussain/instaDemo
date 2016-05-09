package com.nawab.develop.photogrid.presenter;

import com.nawab.develop.photogrid.models.BaseModel;

/**
 * Created by nawab.hussain on 5/6/2016.
 */
public interface IHitApiCallBack {
    void onResponseHitApi(BaseModel response,int reqType,boolean isSuccess);
}

package com.nawab.develop.photogrid.presenter;

import com.nawab.develop.photogrid.models.BaseModel;

/**
 * Created by nawab.hussain on 5/6/2016.
 */
public interface IHitApi {
    void callWebService(int reqType, BaseModel model, IHitApiCallBack callBack);
    void callWebService(int reqType, BaseModel model,String mCode, IHitApiCallBack callBack);
}

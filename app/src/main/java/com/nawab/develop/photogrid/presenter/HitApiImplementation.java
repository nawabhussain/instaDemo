package com.nawab.develop.photogrid.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.nawab.develop.photogrid.PhotoGridApplication;
import com.nawab.develop.photogrid.R;
import com.nawab.develop.photogrid.listener.UpdateListener;
import com.nawab.develop.photogrid.models.AccessTokenModel;
import com.nawab.develop.photogrid.models.BaseModel;
import com.nawab.develop.photogrid.models.DataModel;
import com.nawab.develop.photogrid.network.VolleyStringRequest;
import com.nawab.develop.photogrid.utils.Constants;
import com.nawab.develop.photogrid.utils.SharedPrefsUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by nawab.hussain on 5/6/2016.
 */
public class HitApiImplementation implements IHitApi, UpdateListener.onUpdateViewListener {

    private static final String TAG = HitApiImplementation.class.getSimpleName();
    private static HitApiImplementation hitApiImplementation;
    private static SharedPrefsUtils sharedPrefsUtils;
    private Context mContext;
    private IHitApiCallBack mCallBack;

    private HitApiImplementation(Context context) {
        mContext = context;
    }

    public static HitApiImplementation getInstance(Context context) {
        if (hitApiImplementation == null) {
            sharedPrefsUtils = new SharedPrefsUtils(context, context.getResources().getString(R.string.app_name));
            return new HitApiImplementation(context);
        }
        return hitApiImplementation;
    }


    @Override
    public void callWebService(int reqType, BaseModel model, IHitApiCallBack callBack) {
        String url = null;
        VolleyStringRequest request_ = null;
        mCallBack = callBack;
        switch (reqType) {


            case Constants.REQUEST_PROFILE_INFO:

                String id = sharedPrefsUtils.getSharedPrefString(Constants.PROFILE_ID, "id");
                String mAccessToken = sharedPrefsUtils.getSharedPrefString(Constants.ACCESS_TOKEN, "access_token");

                url = Constants.API_URL + "/users/" + id + "/?access_token=" + mAccessToken;
                Log.d(TAG, url);

                request_ = VolleyStringRequest.doGet(url, new UpdateListener(this, reqType));
                break;


            case Constants.REQUEST_IMAGES:

                String userId = sharedPrefsUtils.getSharedPrefString(Constants.PROFILE_ID, "PROFILE_ID");
                String accessToken = sharedPrefsUtils.getSharedPrefString(Constants.ACCESS_TOKEN, "ACCESS_TOKEN");

                if (mediaCount != 0)
                    url = Constants.API_URL + "/users/" + userId + "/media/recent/?access_token=" + accessToken + "&count=" + mediaCount;
                else
                    url = Constants.API_URL + "/users/" + userId + "/media/recent/?access_token=" + accessToken;
                Log.d(TAG, url);

                request_ = VolleyStringRequest.doGet(url, new UpdateListener(this, reqType));
                break;


        }

        ((PhotoGridApplication) mContext.getApplicationContext()).getVolleyManagerInstance().addToRequestQueue(request_, url);

    }

    @Override
    public void callWebService(int reqType, BaseModel model, String mCode, IHitApiCallBack callBack) {

        String url = null;
        VolleyStringRequest request_ = null;
        mCallBack = callBack;
        switch (reqType)

        {
            case Constants.REQUEST_ACCESS_TOKEN:
                url = Constants.TOKEN_URL;

                final HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("client_id", Constants.CLIENT_ID);
                hashMap.put("client_secret", Constants.CLIENT_SECRET);
                hashMap.put("grant_type", "authorization_code");
                hashMap.put("redirect_uri", Constants.CALLBACK_URL);
                hashMap.put("code", mCode);

                request_ = VolleyStringRequest.doPost(url, new UpdateListener(this, reqType), hashMap);

                break;


        }

        ((PhotoGridApplication) mContext.getApplicationContext()).getVolleyManagerInstance().addToRequestQueue(request_, url);
    }

    @Override
    public void updateView(String responseString, boolean isSuccess, int reqType) {
        switch (reqType) {
            case Constants.REQUEST_PROFILE_INFO:

                if (isSuccess) {
                    try {
                        JSONObject jObject = new JSONObject(responseString);

                        AccessTokenModel model = new Gson().fromJson(jObject.toString(), AccessTokenModel.class);
                        
						int mediaCount = model.getUser().getCount().getMedia();
                        sharedPrefsUtils.setSharedPrefInt(Constants.MEDIA_COUNT, mediaCount);

						mCallBack.onResponseHitApi(model, reqType, true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mCallBack.onResponseHitApi(null, reqType, false);
                break;

            case Constants.REQUEST_ACCESS_TOKEN:
                if (isSuccess) {
                    try {
                        JSONObject jObject = new JSONObject(responseString);

                        String accessToken = jObject.getString("access_token");
                        String id = jObject.getJSONObject("user").getString("id");
                        Log.d("accssToken :", "" + accessToken);

                        sharedPrefsUtils.setSharedPrefString(Constants.ACCESS_TOKEN, accessToken);
                        sharedPrefsUtils.setSharedPrefString(Constants.PROFILE_ID, id);

                        mCallBack.onResponseHitApi(null, reqType, true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mCallBack.onResponseHitApi(null, reqType, false);
                break;

            case Constants.REQUEST_IMAGES:
                if (isSuccess) {
                    try {
                        JSONObject jObject = new JSONObject(responseString);
                        DataModel dataModel = new Gson().fromJson(jObject.toString(), DataModel.class);
                        mCallBack.onResponseHitApi(dataModel, reqType, true);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                mCallBack.onResponseHitApi(null, reqType, false);
                break;
        }

    }
}


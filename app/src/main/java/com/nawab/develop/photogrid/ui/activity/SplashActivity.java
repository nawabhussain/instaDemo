package com.nawab.develop.photogrid.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.nawab.develop.photogrid.R;
import com.nawab.develop.photogrid.models.AccessTokenModel;
import com.nawab.develop.photogrid.models.BaseModel;
import com.nawab.develop.photogrid.presenter.HitApiFactory;
import com.nawab.develop.photogrid.presenter.IHitApi;
import com.nawab.develop.photogrid.presenter.IHitApiCallBack;
import com.nawab.develop.photogrid.utils.Constants;
import com.nawab.develop.photogrid.utils.SharedPrefsUtils;

/**
 * Activity class for splash screen
 *
 * @author Nawab
 */
public class SplashActivity extends Activity implements IHitApiCallBack {

    private ProgressDialog pd;
    private final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPrefsUtils sharedPrefsUtils = new SharedPrefsUtils(SplashActivity.this, getResources().getString(R.string.app_name));
        String accessToken = sharedPrefsUtils.getSharedPrefString(Constants.ACCESS_TOKEN, "access_token");

        if (accessToken.equalsIgnoreCase("access_token")) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            hitApi(Constants.REQUEST_PROFILE_INFO);
        }
    }

    public void hitApi(int reqType) {

        pd = new ProgressDialog(SplashActivity.this);
        pd.setMessage("Loading...");
        pd.show();

        HitApiFactory hitApiFactory = new HitApiFactory();
        IHitApi hitObj = hitApiFactory.getAccessHitApiLibrary(SplashActivity.this);
        hitObj.callWebService(reqType, new AccessTokenModel(), this);


    }

    @Override
    public void onResponseHitApi(BaseModel response, int reqType, boolean isSuccess) {
        if (pd != null && pd.isShowing())
            pd.dismiss();

        AccessTokenModel model = (AccessTokenModel) response;
        if (model != null && model.getUser() != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.PROFILE_MODEL, model);

            Intent intent = new Intent(SplashActivity.this, UserProfileActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();

        }
    }
}

package com.nawab.develop.photogrid.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nawab.develop.photogrid.PhotoGridApplication;
import com.nawab.develop.photogrid.R;
import com.nawab.develop.photogrid.listener.AuthDialogListener;
import com.nawab.develop.photogrid.listener.UpdateListener;
import com.nawab.develop.photogrid.models.AccessTokenModel;
import com.nawab.develop.photogrid.models.BaseModel;
import com.nawab.develop.photogrid.network.VolleyStringRequest;
import com.nawab.develop.photogrid.presenter.HitApiFactory;
import com.nawab.develop.photogrid.presenter.IHitApi;
import com.nawab.develop.photogrid.presenter.IHitApiCallBack;
import com.nawab.develop.photogrid.ui.dialog.InstagramDialog;
import com.nawab.develop.photogrid.utils.Constants;
import com.nawab.develop.photogrid.utils.SharedPrefsUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AuthDialogListener, IHitApiCallBack {
    private String mCode;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnConnect).setOnClickListener(this);
        pd = new ProgressDialog(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConnect:

                String mAuthUrl = Constants.AUTH_URL + "?client_id="
                        + Constants.CLIENT_ID
                        + "&redirect_uri="
                        + Constants.CALLBACK_URL
                        + "&response_type=code&display=touch&scope=likes+comments+relationships";
                InstagramDialog dialog = new InstagramDialog(this, mAuthUrl);
                dialog.show(this.getFragmentManager(), "InstagramDialog");

                break;
        }
    }

    @Override
    public void onComplete(String accessToken) {
        //Called on successful login
        mCode = accessToken;
        hitApi(Constants.REQUEST_ACCESS_TOKEN);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(MainActivity.this, "" + error, Toast.LENGTH_SHORT).show();
    }

    public void hitApi(int reqType) {

//        String url = null;
//        VolleyStringRequest request_ = null;
//        final HashMap<String, String> hashMap = new HashMap<>();

        pd.setMessage("Loading...");
        if (!pd.isShowing())
            pd.show();

        HitApiFactory hitApiFactory = new HitApiFactory();
        IHitApi hitObj = hitApiFactory.getAccessHitApiLibrary(MainActivity.this);

        switch (reqType) {
            case Constants.REQUEST_ACCESS_TOKEN:
                hitObj.callWebService(reqType, null, mCode, this);
                break;
            case Constants.REQUEST_PROFILE_INFO:
                hitObj.callWebService(reqType, new AccessTokenModel(), this);
                break;
        }

    }


    @Override
    public void onResponseHitApi(BaseModel response, int reqType, boolean isSuccess) {
        switch (reqType) {
            case Constants.REQUEST_ACCESS_TOKEN:

                if (isSuccess) {
                    hitApi(Constants.REQUEST_PROFILE_INFO);
                }
                break;
            case Constants.REQUEST_PROFILE_INFO:
                if (pd != null && pd.isShowing())
                    pd.dismiss();

                AccessTokenModel model = (AccessTokenModel) response;
                if (model != null && model.getUser() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.PROFILE_MODEL, model);

                    Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();

                }
                break;
        }
    }
}

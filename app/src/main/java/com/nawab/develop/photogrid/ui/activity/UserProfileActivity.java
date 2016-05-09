package com.nawab.develop.photogrid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nawab.develop.photogrid.R;
import com.nawab.develop.photogrid.adapter.RecyclerViewAdapter;
import com.nawab.develop.photogrid.models.AccessTokenModel;
import com.nawab.develop.photogrid.models.BaseModel;
import com.nawab.develop.photogrid.models.DataModel;
import com.nawab.develop.photogrid.presenter.HitApiFactory;
import com.nawab.develop.photogrid.presenter.IHitApi;
import com.nawab.develop.photogrid.presenter.IHitApiCallBack;
import com.nawab.develop.photogrid.utils.Constants;
import com.nawab.develop.photogrid.utils.SharedPrefsUtils;
import com.nawab.develop.photogrid.utils.SpacesItemDecoration;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nawab on 01-05-2016.
 */
public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener, IHitApiCallBack {
    private AccessTokenModel model;
    private TextView txvName, txvUserName, txvMedia, txvFollowers, txvFollowing;
    private CircleImageView imgProfile;
    private TextView txvLogout;
    private ImageView imgRetry;
    private StaggeredGridLayoutManager layoutManager;
    private RecyclerView lstImages;
    private RecyclerViewAdapter rcAdapter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        setView();
        Bundle bundle = getIntent().getExtras();

        int mediaCount, mFollowers, mFollowing;
        String name, userName, url;

        if (bundle != null) {
            model = (AccessTokenModel) bundle.getSerializable(Constants.PROFILE_MODEL);
            if (model != null) {
                name = model.getUser().getFull_name();
                userName = model.getUser().getUsername();
                mediaCount = model.getUser().getCount().getMedia();
                mFollowing = model.getUser().getCount().getFollows();
                mFollowers = model.getUser().getCount().getFollowed_by();
                url = model.getUser().getProfile_picture();

                txvName.setText(name);
                txvUserName.setText(userName);
                txvMedia.setText("P's:" + mediaCount);
                txvFollowers.setText("F'rs:" + mFollowers);
                txvFollowing.setText("F'ing:" + mFollowing);

                Picasso.with(this)
                        .load(url)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imgProfile);

            } else {
                txvName.setText("NA");
                txvUserName.setText("NA");
                txvMedia.setText("NA");
                txvFollowers.setText("NA");
                txvFollowing.setText("NA");

            }

            hitApi(Constants.REQUEST_IMAGES);
        }
    }

    private void setView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarlayout);
        RelativeLayout lyr = (RelativeLayout) toolbar.findViewById(R.id.lyrImg);
        txvLogout = (TextView) lyr.findViewById(R.id.imgLogout);
        txvLogout.setVisibility(View.VISIBLE);

        txvLogout.setOnClickListener(this);
        txvName = (TextView) findViewById(R.id.txvName);
        txvUserName = (TextView) findViewById(R.id.txvUserName);
        txvMedia = (TextView) findViewById(R.id.txvMedia);
        txvFollowers = (TextView) findViewById(R.id.txvFollowers);
        txvFollowing = (TextView) findViewById(R.id.txvFollowing);
        imgProfile = (CircleImageView) findViewById(R.id.imgProfile);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imgRetry = (ImageView) findViewById(R.id.imgRetry);
        imgRetry.setOnClickListener(this);

        lstImages = (RecyclerView) findViewById(R.id.lstImages);
        layoutManager = new StaggeredGridLayoutManager(3, 1);
        lstImages.setLayoutManager(layoutManager);

//        List<DataModel> gaggeredList = getListItemData();

        rcAdapter = new RecyclerViewAdapter(UserProfileActivity.this);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.two_dp);
        lstImages.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        lstImages.setAdapter(rcAdapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgLogout:
                SharedPrefsUtils sharedPrefsUtils = new SharedPrefsUtils(UserProfileActivity.this, getResources().getString(R.string.app_name));
                sharedPrefsUtils.clearSharedPref();

                Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imgRetry:
                hitApi(Constants.REQUEST_IMAGES);
                break;
        }
    }

    public void hitApi(int reqType) {

        HitApiFactory hitApiFactory = new HitApiFactory();
        IHitApi hitObj = hitApiFactory.getAccessHitApiLibrary(UserProfileActivity.this);
        hitObj.callWebService(reqType, new AccessTokenModel(), this);
    }


    @Override
    public void onResponseHitApi(BaseModel response, int reqType, boolean isSuccess) {
        switch (reqType) {
            case Constants.REQUEST_IMAGES:
                if (isSuccess) {
                    DataModel dataModel = (DataModel) response;
                    if (dataModel.getData().size() > 0) {
                        rcAdapter.setListData(dataModel.getData());
                        rcAdapter.notifyDataSetChanged();
                        lstImages.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                }
                break;
        }
    }
}

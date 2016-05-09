package com.nawab.develop.photogrid.utils;

/**
 * Created by Nawab on 30-04-2016.
 */
public interface Constants {

    String CLIENT_ID = "ec36008486494dfdb6d956886d621384";
    String CLIENT_SECRET = "31193353e21d458f8914d35ae94df572";
    String CALLBACK_URL = "instagram://connect";

    String AUTH_URL = "https://api.instagram.com/oauth/authorize/";
    String TOKEN_URL = "https://api.instagram.com/oauth/access_token";
    String API_URL = "https://api.instagram.com/v1";

    int REQUEST_ACCESS_TOKEN = 1;
    int REQUEST_PROFILE_INFO = 2;
    int REQUEST_IMAGES = 3;

    String ACCESS_TOKEN ="access_token";
    String PROFILE_ID = "profile_id";
	String MEDIA_COUNT = "media_count";

    String PROFILE_MODEL = "profile_model";
}

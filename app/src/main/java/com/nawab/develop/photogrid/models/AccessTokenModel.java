package com.nawab.develop.photogrid.models;

import java.io.Serializable;

/**
 * Created by Nawab on 01-05-2016.
 */
public class AccessTokenModel extends BaseModel implements Serializable{

    UserModel data;


    public UserModel getUser() {
        return data;
    }

    public void setUser(UserModel user) {
        this.data = user;
    }
}

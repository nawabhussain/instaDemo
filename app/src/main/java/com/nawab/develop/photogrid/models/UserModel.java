package com.nawab.develop.photogrid.models;

import java.io.Serializable;

/**
 * Created by Nawab on 01-05-2016.
 */
public class UserModel implements Serializable{
    String username, bio, website, profile_picture, full_name, id;
    Count counts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Count getCount() {
        return counts;
    }

    public void setCount(Count count) {
        this.counts = count;
    }


}

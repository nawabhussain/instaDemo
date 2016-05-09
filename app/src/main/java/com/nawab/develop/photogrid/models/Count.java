package com.nawab.develop.photogrid.models;

import java.io.Serializable;

/**
 * Created by Nawab on 01-05-2016.
 */
public class Count implements Serializable {
    int media, followed_by, follows;

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public int getFollowed_by() {
        return followed_by;
    }

    public void setFollowed_by(int followed_by) {
        this.followed_by = followed_by;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }
}

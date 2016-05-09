package com.nawab.develop.photogrid.models;

import java.util.HashMap;

/**
 * Created by Nawab on 05-05-2016.
 */
public class ImagesDataModel {

    private ImagesModel images;
    private Captions caption;

    public LikesModel getLikes() {
        return likes;
    }

    public void setLikes(LikesModel likes) {
        this.likes = likes;
    }

    private LikesModel likes;

    public Captions getCaptions() {
        return caption;
    }

    public void setCaptions(Captions captions) {
        this.caption = captions;
    }

    public ImagesModel getImages() {
        return images;
    }

    public void setImages(ImagesModel images) {
        this.images = images;
    }
}

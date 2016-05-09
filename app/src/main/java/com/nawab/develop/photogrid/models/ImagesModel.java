package com.nawab.develop.photogrid.models;

/**
 * Created by Nawab on 05-05-2016.
 */
public class ImagesModel {
    private LowResolutionImageModel low_resolution;
    private ThumbnailModel thumbnail;

    public LowResolutionImageModel getLow_resolution() {
        return low_resolution;
    }

    public void setLow_resolution(LowResolutionImageModel low_resolution) {
        this.low_resolution = low_resolution;
    }

    public ThumbnailModel getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbnailModel thumbnail) {
        this.thumbnail = thumbnail;
    }
}

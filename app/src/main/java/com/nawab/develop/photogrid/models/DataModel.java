package com.nawab.develop.photogrid.models;

import java.util.ArrayList;

/**
 * Created by Nawab on 05-05-2016.
 */
public class DataModel extends BaseModel{

    ArrayList<ImagesDataModel> data;

    public ArrayList<ImagesDataModel> getData() {
        return data;
    }

    public void setData(ArrayList<ImagesDataModel> data) {
        this.data = data;
    }
}

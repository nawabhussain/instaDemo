package com.nawab.develop.photogrid.listener;

/**
 * Created by Nawab on 30-04-2016.
 */
public interface AuthDialogListener {
    public abstract void onComplete(String accessToken);
    public abstract void onError(String error);
}

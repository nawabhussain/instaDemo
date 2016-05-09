package com.nawab.develop.photogrid.ui.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nawab.develop.photogrid.listener.AuthDialogListener;
import com.nawab.develop.photogrid.utils.Constants;
import com.nawab.develop.photogrid.R;

/**
 * Created by Nawab on 30-04-2016.
 */
public class InstagramDialog extends DialogFragment {

    AuthDialogListener listener;
    private String TAG = "InstagramDialog";
    private WebView webView;
    private String mUrl;
    private ProgressDialog pd;
    private View view;

    public InstagramDialog() {

    }

    public InstagramDialog(AuthDialogListener listener, String url) {
        this.listener = listener;
        mUrl = url;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        getDialog().getWindow().requestFeature(STYLE_NO_TITLE);
        view = inflater.inflate(R.layout.dialogue_login_instagram, container);
        webView = (WebView) view.findViewById(R.id.mWebview);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(new OAuthWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(mUrl);

       clearCookies();

        return view;


    }
    @SuppressWarnings("deprecation")
    public void clearCookies()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            Log.d("InstaDialog", "Using clearCookies code for API >=" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else
        {
            Log.d("InstaDialog", "Using clearCookies code for API <" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(getActivity());
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }

    private class OAuthWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "Redirecting URL " + url);
            if (url.startsWith(Constants.CALLBACK_URL)) {
                String urls[] = url.split("=");
                listener.onComplete(urls[1]);
                InstagramDialog.this.dismiss();
                return true;
            }
            return false;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            Log.d(TAG, "Page error: " + description);
            listener.onError(description);
            InstagramDialog.this.dismiss();
        }

//        @Override
//        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//            super.onReceivedError(view, request, error);
//            listener.onError(error.getDescription().toString());
//            InstagramDialog.this.dismiss();
//        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d(TAG, "Loading URL: " + url);

            super.onPageStarted(view, url, favicon);
            pd.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (pd.isShowing())
                pd.dismiss();
        }

    }

}

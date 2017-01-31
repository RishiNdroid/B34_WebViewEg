package com.techpalle.b34_webvieweg;

import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView1);

        //CHECK FOR INTERNET CONNECTION.
        //a. get network manager object
        ConnectivityManager manager = (ConnectivityManager)
                            getSystemService(Context.CONNECTIVITY_SERVICE);
        //b. from network manager, get active network information
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //c. check if netowrk is connected or not
        if(networkInfo == null || networkInfo.isConnected() == false){
            //means there is no internet , return from here.
            webView.loadData("<h1>NO INTERNET - CHECK AGAIN<h1>",
                            "text/html",
                            null);
            return;
        }

        webView.getSettings().setJavaScriptEnabled(true);
        //below line will make sure that onclicking any hyper link, next page
        //will be loaded in your activity.
        webView.setWebViewClient(new WebViewClient());
        //either you can give url, or can search in google
        webView.loadUrl("http://google.com/search?q="+"android interview questions");
    }

    //WE WILL HANDLE BACK BUTTON CLICKS - ON WEBVIEW

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            //ASK WEBVIEW - IF THERE ARE ANY PREVIOUSLY NAVIGATED PAGES
            if(webView.canGoBack() == true){
                //CONTROL COMES HERE IF THERE IS PREVIOUS PAGE
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

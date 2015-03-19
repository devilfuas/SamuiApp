package com.psu.samuiapp.activity.event;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.psu.samuiapp.R;

public class WebViewActivity extends Activity {

	private WebView webView;
	String url;
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		

		Bundle b = getIntent().getExtras();
		url = b.getString("url");
 
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(url);
 
	}
}
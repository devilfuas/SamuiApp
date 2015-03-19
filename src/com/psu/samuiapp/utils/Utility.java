package com.psu.samuiapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utility {
	public static String url="http://www.tourismthailand.org/rss/event";
	
	//http://thai.tourismthailand.org/rss/event
	

	public static boolean determineConnectivity(Context ctx) {
		ConnectivityManager manager = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		return info != null && info.getState() == NetworkInfo.State.CONNECTED;
	}

}

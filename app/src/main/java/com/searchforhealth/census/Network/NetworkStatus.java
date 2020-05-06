package com.searchforhealth.census.Network;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetworkStatus {

	public static String checkConnection(Context context){

		ConnectivityManager connectivitymanager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

  		if(connectivitymanager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()== NetworkInfo.State.CONNECTED || connectivitymanager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED){

			return "true";

		}else{
			return "false";
		}
	}


}




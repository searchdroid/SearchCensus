package com.searchforhealth.census.Preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SYSTEM on 7/15/2016.
 */
public class SharedCS {

    public static String LOGIN_STATUS = "loginstatus";
    public static String EMAIL = "email";
    public static String USER_ID = "userId";

    public static final String PREF_NAME = "SHARED_PREFERENCES";

    static Context context;

    public SharedCS(Context context){
        this.context = context;
    }

    public static SharedPreferences getPreferences(){

        return context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
    }

    public static void writeString(String key, String value){
        getEditor().putString(key, value).commit();
    }

    public static SharedPreferences.Editor getEditor(){

        return getPreferences().edit();
    }

}

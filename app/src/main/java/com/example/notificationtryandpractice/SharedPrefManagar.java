package com.example.notificationtryandpractice;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManagar {//for store and get token
    private static  final String SHARD_PREF_NAME ="fcmToken";
    private static  final String SHARD_PREF_KEY ="token";
    private static Context mcontext;
    private static SharedPrefManagar mInstantance;

    private SharedPrefManagar(Context context) {
        mcontext = context;
    }

    public static synchronized SharedPrefManagar getInstance(Context context) {//for getInstant for the class
       return mInstantance = new SharedPrefManagar(context);
    }
    public boolean storeToken(String token){//for save  token to SharedPreferences
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(SHARD_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARD_PREF_KEY,token);
        editor.apply();
        return true;
    }
    public String getToken(){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(SHARD_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(SHARD_PREF_KEY,null);
    }
}


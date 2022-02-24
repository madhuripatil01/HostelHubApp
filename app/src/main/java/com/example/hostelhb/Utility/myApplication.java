package com.example.hostelhb.Utility;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class myApplication extends Application
{
    private static myApplication myApplicationInstance;
    private SharedPreferences sharedPreferences;
    public static synchronized myApplication getInstance()
    {
        if (myApplicationInstance==null)
        {
            myApplicationInstance=new myApplication();
        }
        return myApplicationInstance;
    }
    public SharedPreferences getSharedPreferences(Context context)
    {
        if (sharedPreferences==null)
        {
            sharedPreferences=context.getSharedPreferences(SharedPreferencesKeyConstant.PRE_Name,0);
        }
        return sharedPreferences;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }
}

package com.example.hostelhb.Utility;

import android.content.Context;

public class SharedPreferencesManager
{
    public static boolean setUserId(Context context,long UserId)
    {
        return myApplication.getInstance().getSharedPreferences(context).edit()
                .putLong(SharedPreferencesKeyConstant.USER_ID,UserId).commit();
    }
    public static long getUserId(Context context)
    {
        return myApplication.getInstance().getSharedPreferences(context)
                .getLong(SharedPreferencesKeyConstant.USER_ID,0);
    }
    public static boolean setUserName(Context context,String User_Name)
    {
        return myApplication.getInstance().getSharedPreferences(context).edit()
                .putString(SharedPreferencesKeyConstant.USER_NAME,User_Name).commit();
    }
    public static String getUserName(Context context)
    {
        return myApplication.getInstance().getSharedPreferences(context)
                .getString(SharedPreferencesKeyConstant.USER_NAME,"");
    }
    public static boolean setUserType(Context context,String User_Type)
    {
        return myApplication.getInstance().getSharedPreferences(context).edit()
                .putString(SharedPreferencesKeyConstant.USER_TYPE,User_Type).commit();
    }
    public static String getUserType(Context context)
    {
        return myApplication.getInstance().getSharedPreferences(context)
                .getString(SharedPreferencesKeyConstant.USER_TYPE,"");
    }
    public static String getEmail(Context context)
    {
        return myApplication.getInstance().getSharedPreferences(context)
                .getString(SharedPreferencesKeyConstant.USER_EMAIL,"");
    }
    public static boolean setEmail(Context context,String User_Email)
    {
        return myApplication.getInstance().getSharedPreferences(context).edit()
                .putString(SharedPreferencesKeyConstant.USER_EMAIL,User_Email).commit();
    }
    public static String getUserMobNo(Context context)
    {
        return myApplication.getInstance().getSharedPreferences(context)
                .getString(SharedPreferencesKeyConstant.USER_MOB_NO,"");
    }
    public static boolean setUserMobNo(Context context,String MobNo)
    {
        return myApplication.getInstance().getSharedPreferences(context).edit()
                .putString(SharedPreferencesKeyConstant.USER_MOB_NO,MobNo).commit();
    }
    public static String getAddress(Context context)
    {
        return myApplication.getInstance().getSharedPreferences(context)
                .getString(SharedPreferencesKeyConstant.USER_ADDRESS,"");

    }
    public static boolean setAddress(Context context,String address)
    {
        return  myApplication.getInstance().getSharedPreferences(context).edit()
                .putString(SharedPreferencesKeyConstant.USER_ADDRESS,address).commit();
    }
    public static String getCity(Context  context)
    {
        return myApplication.getInstance().getSharedPreferences(context)
                .getString(SharedPreferencesKeyConstant.USER_CITY,"");

    }
    public static boolean setCity(Context context,String city)
    {
        return myApplication.getInstance().getSharedPreferences(context).edit()
                .putString(SharedPreferencesKeyConstant.USER_CITY,city).commit();
    }
    public static boolean setHosId(Context context,long HosId)
    {
        return myApplication.getInstance().getSharedPreferences(context).edit()
                .putLong(SharedPreferencesKeyConstant.HOS_ID,HosId).commit();
    }
    public static long getHosId(Context context)
    {
        return myApplication.getInstance().getSharedPreferences(context)
                .getLong(SharedPreferencesKeyConstant.HOS_ID,0);
    }

    public static boolean setMesId(Context context,long MesId)
    {
        return myApplication.getInstance().getSharedPreferences(context).edit()
                .putLong(SharedPreferencesKeyConstant.MES_ID,MesId).commit();
    }
    public static long getMesId(Context context)
    {
        return myApplication.getInstance().getSharedPreferences(context)
                .getLong(SharedPreferencesKeyConstant.MES_ID,0);
    }
}

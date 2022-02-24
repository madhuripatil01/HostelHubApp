package com.example.hostelhb.Utility;

import android.content.Context;

import androidx.room.Room;

public class HostelDatabaseClient
{
    private Context context;
    private static HostelDatabaseClient hInstance;

    private AppDatabase appDatabase;

    public HostelDatabaseClient(Context context) {
        this.context = context;
        appDatabase= Room.databaseBuilder(context,AppDatabase.class,"MyDb").allowMainThreadQueries().build();
    }
public static synchronized HostelDatabaseClient gethInstance(Context context)
{
    if (hInstance==null)
    {
      hInstance=new HostelDatabaseClient(context);
    }
    return hInstance;
}
public AppDatabase getAppDatabase()
{
    return appDatabase;
}
}

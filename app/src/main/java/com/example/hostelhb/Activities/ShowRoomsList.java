package com.example.hostelhb.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelhb.Adapters.RoomsListAdapter;
import com.example.hostelhb.DataModels.RoomsModel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class ShowRoomsList extends AppCompatActivity
{
    List<RoomsModel> RoomList=new ArrayList<>();
    RecyclerView RecViewRoomList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showroomslist);
        RecViewRoomList=findViewById(R.id.RecViewRoomList);
        int hosIdroom= getIntent().getIntExtra("HosIdRoom",0);

        RoomList= HostelDatabaseClient.gethInstance(getApplicationContext())
                .getAppDatabase().roomDAO().SelectRoomsById(hosIdroom);

        RoomsListAdapter roomsListAdapter=new RoomsListAdapter(RoomList,ShowRoomsList.this);
        RecViewRoomList.setLayoutManager(new LinearLayoutManager(ShowRoomsList.this,LinearLayoutManager.VERTICAL,false));
        RecViewRoomList.setAdapter(roomsListAdapter);
    }
}

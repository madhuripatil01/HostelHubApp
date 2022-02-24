package com.example.hostelhb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelhb.DataModels.RoomsModel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.UtilityMethods;

import java.util.List;

public class RoomsListAdapter extends RecyclerView.Adapter<RoomsListAdapter.MyViewHolder>
{
    List<RoomsModel> RoomList;
    Context context;


    public RoomsListAdapter(List<RoomsModel> roomList, Context context) {
        RoomList = roomList;
        this.context = context;
    }

    @NonNull
    @Override
    public RoomsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.showroomlistitems,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomsListAdapter.MyViewHolder holder, int position)
    {
        RoomsModel roomsModel=RoomList.get(position);
        holder.txtRoomHosName.setText(roomsModel.getRoomHName());
        holder.txtRoomNo.setText(roomsModel.getRoomNo());
        holder.txtRoomRent.setText(roomsModel.getMonRent());
        holder.txtRoomCapacity.setText(roomsModel.getRoomCapacity());
        holder.ImgShowRoom.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(roomsModel.getImgRoom()));

    }

    @Override
    public int getItemCount() {
        return RoomList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ImgShowRoom;
        TextView txtRoomHosName,txtRoomNo,txtRoomRent,txtRoomCapacity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgShowRoom=itemView.findViewById(R.id.ImgShowRoom);
            txtRoomHosName=itemView.findViewById(R.id.txtRoomHosName);
            txtRoomNo=itemView.findViewById(R.id.txtRoomNo);
            txtRoomRent=itemView.findViewById(R.id.txtRoomRent);
            txtRoomCapacity=itemView.findViewById(R.id.txtRoomCapacity);

        }
    }
}

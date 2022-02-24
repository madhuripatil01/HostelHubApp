package com.example.hostelhb.Adapters;

import
        android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelhb.DataModels.OwnerModel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.UtilityMethods;

import java.util.List;

public class OwnerListAdapter extends RecyclerView.Adapter<OwnerListAdapter.MyViewHolder>
{
    List<OwnerModel> ownerList;
    Context context;

    public OwnerListAdapter(List<OwnerModel> ownerList, Context context) {
        this.context=context;
        this.ownerList=ownerList;
    }

    @NonNull
    @Override
    public OwnerListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OwnerListAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.admin_buyer_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final OwnerModel ownerModel=ownerList.get(position);

        holder.txtOwnname.setText(ownerModel.getOwnerName());
        holder.txtOwnemail.setText(ownerModel.getOwnerEmail());
        holder.txtOwnmobno.setText(ownerModel.getOwnerContactNo());
        holder.txtOwnaddress.setText(ownerModel.getOwnerAddress());

        holder.OwnerProImg.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap
                (ownerModel.getImgOwner()));

    }


    @Override
    public int getItemCount() {
        return ownerList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtOwnname,txtOwnemail,txtOwnmobno,txtOwnaddress;
        ImageView OwnerProImg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtOwnname=itemView.findViewById(R.id.txtOwnname);
            txtOwnemail=itemView.findViewById(R.id.txtOwnemail);
            txtOwnaddress=itemView.findViewById(R.id.txtOwnaddress);
            txtOwnmobno=itemView.findViewById(R.id.txtOwnmobno);
            OwnerProImg=itemView.findViewById(R.id.OwnerProImg);


        }
    }
}



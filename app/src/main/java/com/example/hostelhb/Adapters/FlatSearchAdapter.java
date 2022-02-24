package com.example.hostelhb.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelhb.Activities.ShowFlatInfo;
import com.example.hostelhb.Activities.ShowHostelInfo;
import com.example.hostelhb.DataModels.FlatsModel;
import com.example.hostelhb.DataModels.SearchHostel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.UtilityMethods;

import java.util.List;

public class FlatSearchAdapter extends RecyclerView.Adapter<FlatSearchAdapter.MyViewHolder> {
    List<FlatsModel> FlatList;
    Context context;

    public FlatSearchAdapter(List<FlatsModel> flatList, Context context) {
        FlatList = flatList;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemflatsearch,parent,false));
    }

    public void setFilterList(List<FlatsModel> fList)
    {
      FlatList=fList;
       notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position)
    {
        final FlatsModel flatsModel=FlatList.get(position);
        holder.ImgSearchFlat.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(flatsModel.getImgFlat()));
        holder.txtFlatSearchName.setText(flatsModel.getFlatName());
        holder.lnrSFlat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ShowFlatInfo.class);
                intent.putExtra("flatInfo",flatsModel);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return FlatList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ImgSearchFlat;
        TextView txtFlatSearchName;
        LinearLayout lnrSFlat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgSearchFlat=itemView.findViewById(R.id.ImgflatSearch);
            txtFlatSearchName=itemView.findViewById(R.id.txtflatSearchName);
            lnrSFlat=itemView.findViewById(R.id.lnrSFlat);
        }
    }
}

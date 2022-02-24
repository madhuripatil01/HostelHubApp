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

import com.example.hostelhb.Activities.ShowMesInfo;
import com.example.hostelhb.DataModels.SearchMes;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.UtilityMethods;

import java.util.ArrayList;
import java.util.List;

public class MesSearchAdapter extends RecyclerView.Adapter<MesSearchAdapter.MyViewHolder>
{
    List<SearchMes> MesList=new ArrayList<>();
    Context context;

    public MesSearchAdapter(List<SearchMes> mesList, Context context)
    {
        MesList = mesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MesSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MesSearchAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemmessearch,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MesSearchAdapter.MyViewHolder holder, int position)
    {
        final SearchMes searchMes=MesList.get(position);
        holder.ImgSearchMes.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(searchMes.getImgMes()));
        holder.txtMesSearchName.setText(searchMes.getMesName());
        holder.lnrMesInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ShowMesInfo.class);
                intent.putExtra("mesInfo",searchMes);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return MesList.size();
    }

    public void setFilterList(List<SearchMes> mList)
    {
        MesList=mList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ImgSearchMes;
        TextView txtMesSearchName;
        LinearLayout lnrMesInfo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgSearchMes=itemView.findViewById(R.id.ImgSearchMes);
            txtMesSearchName=itemView.findViewById(R.id.txtMesSearchName);
            lnrMesInfo=itemView.findViewById(R.id.lnrMesInfo);
        }
    }
}

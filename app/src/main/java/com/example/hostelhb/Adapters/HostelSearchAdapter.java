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

import com.example.hostelhb.Activities.ShowHostelInfo;
import com.example.hostelhb.DataModels.SearchHostel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.UtilityMethods;

import java.util.List;

public class HostelSearchAdapter extends RecyclerView.Adapter<HostelSearchAdapter.MyViewHolder> {
    List<SearchHostel> HostelList;
    Context context;

    public HostelSearchAdapter(List<SearchHostel> hostelList, Context context) {
        HostelList = hostelList;
        this.context = context;
    }



    @NonNull
    @Override
    public HostelSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HostelSearchAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemhostelsearch,parent,false));
    }
    public void setFilterList(List<SearchHostel> hList)
    {
       HostelList=hList;
       notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull  HostelSearchAdapter.MyViewHolder holder, int position)
    {
        final SearchHostel searchHostel=HostelList.get(position);
        holder.ImgSearchHostel.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(searchHostel.getImgHostel()));
        holder.txtHostelSearchName.setText(searchHostel.getHosName());
        holder.lnrSHos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ShowHostelInfo.class);
                intent.putExtra("hosInfo",searchHostel);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return HostelList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ImgSearchHostel;
        TextView txtHostelSearchName;
        LinearLayout lnrSHos;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgSearchHostel=itemView.findViewById(R.id.ImgHosSearch);
            txtHostelSearchName=itemView.findViewById(R.id.txtHosSearchName);
            lnrSHos=itemView.findViewById(R.id.lnrSHos);
        }
    }
}

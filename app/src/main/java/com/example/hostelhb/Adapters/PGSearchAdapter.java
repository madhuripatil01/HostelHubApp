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

import com.example.hostelhb.Activities.ShowPGInfo;
import com.example.hostelhb.DataModels.FlatsModel;
import com.example.hostelhb.DataModels.PGModel;
import com.example.hostelhb.DataModels.SearchHostel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.UtilityMethods;

import java.util.List;

public class PGSearchAdapter extends RecyclerView.Adapter<PGSearchAdapter.MyViewHolder> {
    List<PGModel> PGList;
    Context context;

    public PGSearchAdapter(List<PGModel> pgList, Context context) {
        PGList = pgList;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itempgsearch,parent,false));
    }

    public void setFilterList(List<PGModel> pgList)
    {
       PGList= pgList;
       notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position)
    {

        final PGModel pgModel=PGList.get(position);
        holder.ImgSearchPG.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(pgModel.getImgPG()));
        holder.txtPGSearchName.setText(pgModel.getPGAddress());
        holder.lnrSpG.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ShowPGInfo.class);
                intent.putExtra("pgInfo",pgModel);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
       return PGList.size();
        //return  0;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ImgSearchPG;
        TextView txtPGSearchName;
        LinearLayout lnrSpG;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgSearchPG=itemView.findViewById(R.id.ImgPGSearch);
            txtPGSearchName=itemView.findViewById(R.id.txtPGSearchName);
            lnrSpG=itemView.findViewById(R.id.lnrSpG);
        }
    }
}

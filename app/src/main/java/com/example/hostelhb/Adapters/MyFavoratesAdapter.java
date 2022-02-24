package com.example.hostelhb.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelhb.Activities.ShowHostelInfo;
import com.example.hostelhb.DataModels.CustMyFavorate;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.UtilityMethods;

import java.util.ArrayList;
import java.util.List;

public class MyFavoratesAdapter extends RecyclerView.Adapter<MyFavoratesAdapter.MyViewHolder>  {
    List<CustMyFavorate> FavorateList=new ArrayList<>();
    Context context;
    public MyFavoratesAdapter(List<CustMyFavorate> favorateList, Context context) {
        FavorateList = favorateList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyFavoratesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyFavoratesAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemmyfavorates,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyFavoratesAdapter.MyViewHolder holder, int position)
    {
       final CustMyFavorate custMyFavorate=FavorateList.get(position);
        holder.txtFavName.setText(custMyFavorate.getTxtFavName());
        holder.ImgFav.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(custMyFavorate.getImgFav()));
        holder.txtFavAddress.setText(custMyFavorate.getFavAddress());
        holder.txtFavContactNo.setText(custMyFavorate.getFavContactNo());
        holder.txtFavContactNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callintent=new Intent(Intent.ACTION_CALL);
                callintent.setData(Uri.parse("tel:" + custMyFavorate.getFavContactNo()));
                context.startActivity(callintent);
            }
        });
        holder.ImgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ShowHostelInfo.class);
                intent.putExtra("hosInfo", custMyFavorate);
                context.startActivity(intent);
            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HostelDatabaseClient.gethInstance(context
                        .getApplicationContext())
                        .getAppDatabase()
                        .myFavDao()
                        .deleteFav(custMyFavorate);
                Toast.makeText(context, "Removed from Favourates", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return FavorateList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ImgFav;
        TextView txtFavName,txtFavAddress,txtFavContactNo,cancel;
        LinearLayout lnrFav;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
          ImgFav=itemView.findViewById(R.id.ImgFav);
          txtFavName=itemView.findViewById(R.id.txtFavName);
          txtFavAddress=itemView.findViewById(R.id.txtFavAddress);
            txtFavContactNo=itemView.findViewById(R.id.txtFavContactNo);
            lnrFav=itemView.findViewById(R.id.lnrFav);
            cancel=itemView.findViewById(R.id.cancel);
        }
    }
}

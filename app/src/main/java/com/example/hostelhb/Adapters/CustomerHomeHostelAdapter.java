package com.example.hostelhb.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelhb.Activities.ShowHostelInfo;
import com.example.hostelhb.DataModels.CustMyFavorate;
import com.example.hostelhb.DataModels.SearchHostel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.UtilityMethods;

import java.util.ArrayList;
import java.util.List;

public class CustomerHomeHostelAdapter extends RecyclerView.Adapter<CustomerHomeHostelAdapter.MyViewHolder> {
    List<SearchHostel> CustHomeHosList = new ArrayList<>();
    Context context;


    public CustomerHomeHostelAdapter(List<SearchHostel> custHomeList, Context context) {
        CustHomeHosList = custHomeList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerHomeHostelAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerHomeHostelAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemhomehostelinfo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerHomeHostelAdapter.MyViewHolder holder, int position) {
        final SearchHostel searchHostel = CustHomeHosList.get(position);
        holder.txtHomeHosName.setText(searchHostel.getHosName());
        holder.txtHomeHosContactNo.setText(searchHostel.getHosConNo());
        holder.txtHomeHosAddress.setText(searchHostel.getHosAddress());
        holder.ShowImgHOs.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(searchHostel.getImgHostel()));
        holder.AddRateHomeHos.setRating(searchHostel.getHomeHostelrating());
        holder.txtHomeHosContactNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callintent = new Intent(Intent.ACTION_CALL);
                callintent.setData(Uri.parse("tel:" + searchHostel.getHosConNo()));
                context.startActivity(callintent);
            }
        });
        holder.ShowImgHOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowHostelInfo.class);
                intent.putExtra("hosInfo", searchHostel);
                context.startActivity(intent);
            }
        });
        holder.ImgFavCHomeHos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               CustMyFavorate custMyFavorate=new CustMyFavorate();

               custMyFavorate.setTxtFavName(searchHostel.getHosName());
               custMyFavorate.setFavAddress(searchHostel.getHosAddress());
               custMyFavorate.setFavContactNo(searchHostel.getHosConNo());
                custMyFavorate.setImgFav(searchHostel.getImgHostel());
                CustMyFavorate custMyFavorate1=HostelDatabaseClient
                        .gethInstance(context)
                        .getAppDatabase()
                        .myFavDao().SelectFavById(searchHostel.getHosId());

                if (custMyFavorate1==null) {
                    HostelDatabaseClient.gethInstance(context)
                            .getAppDatabase()
                            .myFavDao()
                            .insertFav(custMyFavorate);
                    Toast.makeText(context, "Favorate Added Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context, "Already Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return CustHomeHosList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtHomeHosName, txtHomeHosContactNo, txtHomeHosAddress;
        ImageView ShowImgHOs, ImgFavCHomeHos;
        RatingBar AddRateHomeHos;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHomeHosName = itemView.findViewById(R.id.txtHomeHosName);
            txtHomeHosContactNo = itemView.findViewById(R.id.txtHomeHosContactNo);
            txtHomeHosAddress = itemView.findViewById(R.id.txtHomeHosAddress);
            ShowImgHOs = itemView.findViewById(R.id.ShowImgHos);
            AddRateHomeHos = itemView.findViewById(R.id.AddRateHomeHos);
            ImgFavCHomeHos = itemView.findViewById(R.id.ImgFavCHomeHos);
        }
    }
}
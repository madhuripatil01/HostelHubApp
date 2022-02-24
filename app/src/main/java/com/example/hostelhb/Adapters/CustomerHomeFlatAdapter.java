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

import com.example.hostelhb.Activities.ShowFlatInfo;
import com.example.hostelhb.Activities.ShowHostelInfo;
import com.example.hostelhb.DataModels.CustMyFavorate;
import com.example.hostelhb.DataModels.FlatsModel;
import com.example.hostelhb.DataModels.SearchHostel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.UtilityMethods;

import java.util.ArrayList;
import java.util.List;

public class CustomerHomeFlatAdapter extends RecyclerView.Adapter<CustomerHomeFlatAdapter.MyViewHolder> {
    List<FlatsModel> CustHomeFlatList = new ArrayList<>();
    Context context;


    public CustomerHomeFlatAdapter(List<FlatsModel> custHomeList, Context context) {
        CustHomeFlatList = custHomeList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemhomeflatinfo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final FlatsModel flatsModel = CustHomeFlatList.get(position);
        holder.txtHomeFlatAddress.setText(flatsModel.getFlatAddress());
        holder.txtHomeFlatContactNo.setText(flatsModel.getFlatConNo());
        holder.ShowImgFlat.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(flatsModel.getImgFlat()));
        holder.txtHomeFlatContactNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callintent = new Intent(Intent.ACTION_CALL);
                callintent.setData(Uri.parse("tel:" + flatsModel.getFlatConNo()));
                context.startActivity(callintent);
            }
        });
        holder.ShowImgFlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowFlatInfo.class);
                intent.putExtra("flatInfo", flatsModel);
                context.startActivity(intent);
            }
        });
        holder.ImgFavCHomeFlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustMyFavorate custMyFavorate=new CustMyFavorate();

                custMyFavorate.setTxtFavName(flatsModel.getFlatName());
                custMyFavorate.setFavAddress(flatsModel.getFlatAddress());
                custMyFavorate.setFavContactNo(flatsModel.getFlatConNo());
                custMyFavorate.setImgFav(flatsModel.getImgFlat());
                CustMyFavorate custMyFavorate1=HostelDatabaseClient
                        .gethInstance(context)
                        .getAppDatabase()
                        .myFavDao().SelectFavById(flatsModel.getFlatId());

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
        return CustHomeFlatList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  txtHomeFlatContactNo, txtHomeFlatAddress;
        ImageView ShowImgFlat, ImgFavCHomeFlat;
        RatingBar AddRateHomeFlat;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHomeFlatContactNo = itemView.findViewById(R.id.txtHomeFlatContactNo);
            txtHomeFlatAddress = itemView.findViewById(R.id.txtHomeFlatAddress);
            ShowImgFlat = itemView.findViewById(R.id.ShowImgflat);
            AddRateHomeFlat = itemView.findViewById(R.id.AddRateHomeFlat);
            ImgFavCHomeFlat = itemView.findViewById(R.id.ImgFavCHomeflat);
        }
    }
}
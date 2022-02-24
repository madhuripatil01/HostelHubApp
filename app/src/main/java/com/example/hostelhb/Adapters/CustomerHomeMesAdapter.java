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

import com.example.hostelhb.Activities.ShowMesInfo;
import com.example.hostelhb.DataModels.CustMyFavorate;
import com.example.hostelhb.DataModels.SearchMes;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.UtilityMethods;

import java.util.ArrayList;
import java.util.List;

public class CustomerHomeMesAdapter extends RecyclerView.Adapter<CustomerHomeMesAdapter.MyViewHolder>
{
    List<SearchMes> CustHomeMesList=new ArrayList<>();
    Context context;

    public CustomerHomeMesAdapter(List<SearchMes> custHomeMesList, Context context) {
        CustHomeMesList = custHomeMesList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerHomeMesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerHomeMesAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemhomemesinfo,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerHomeMesAdapter.MyViewHolder holder, int position) {

        final SearchMes searchMes =CustHomeMesList.get(position);
        holder.txtHomeMesName.setText(searchMes.getMesName());
        holder.txtHomeMesAddress.setText(searchMes.getMesAddress());
        holder.txtHomeMesContactNo.setText(searchMes.getMesConNo());
        holder.ShowImgMes.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(searchMes.getImgMes()));
        holder.txtHomeMesContactNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent callintent=new Intent(Intent.ACTION_CALL);
                callintent.setData(Uri.parse("tel:" +searchMes.getMesConNo()));
                context.startActivity(callintent);

            }
        });
        holder.ShowImgMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ShowMesInfo.class);
                intent.putExtra("mesInfo",searchMes);
                context.startActivity(intent);
            }
        });
        holder.ImgFavHomeMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustMyFavorate custMyFavorate=new CustMyFavorate();

                custMyFavorate.setTxtFavName(searchMes.getMesName());
                custMyFavorate.setFavAddress(searchMes.getMesAddress());
                custMyFavorate.setFavContactNo(searchMes.getMesConNo());
                custMyFavorate.setImgFav(searchMes.getImgMes());

               HostelDatabaseClient.gethInstance(context)
                            .getAppDatabase()
                            .myFavDao()
                            .insertFav(custMyFavorate);
                    Toast.makeText(context, "Favorate Added Successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return CustHomeMesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
       TextView txtHomeMesName,txtHomeMesAddress,txtHomeMesContactNo;
       ImageView ShowImgMes,ImgFavHomeMes;
       RatingBar AddRateHomeMes;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHomeMesName=itemView.findViewById(R.id.txtHomeMesName);
            txtHomeMesAddress=itemView.findViewById(R.id.txtHomeMesAddress);
            txtHomeMesContactNo=itemView.findViewById(R.id.txtHomeMesContactNo);
            ShowImgMes=itemView.findViewById(R.id.ShowImgMes);
            ImgFavHomeMes=itemView.findViewById(R.id.ImgFavCHomeMes);
            AddRateHomeMes=itemView.findViewById(R.id.AddRateHomeMes);
        }
    }
}

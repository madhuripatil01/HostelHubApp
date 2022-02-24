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
import com.example.hostelhb.Activities.ShowPGInfo;
import com.example.hostelhb.DataModels.CustMyFavorate;
import com.example.hostelhb.DataModels.PGModel;
import com.example.hostelhb.DataModels.SearchHostel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.UtilityMethods;

import java.util.ArrayList;
import java.util.List;

public class CustomerHomePGAdapter extends RecyclerView.Adapter<CustomerHomePGAdapter.MyViewHolder> {
    List<PGModel> CustHomePGList = new ArrayList<>();
    Context context;


    public CustomerHomePGAdapter(List<PGModel> custHomeList, Context context) {
        CustHomePGList = custHomeList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemhomepginfo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final PGModel pgModel = CustHomePGList.get(position);
        holder.txtHomePGAddress.setText(pgModel.getPGAddress());
        holder.txtHomePGContactNo.setText(pgModel.getPGConNo());
        holder.ShowImgPG.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(pgModel.getImgPG()));
        holder.txtHomePGContactNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callintent = new Intent(Intent.ACTION_CALL);
                callintent.setData(Uri.parse("tel:" + pgModel.getPGConNo()));
                context.startActivity(callintent);
            }
        });
        holder.ShowImgPG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowPGInfo.class);
                intent.putExtra("pgInfo", pgModel);
                context.startActivity(intent);
            }
        });
        holder.ImgFavCHomePG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustMyFavorate custMyFavorate=new CustMyFavorate();

                custMyFavorate.setTxtFavName(pgModel.getPGName());
                custMyFavorate.setFavAddress(pgModel.getPGAddress());
                custMyFavorate.setFavContactNo(pgModel.getPGConNo());
                custMyFavorate.setImgFav(pgModel.getImgPG());
                CustMyFavorate custMyFavorate1= HostelDatabaseClient
                        .gethInstance(context)
                        .getAppDatabase()
                        .myFavDao().SelectFavById(pgModel.getPGId());

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
        return CustHomePGList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  txtHomePGContactNo, txtHomePGAddress;
        ImageView ShowImgPG, ImgFavCHomePG;
        RatingBar AddRateHomePG;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHomePGContactNo = itemView.findViewById(R.id.txtHomePGContactNo);
            txtHomePGAddress = itemView.findViewById(R.id.txtHomePGAddress);
            ShowImgPG = itemView.findViewById(R.id.ShowImgPG);
            AddRateHomePG = itemView.findViewById(R.id.AddRateHomePG);
            ImgFavCHomePG = itemView.findViewById(R.id.ImgFavCHomePG);
        }
    }
}
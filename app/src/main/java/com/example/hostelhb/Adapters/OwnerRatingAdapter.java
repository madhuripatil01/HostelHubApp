package com.example.hostelhb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelhb.DataModels.OwnerShowRating;
import com.example.hostelhb.R;

import java.util.ArrayList;
import java.util.List;

public class OwnerRatingAdapter extends RecyclerView.Adapter<OwnerRatingAdapter.MyViewHolder> {
    List<OwnerShowRating> CustRateList=new ArrayList<>();
    Context context;


    public OwnerRatingAdapter(List<OwnerShowRating> custRateList, Context context) {
        CustRateList = custRateList;
        this.context = context;
    }

    @NonNull
    @Override
    public OwnerRatingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemratingreclayout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OwnerRatingAdapter.MyViewHolder holder, int position)
    {
        OwnerShowRating ownerShowRating=CustRateList.get(position);
        holder.txtCustRateName.setText(ownerShowRating.getCustRateName());
        holder.txtCustRateComment.setText(ownerShowRating.getRateComment());
        holder.CustRateStar.setRating(ownerShowRating.getRateStars());
        //holder.ImgRateCustProf.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(ownerShowRating.getImgRate()));
    }

    @Override
    public int getItemCount()
    {
        return CustRateList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtCustRateName,txtCustRateComment;
        RatingBar CustRateStar;
        RelativeLayout relRating;
        ImageView ImgRateCustProf;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCustRateName=itemView.findViewById(R.id.txtCustRateName);
            txtCustRateComment=itemView.findViewById(R.id.txtCustRateComment);
            CustRateStar=itemView.findViewById(R.id.CustRateStar);
            relRating=itemView.findViewById(R.id.relRating);
            ImgRateCustProf=itemView.findViewById(R.id.ImgRateCustProf);

        }
    }
}

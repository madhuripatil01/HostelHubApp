package com.example.hostelhb.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelhb.DataModels.OwnerShowInquiries;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.UtilityMethods;

import java.util.ArrayList;
import java.util.List;

public class OwnerHomeInquiriesAdapter extends RecyclerView.Adapter<OwnerHomeInquiriesAdapter.MyViewHolder>
{
  List<OwnerShowInquiries> CustList=new ArrayList<>();
  Context context;


    public OwnerHomeInquiriesAdapter(List<OwnerShowInquiries> custList, Context context) {
        CustList = custList;
        this.context = context;
    }

    @NonNull
    @Override
    public OwnerHomeInquiriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemlayoutrecyclerview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OwnerHomeInquiriesAdapter.MyViewHolder holder, int position)
    {
        final OwnerShowInquiries ownerShowInquiries=CustList.get(position);
        holder.txtCustInqName.setText(ownerShowInquiries.getCustomerName());
        holder.txtCustInqContactNo.setText(ownerShowInquiries.getContactNo());
        holder.txtInqFor.setText(ownerShowInquiries.getInquiryFor());
        holder.txtInqDate.setText(ownerShowInquiries.getInqDate());
        holder.txtInqTime.setText(ownerShowInquiries.getInqTime());
        holder.ImgInqCust.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(ownerShowInquiries.getImgInq()));

        holder.txtCustInqContactNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent callintent=new Intent(Intent.ACTION_CALL);
                callintent.setData(Uri.parse("tel:" + ownerShowInquiries.getContactNo()));
                context.startActivity(callintent);
            }
        });
        holder.relCustInqItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    @Override
    public int getItemCount() {
        return CustList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtCustInqName,txtCustInqContactNo,txtInqFor,txtInqDate,txtInqTime;
        ImageView ImgInqCust;
        RelativeLayout relCustInqItem;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtCustInqName=itemView.findViewById(R.id.txtCustInqName);
            txtCustInqContactNo=itemView.findViewById(R.id.txtCustInqContactNo);
            txtInqFor=itemView.findViewById(R.id.txtCustInqFor);
            txtInqDate=itemView.findViewById(R.id.txtCustInqDate);
            txtInqTime=itemView.findViewById(R.id.txtCustInqTime);
            ImgInqCust=itemView.findViewById(R.id.ImgInqCust);
            relCustInqItem=itemView.findViewById(R.id.relCustInqItem);
        }
    }
}

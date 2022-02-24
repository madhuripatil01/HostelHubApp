package com.example.hostelhb.Adapters;

import
        android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelhb.DataModels.CustomerModel;
import com.example.hostelhb.DataModels.OwnerModel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.UtilityMethods;

import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> {
    List<CustomerModel> custList;
    Context context;

    public CustomerListAdapter(List<CustomerModel> custList, Context context) {
        this.context = context;
        this.custList = custList;
    }
        @NonNull
        @Override
        public CustomerListAdapter.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent,
        int viewType){
            return new CustomerListAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.admin_customer_layout, parent, false));
        }

        @Override
        public void onBindViewHolder (@NonNull MyViewHolder holder,int position){
            final CustomerModel customerModel = custList.get(position);

            holder.txtcustname.setText(customerModel.getCustomerName());
            holder.txtcustemail.setText(customerModel.getCustomerEmail());
            holder.txtcustmobno.setText(customerModel.getCustomerContNo());
            holder.txtcustaddress.setText(customerModel.getCustomerAddress());

            holder.CustomerProImg.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap
                    (customerModel.getImgCustomer()));

        }


        @Override
        public int getItemCount () {
            return custList.size();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView txtcustname, txtcustemail, txtcustmobno, txtcustaddress;
            ImageView CustomerProImg;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                txtcustname = itemView.findViewById(R.id.txtcustname);
                txtcustemail = itemView.findViewById(R.id.txtcustemail);
                txtcustaddress = itemView.findViewById(R.id.txtcustaddress);
                txtcustmobno = itemView.findViewById(R.id.txtcustmbno);
                CustomerProImg = itemView.findViewById(R.id.customerimg);


            }
        }
    }




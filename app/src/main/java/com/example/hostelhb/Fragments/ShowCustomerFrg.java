package com.example.hostelhb.Fragments;//package com.example.rebuy.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelhb.Adapters.CustomerListAdapter;
import com.example.hostelhb.DataModels.CustomerModel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;

import java.util.List;


public class ShowCustomerFrg extends Fragment
{
    private List<CustomerModel> CustList;
    Context context;
    RecyclerView customer_recview;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.showcustomer,container,false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customer_recview=view.findViewById(R.id.customer_reclist);

       CustList= HostelDatabaseClient.gethInstance(context).getAppDatabase().customerDAO().getAllCustomer();


       CustomerListAdapter customerListAdapter =new CustomerListAdapter(CustList,context);
       customer_recview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
      customer_recview.setAdapter(customerListAdapter);


    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}



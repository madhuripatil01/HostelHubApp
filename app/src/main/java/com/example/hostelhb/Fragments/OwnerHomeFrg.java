package com.example.hostelhb.Fragments;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelhb.Adapters.OwnerHomeInquiriesAdapter;
import com.example.hostelhb.DataModels.OwnerShowInquiries;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class OwnerHomeFrg extends Fragment
{
    RecyclerView RecView;
    Context context;
    List<OwnerShowInquiries> CustList=new ArrayList<>();
    @Override
    public void onAttach(Context context) {

        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ownerhomefrg,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},10);

        RecView=view.findViewById(R.id.RecView);
        CustList= HostelDatabaseClient.gethInstance(getActivity().getApplicationContext())
                .getAppDatabase().ownerInquiriesDAO().getAllInquiries();

        OwnerHomeInquiriesAdapter customerAdapter=new OwnerHomeInquiriesAdapter(CustList,context);
        RecView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        RecView.setAdapter(customerAdapter);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }
}

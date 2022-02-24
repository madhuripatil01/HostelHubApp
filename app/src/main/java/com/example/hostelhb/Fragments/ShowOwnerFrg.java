package com.example.hostelhb.Fragments;

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

import com.example.hostelhb.Adapters.OwnerListAdapter;
import com.example.hostelhb.DataModels.OwnerModel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;

import java.util.List;


public class ShowOwnerFrg extends Fragment {

    private List<OwnerModel> ownerList;

    Context context;
    RecyclerView ownerreclist;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.showowner,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ownerreclist=view.findViewById(R.id.ownerreclist);

        ownerList= HostelDatabaseClient.gethInstance(context).getAppDatabase().ownerDAO().getAllOwner();

        OwnerListAdapter buyerListAdapter=new OwnerListAdapter(ownerList,context);
        ownerreclist.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
        ownerreclist.setAdapter(buyerListAdapter);


    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}



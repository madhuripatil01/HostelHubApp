package com.example.hostelhb.Fragments;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelhb.Adapters.CustomerHomeFlatAdapter;
import com.example.hostelhb.Adapters.CustomerHomeHostelAdapter;
import com.example.hostelhb.Adapters.CustomerHomeMesAdapter;
import com.example.hostelhb.Adapters.CustomerHomePGAdapter;
import com.example.hostelhb.DataModels.FlatsModel;
import com.example.hostelhb.DataModels.PGModel;
import com.example.hostelhb.DataModels.SearchHostel;
import com.example.hostelhb.DataModels.SearchMes;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class CustomerHomeFrg extends Fragment implements View.OnClickListener {
    List<SearchHostel> CustHomeHosList=new ArrayList<>();
    List<SearchMes> CustHomeMesList=new ArrayList<>();
    List<FlatsModel> CustHomeFlatList=new ArrayList<>();
    List<PGModel> CustHomePGList=new ArrayList<>();
    Context context;
    Button btnShowHosList,btnShowMesList,btnShowFlatList,btnShowPGList;
    RecyclerView RecViewCustHome;


    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.customerhomefrglayout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},10);
        RecViewCustHome=view.findViewById(R.id.RecViewCustHome);
        btnShowHosList=view.findViewById(R.id.btnShowHosList);
        btnShowMesList=view.findViewById(R.id.btnShowMesList);
        btnShowFlatList=view.findViewById(R.id.btnShowFlatList);
        btnShowPGList=view.findViewById(R.id.btnShowPGList);

        btnShowMesList.setOnClickListener(this);
        btnShowHosList.setOnClickListener(this);
        btnShowFlatList.setOnClickListener(this);
        btnShowPGList.setOnClickListener(this);

        CustHomeHosList= HostelDatabaseClient.gethInstance(context)
                .getAppDatabase()
                .searchHostelDAO().getAllSHostels();

        CustHomeMesList=HostelDatabaseClient.gethInstance(context)
                .getAppDatabase()
                .searchMesDAO().getAllSMes();

        CustHomeFlatList=HostelDatabaseClient.gethInstance(context)
                .getAppDatabase()
                .flatsDAO().getAllFlats();

        CustHomePGList=HostelDatabaseClient.gethInstance(context)
                .getAppDatabase()
                .pgdao().getAllPGs();



    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnShowHosList)
        {
            CustomerHomeHostelAdapter customerHomeHostelAdapter = new CustomerHomeHostelAdapter(CustHomeHosList, context);
            RecViewCustHome.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            RecViewCustHome.setAdapter(customerHomeHostelAdapter);
        }
        if (v.getId()==R.id.btnShowMesList)
        {
            CustomerHomeMesAdapter customerHomeMesAdapter=new CustomerHomeMesAdapter(CustHomeMesList,context);
            RecViewCustHome.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            RecViewCustHome.setAdapter(customerHomeMesAdapter);
        }
        if(v.getId()==R.id.btnShowFlatList)
        {
            CustomerHomeFlatAdapter customerHomeflatAdapter = new CustomerHomeFlatAdapter(CustHomeFlatList, context);
            RecViewCustHome.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            RecViewCustHome.setAdapter(customerHomeflatAdapter);
        }
        if(v.getId()==R.id.btnShowPGList)
        {
            CustomerHomePGAdapter customerHomepgAdapter = new CustomerHomePGAdapter(CustHomePGList, context);
            RecViewCustHome.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            RecViewCustHome.setAdapter(customerHomepgAdapter);

        }
    }
}

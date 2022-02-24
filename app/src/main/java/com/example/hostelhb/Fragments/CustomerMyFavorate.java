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

import com.example.hostelhb.Adapters.MyFavoratesAdapter;
import com.example.hostelhb.DataModels.CustMyFavorate;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class CustomerMyFavorate extends Fragment {
    List<CustMyFavorate> FavorateList=new ArrayList<>();
    Context context;
    RecyclerView RecViewFav;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.customermyfavorate,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},10);
        RecViewFav=view.findViewById(R.id.RecViewFav);

        FavorateList=HostelDatabaseClient.gethInstance(getActivity()
                .getApplicationContext())
                .getAppDatabase()
                .myFavDao()
                .getAllFav();

        MyFavoratesAdapter myFavoratesAdapter=new MyFavoratesAdapter(FavorateList,context);
        RecViewFav.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        RecViewFav.setAdapter(myFavoratesAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

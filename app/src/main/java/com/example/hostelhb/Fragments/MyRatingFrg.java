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

import com.example.hostelhb.Adapters.OwnerRatingAdapter;
import com.example.hostelhb.DataModels.OwnerShowRating;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class MyRatingFrg extends Fragment
{
    List<OwnerShowRating> CustRateList=new ArrayList<>();
    Context context;
    RecyclerView RateRecView;
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.myratinglayout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RateRecView=view.findViewById(R.id.RateRecView);
        CustRateList= HostelDatabaseClient.gethInstance(getActivity().getApplicationContext())
                .getAppDatabase().ownerRatingDAO().getAllShowRatings();

        OwnerRatingAdapter customerRatingAdapter=new OwnerRatingAdapter(CustRateList,context);
        RateRecView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        RateRecView.setAdapter(customerRatingAdapter);

    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }
}

package com.example.hostelhb.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hostelhb.Adapters.PGSearchAdapter;

import com.example.hostelhb.DataModels.PGModel;
import com.example.hostelhb.DataModels.SearchHostel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class PGSearchFrg extends Fragment
{
    List<PGModel> PGList;
    Context context;
    RecyclerView RecViewSearchPG;
    PGSearchAdapter pgSearchAdapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pgsearchfrglayout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        RecViewSearchPG=view.findViewById(R.id.RecViewSearchPG);

        PGList= HostelDatabaseClient.gethInstance(getActivity().getApplicationContext())
                .getAppDatabase().pgdao().getAllPGs();


        pgSearchAdapter=new PGSearchAdapter(PGList,context);
        RecViewSearchPG.setLayoutManager(new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false));
        RecViewSearchPG.setAdapter(pgSearchAdapter);


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menusearch,menu);

        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) menuItem.getActionView();

        searchView.setQueryHint("Search PG's");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<PGModel> PList=filter(PGList,newText);
                pgSearchAdapter.setFilterList(PList);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private List<PGModel> filter(List<PGModel> pgList, String newText)
    {
        final  List<PGModel> PList=new ArrayList<>();
        for (PGModel pgModel : pgList)
        {
            final String text=pgModel.getPGAddress().toLowerCase();
            final String text1=pgModel.getPGCity().toLowerCase();
            if (text.contains(newText) || text1.contains(newText))
            {

                PList.add(pgModel);
            }
        }
        return PList;
    }




    @Override
    public void onDetach()
    {
        super.onDetach();
    }

}

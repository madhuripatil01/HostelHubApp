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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelhb.Adapters.MesSearchAdapter;
import com.example.hostelhb.DataModels.SearchMes;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class MesSearchFrg extends Fragment
{
    List<SearchMes> MesList=new ArrayList<>();
    Context context;
    RecyclerView RecViewSearchMes;
    MesSearchAdapter mesSearchAdapter;

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.messearchfrglayout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        RecViewSearchMes=view.findViewById(R.id.RecViewSearchMes);

        MesList= HostelDatabaseClient.gethInstance(getActivity().getApplicationContext())
                .getAppDatabase().searchMesDAO().getAllSMes();

        mesSearchAdapter=new MesSearchAdapter(MesList,context);
        RecViewSearchMes.setLayoutManager(new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false));
        RecViewSearchMes.setAdapter(mesSearchAdapter);
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menusearch,menu);

        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) menuItem.getActionView();

        searchView.setQueryHint("Search Mes");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<SearchMes> MList=filter(MesList,newText);
                mesSearchAdapter.setFilterList(MList);
                return true;
            }
        });
    }

    private List<SearchMes> filter(List<SearchMes> mesList, String newText)
    {
        final  List<SearchMes> MList=new ArrayList<>();
        for (SearchMes searchMes : mesList)
        {
            final String text=searchMes.getMesName().toLowerCase();
            final String text1=searchMes.getMesAddress().toLowerCase();
            final String text2=searchMes.getMesCity().toLowerCase();
            if (text.contains(newText)||text1.contains(newText)||text2.contains(newText))
            {
                MList.add(searchMes);
            }
        }
        return MList;
    }


    @Override
    public void onDetach()
    {
        super.onDetach();
    }
}

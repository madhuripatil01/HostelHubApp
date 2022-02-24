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

import com.example.hostelhb.Adapters.FlatSearchAdapter;
import com.example.hostelhb.Adapters.HostelSearchAdapter;
import com.example.hostelhb.DataModels.FlatsModel;
import com.example.hostelhb.DataModels.SearchHostel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class FlatSearchFrg extends Fragment
{
    List<FlatsModel> FlatList;
    Context context;
    RecyclerView RecViewSearchFlats;
   FlatSearchAdapter flatSearchAdapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.flatsearchfrglayout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        RecViewSearchFlats=view.findViewById(R.id.RecViewSearchFlats);



        FlatList= HostelDatabaseClient.gethInstance(getActivity().getApplicationContext())
                .getAppDatabase().flatsDAO().getAllFlats();


        flatSearchAdapter=new FlatSearchAdapter(FlatList,context);
        RecViewSearchFlats.setLayoutManager(new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false));
        RecViewSearchFlats.setAdapter(flatSearchAdapter);



    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menusearch,menu);

        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) menuItem.getActionView();

        searchView.setQueryHint("Search Flats");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<FlatsModel> FList=filter(FlatList,newText);
                flatSearchAdapter.setFilterList(FList);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private List<FlatsModel> filter(List<FlatsModel> flatList,String newText)
    {
        final  List<FlatsModel> FList=new ArrayList<>();
        for (FlatsModel flatsModel : flatList)
        {
            final String text=flatsModel.getFlatName().toLowerCase();
            final String text1=flatsModel.getFlatAddress().toLowerCase();
            final String text2=flatsModel.getFlatCity().toLowerCase();
            if (text.contains(newText)||text1.contains(newText)||text2.contains(newText))
            {

                FList.add(flatsModel);
            }
        }
        return FList;
    }



    @Override
    public void onDetach()
    {
        super.onDetach();
    }

}

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

import com.example.hostelhb.Adapters.HostelSearchAdapter;
import com.example.hostelhb.DataModels.SearchHostel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class HostelSearchFrg extends Fragment
{
    List<SearchHostel> HostelList;
    Context context;
    RecyclerView RecViewSearchHostel;
    HostelSearchAdapter hostelSearchAdapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hostelsearchfrglayout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        RecViewSearchHostel=view.findViewById(R.id.RecViewSearchHostel);


        HostelList= HostelDatabaseClient.gethInstance(getActivity().getApplicationContext())
                .getAppDatabase().searchHostelDAO().getAllSHostels();


        hostelSearchAdapter=new HostelSearchAdapter(HostelList,context);
        RecViewSearchHostel.setLayoutManager(new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false));
        RecViewSearchHostel.setAdapter(hostelSearchAdapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menusearch,menu);

        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) menuItem.getActionView();

        searchView.setQueryHint("Search Hostels");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<SearchHostel> HList=filter(HostelList,newText);
                hostelSearchAdapter.setFilterList(HList);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }


    private List<SearchHostel> filter(List<SearchHostel> hostelList,String newText)
    {
        final  List<SearchHostel> HList=new ArrayList<>();
        for (SearchHostel searchHostel : hostelList)
        {
            final String text=searchHostel.getHosName().toLowerCase();
        final String text1=searchHostel.getHosAddress().toLowerCase();
            final String text2=searchHostel.getHosCity().toLowerCase();

            if (text.contains(newText)||text1.contains(newText)||text2.contains(newText))
            {

                HList.add(searchHostel);
            }
        }
        return HList;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

}

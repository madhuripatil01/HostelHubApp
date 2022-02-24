package com.example.hostelhb.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.hostelhb.Fragments.FlatSearchFrg;
import com.example.hostelhb.Fragments.HostelSearchFrg;
import com.example.hostelhb.Fragments.MesSearchFrg;
import com.example.hostelhb.Fragments.PGSearchFrg;
import com.example.hostelhb.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity
{
    Toolbar Searchtoolbar;
    TabLayout Searchtablayout;
    ViewPager SearchviewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customersearchlayout);

        Searchtoolbar=findViewById(R.id.Searchtoolbar);

        setSupportActionBar(Searchtoolbar);

        Searchtablayout=findViewById(R.id.Serchtablayout);
        SearchviewPager=findViewById(R.id.Searchviewpager);

        ViewPagerAdapter viewPageAdapter=new ViewPagerAdapter((getSupportFragmentManager()));

        viewPageAdapter.addFragmentAndTitle(new HostelSearchFrg(),"Hostel");
        viewPageAdapter.addFragmentAndTitle(new MesSearchFrg(),"Mess");
        viewPageAdapter.addFragmentAndTitle(new FlatSearchFrg(),"Flats");
        viewPageAdapter.addFragmentAndTitle(new PGSearchFrg(),"PGs");

        SearchviewPager.setAdapter(viewPageAdapter);
        Searchtablayout.setupWithViewPager(SearchviewPager);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private  final ArrayList<Fragment> fragmentList=new ArrayList<>();
        private final ArrayList<String> tabtitles=new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabtitles.get(position);
        }
        public void addFragmentAndTitle(Fragment fragment,String title)
        {
            fragmentList.add(fragment);
            tabtitles.add(title);
        }
    }


}

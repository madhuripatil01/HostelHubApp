package com.example.hostelhb.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.hostelhb.Fragments.CustomerProfile;
import com.example.hostelhb.Fragments.CustomerHomeFrg;
import com.example.hostelhb.Fragments.CustomerMyFavorate;
import com.example.hostelhb.R;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CustomerHomeActivity1 extends AppCompatActivity
{
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    Toolbar Custtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home1);
        Custtoolbar=findViewById(R.id.Custtoolbar);
        setSupportActionBar(Custtoolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.Custdrawer_layout);
        navigationView=(NavigationView)findViewById(R.id.Custnav_view);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment,new CustomerHomeFrg());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home");
        setUpNavigationView();

    }

    private void setUpNavigationView()
    {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_home:
                        FragmentManager fragmentManager=getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment,new CustomerHomeFrg());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Home");
                        break;
                    case R.id.nav_Favorate:
                        FragmentManager fragmentManager1=getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction1=fragmentManager1.beginTransaction();
                        fragmentTransaction1.replace(R.id.nav_host_fragment,new CustomerMyFavorate());
                        fragmentTransaction1.commit();
                        getSupportActionBar().setTitle("Favorates");
                        break;
                    case R.id.nav_rating:
                        FragmentManager fragmentManager2=getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction2=fragmentManager2.beginTransaction();
                        fragmentTransaction2.replace(R.id.nav_host_fragment,new CustomerProfile());
                        fragmentTransaction2.commit();
                        getSupportActionBar().setTitle("Profile");
                        break;
                    case R.id.nav_Search:
                        startActivity(new Intent(CustomerHomeActivity1.this, SearchActivity.class));
                        drawerLayout.closeDrawers();
                        getSupportActionBar().setTitle("Search");
                        return true;
                    default:
                }
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);
                 drawerLayout.closeDrawers();
                return true;
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(CustomerHomeActivity1.this,drawerLayout,Custtoolbar,R.string.openDrawer,R.string.closeDrawer)
        {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }



    @Override
    public void onBackPressed()
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }


}

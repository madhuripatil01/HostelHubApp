package com.example.hostelhb.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hostelhb.Fragments.AddHostelFrg;
import com.example.hostelhb.Fragments.AddMesFrg;
import com.example.hostelhb.Fragments.CategoryFragment;
import com.example.hostelhb.Fragments.OwnerProfileFrg;
import com.example.hostelhb.Fragments.MyRatingFrg;
import com.example.hostelhb.Fragments.OwnerHomeFrg;
import com.example.hostelhb.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OwnerHomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView btmNavId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ownerhomelayout);
        btmNavId=findViewById(R.id.btmNavId);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmlayout,new OwnerHomeFrg());
        fragmentTransaction.commit();
        btmNavId.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        if (menuItem.getItemId()==R.id.menuhome)
        {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmlayout,new OwnerHomeFrg());
            fragmentTransaction.commit();
        }

        if (menuItem.getItemId()==R.id.menuAddCategory)
        {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmlayout,new CategoryFragment());
            fragmentTransaction.commit();
        }
//        if (menuItem.getItemId()==R.id.menuAddHostel)
//        {
//            FragmentManager fragmentManager=getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frmlayout,new AddHostelFrg());
//            fragmentTransaction.commit();
//        }
        if (menuItem.getItemId()==R.id.menurating)
        {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmlayout,new MyRatingFrg());
            fragmentTransaction.commit();
        }
        if (menuItem.getItemId()==R.id.menuProfile)
        {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmlayout,new OwnerProfileFrg());
            fragmentTransaction.commit();
        }
        return false;
    }
}

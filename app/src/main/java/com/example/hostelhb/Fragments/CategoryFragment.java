package com.example.hostelhb.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.hostelhb.Activities.AddFlatsActivity;
import com.example.hostelhb.Activities.AddHostelActivity;
import com.example.hostelhb.Activities.AddMessActivity;
import com.example.hostelhb.Activities.AddPGActivity;
import com.example.hostelhb.R;

public class CategoryFragment extends Fragment implements View.OnClickListener {
    CardView crdHostel,crdMess,crdFlats,crdPGs;
    Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.category_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        crdHostel=view.findViewById(R.id.cardviewHostel);
        crdMess=view.findViewById(R.id.cardviewMess);
        crdFlats=view.findViewById(R.id.cardviewFlats);
        crdPGs=view.findViewById(R.id.cardviewPGs);
        crdHostel.setOnClickListener(this);
        crdMess.setOnClickListener(this);
        crdFlats.setOnClickListener(this);
        crdPGs.setOnClickListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.cardviewHostel)
        {
            Intent intent = new Intent(context, AddHostelActivity.class);
            intent.putExtra("catogoryHostel","Hostel");
            startActivity(intent);
        }
        else if (v.getId()==R.id.cardviewMess)
        {
            Intent intent = new Intent(context, AddMessActivity.class);
            intent.putExtra("catogoryMess","Mess");
            startActivity(intent);

        }
        else if(v.getId()==R.id.cardviewFlats)
        {
            Intent intent = new Intent(context, AddFlatsActivity.class);
            intent.putExtra("catogoryFlats","Flats");
            startActivity(intent);
        }
        else if(v.getId()==R.id.cardviewPGs)
        {
            Intent intent = new Intent(context, AddPGActivity.class);
            intent.putExtra("catogoryPG","PG");
            startActivity(intent);
        }
    }
}

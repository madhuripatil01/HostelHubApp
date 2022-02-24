package com.example.hostelhb.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hostelhb.R;

public class AddOwnerProfileActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnOwnerSubmit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addownerprofilelayout);
        btnOwnerSubmit=findViewById(R.id.btnOwnerSubmit);
        btnOwnerSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(AddOwnerProfileActivity.this,OwnerHomeActivity.class);
        startActivity(intent);
    }
}

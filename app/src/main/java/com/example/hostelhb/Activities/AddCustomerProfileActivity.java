package com.example.hostelhb.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hostelhb.R;

public class AddCustomerProfileActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnCustomerSubmit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcustomerorofilelayout);
        btnCustomerSubmit=findViewById(R.id.btnCustomerSubmit);
        btnCustomerSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(AddCustomerProfileActivity.this,CustomerHomeActivity1.class);
        startActivity(intent);
    }
}

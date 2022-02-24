package com.example.hostelhb.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;


public class Forget_Pass_Act extends AppCompatActivity implements View.OnClickListener {
    EditText edtEmail,edtPass,edtConpass;
    Button btnChngPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_layout);
        edtEmail=findViewById(R.id.edtemail);
        edtPass=findViewById(R.id.edtPass);
        edtConpass=findViewById(R.id.edtCPass);
        btnChngPass=findViewById(R.id.btnChngPass);

        btnChngPass.setOnClickListener(this);
    }




    @Override
    public void onClick(View v)
    {
        HostelDatabaseClient.gethInstance(getApplicationContext()).getAppDatabase().userDAO()
                .Update(edtPass.getText().toString(),edtEmail.getText().toString());


      // HostelDatabaseClient.getRebuyInstance(getApplicationContext()).getRebuyDatabase()
               //.userDAO().Update(edtPass.getText().toString()
                        //,edtEmail.getText().toString());

        Toast.makeText(this,"Password Changed Successfully",Toast.LENGTH_SHORT).show();

    }
}

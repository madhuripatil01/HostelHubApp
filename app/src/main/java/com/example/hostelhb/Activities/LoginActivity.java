package com.example.hostelhb.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hostelhb.DataModels.UserModel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLog;
    String uType;
    EditText edtemail,edtpasswd;
    TextView txtforget;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivitylayout);

        uType=getIntent().getStringExtra("utype");
        edtemail=findViewById(R.id.edtemail);
        edtpasswd=findViewById(R.id.edtpasswd);
        btnLog=findViewById(R.id.btnSignIn);
        txtforget=findViewById(R.id.txtforget);
        btnLog.setOnClickListener(this);
        txtforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,Forget_Pass_Act.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) 
    {
      if(edtemail.getText().toString().equals("admin") && edtpasswd.getText().toString().equals("admin"))
        {
            Intent intent = new Intent(LoginActivity.this, Appbar_Activity.class);
            startActivity(intent);


        }

     else{
        UserModel userModel= HostelDatabaseClient
                .gethInstance(getApplicationContext())
                .getAppDatabase()
                .userDAO().
                        userLogin(edtemail.getText().toString(),edtpasswd.getText().toString());
        if(userModel!=null)
        {
            if (userModel.getUserType().equals("Owner"))
            {
                Intent intent = new Intent(LoginActivity.this, OwnerHomeActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(LoginActivity.this, CustomerHomeActivity1.class);
                startActivity(intent);
            }
        }
}



    }
}

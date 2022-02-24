package com.example.hostelhb.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hostelhb.DataModels.UserModel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.EmailValidater;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.SharedPreferencesManager;

public class RegistrationAct extends AppCompatActivity implements View.OnClickListener {
    Button btnReg;
    TextView txtOPenLog;
    RadioButton RbOwner,RbCustomer;
    RadioGroup rbgroup;
    String Email;
    EditText edtRegName,edtRegMobNo,edtRegEmail,edtRegPasswd,edtRegAddress,edtRegCity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrationlayout);
        btnReg=findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);
        txtOPenLog=findViewById(R.id.txtOpenLogAct);
        RbOwner=findViewById(R.id.RbOwner);
        RbCustomer=findViewById(R.id.RbCustomer);
        rbgroup=findViewById(R.id.rbgroup);
        edtRegName=findViewById(R.id.edtRegName);
        edtRegMobNo=findViewById(R.id.edtRegMobNo);
        edtRegEmail=findViewById(R.id.edtRegEmail);
        edtRegPasswd=findViewById(R.id.edtRegPasswd);
        edtRegAddress=findViewById(R.id.edtRegAddress);
        edtRegCity=findViewById(R.id.edtRegCity);
        txtOPenLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegistrationAct.this,LoginActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onClick(View v)
    {
        EmailValidater emailValidater=new EmailValidater();

        if(!TextUtils.isEmpty(edtRegEmail.getText().toString())
            && !TextUtils.isEmpty(edtRegMobNo.getText().toString())
                && !TextUtils.isEmpty(edtRegPasswd.getText().toString())
                )
        {

            if ( emailValidater.validate(edtRegEmail.getText().toString())==true)
                {

           UserModel userModel=new UserModel();

            userModel.setName(edtRegName.getText().toString());
            userModel.setConNo(edtRegMobNo.getText().toString());
            userModel.setEmail(edtRegEmail.getText().toString());
            userModel.setUPasswd(edtRegPasswd.getText().toString());
            userModel.setAddress(edtRegAddress.getText().toString());
            userModel.setCity(edtRegCity.getText().toString());
            userModel.setUserType(getRadioButtonValue());

            long uId= HostelDatabaseClient.gethInstance(getApplicationContext())
                    .getAppDatabase()
                    .userDAO().insertUsers(userModel);

            Toast.makeText(this, "Registration Successfull", Toast.LENGTH_SHORT).show();

            SharedPreferencesManager.setUserId(RegistrationAct.this,uId);
            SharedPreferencesManager.setUserType(RegistrationAct.this,getRadioButtonValue());
            SharedPreferencesManager.setUserMobNo(RegistrationAct.this,edtRegMobNo.getText().toString());
            SharedPreferencesManager.setEmail(RegistrationAct.this,edtRegEmail.getText().toString());
            SharedPreferencesManager.setAddress(RegistrationAct.this,edtRegAddress.getText().toString());
            SharedPreferencesManager.setCity(RegistrationAct.this,edtRegCity.getText().toString());
            SharedPreferencesManager.setUserName(RegistrationAct.this,edtRegName.getText().toString());


            Intent intent = new Intent(RegistrationAct.this, LoginActivity.class);
            intent.putExtra("utype",getRadioButtonValue());
            startActivity(intent);
                }
                else
                {
                    Toast.makeText(this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }
        }
        else
        {

            Toast.makeText(this, "Enter all field", Toast.LENGTH_SHORT).show();
        }
    }


    private String getRadioButtonValue()
    {
        int chkId=rbgroup.getCheckedRadioButtonId();

        if(chkId==R.id.RbOwner)
        {
            return "Owner";
        }else {
            return "Customer";
        }
    }
}

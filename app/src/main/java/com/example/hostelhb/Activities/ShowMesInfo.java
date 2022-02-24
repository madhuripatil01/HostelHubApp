package com.example.hostelhb.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hostelhb.DataModels.OwnerShowRating;
import com.example.hostelhb.DataModels.SearchMes;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.UtilityMethods;

public class ShowMesInfo extends AppCompatActivity implements View.OnClickListener {
    ImageView ImgMesShow;
    TextView txtMesName,txtMesAddress,txtMesConNo,txtMesCity,txtMonRent;
    Button AddMesRatingBtn,AddRating;
    EditText edtAddCustName,edtAddCustComment;
    RatingBar CustAddRating;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmesinfo);
        ImgMesShow=findViewById(R.id.ImgMesShow);
        txtMesName=findViewById(R.id.txtMesName);
        txtMesAddress=findViewById(R.id.txtMesAddress);
        txtMesConNo=findViewById(R.id.txtMesContactNo);
        txtMesCity=findViewById(R.id.txtMesCity);
        txtMonRent=findViewById(R.id.txtMonRent);
        AddMesRatingBtn=findViewById(R.id.AddMesRatingBtn);
        AddMesRatingBtn.setOnClickListener(this);

        SearchMes searchMes= (SearchMes) getIntent().getSerializableExtra("mesInfo");

        txtMesName.setText(searchMes.getMesName());
        txtMesAddress.setText(searchMes.getMesAddress());
        txtMesCity.setText(searchMes.getMesCity());
        txtMesConNo.setText(searchMes.getMesConNo());
        txtMonRent.setText(searchMes.getMesMonRent());
        ImgMesShow.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(searchMes.getImgMes()));

    }

    @Override
    public void onClick(View v)
    {
        View view= LayoutInflater.from(ShowMesInfo.this).inflate(R.layout.addrating,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(ShowMesInfo.this);
        builder.setView(view);
        builder.create().show();


        AddRating=view.findViewById(R.id.btnAdd);
        edtAddCustName=view.findViewById(R.id.edtAddCustName);
        edtAddCustComment=view.findViewById(R.id.edtAddCustComment);
        CustAddRating=view.findViewById(R.id.CustAddRating);
        AddRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OwnerShowRating ownerShowRating = new OwnerShowRating();

                ownerShowRating.setCustRateName(edtAddCustName.getText().toString());
                ownerShowRating.setRateComment(edtAddCustComment.getText().toString());
                ownerShowRating.setRateStars(CustAddRating.getRating());

                HostelDatabaseClient.gethInstance(getApplicationContext())
                        .getAppDatabase()
                        .ownerRatingDAO()
                        .insertSRatings(ownerShowRating);
                Toast.makeText(ShowMesInfo.this, "Rating Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


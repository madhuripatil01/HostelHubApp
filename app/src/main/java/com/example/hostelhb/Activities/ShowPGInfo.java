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
import com.example.hostelhb.DataModels.PGModel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.UtilityMethods;

public class ShowPGInfo extends AppCompatActivity implements View.OnClickListener {
    ImageView ImgPGShow;
    TextView txtPGAddress,txtPGConNo,txtPGCity,txtPGRent,txtPGCapacity,txtPGFacilities;
    Button AddPGRatingBtn,AddRating;
    EditText edtAddCustName,edtAddCustComment;
    RatingBar CustAddRating;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showpginfo);
        ImgPGShow=findViewById(R.id.ImgPGShow);
        txtPGAddress=findViewById(R.id.txtPGAddress);
        txtPGConNo=findViewById(R.id.txtPGContactNo);
        txtPGCity=findViewById(R.id.txtPGCity);
        txtPGRent=findViewById(R.id.txtPGRent);
        txtPGCapacity=findViewById(R.id.txtPGCapacity);
        txtPGFacilities=findViewById(R.id.txtPGFacilities);

        AddPGRatingBtn=findViewById(R.id.AddPGRatingBtn);
        AddPGRatingBtn.setOnClickListener(this);

        PGModel pgModel= (PGModel) getIntent().getSerializableExtra("pgInfo");


        txtPGCapacity.setText(pgModel.getPGName());
        txtPGAddress.setText(pgModel.getPGAddress());
        txtPGCity.setText(pgModel.getPGCity());
        txtPGConNo.setText(pgModel.getPGConNo());
        txtPGRent.setText(pgModel.getPGRent());
        ImgPGShow.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(pgModel.getImgPG()));
        txtPGFacilities.setText(pgModel.getFacilities());

    }

    @Override
    public void onClick(View v)
    {
        View view= LayoutInflater.from(ShowPGInfo.this).inflate(R.layout.addrating,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(ShowPGInfo.this);
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
                Toast.makeText(ShowPGInfo.this, "Rating Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


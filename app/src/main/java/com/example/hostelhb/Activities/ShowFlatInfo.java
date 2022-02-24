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

import com.example.hostelhb.DataModels.FlatsModel;
import com.example.hostelhb.DataModels.OwnerShowRating;
import com.example.hostelhb.DataModels.SearchMes;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.UtilityMethods;

public class ShowFlatInfo extends AppCompatActivity implements View.OnClickListener {
    ImageView ImgFlatShow;
    TextView txtFlatAddress,txtFlatConNo,txtFlatCity,txtRent,txtFlatType,txtFlatFacilities;
    Button AddFlatRatingBtn,AddRating;
    EditText edtAddCustName,edtAddCustComment;
    RatingBar CustAddRating;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showflatinfo);
        ImgFlatShow=findViewById(R.id.ImgFlatShow);
        txtFlatAddress=findViewById(R.id.txtFlatAddress);
        txtFlatConNo=findViewById(R.id.txtFlatContactNo);
        txtFlatCity=findViewById(R.id.txtFlatCity);
        txtRent=findViewById(R.id.txtFlatRent);
        txtFlatType=findViewById(R.id.txtFlatType);
        txtFlatFacilities=findViewById(R.id.txtFlatFacilities);

        AddFlatRatingBtn=findViewById(R.id.AddFlatRatingBtn);
        AddFlatRatingBtn.setOnClickListener(this);

        FlatsModel flatsModel = (FlatsModel) getIntent().getSerializableExtra("flatInfo");


        txtFlatType.setText(flatsModel.getFlatName());
        txtFlatAddress.setText(flatsModel.getFlatAddress());
        txtFlatCity.setText(flatsModel.getFlatCity());
        txtFlatConNo.setText(flatsModel.getFlatConNo());
        txtRent.setText(flatsModel.getFlatRent());
        ImgFlatShow.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(flatsModel.getImgFlat()));
        txtFlatFacilities.setText(flatsModel.getFacilities());

    }

    @Override
    public void onClick(View v)
    {
        View view= LayoutInflater.from(ShowFlatInfo.this).inflate(R.layout.addrating,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(ShowFlatInfo.this);
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
                Toast.makeText(ShowFlatInfo.this, "Rating Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


package com.example.hostelhb.Activities;

import android.content.Intent;
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

import com.example.hostelhb.DataModels.CustomerModel;
import com.example.hostelhb.DataModels.OwnerShowInquiries;
import com.example.hostelhb.DataModels.OwnerShowRating;
import com.example.hostelhb.DataModels.SearchHostel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.SharedPreferencesManager;
import com.example.hostelhb.Utility.UtilityMethods;

public class ShowHostelInfo extends AppCompatActivity {
    TextView txtHosName,txtHAddress,txtContactNo,txtHCity,txtHFacilities;
    ImageView ImgHosShow,ImgClickOpenRoomList;
     Button AddRatingBtn,AddRating,BtnInq,BtnAddInqFor;
     EditText edtAddCustName,edtAddCustComment,edtInqFor;
     RatingBar CustAddRating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showhomeinformation);
        txtHosName=findViewById(R.id.txtHosName);
        txtHAddress=findViewById(R.id.txtHAddress);
        txtContactNo=findViewById(R.id.txtHContactNo);
        ImgHosShow=findViewById(R.id.ImgHosShow);
        AddRatingBtn=findViewById(R.id.AddRatingBtn);
        txtHFacilities=findViewById(R.id.txtHFacilities);
        ImgClickOpenRoomList=findViewById(R.id.ImgClickOpenRoomList);
         BtnInq=findViewById(R.id.BtnInq);
        txtHCity=findViewById(R.id.txtHCity);


        final SearchHostel searchHostel= (SearchHostel) getIntent().getSerializableExtra("hosInfo");

        txtHosName.setText(searchHostel.getHosName());
        txtHAddress.setText(searchHostel.getHosAddress());
        txtContactNo.setText(searchHostel.getHosConNo());
        txtHCity.setText(searchHostel.getHosCity());
        txtHFacilities.setText(searchHostel.getFacilities());
        ImgHosShow.setImageBitmap(UtilityMethods.imgConvertFromByteArrayToBitmap(searchHostel.getImgHostel()));

        ImgClickOpenRoomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId()==R.id.ImgClickOpenRoomList) {
                    Intent intent = new Intent(ShowHostelInfo.this, ShowRoomsList.class);
                    intent.putExtra("HosIdRoom",searchHostel.getHosId());
                    startActivity(intent);
                }
            }
        });

        BtnInq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                View view= LayoutInflater.from(ShowHostelInfo.this).inflate(R.layout.inqlayout,null);
                AlertDialog.Builder builder=new AlertDialog.Builder(ShowHostelInfo.this);
                builder.setView(view);
                builder.create().show();

                edtInqFor=view.findViewById(R.id.edtInqFor);
                BtnAddInqFor=view.findViewById(R.id.BtnAddInqFor);
                BtnAddInqFor.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         OwnerShowInquiries ownerShowInquiries=new OwnerShowInquiries();
                         CustomerModel customerModel=HostelDatabaseClient.gethInstance(ShowHostelInfo.this).getAppDatabase().customerDAO().
                                 SelectCustomerByEmail(SharedPreferencesManager.getEmail(ShowHostelInfo.this));
                         //BuyerModel buyerModel = RebuyDatabaseClient.getRebuyInstance(BuyProduct.this)
                         //                            .getRebuyDatabase().buyerDAO()
                         //                            .SelectBuyerById(Shared_Preference_Manager.getBuyer_ID(BuyProduct.this));
                         ownerShowInquiries.setCustomerName(SharedPreferencesManager.getUserName(ShowHostelInfo.this));
                         ownerShowInquiries.setContactNo(SharedPreferencesManager.getUserMobNo(ShowHostelInfo.this));
                         ownerShowInquiries.setInquiryFor(edtInqFor.getText().toString());
                         ownerShowInquiries.setInqDate(UtilityMethods.getDate());
                         ownerShowInquiries.setImgInq(customerModel.getImgCustomer());
                         HostelDatabaseClient.gethInstance(getApplicationContext())
                                 .getAppDatabase()
                                 .ownerInquiriesDAO()
                                 .insertInquiries(ownerShowInquiries);
                         Toast.makeText(ShowHostelInfo.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                     }
                 });

            }
        });
        AddRatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view= LayoutInflater.from(ShowHostelInfo.this).inflate(R.layout.addrating,null);
                AlertDialog.Builder builder=new AlertDialog.Builder(ShowHostelInfo.this);
                builder.setView(view);
                builder.create().show();


                AddRating=view.findViewById(R.id.btnAdd);
                edtAddCustName=view.findViewById(R.id.edtAddCustName);
                edtAddCustComment=view.findViewById(R.id.edtAddCustComment);
                CustAddRating=view.findViewById(R.id.CustAddRating);
                AddRating.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        OwnerShowRating ownerShowRating=new OwnerShowRating();

                        ownerShowRating.setCustRateName(edtAddCustName.getText().toString());
                        ownerShowRating.setRateComment(edtAddCustComment.getText().toString());
                        ownerShowRating.setRateStars(CustAddRating.getRating());

                        HostelDatabaseClient.gethInstance(getApplicationContext())
                                .getAppDatabase()
                                .ownerRatingDAO()
                                .insertSRatings(ownerShowRating);

                        Toast.makeText(ShowHostelInfo.this, "Rating Added Successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialogbox1=builder.create();
                dialogbox1.dismiss();

            }
        });



    }



}

package com.example.hostelhb.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.hostelhb.DataModels.CustomerModel;
import com.example.hostelhb.DataModels.OwnerModel;
import com.example.hostelhb.DataModels.UserModel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.SharedPreferencesManager;
import com.example.hostelhb.Utility.UtilityMethods;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class CustomerProfile extends Fragment  {
    Context context;
    TextView txtCustCamera;
    CircleImageView CustProfImg;
    BitmapFactory.Options options;
    Button btnSave;
    Bitmap bitMapImg;
    public static final int CAMERA_REQUEST=20;
    TextView txtProfCustName,txtProfCustAddress,txtProfCustConNo,txtProfCustEmail;
    private long cId;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custprofilelayout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},20);
        txtProfCustName=view.findViewById(R.id.txtProfCustName);
        txtProfCustAddress=view.findViewById(R.id.txtProfCustAddress);
        txtProfCustConNo=view.findViewById(R.id.txtProfCustConNo);
        txtProfCustEmail=view.findViewById(R.id.txtProfCustEmail);
        txtCustCamera=view.findViewById(R.id.txtCustCamera);
        CustProfImg=view.findViewById(R.id.CustProfImg);
        btnSave=view.findViewById(R.id.btnSave);
        //BtnEditCustProf=view.findViewById(R.id.BtnEditCustProf);

            txtProfCustName.setText(SharedPreferencesManager.getUserName(context));
            txtProfCustAddress.setText(SharedPreferencesManager.getAddress(context));
            txtProfCustConNo.setText(SharedPreferencesManager.getUserMobNo(context));
            txtProfCustEmail.setText(SharedPreferencesManager.getEmail(context));

        UserModel userModel = HostelDatabaseClient.gethInstance(context)
                .getAppDatabase().userDAO()
                .selectUserById(SharedPreferencesManager.getUserId(context));

        txtProfCustName.setText(userModel.getName());
       txtProfCustAddress.setText(userModel.getAddress());
       txtProfCustConNo.setText(userModel.getConNo());
        txtProfCustEmail.setText(userModel.getEmail());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerModel customerModel =new CustomerModel();
                customerModel.setCustomerName(txtProfCustName.getText().toString());
                customerModel.setCustomerAddress(txtProfCustAddress.getText().toString());
                customerModel.setCustomerContNo(txtProfCustConNo.getText().toString());
                customerModel.setCustomerEmail(txtProfCustEmail.getText().toString());
                customerModel.setImgCustomer(UtilityMethods.imgConvertFromBitmapToByteArray(bitMapImg));

                cId=HostelDatabaseClient.gethInstance(getActivity().getApplicationContext())
                        .getAppDatabase().customerDAO().insertCustomerModel(customerModel);
                Toast.makeText(context, "Buyer Added Successfully", Toast.LENGTH_SHORT).show();



            }
        });

            txtCustCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,CAMERA_REQUEST);

                }
            });
            CustProfImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent pickPhotoIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.
                            EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhotoIntent,1);

                }
            });

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode== RESULT_OK && data!=null && requestCode==CAMERA_REQUEST  )
        {
            bitMapImg=(Bitmap) data.getExtras().get("data");
            CustProfImg.setImageBitmap(bitMapImg);
        }
        if (resultCode==RESULT_OK && data!=null && requestCode==1)
        {
            Uri selectedImage=data.getData();
            String[] filepathcolumn={MediaStore.Images.Media.DATA};
            if (selectedImage!=null)
            {
                Cursor cursor= context.getContentResolver().query(selectedImage,filepathcolumn,null,null,null);
                if (cursor!=null)
                {
                    cursor.moveToFirst();

                    int columnIndex=cursor.getColumnIndex(filepathcolumn[0]);
                    String picturePath=cursor.getString(columnIndex);
                    options=new BitmapFactory.Options();
                    options.inSampleSize=2;
                    bitMapImg=BitmapFactory.decodeFile(picturePath,options);
                    CustProfImg.setImageBitmap(bitMapImg);
                    cursor.close();
                }
            }
        }

    }


}

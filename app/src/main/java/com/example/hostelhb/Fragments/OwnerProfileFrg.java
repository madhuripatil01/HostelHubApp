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

import com.example.hostelhb.DataModels.OwnerModel;
import com.example.hostelhb.DataModels.UserModel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.SharedPreferencesManager;
import com.example.hostelhb.Utility.UtilityMethods;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class OwnerProfileFrg extends Fragment implements View.OnClickListener
{
    CircleImageView profile_image;
    public static final int CAMERA_REQUEST=20;
    Context context;
    TextView txtCamera;
    Button btnsave;
    BitmapFactory.Options options;
    Bitmap imgBit;
    TextView txtProfName,txtProfAddress,txtProfConNo,txtProfEmail;
    private long bId;
    @Override
    public void onAttach(Context context) {

        this.context=context;
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.myprofilelayout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},20);
        profile_image=view.findViewById(R.id.profile_image);
        txtCamera=view.findViewById(R.id.txtCamera);
        txtProfName=view.findViewById(R.id.txtProfName);
        txtProfAddress=view.findViewById(R.id.txtProfAddress);
        txtProfConNo=view.findViewById(R.id.txtProfConNo);
        txtProfEmail=view.findViewById(R.id.txtProfEmail);


            txtProfName.setText(SharedPreferencesManager.getUserName(context));
            txtProfAddress.setText(SharedPreferencesManager.getAddress(context));
            txtProfConNo.setText(SharedPreferencesManager.getUserMobNo(context));
            txtProfEmail.setText(SharedPreferencesManager.getEmail(context));


        UserModel userModel = HostelDatabaseClient.gethInstance(context)
                .getAppDatabase().userDAO()
                .selectUserById(SharedPreferencesManager.getUserId(context));

        txtProfName.setText(userModel.getName());
        txtProfAddress.setText(userModel.getAddress());
       txtProfConNo.setText(userModel.getConNo());
       txtProfEmail.setText(userModel.getEmail());

        btnsave=view.findViewById(R.id.btnSave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //AlertDialog.Builder builder=new AlertDialog.Builder(context);
               OwnerModel ownerModel =new OwnerModel();
                ownerModel.setOwnerName(txtProfName.getText().toString());
                ownerModel.setOwnerAddress(txtProfAddress.getText().toString());
                ownerModel.setOwnerEmail(txtProfConNo.getText().toString());
                ownerModel.setOwnerEmail(txtProfEmail.getText().toString());
                ownerModel.setImgOwner(UtilityMethods.imgConvertFromBitmapToByteArray(imgBit));

                bId=HostelDatabaseClient.gethInstance(getActivity().getApplicationContext())
                       .getAppDatabase().ownerDAO().insertOwnerModel(ownerModel);
                Toast.makeText(context, "Buyer Added Successfully", Toast.LENGTH_SHORT).show();



            }
        });
        txtCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST);
            }
        });
        profile_image.setOnClickListener(this);

    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

    @Override
    public void onClick(View v)
    {
       Intent pickPhotoIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.
               EXTERNAL_CONTENT_URI);
       startActivityForResult(pickPhotoIntent,1);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode== RESULT_OK && data!=null && requestCode==CAMERA_REQUEST  )
        {
            imgBit=(Bitmap) data.getExtras().get("data");
            profile_image.setImageBitmap(imgBit);
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
                    imgBit=BitmapFactory.decodeFile(picturePath,options);
                    profile_image.setImageBitmap(imgBit);
                    cursor.close();
                }
            }
        }
    }
}

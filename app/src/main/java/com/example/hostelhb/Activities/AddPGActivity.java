package com.example.hostelhb.Activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.hostelhb.DataModels.PGModel;
import com.example.hostelhb.DataModels.SearchMes;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.UtilityMethods;

public class AddPGActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ImgPGAdd;
    TextView AddPGCamera,txtPGCapacity;
    EditText edtPGAddress,edtPGCity,edtPGContactNo,edtPGFacilities,edtPGRent;
    Button btnAddPG;
    BitmapFactory.Options options;
    Bitmap bitmapMes;
    String[] listItem;
    public static final int CAMERA_REQUEST=20;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpglayout);
        ActivityCompat.requestPermissions(AddPGActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},20);
        ImgPGAdd=findViewById(R.id.ImgPGAdd);
        AddPGCamera=findViewById(R.id.AddPGCamera);
        txtPGCapacity=findViewById(R.id.txtPGCapacity);
        edtPGAddress=findViewById(R.id.edtPGAddress);
        edtPGCity=findViewById(R.id.edtPGCity);
        edtPGContactNo=findViewById(R.id.edtPGContactNo);
        edtPGFacilities=findViewById(R.id.edtPGFacilities);
        edtPGRent=findViewById(R.id.edtPGRent);
        btnAddPG=findViewById(R.id.btnAddPG);
        btnAddPG.setOnClickListener(this);
        ImgPGAdd.setOnClickListener(this);
        AddPGCamera.setOnClickListener(this);
        listItem=getResources().getStringArray(R.array.item_Cap);
        txtPGCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(AddPGActivity.this);
                builder.setTitle("Choose PG Capacity");
                builder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        txtPGCapacity.setText(listItem[i]);
                        dialog.dismiss();
                    }
                });
                AlertDialog dialogbox1=builder.create();
                dialogbox1.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnAddPG)
        {
            PGModel pgModel= new PGModel();
            pgModel.setPGName(txtPGCapacity.getText().toString());
            pgModel.setPGAddress(edtPGAddress.getText().toString());
            pgModel.setPGConNo(edtPGContactNo.getText().toString());
            pgModel.setPGCity(edtPGCity.getText().toString());
            pgModel.setFacilities(edtPGFacilities.getText().toString());
            pgModel.setPGRent(edtPGRent.getText().toString());
            pgModel.setImgPG(UtilityMethods.imgConvertFromBitmapToByteArray(bitmapMes));

            HostelDatabaseClient.gethInstance(getApplication().getApplicationContext())
                    .getAppDatabase().pgdao().insertPGs(pgModel);

            Toast.makeText(AddPGActivity.this, "PG Added Successfully", Toast.LENGTH_SHORT).show();

        }
        if (v.getId()==R.id.ImgPGAdd)
        {
            Intent PickPic=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(PickPic,1);

        }
        if (v.getId()==R.id.AddMesCamera)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_REQUEST && resultCode== RESULT_OK && data!=null)
        {
            bitmapMes=(Bitmap) data.getExtras().get("data");
            ImgPGAdd.setImageBitmap(bitmapMes);
        }

        if (resultCode==RESULT_OK && data!=null && requestCode==1)
        {
            Uri SelectedImage = data.getData();
            String[] FilePathColumn={MediaStore.Images.Media.DATA};
            if (SelectedImage!=null)
            {
                Cursor cursor= getApplication().getContentResolver().query(SelectedImage,FilePathColumn,null,null,null);
                if(cursor!=null)
                {
                    cursor.moveToFirst();

                    int ColumnIndex=cursor.getColumnIndex(FilePathColumn[0]);
                    String PicturePath=cursor.getString(ColumnIndex);
                    options=new BitmapFactory.Options();
                    options.inSampleSize=2;
                    bitmapMes=BitmapFactory.decodeFile(PicturePath,options);
                    ImgPGAdd.setImageBitmap(bitmapMes);
                    cursor.close();
                }
            }
        }
    }
}

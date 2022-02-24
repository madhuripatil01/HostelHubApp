package com.example.hostelhb.Activities;

import android.Manifest;
import android.content.Context;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.hostelhb.DataModels.SearchMes;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.UtilityMethods;

public class AddMessActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ImgMesAdd;
    EditText edtMName,edtMAddress,edtMCity,edtMConNo,edtMRent;
    Button btnAddMes;
    Context context;
    TextView AddMesCamera;
    BitmapFactory.Options options;
    Bitmap bitmapMes;
    public static final int CAMERA_REQUEST=20;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmeslayout);
        ActivityCompat.requestPermissions(AddMessActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},20);
        ImgMesAdd=findViewById(R.id.ImgMesAdd);
        edtMName=findViewById(R.id.edtMName);
        edtMAddress=findViewById(R.id.edtMAddress);
        edtMCity=findViewById(R.id.edtMCity);
        edtMConNo=findViewById(R.id.edtMContactNo);
        edtMRent=findViewById(R.id.edtMRent);
        btnAddMes=findViewById(R.id.btnAddMes);
        AddMesCamera=findViewById(R.id.AddMesCamera);
        btnAddMes.setOnClickListener(this);
        ImgMesAdd.setOnClickListener(this);
        AddMesCamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnAddMes)
        {
            SearchMes searchMes=new SearchMes();
            searchMes.setMesName(edtMName.getText().toString());
            searchMes.setMesAddress(edtMAddress.getText().toString());
            searchMes.setMesConNo(edtMConNo.getText().toString());
            searchMes.setMesCity(edtMCity.getText().toString());
            searchMes.setMesMonRent(edtMRent.getText().toString());
            searchMes.setImgMes(UtilityMethods.imgConvertFromBitmapToByteArray(bitmapMes));

            HostelDatabaseClient.gethInstance(getApplication().getApplicationContext())
                    .getAppDatabase().searchMesDAO().insertSMes(searchMes);

            Toast.makeText(AddMessActivity.this, "Mess Added Successfully", Toast.LENGTH_SHORT).show();

        }
        if (v.getId()==R.id.ImgMesAdd)
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
            ImgMesAdd.setImageBitmap(bitmapMes);
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
                    ImgMesAdd.setImageBitmap(bitmapMes);
                    cursor.close();
                }
            }
        }
    }
}

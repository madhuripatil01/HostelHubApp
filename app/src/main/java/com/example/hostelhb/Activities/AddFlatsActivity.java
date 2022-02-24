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

import com.example.hostelhb.DataModels.FlatsModel;
import com.example.hostelhb.DataModels.PGModel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.UtilityMethods;

public class AddFlatsActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ImgFlatAdd;
    TextView AddFlatCamera,txtFlatType;
    EditText edtflatAddress,edtflatCity,edtflatContactNo,edtflatFacilities,edtflatRent;
    Button btnAddFlat;
    TextView AddMesCamera;
    BitmapFactory.Options options;
    Bitmap bitmapMes;
    String[] listItem;
    public static final int CAMERA_REQUEST=20;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addflatlayout);
        ActivityCompat.requestPermissions(AddFlatsActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},20);
        ImgFlatAdd=findViewById(R.id.ImgFlatAdd);
        AddFlatCamera=findViewById(R.id.AddFlatCamera);
        txtFlatType=findViewById(R.id.txtFlatType);
        edtflatAddress=findViewById(R.id.edtflatAddress);
        edtflatCity=findViewById(R.id.edtflatCity);
        edtflatContactNo=findViewById(R.id.edtflatContactNo);
        edtflatFacilities=findViewById(R.id.edtflatFacilities);
        edtflatRent=findViewById(R.id.edtflatRent);
        btnAddFlat=findViewById(R.id.btnAddflat);
        btnAddFlat.setOnClickListener(this);
        ImgFlatAdd.setOnClickListener(this);
        AddFlatCamera.setOnClickListener(this);
        listItem=getResources().getStringArray(R.array.item_Type);
        txtFlatType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(AddFlatsActivity.this);
                builder.setTitle("Choose Flat Type");
                builder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        txtFlatType.setText(listItem[i]);
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
        if (v.getId()==R.id.btnAddflat)
        {
            FlatsModel flatsModel=new FlatsModel();
            flatsModel.setFlatName(txtFlatType.getText().toString());
            flatsModel.setFlatAddress(edtflatAddress.getText().toString());
            flatsModel.setFlatConNo(edtflatContactNo.getText().toString());
            flatsModel.setFlatCity(edtflatCity.getText().toString());
            flatsModel.setFacilities(edtflatFacilities.getText().toString());
            flatsModel.setFlatRent(edtflatRent.getText().toString());
            flatsModel.setImgFlat(UtilityMethods.imgConvertFromBitmapToByteArray(bitmapMes));

            HostelDatabaseClient.gethInstance(getApplication().getApplicationContext())
                    .getAppDatabase().flatsDAO().insertFlat(flatsModel);

            Toast.makeText(AddFlatsActivity.this, "Flat Added Successfully", Toast.LENGTH_SHORT).show();

        }
        if (v.getId()==R.id.ImgFlatAdd)
        {
            Intent PickPic=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(PickPic,1);

        }
        if (v.getId()==R.id.AddFlatCamera)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && data != null) {
            bitmapMes = (Bitmap) data.getExtras().get("data");
            ImgFlatAdd.setImageBitmap(bitmapMes);
        }

        if (resultCode == RESULT_OK && data != null && requestCode == 1) {
            Uri SelectedImage = data.getData();
            String[] FilePathColumn = {MediaStore.Images.Media.DATA};
            if (SelectedImage != null) {
                Cursor cursor = getApplication().getContentResolver().query(SelectedImage, FilePathColumn, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();

                    int ColumnIndex = cursor.getColumnIndex(FilePathColumn[0]);
                    String PicturePath = cursor.getString(ColumnIndex);
                    options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    bitmapMes = BitmapFactory.decodeFile(PicturePath, options);
                    ImgFlatAdd.setImageBitmap(bitmapMes);
                    cursor.close();
                }
            }
        }
    }
}

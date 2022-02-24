package com.example.hostelhb.Activities;

import android.Manifest;
import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.hostelhb.DataModels.RoomsModel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.UtilityMethods;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddRoomsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtRoomCapacity;
    String[] listItem;
    CircleImageView CircuProfImg;
    TextView RoomAddCamera;
    BitmapFactory.Options options;
    Bitmap bitmap;
    Button btnAddRoom;
    EditText edtRoomHName,edtRoomNo,edtRoomRent;

    public static final int CAMERA_REQUEST=20;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addroomslayout);
        ActivityCompat.requestPermissions(AddRoomsActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},20);
        txtRoomCapacity=findViewById(R.id.txtRoomCapacity);
        CircuProfImg=findViewById(R.id.CircuProfImg);
        RoomAddCamera=findViewById(R.id.RoomAddCamera);
        btnAddRoom=findViewById(R.id.btnAddRoom);
        edtRoomHName=findViewById(R.id.edtRoomHName);
        edtRoomNo=findViewById(R.id.edtRoomNo);
        edtRoomRent=findViewById(R.id.edtRoomRent);

         txtRoomCapacity.setOnClickListener(this);
         CircuProfImg.setOnClickListener(this);

         RoomAddCamera.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (v.getId()==R.id.RoomAddCamera) {
                     Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                     startActivityForResult(intent, CAMERA_REQUEST);
                 }
             }
         });
         CircuProfImg.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(v.getId()==R.id.CircuProfImg)
                 {
                    Intent PickPic=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(PickPic,1);
                 }
             }
         });

        listItem=getResources().getStringArray(R.array.item_Capacity);
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoomsModel roomsModel=new RoomsModel();

                roomsModel.setRoomHName(edtRoomHName.getText().toString());
                roomsModel.setRoomNo(edtRoomNo.getText().toString());
                roomsModel.setMonRent(edtRoomRent.getText().toString());
                roomsModel.setRoomCapacity(txtRoomCapacity.getText().toString());
                roomsModel.setHosIdRoom(getIntent().getLongExtra("hId",0));
                roomsModel.setImgRoom(UtilityMethods.imgConvertFromBitmapToByteArray(bitmap));

                HostelDatabaseClient.gethInstance(getApplication().getApplicationContext())
                        .getAppDatabase().roomDAO().insertRooms(roomsModel);

                Toast.makeText(AddRoomsActivity.this, "Rooms Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder=new AlertDialog.Builder(AddRoomsActivity.this);
        builder.setTitle("Choose Room Capacity");
        builder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                txtRoomCapacity.setText(listItem[i]);
                dialog.dismiss();
            }
        });
        AlertDialog dialogbox1=builder.create();
        dialogbox1.show();


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (requestCode==CAMERA_REQUEST && resultCode== Activity.RESULT_OK && data!=null)
        {
            bitmap=(Bitmap) data.getExtras().get("data");
            CircuProfImg.setImageBitmap(bitmap);
        }
        if (resultCode==RESULT_OK && data!=null && requestCode==1)
        {
            Uri SelectedImage = data.getData();
            String[] FilePathColumn={MediaStore.Images.Media.DATA};
            if (SelectedImage!=null)
            {
                Cursor cursor=getContentResolver().query(SelectedImage,FilePathColumn,null,null,null);
                if(cursor!=null)
                {
                    cursor.moveToFirst();

                    int ColumnIndex=cursor.getColumnIndex(FilePathColumn[0]);
                    String PicturePath=cursor.getString(ColumnIndex);
                    options=new BitmapFactory.Options();
                    options.inSampleSize=2;
                    bitmap=BitmapFactory.decodeFile(PicturePath,options);
                    CircuProfImg.setImageBitmap(bitmap);
                    cursor.close();
                }
            }
        }
    }


}

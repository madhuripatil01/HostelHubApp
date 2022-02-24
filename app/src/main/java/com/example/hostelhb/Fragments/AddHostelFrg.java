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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.hostelhb.Activities.AddRoomsActivity;
import com.example.hostelhb.DataModels.SearchHostel;
import com.example.hostelhb.R;
import com.example.hostelhb.Utility.HostelDatabaseClient;
import com.example.hostelhb.Utility.UtilityMethods;

import static android.app.Activity.RESULT_OK;

public class AddHostelFrg extends Fragment implements View.OnClickListener
{
    ImageView ImgHosAdd;
    TextView txtAddRoomClick,AddHosCamera;
    Context context;
    EditText edtHName,edtHAddress,edtHCity,edtHContactNo,edtHosFacilities;
    Button btnAddHostel;
    BitmapFactory.Options options;
    Bitmap bitmapHos;
    public static final int CAMERA_REQUEST=20;
    private long hId;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.addhostellayout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},20);
        txtAddRoomClick=view.findViewById(R.id.txtAddRoomClick);
        edtHName=view.findViewById(R.id.edtHName);
        edtHAddress=view.findViewById(R.id.edtHAddress);
        edtHCity=view.findViewById(R.id.edtHCity);
        edtHContactNo=view.findViewById(R.id.edtHContactNo);
        btnAddHostel=view.findViewById(R.id.btnAddHostel);
        AddHosCamera=view.findViewById(R.id.AddHosCamera);
        ImgHosAdd=view.findViewById(R.id.ImgHosAdd);
        edtHosFacilities=view.findViewById(R.id.edtHosFacilities);
        txtAddRoomClick.setOnClickListener(this);
        btnAddHostel.setOnClickListener(this);
        ImgHosAdd.setOnClickListener(this);
        AddHosCamera.setOnClickListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.txtAddRoomClick) {
            Intent intent = new Intent(context, AddRoomsActivity.class);
            intent.putExtra("hId",hId);
            startActivity(intent);
        }

        if(v.getId()==R.id.btnAddHostel)
        {
            SearchHostel searchHostel=new SearchHostel();

            searchHostel.setHosName(edtHName.getText().toString());
            searchHostel.setHosAddress(edtHAddress.getText().toString());
            searchHostel.setHosCity(edtHCity.getText().toString());
            searchHostel.setHosConNo(edtHContactNo.getText().toString());
            searchHostel.setFacilities(edtHosFacilities.getText().toString());
            searchHostel.setImgHostel(UtilityMethods.imgConvertFromBitmapToByteArray(bitmapHos));

            hId=HostelDatabaseClient.gethInstance(getActivity().getApplicationContext())
                    .getAppDatabase()
                    .searchHostelDAO()
                    .insertSHostel(searchHostel);

            Toast.makeText(context, "Hostel Added Successfully", Toast.LENGTH_SHORT).show();
        }
        if (v.getId()==R.id.ImgHosAdd)
        {
            Intent PickPic=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(PickPic,1);

        }
        if (v.getId()==R.id.AddHosCamera)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_REQUEST && resultCode== RESULT_OK && data!=null)
        {
            bitmapHos=(Bitmap) data.getExtras().get("data");
            ImgHosAdd.setImageBitmap(bitmapHos);
        }

        if (resultCode==RESULT_OK && data!=null && requestCode==1)
        {
            Uri SelectedImage = data.getData();
            String[] FilePathColumn={MediaStore.Images.Media.DATA};
            if (SelectedImage!=null)
            {
                Cursor cursor= context.getContentResolver().query(SelectedImage,FilePathColumn,null,null,null);
                if(cursor!=null)
                {
                    cursor.moveToFirst();
                    int ColumnIndex=cursor.getColumnIndex(FilePathColumn[0]);
                    String PicturePath=cursor.getString(ColumnIndex);
                    options=new BitmapFactory.Options();
                    options.inSampleSize=2;
                    bitmapHos=BitmapFactory.decodeFile(PicturePath,options);
                    ImgHosAdd.setImageBitmap(bitmapHos);
                    cursor.close();
                }
            }
        }
    }
}

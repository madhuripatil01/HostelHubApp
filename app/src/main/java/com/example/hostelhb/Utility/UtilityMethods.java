package com.example.hostelhb.Utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilityMethods
{
    public static byte[] imgConvertFromBitmapToByteArray(Bitmap bitmap)
    {
        ByteArrayOutputStream blob=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,blob);
        return blob.toByteArray();
    }
    public static Bitmap imgConvertFromByteArrayToBitmap(byte[] image)
    {
        return BitmapFactory.decodeByteArray(image,0,image.length);
    }
    public static String getDate()
    {
      DateFormat df=new SimpleDateFormat("dd-mm-yyy");
        Date date=new Date();
        return df.format(date);

    }
}

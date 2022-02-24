package com.example.hostelhb.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class CustMyFavorate implements Serializable
{
    @PrimaryKey(autoGenerate =true)
    private int CustFavId;

    @ColumnInfo(name ="txtFavName")
    private String txtFavName;

    @ColumnInfo(name ="ImgFav")
    private byte[] ImgFav;

    @ColumnInfo(name = "FavAddress")
    private String FavAddress;

    @ColumnInfo(name = "FavContactNo")
    private String FavContactNo;
    @Ignore
    public CustMyFavorate(String txtFavName,byte[] ImgFav, String FavAddress, String FavContactNo) {

        this.txtFavName = txtFavName;
        this.ImgFav = ImgFav;
       this.FavAddress = FavAddress;
        this.FavContactNo = FavContactNo;
        this.CustFavId=CustFavId;
    }

    public CustMyFavorate() {
    }

    public int getCustFavId() {
        return CustFavId;
    }

    public void setCustFavId(int custFavId) {
        CustFavId = custFavId;
    }

    public String getTxtFavName()
    {
        return txtFavName;
    }

    public byte[] getImgFav() {
        return ImgFav;
    }

    public String getFavAddress() {
        return FavAddress;
    }

    public String getFavContactNo() {
        return FavContactNo;
    }

    public void setTxtFavName(String txtFavName) {
        this.txtFavName = txtFavName;
    }



    public void setImgFav(byte[] imgFav) {
        ImgFav = imgFav;
    }

    public void setFavAddress(String favAddress) {
        FavAddress = favAddress;
    }

    public void setFavContactNo(String favContactNo) {
        FavContactNo = favContactNo;
    }
}

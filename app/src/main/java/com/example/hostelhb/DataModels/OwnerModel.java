package com.example.hostelhb.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class OwnerModel implements Serializable
{
    @PrimaryKey(autoGenerate =true)
    private int OwnerId;

    @ColumnInfo(name ="txtOwnerName")
    private String OwnerName;

    @ColumnInfo(name ="ImgOwner")
    private byte[] ImgOwner;

    @ColumnInfo(name = "txtOwnerAddress")
    private String OwnerAddress;

    @ColumnInfo(name = "txtOwnerContactNo")
    private String OwnerContactNo;

    @ColumnInfo(name = "txtOwnerEmail")
    private String OwnerEmail;

    public OwnerModel()
    {

    }

    public int getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(int ownerId) {
        OwnerId = ownerId;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public byte[] getImgOwner() {
        return ImgOwner;
    }

    public void setImgOwner(byte[] imgOwner) {
        ImgOwner = imgOwner;
    }

    public String getOwnerAddress() {
        return OwnerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        OwnerAddress = ownerAddress;
    }

    public String getOwnerContactNo() {
        return OwnerContactNo;
    }

    public void setOwnerContactNo(String ownerContactNo) {
        OwnerContactNo = ownerContactNo;
    }

    public String getOwnerEmail() {
        return OwnerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        OwnerEmail = ownerEmail;
    }
}

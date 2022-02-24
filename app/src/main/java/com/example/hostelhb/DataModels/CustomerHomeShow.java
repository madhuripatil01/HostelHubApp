package com.example.hostelhb.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class CustomerHomeShow implements Serializable
{
    @PrimaryKey(autoGenerate =true)
    private int CustHomeId;

    @ColumnInfo(name = "HOrMName")
    private String HOrMName;

    @ColumnInfo(name = "Address")
    private String Address;

    @ColumnInfo(name = "ContactNo")
    private String ContactNo;

    @ColumnInfo(name = "HosImage")
    private int HosImage;

    @ColumnInfo(name = "HomeCustrating")
   private float HomeCustrating;

    @Ignore
    public CustomerHomeShow(String HOrMName, String Address, String ContactNo, int HosImage,float homeCustrating)
    {
        this.HOrMName = HOrMName;
        this.Address = Address;
        this.ContactNo = ContactNo;
        this.HosImage = HosImage;
        this.HomeCustrating=homeCustrating;
    }

    public CustomerHomeShow() {
    }

    public int getCustHomeId() {
        return CustHomeId;
    }

    public void setCustHomeId(int custHomeId) {
        CustHomeId = custHomeId;
    }

    public String getHOrMName()
    {
        return HOrMName;
    }

    public String getAddress()
    {
        return Address;
    }

    public String getContactNo()
    {
        return ContactNo;
    }

    public int getHosImage()
    {
        return HosImage;
    }

    public float getHomeCustrating() {
        return HomeCustrating;
    }


    public void setHOrMName(String HOrMName) {
        this.HOrMName = HOrMName;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public void setHosImage(int hosImage) {
        HosImage = hosImage;
    }

    public void setHomeCustrating(float homeCustrating) {
        HomeCustrating = homeCustrating;
    }
}

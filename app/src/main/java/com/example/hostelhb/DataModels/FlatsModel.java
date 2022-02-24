package com.example.hostelhb.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class FlatsModel implements Serializable
{
    @PrimaryKey(autoGenerate =true)
    private int FlatId;

    @ColumnInfo(name = "ImgFlat")
    private byte[] ImgFlat;

    @ColumnInfo(name = "Name")
    private String FlatName;

    @ColumnInfo(name = "FlatAddress")
    private String FlatAddress;

    @ColumnInfo(name ="FlatCity")
    private String FlatCity;

    @ColumnInfo(name = "FlatConNo")
    private String FlatConNo;

    @ColumnInfo(name = "HomeFlatrating")
    private float HomeHostelrating;

    @ColumnInfo(name = "Facilities")
    private String Facilities;

    @ColumnInfo(name = "Rent")
    private String FlatRent;

    public String getFlatRent() {
        return FlatRent;
    }

    public void setFlatRent(String flatRent) {
        FlatRent = flatRent;
    }

    public int getFlatId() {
        return FlatId;
    }

    public void setFlatId(int flatId) {
        FlatId = flatId;
    }

    public byte[] getImgFlat() {
        return ImgFlat;
    }

    public void setImgFlat(byte[] imgFlat) {
        ImgFlat = imgFlat;
    }

    public String getFlatName() {
        return FlatName;
    }

    public void setFlatName(String flatName) {
        FlatName = flatName;
    }

    public String getFlatAddress() {
        return FlatAddress;
    }

    public void setFlatAddress(String flatAddress) {
        FlatAddress = flatAddress;
    }

    public String getFlatCity() {
        return FlatCity;
    }

    public void setFlatCity(String flatCity) {
        FlatCity = flatCity;
    }

    public String getFlatConNo() {
        return FlatConNo;
    }

    public void setFlatConNo(String flatConNo) {
        FlatConNo = flatConNo;
    }

    public float getHomeHostelrating() {
        return HomeHostelrating;
    }

    public void setHomeHostelrating(float homeHostelrating) {
        HomeHostelrating = homeHostelrating;
    }

    public String getFacilities() {
        return Facilities;
    }

    public void setFacilities(String facilities) {
        Facilities = facilities;
    }
}

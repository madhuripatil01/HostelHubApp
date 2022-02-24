package com.example.hostelhb.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class SearchHostel implements Serializable
{
    @PrimaryKey(autoGenerate =true)
    private int HosId;

    @ColumnInfo(name = "ImgHostel")
    private byte[] ImgHostel;

    @ColumnInfo(name = "Name")
    private String HosName;

    @ColumnInfo(name = "HosAddress")
    private String HosAddress;

    @ColumnInfo(name ="HosCity")
    private String HosCity;

    @ColumnInfo(name = "HosConNo")
    private String HosConNo;

    @ColumnInfo(name = "HomeHostelrating")
    private float HomeHostelrating;

    @ColumnInfo(name = "Facilities")
    private String Facilities;

    @Ignore
    public SearchHostel(int hosId, byte[] imgHostel, String hosName, String hosAddress, String hosCity, String hosConNo,long homeCustrating, String facilities) {
        HosId = hosId;
        ImgHostel = imgHostel;
        HosName = hosName;
        HosAddress = hosAddress;
        HosCity = hosCity;
        HosConNo = hosConNo;
        HomeHostelrating=homeCustrating;
        Facilities = facilities;

    }

    public SearchHostel() {
    }

    public String getFacilities() {
        return Facilities;
    }

    public int getHosId() {
        return HosId;
    }

    public byte[] getImgHostel() {
        return ImgHostel;
    }

    public String getHosName() {
        return HosName;
    }

    public String getHosAddress() {
        return HosAddress;
    }

    public String getHosCity() {
        return HosCity;
    }

    public String getHosConNo() {
        return HosConNo;
    }

    public float getHomeHostelrating() {
        return HomeHostelrating;
    }

    public void setFacilities(String facilities) {
        Facilities = facilities;
    }

    public void setHosId(int hosId) {
        HosId = hosId;
    }

    public void setImgHostel(byte[] imgHostel) {
        ImgHostel = imgHostel;
    }

    public void setHosName(String hosName) {
        HosName = hosName;
    }

    public void setHosAddress(String hosAddress) {
        HosAddress = hosAddress;
    }

    public void setHosCity(String hosCity) {
        HosCity = hosCity;
    }

    public void setHosConNo(String hosConNo) {
        HosConNo = hosConNo;
    }

    public void setHomeHostelrating(float homeHostelrating) {
        HomeHostelrating = homeHostelrating;
    }
}

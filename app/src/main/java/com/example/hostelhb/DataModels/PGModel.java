package com.example.hostelhb.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class PGModel implements Serializable
{
    @PrimaryKey(autoGenerate =true)
    private int PGId;

    @ColumnInfo(name = "ImgPG")
    private byte[] ImgPG;

    @ColumnInfo(name = "Name")
    private String PGName;

    @ColumnInfo(name = "PGAddress")
    private String PGAddress;

    @ColumnInfo(name ="PGCity")
    private String PGCity;

    @ColumnInfo(name = "PGConNo")
    private String PGConNo;

    @ColumnInfo(name = "HomePGrating")
    private float HomePGrating;

    @ColumnInfo(name = "Facilities")
    private String Facilities;

    @ColumnInfo(name = "Rent")
    private String PGRent;

    public String getPGRent() {
        return PGRent;
    }

    public void setPGRent(String PGRent) {
        this.PGRent = PGRent;
    }

    public int getPGId() {
        return PGId;
    }

    public void setPGId(int PGId) {
        this.PGId = PGId;
    }

    public byte[] getImgPG() {
        return ImgPG;
    }

    public void setImgPG(byte[] imgPG) {
        ImgPG = imgPG;
    }

    public String getPGName() {
        return PGName;
    }

    public void setPGName(String PGName) {
        this.PGName = PGName;
    }

    public String getPGAddress() {
        return PGAddress;
    }

    public void setPGAddress(String PGAddress) {
        this.PGAddress = PGAddress;
    }

    public String getPGCity() {
        return PGCity;
    }

    public void setPGCity(String PGCity) {
        this.PGCity = PGCity;
    }

    public String getPGConNo() {
        return PGConNo;
    }

    public void setPGConNo(String PGConNo) {
        this.PGConNo = PGConNo;
    }

    public float getHomePGrating() {
        return HomePGrating;
    }

    public void setHomePGrating(float homePGrating) {
        HomePGrating = homePGrating;
    }

    public String getFacilities() {
        return Facilities;
    }

    public void setFacilities(String facilities) {
        Facilities = facilities;
    }
}

package com.example.hostelhb.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class SearchMes implements Serializable
{
    @PrimaryKey(autoGenerate =true)
    private int SMesId;

    @ColumnInfo(name = "ImgMes")
    private byte[] ImgMes;

    @ColumnInfo(name = "MesName")
    private String MesName;

    @ColumnInfo(name = "MesAddress")
    private String MesAddress;

    @ColumnInfo(name = "MesCity")
    private String MesCity;

    @ColumnInfo(name = "MesConNo")
    private String MesConNo;

    @ColumnInfo(name = "MesMonRent")
    private  String MesMonRent;
  @Ignore
    public SearchMes(byte[] imgMes, String mesName, String mesAddress, String mesCity, String mesConNo, String mesMonRent) {
        ImgMes = imgMes;
        MesName = mesName;
        MesAddress = mesAddress;
        MesCity = mesCity;
        MesConNo = mesConNo;
        MesMonRent = mesMonRent;
    }

    public SearchMes() {
    }

    public int getSMesId() {
        return SMesId;
    }


    public String getMesName() {
        return MesName;
    }

    public String getMesAddress() {
        return MesAddress;
    }

    public String getMesCity() {
        return MesCity;
    }

    public String getMesConNo() {
        return MesConNo;
    }

    public String getMesMonRent() {
        return MesMonRent;
    }

    public void setSMesId(int SMesId) {
        this.SMesId = SMesId;
    }

    public byte[] getImgMes() {
        return ImgMes;
    }

    public void setImgMes(byte[] imgMes) {
        ImgMes = imgMes;
    }

    public void setMesName(String mesName) {
        MesName = mesName;
    }

    public void setMesAddress(String mesAddress) {
        MesAddress = mesAddress;
    }

    public void setMesCity(String mesCity) {
        MesCity = mesCity;
    }

    public void setMesConNo(String mesConNo) {
        MesConNo = mesConNo;
    }

    public void setMesMonRent(String mesMonRent) {
        MesMonRent = mesMonRent;
    }
}

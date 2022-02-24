package com.example.hostelhb.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class OwnerShowRating implements Serializable
{
    @PrimaryKey(autoGenerate =true)
    private int oratingId;

    @ColumnInfo(name = "CustRateName")
    private String CustRateName;

    @ColumnInfo(name = "RateComment")
    private String RateComment;

    @ColumnInfo(name = "RateStars")
    private Float RateStars;

    @ColumnInfo(name = "ImgRate")
    private byte[] ImgRate;

    @Ignore
    public OwnerShowRating(int oratingId, String custRateName, String rateComment, Float rateStars, byte[] imgRate) {
        this.oratingId = oratingId;
        CustRateName = custRateName;
        RateComment = rateComment;
        RateStars = rateStars;
        ImgRate = imgRate;
    }




    public OwnerShowRating() {
    }

    public int getOratingId() {
        return oratingId;
    }

    public String getCustRateName() {
        return CustRateName;
    }

    public String getRateComment() {
        return RateComment;
    }

    public Float getRateStars() {
        return RateStars;
    }

    public byte[] getImgRate() {
        return ImgRate;
    }

    public void setOratingId(int oratingId) {
        this.oratingId = oratingId;
    }

    public void setCustRateName(String custRateName) {
        CustRateName = custRateName;
    }

    public void setRateComment(String rateComment) {
        RateComment = rateComment;
    }

    public void setRateStars(Float rateStars) {
        RateStars = rateStars;
    }

    public void setImgRate(byte[] imgRate) {
        ImgRate = imgRate;
    }
}

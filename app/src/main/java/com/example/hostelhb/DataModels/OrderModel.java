package com.example.hostelhb.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class OrderModel implements Serializable
{
    @PrimaryKey(autoGenerate =true)
    private int OrderId;

   @ColumnInfo(name="txtOrdProdName")
    private int OrdProName;

    @ColumnInfo(name="txtOrdBuyerMobNo")
    private String OrdBuyerMobNo;

    @ColumnInfo(name = "txtOrdBAddress")
    private String OrdBAddress;

    @ColumnInfo(name = "txtOrdPincode")
    private String OrdBPincode;

    public OrderModel()
    {

    }

    @Ignore
    public OrderModel(int ordProName, String ordBuyerMobNo, String ordBAddress, String ordBPincode) {
        OrdProName = ordProName;
        OrdBuyerMobNo = ordBuyerMobNo;
        OrdBAddress = ordBAddress;
        OrdBPincode = ordBPincode;
    }





    public int getOrdProName() {
        return OrdProName;
    }

    public void setOrdProName(int ordProName) {
        OrdProName = ordProName;
    }

    public String getOrdBuyerMobNo() {
        return OrdBuyerMobNo;
    }

    public void setOrdBuyerMobNo(String ordBuyerMobNo) {
        OrdBuyerMobNo = ordBuyerMobNo;
    }

    public String getOrdBAddress() {
        return OrdBAddress;
    }

    public void setOrdBAddress(String ordBAddress) {
        OrdBAddress = ordBAddress;
    }

    public String getOrdBPincode() {
        return OrdBPincode;
    }

    public void setOrdBPincode(String ordBPincode) {
        OrdBPincode = ordBPincode;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }
}

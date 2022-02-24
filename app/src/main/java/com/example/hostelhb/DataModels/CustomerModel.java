package com.example.hostelhb.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class CustomerModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int CustomerId;

    @ColumnInfo(name = "txtCustName")
    private String CustomerName;

    @ColumnInfo(name = "ImgCustomer")
    private byte[] ImgCustomer;

    @ColumnInfo(name = "txtCustomerAddress")
    private String CustomerAddress;

    @ColumnInfo(name = "txtCustomerContNo")
    private String CustomerContNo;

    @ColumnInfo(name = "txtCustomerEmail")
    private String CustomerEmail;

    public CustomerModel() {

    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public byte[] getImgCustomer() {
        return ImgCustomer;
    }

    public void setImgCustomer(byte[] imgCustomer) {
        ImgCustomer = imgCustomer;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerContNo() {
        return CustomerContNo;
    }

    public void setCustomerContNo(String customerContNo) {
        CustomerContNo = customerContNo;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }
}

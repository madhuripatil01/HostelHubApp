package com.example.hostelhb.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class OwnerShowInquiries implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    private int OInquiries;

    public byte[] getImgInq() {
        return ImgInq;
    }

    public void setImgInq(byte[] imgInq) {
        ImgInq = imgInq;
    }

    @ColumnInfo(name = "ImgInq")
    private byte[] ImgInq;

    @ColumnInfo(name = "CustomerName")
   private String CustomerName;

    @ColumnInfo(name = "ContactNo")
    private String ContactNo;

    @ColumnInfo(name = "InquiryFor")
    private String InquiryFor;

    @ColumnInfo(name = "InqDate")
    private String InqDate;

    @ColumnInfo(name = "InqTime")
    private String InqTime;



    @Ignore
    public OwnerShowInquiries(String customerName, String contactNo, String inquiryFor, String inqDate, String inqTime) {

        CustomerName = customerName;
        ContactNo = contactNo;
        InquiryFor = inquiryFor;
        InqDate = inqDate;
        InqTime = inqTime;

    }

    public OwnerShowInquiries() {
    }

    public int getOInquiries() {
        return OInquiries;
    }

    public void setOInquiries(int OInquiries) {
        this.OInquiries = OInquiries;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public String getInquiryFor() {
        return InquiryFor;
    }

    public String getInqDate() {
        return InqDate;
    }

    public String getInqTime() {
        return InqTime;
    }



    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public void setInquiryFor(String inquiryFor) {
        InquiryFor = inquiryFor;
    }

    public void setInqDate(String inqDate) {
        InqDate = inqDate;
    }

    public void setInqTime(String inqTime) {
        InqTime = inqTime;
    }



}

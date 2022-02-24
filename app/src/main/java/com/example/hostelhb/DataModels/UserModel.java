package com.example.hostelhb.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class UserModel implements Serializable
{
    @PrimaryKey(autoGenerate =true)
    private int UserId;

    @ColumnInfo(name = "Name")
    private String Name;

    @ColumnInfo(name = "Address")
    private String Address;

    @ColumnInfo(name = "ConNo")
    private String ConNo;

    @ColumnInfo(name = "Email")
    private String Email;

    @ColumnInfo(name = "City")
    private String City;

    @ColumnInfo(name = "UserType")
   private String UserType;

    @ColumnInfo(name = "UPasswd")
    private String UPasswd;


    @Ignore
    public UserModel(int userId, String name, String address, String conNo, String email, String city, String userType, String UPasswd) {
        UserId = userId;
        Name = name;
        Address = address;
        ConNo = conNo;
        Email = email;
        City = city;
        UserType = userType;
        this.UPasswd = UPasswd;
    }

    public UserModel()
    {
    }


    public int getUserId() {
        return UserId;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getConNo() {
        return ConNo;
    }

    public String getEmail() {
        return Email;
    }

    public String getCity() {
        return City;
    }

    public String getUserType() {
        return UserType;
    }

    public String getUPasswd() {
        return UPasswd;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setConNo(String conNo) {
        ConNo = conNo;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public void setUPasswd(String UPasswd) {
        this.UPasswd = UPasswd;
    }
}

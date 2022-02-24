package com.example.hostelhb.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hostelhb.DataModels.CustomerModel;
import com.example.hostelhb.DataModels.OwnerModel;

import java.util.List;

@Dao
public interface CustomerDAO
{
    @Query("SELECT * FROM CustomerModel")
    List<CustomerModel> getAllCustomer();

    @Insert
    long insertCustomerModel(CustomerModel customerModel);

    @Update
    void updateCustomerModel(CustomerModel customerModel);

    @Delete
    void  deleteCustomerModel(CustomerModel customerModel);

    @Query("Select * from CustomerModel where CustomerId=:Cid")
   CustomerModel SelectCustomerById(long Cid);

    @Query("Select * from CustomerModel where txtCustomerEmail=:Cemail")
    CustomerModel SelectCustomerByEmail(String Cemail);


}

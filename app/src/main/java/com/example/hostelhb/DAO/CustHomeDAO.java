package com.example.hostelhb.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hostelhb.DataModels.CustomerHomeShow;

import java.util.List;

@Dao
public interface CustHomeDAO
{
    @Query("SELECT * FROM CustomerHomeShow")
    List<CustomerHomeShow> getAllHAndM();

    @Insert
    void insertHostel(CustomerHomeShow custHome);
    
    @Update
    void updateHostel(CustomerHomeShow custHome);

    @Delete
    void deleteHostel(CustomerHomeShow custHome);
}

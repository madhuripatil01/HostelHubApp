package com.example.hostelhb.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hostelhb.DataModels.OwnerShowInquiries;

import java.util.List;

@Dao
public interface OwnerInquiriesDAO
{
    @Query("SELECT * FROM OwnerShowInquiries")
    List<OwnerShowInquiries> getAllInquiries();

    @Insert
    void insertInquiries(OwnerShowInquiries OInq);

    @Update
    void updateInquiries(OwnerShowInquiries OInq);

    @Delete
    void deleteInquiries(OwnerShowInquiries OInq);
}

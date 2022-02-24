package com.example.hostelhb.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hostelhb.DataModels.OwnerModel;

import java.util.List;

@Dao
public interface OwnerDAO
{
    @Query("SELECT * FROM OwnerModel")
    List<OwnerModel> getAllOwner();

    @Insert
    long insertOwnerModel(OwnerModel ownerModel);

    @Update
    void updateOwnerModel(OwnerModel ownerModel);

    @Delete
    void  deleteOwnerModel(OwnerModel ownerModel);

    @Query("Select * from OwnerModel where OwnerId=:Oid")
    OwnerModel SelectOwnerById(long Oid);


}

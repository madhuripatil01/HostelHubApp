package com.example.hostelhb.DAO;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hostelhb.DataModels.FlatsModel;


import java.util.List;
@Dao
public interface FlatsDAO
{
    @Query("SELECT * FROM FlatsModel")
    List<FlatsModel> getAllFlats();

    @Insert
    long insertFlat(FlatsModel sflat);

    @Update
    void updateFlat(FlatsModel sflat);

    @Delete
    void deleteFlat(FlatsModel sflat);

}

package com.example.hostelhb.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hostelhb.DataModels.PGModel;

import java.util.List;
@Dao
public interface PGDAO
{
    @Query("SELECT * FROM PGModel")
    List<PGModel> getAllPGs();

    @Insert
    long insertPGs(PGModel sPg);

    @Update
    void updatePGs(PGModel sPg);

    @Delete
    void deletePGs(PGModel sPg);
}

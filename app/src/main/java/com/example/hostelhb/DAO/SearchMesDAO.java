package com.example.hostelhb.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hostelhb.DataModels.SearchMes;

import java.util.List;

@Dao
public interface SearchMesDAO
{
    @Query("SELECT * FROM SearchMes")
    List<SearchMes> getAllSMes();

    @Insert
    long insertSMes(SearchMes Smes);

    @Update
    void updateSMes(SearchMes Smes);

    @Delete
    void deleteSMes(SearchMes Smes);

    @Query("Select * from SearchMes where SMesId=:Mid")
    SearchMes SelectMesById(long Mid);
}

package com.example.hostelhb.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hostelhb.DataModels.SearchHostel;

import java.util.List;

@Dao
public interface SearchHostelDAO
{
    @Query("SELECT * FROM SearchHostel")
    List<SearchHostel> getAllSHostels();

    @Insert
    long insertSHostel(SearchHostel SHos);

    @Update
    void updateSHostel(SearchHostel SHos);

    @Delete
    void deleteSHostel(SearchHostel SHos);

    @Query("Select * from SearchHostel where HosId=:Hid")
    SearchHostel SelectHostelById(long Hid);
}

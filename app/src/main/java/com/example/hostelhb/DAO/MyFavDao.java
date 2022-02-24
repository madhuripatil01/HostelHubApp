package com.example.hostelhb.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hostelhb.DataModels.CustMyFavorate;

import java.util.List;

@Dao
public interface MyFavDao
{
   @Query("SELECT * FROM CustMyFavorate")
    List<CustMyFavorate> getAllFav();

   @Insert
    void insertFav(CustMyFavorate Fav);

   @Update
    void updateFav(CustMyFavorate Fav);

   @Delete
   void deleteFav(CustMyFavorate Fav);

    @Query("Select * from CustMyFavorate where CustFavId=:FId")
    CustMyFavorate SelectFavById(long FId);
}

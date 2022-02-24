package com.example.hostelhb.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hostelhb.DataModels.OwnerShowRating;

import java.util.List;


@Dao
public interface OwnerRatingDAO
{
    @Query("SELECT * FROM OwnerShowRating")
    List<OwnerShowRating> getAllShowRatings();

    @Insert
    void insertSRatings(OwnerShowRating ORate);

    @Update
    void updateSRating(OwnerShowRating ORate);

    @Delete
    void deleteSRating(OwnerShowRating ORate);


}

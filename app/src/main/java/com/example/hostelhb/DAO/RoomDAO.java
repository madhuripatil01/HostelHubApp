package com.example.hostelhb.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hostelhb.DataModels.RoomsModel;

import java.util.List;
@Dao
public interface
RoomDAO
{
    @Query("SELECT * From RoomsModel")
    List<RoomsModel> getAllRooms();

    @Insert
    long insertRooms(RoomsModel roomsModel);

    @Update
    void updateRooms(RoomsModel roomsModel);

    @Delete
    void deleteRooms(RoomsModel roomsModel);

    @Query("Select * from RoomsModel WHERE HosIdRoom = :hosid")
    List<RoomsModel> SelectRoomsById(int hosid);

}

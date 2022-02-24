package com.example.hostelhb.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class RoomsModel implements Serializable
{
    @PrimaryKey(autoGenerate =true)
    private int RoomId;

    @ColumnInfo(name = "RoomHName")
    private String RoomHName;

    @ColumnInfo(name = "RoomNo")
    private String RoomNo;

    @ColumnInfo(name = "MonRent")
    private String MonRent;

    @ColumnInfo(name = "ImgRoom")
    private byte[] ImgRoom;

    @ColumnInfo(name = "HosIdRoom")
    private long HosIdRoom;

    @ColumnInfo(name = "RoomCapacity")
    private String RoomCapacity;
    @Ignore
    public RoomsModel(String roomHName, String roomNo, String monRent, String roomCapacity, byte[] imgRoom) {
        RoomHName = roomHName;
        RoomNo = roomNo;
        MonRent = monRent;
        RoomCapacity = roomCapacity;
        ImgRoom = imgRoom;
    }

    public RoomsModel() {
    }

    public int getRoomId() {
        return RoomId;
    }

    public String getRoomHName() {
        return RoomHName;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public String getMonRent() {
        return MonRent;
    }


    public String getRoomCapacity() {
        return RoomCapacity;
    }

    public byte[] getImgRoom() {
        return ImgRoom;
    }

    public long getHosIdRoom() {
        return HosIdRoom;
    }

    public void setRoomId(int roomId) {
        RoomId = roomId;
    }

    public void setRoomHName(String roomHName) {
        RoomHName = roomHName;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }

    public void setMonRent(String monRent) {
        MonRent = monRent;
    }

    public void setRoomCapacity(String roomCapacity) {
        RoomCapacity = roomCapacity;
    }

    public void setImgRoom(byte[] imgRoom) {
        ImgRoom = imgRoom;
    }

    public void setHosIdRoom(long hosIdRoom) {
        HosIdRoom = hosIdRoom;
    }
}

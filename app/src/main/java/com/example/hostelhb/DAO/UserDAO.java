package com.example.hostelhb.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hostelhb.DataModels.UserModel;

import java.util.List;
@Dao
public interface UserDAO
{
   @Query("SELECT * From UserModel")
    List<UserModel> getAllUsers();

    @Insert
    long insertUsers(UserModel userModel);

    @Update
    void updateUsers(UserModel userModel);

 @Query("Update Usermodel SET  UPasswd =:newpassword  WHERE Email =:email")
 void Update(String newpassword,String email);


    @Delete
    void deleteUsers(UserModel userModel);

    @Query("Select * from UserModel WHERE Email=:email and UPasswd=:pass")
    UserModel userLogin(String email,String pass);


    @Query("Select * from UserModel where UserId=:uId")
    UserModel selectUserById(long uId);
}

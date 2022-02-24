package com.example.hostelhb.Utility;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hostelhb.DAO.CustHomeDAO;
import com.example.hostelhb.DAO.CustomerDAO;
import com.example.hostelhb.DAO.FlatsDAO;
import com.example.hostelhb.DAO.MyFavDao;
import com.example.hostelhb.DAO.OwnerDAO;
import com.example.hostelhb.DAO.OwnerInquiriesDAO;
import com.example.hostelhb.DAO.OwnerRatingDAO;
import com.example.hostelhb.DAO.PGDAO;
import com.example.hostelhb.DAO.RoomDAO;
import com.example.hostelhb.DAO.SearchHostelDAO;
import com.example.hostelhb.DAO.SearchMesDAO;
import com.example.hostelhb.DAO.UserDAO;
import com.example.hostelhb.DataModels.CustMyFavorate;
import com.example.hostelhb.DataModels.CustomerHomeShow;
import com.example.hostelhb.DataModels.CustomerModel;
import com.example.hostelhb.DataModels.FlatsModel;
import com.example.hostelhb.DataModels.OwnerModel;
import com.example.hostelhb.DataModels.OwnerShowInquiries;
import com.example.hostelhb.DataModels.OwnerShowRating;
import com.example.hostelhb.DataModels.PGModel;
import com.example.hostelhb.DataModels.RoomsModel;
import com.example.hostelhb.DataModels.SearchHostel;
import com.example.hostelhb.DataModels.SearchMes;
import com.example.hostelhb.DataModels.UserModel;

@Database(entities = {CustMyFavorate.class,CustomerHomeShow.class
        ,OwnerShowInquiries.class,SearchHostel.class,SearchMes.class
        ,UserModel.class, OwnerShowRating.class, RoomsModel.class, OwnerModel.class, CustomerModel.class, FlatsModel.class, PGModel.class}
        ,version = 3,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
  public abstract CustHomeDAO custHomeDAO();
  public  abstract MyFavDao myFavDao();
  public  abstract OwnerInquiriesDAO ownerInquiriesDAO();
  public  abstract OwnerRatingDAO ownerRatingDAO();
  public abstract SearchHostelDAO searchHostelDAO();
  public abstract SearchMesDAO searchMesDAO();
  public abstract UserDAO userDAO();
  public abstract RoomDAO roomDAO();
  public abstract OwnerDAO ownerDAO();
  public abstract CustomerDAO customerDAO();
  public abstract FlatsDAO flatsDAO();
  public abstract PGDAO pgdao();
}

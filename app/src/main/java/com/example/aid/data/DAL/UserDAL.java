package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.example.aid.data.model.user;
import com.example.aid.ui.login.LoginActivity;

public class UserDAL  {
    private DataBaseHelper dbhelper;
    public UserDAL(Context context) {
        dbhelper=new DataBaseHelper(context);
        Log.v("tag","success");
    }
//    public String count() throws SQLException {
//        SQLiteDatabase db=dbhelper.getReadableDatabase();
//        String sql = "select count(*) from user";
//        ResultSet rs = stmt.executeQuery(sql);
//        String c = "0";
//        while(rs.next()) {c=rs.getString(1);}
//        rs.close();
//        return c;
//    }
    public boolean login(String id ,String password) throws SQLException {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select count(*) from user where User_ID='" + id + "' and User_Pwd='" + password +"'";
        Cursor cursor = db.rawQuery(sql,null);
        //int count = cursor.getCount();
        System.out.println(id);
        System.out.println(password);

        cursor.moveToFirst();
        long count = cursor.getLong(0);
        System.out.println(count);
        if(count > 0) {
           cursor.close();
           return true;
        }
        else {cursor.close();return false;}

    }
}

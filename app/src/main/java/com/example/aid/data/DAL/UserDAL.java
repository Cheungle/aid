package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.aid.data.model.identity;
import com.example.aid.data.model.user;
import com.example.aid.ui.login.LoginActivity;

public class UserDAL  {
    private DataBaseHelper dbhelper;
    public UserDAL(Context context) {
        dbhelper=new DataBaseHelper(context);
        Log.v("tag","success");
    }
    public boolean login(String id ,String password) throws SQLException {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select count(*) from user where User_ID='" + id + "' and User_Pwd='" + password +"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        System.out.println(count);
        if(count > 0) {
           cursor.close();
           return true;
        }
        else {cursor.close();return false;}

    }
    public boolean idIsExist(String id){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select count(*) from user where User_ID='" + id + "'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        System.out.println(count);
        if(count > 0) {
            cursor.close();
            return true;
        }
        else {cursor.close();return false;}
    }
    public void register(String id,String password,String name,String sex){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        String sql = "insert into user(User_ID,User_Pwd,User_Name,User_Sex) values('"+id+"','"+password+"','"+name+"','"+sex+"')";
        db.execSQL(sql);
    }
    public String[] selectNameAndPhoto(String id){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select User_Name,User_Head from user where User_ID='" + id + "'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        String base[] = new String[2];
        base[0]= cursor.getString(0);
        base[1]= cursor.getString(1);
        cursor.close();
        return base;
    }
    public String[] selectPhotoPage(String id){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select User_Head,User_Name,User_Sex,User_Age from user where User_ID='" + id + "'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        String base[] = new String[4];
        for(int i =0;i<4;i++){
            base[i] = cursor.getString(i);
        }
        cursor.close();
        return base;
    }
    public identity selectIdentity(String id){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select User_Identity_fk,User_RealName_fk from user where User_ID='" + id + "'";
        Cursor cursor = db.rawQuery(sql,null);
        identity iden;
        if(cursor.moveToFirst())
        iden = new identity(cursor.getString(0),cursor.getString(1));
        else iden = new identity("","");
        cursor.close();
        return iden;
    }
    public void updateAge(String age,String id){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        String sql = "update user set User_Age ='" + age + "' where User_ID = '"+ id +"'";
        db.execSQL(sql);
    }
    public void updateSex(String sex,String id){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        String sql = "update user set User_Sex ='" + sex + "' where User_ID = '"+ id +"'";
        db.execSQL(sql);
    }
    public void updateName(String name,String id){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        String sql = "update user set User_Name ='" + name + "' where User_ID = '"+ id +"'";
        db.execSQL(sql);
    }
    public void updateIdentity(String id,String ID,String name){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        String sql = "update user set User_Identity_fk ='" + ID + "',User_RealName_fk='"+ name +"' where User_ID = '"+ id +"'";
        db.execSQL(sql);
    }
}

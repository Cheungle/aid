package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;

public class ManagerDAL {
    private DataBaseHelper dbhelper;
    public ManagerDAL(Context context) {
        dbhelper=new DataBaseHelper(context);
        Log.v("tag","success");
    }
    public boolean login(String id ,String password) throws SQLException {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select count(*) from manager where Manager_ID='" + id + "' and Manager_Password='" + password +"'";
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
//    public boolean idIsExist(String id){
//        SQLiteDatabase db=dbhelper.getReadableDatabase();
//        String sql = "select count(*) from user where User_ID='" + id + "'";
//        Cursor cursor = db.rawQuery(sql,null);
//        cursor.moveToFirst();
//        long count = cursor.getLong(0);
//        System.out.println(count);
//        if(count > 0) {
//            cursor.close();
//            return true;
//        }
//        else {cursor.close();return false;}
//    }
//    public void register(String id,String password,String name,String sex){
//        SQLiteDatabase db=dbhelper.getWritableDatabase();
//        String sql = "insert into user(User_ID,User_Pwd,User_Name,User_Sex) values('"+id+"','"+password+"','"+name+"','"+sex+"')";
//        db.execSQL(sql);
//    }
//    public String[] selectNameAndPhoto(String id){
//        SQLiteDatabase db=dbhelper.getReadableDatabase();
//        String sql = "select User_Name,User_Head from user where User_ID='" + id + "'";
//        Cursor cursor = db.rawQuery(sql,null);
//        cursor.moveToFirst();
//        String base[] = new String[2];
//        base[0]= cursor.getString(0);
//        base[1]= cursor.getString(1);
//        cursor.close();
//        return base;
//    }
//    public String[] selectPhotoPage(String id){
//        SQLiteDatabase db=dbhelper.getReadableDatabase();
//        String sql = "select User_Head,User_Name,User_Sex,User_Age from user where User_ID='" + id + "'";
//        Cursor cursor = db.rawQuery(sql,null);
//        cursor.moveToFirst();
//        String base[] = new String[4];
//        for(int i =0;i<4;i++){
//            base[i] = cursor.getString(i);
//        }
//        cursor.close();
//        return base;
//    }
//    public void updateAge(String age,String id){
//        SQLiteDatabase db=dbhelper.getWritableDatabase();
//        String sql = "update user set User_Age ='" + age + "' where User_ID = '"+ id +"'";
//        db.execSQL(sql);
//    }
//    public void updateSex(String sex,String id){
//        SQLiteDatabase db=dbhelper.getWritableDatabase();
//        String sql = "update user set User_Sex ='" + sex + "' where User_ID = '"+ id +"'";
//        db.execSQL(sql);
//    }
//    public void updateName(String name,String id){
//        SQLiteDatabase db=dbhelper.getWritableDatabase();
//        String sql = "update user set User_Name ='" + name + "' where User_ID = '"+ id +"'";
//        db.execSQL(sql);
//    }
}

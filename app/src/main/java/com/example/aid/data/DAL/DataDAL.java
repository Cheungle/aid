package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aid.ui.data.ChinaMapView;

public class DataDAL  {
    private DataBaseHelper dbhelper;
    private DataBaseHelper dbhelper1;
    public DataDAL(Context context) {
        dbhelper=new DataBaseHelper(context);
        Log.v("tag","success");
    }

    public DataDAL(ChinaMapView.OnProvinceSelectedListener onProvinceSelectedListener) {
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
    public Cursor chaxun(String place) {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql="select * from data where Data_Place='"+place+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        db.close();
        return cursor;
    }
    public Cursor zhufirst() {
        SQLiteDatabase db1=dbhelper.getReadableDatabase();
        String sql2="select * from data order by Data_Data4 desc limit 1,10";
        Cursor cursor1 = db1.rawQuery(sql2,null);
        cursor1.moveToNext();
        db1.close();
        return cursor1;
    }
    public Cursor zhusecond() {
        SQLiteDatabase db2=dbhelper.getReadableDatabase();
        String sql3="select * from data order by Data_Data4 desc limit 1,6";
        Cursor cursor2 = db2.rawQuery(sql3,null);
        cursor2.moveToNext();
        db2.close();
        return cursor2;
    }
    public Cursor die() {
        SQLiteDatabase db3=dbhelper.getReadableDatabase();
        String sql4="select sum(Data_Data1) as die from data ";
        Cursor cursor3 = db3.rawQuery(sql4,null);
        return cursor3;
    }
    public Cursor zhiyu() {
        SQLiteDatabase db4=dbhelper.getReadableDatabase();
        String sql5="select sum(Data_Data2) as zhiyu from data ";
        Cursor cursor4 = db4.rawQuery(sql5,null);
        return cursor4;
    }
    public Cursor zhexian() {
        SQLiteDatabase db5=dbhelper.getReadableDatabase();
        String sql6="select * from daydata order by daydata_ID desc limit 7";
        Cursor cursor5 = db5.rawQuery(sql6,null);
        return cursor5;
    }
}

package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class InfoDAL  {
    private DataBaseHelper dbhelper;
    public InfoDAL(Context context) {
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
    public Cursor info(int zhye) {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql="select * from information";
        if(zhye==2){
        sql = "select * from information ";}
        else if(zhye==3){
            String a="央视新闻";
        sql = "select * from information where Info_Source='"+a+"'";}
        else if(zhye==4){
            String a="人民日报";
            sql = "select * from information where Info_Source='"+a+"'";}
        else if(zhye==5){
            String a="央视新闻";
            sql = "select * from information where Info_Source='"+a+"'";}
        Cursor cursor = db.rawQuery(sql,null);

      /*  String sql1 = "select count(*) from info";
        Cursor cursor1 = db.rawQuery(sql1,null);

        cursor1.moveToFirst();
        long count = cursor1.getLong(0);
        Log.v("count", String.valueOf(count));*/
       /* String Title[]= new String[count];
        String Content[]= new String[count];
        int i=0;
        while(cursor.moveToNext()){

            Title[i] = cursor.getString(cursor.getColumnIndex("Info_Title"));
            Content[i] = cursor.getString(cursor.getColumnIndex("Info_Content"));

            i=i+1;
        }*/

        //int count = cursor.getCount();
       // cursor.moveToNext();
        //Log.v("information", String.valueOf(cursor));
        return cursor;
    }
}

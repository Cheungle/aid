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
    public Cursor info(int zhye,int b,String sousuo) {
        String sql = null;
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        if(sousuo==""){
            if(b==1){
                if(zhye==0){
                    sql="select * from information order by Info_Time ASC";}
                if(zhye==2){
                    sql = "select * from information order by Info_Time ASC";}
                else if(zhye==3){
                    String a="数据";
                    sql = "select * from information where Info_Kind='"+a+"' order by Info_Time ASC";}
                else if(zhye==4){
                    String a="预防";
                    sql = "select * from information where Info_Kind='"+a+"' order by Info_Time ASC";}
                else if(zhye==5){
                    String a="轨迹";
                    sql = "select * from information where Info_Kind='"+a+"' order by Info_Time ASC";}}
            else{
                if(zhye==0){
                    sql="select * from information order by Info_Time DESC";}
                if(zhye==2){
                    sql = "select * from information order by Info_Time DESC";}
                else if(zhye==3){
                    String a="数据";
                    sql = "select * from information where Info_Kind='"+a+"' order by Info_Time DESC";}
                else if(zhye==4){
                    String a="预防";
                    sql = "select * from information where Info_Kind='"+a+"' order by Info_Time DESC";}
                else if(zhye==5){
                    String a="轨迹";
                    sql = "select * from information where Info_Kind='"+a+"' order by Info_Time DESC";}
            }}
        else{
            if(b==1){
                if(zhye==0){
                    sql="select * from information order by Info_Time ASC";}
                if(zhye==2){
                    sql = "select * from information order by Info_Time ASC";}
                else if(zhye==3){
                    String a="数据";
                    sql = "select * from information where Info_Kind='"+a+"' order by Info_Time ASC";}
                else if(zhye==4){
                    String a="预防";
                    sql = "select * from information where Info_Kind='"+a+"' order by Info_Time ASC";}
                else if(zhye==5){
                    String a="轨迹";
                    sql = "select * from information where Info_Kind='"+a+"' order by Info_Time ASC";}}
            else{
                if(zhye==0){
                    sql="select * from information where Info_Content like '%"+sousuo+"%' order by Info_Time DESC ";}
                if(zhye==2){
                    sql = "select * from information where Info_Content like '%"+sousuo+"%' order by Info_Time DESC";}
                else if(zhye==3){
                    String a="数据";
                    sql = "select * from information where Info_Kind='"+a+"' and Info_Content like '%"+sousuo+"%' order by Info_Time DESC";}
                else if(zhye==4){
                    String a="预防";
                    sql = "select * from information where Info_Kind='"+a+"' and Info_Content like '%"+sousuo+"%' order by Info_Time DESC";}
                else if(zhye==5){
                    String a="轨迹";
                    sql = "select * from information where Info_Kind='"+a+"' and Info_Content like '%"+sousuo+"%' order by Info_Time DESC";}
            }
        }
        Cursor cursor = db.rawQuery(sql,null);
        return cursor;

    }
    public void deleteinfo(String title) {
        SQLiteDatabase db6=dbhelper.getReadableDatabase();
        String sql7="delete from information where Info_Title='"+title+"'";
        db6.rawQuery(sql7,null);
        Cursor cursor = db6.rawQuery(sql7,null);
        while (cursor.moveToNext()) {
            Log.i("TAG","title"+cursor.getString(1));
        }
        /*String sql2="select * from information where Info_Title='"+title+"'";
        Cursor cursor7 = db6.rawQuery(sql2,null);
        while (cursor7.moveToNext()){
            Log.v("title111",cursor7.getString(cursor7.getColumnIndex("Info_Title")));
        }*/
    }
}

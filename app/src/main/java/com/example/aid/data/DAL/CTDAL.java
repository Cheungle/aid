package com.example.aid.data.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CTDAL {
    private DataBaseHelper dbhelper;
    int Task_ID;
    public CTDAL(Context context) {
        dbhelper = new DataBaseHelper(context);
        Log.v("tag","success");
    }
    public void addCTask(String id,String time){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        String sql = "insert into completedtask values('"+id+"','"+time+"')";
        db.execSQL(sql);
    }
    public void deleteCTask(String id){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        String sql = "delete from completedtask where CT_ID_fk ='"+ id +"'";
        db.execSQL(sql);
    }
}

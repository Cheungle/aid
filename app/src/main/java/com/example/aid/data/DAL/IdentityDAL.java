package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;

public class IdentityDAL {
    private DataBaseHelper dbhelper;
    public IdentityDAL(Context context) {
        dbhelper=new DataBaseHelper(context);
        Log.v("tag","success");
    }
    public boolean login(String id ,String name) throws SQLException {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select count(*) from identity where Identity_ID='" + id + "' and Identity_Name='" + name +"'";
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
}

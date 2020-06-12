package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MWDAL {
    private DataBaseHelper dbhelper;
    private String[] user_names;
    private String last_message;
    String[] str_names=new String[10];

    public MWDAL(Context context) {
        dbhelper = new DataBaseHelper(context);
        Log.v("tag","msg");
    }

    public String[] getUser_Names(String User_ID) throws SQLException {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select user.User_Name from user,messagewindow where user.User_ID=messagewindow.MW_UserID2_fk" +
                "and messagewindow.MW_UserID1_fk='"+User_ID+"' ";
        Cursor cursor = db.rawQuery(sql,null);
        int i=0;
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            str_names[i]=cursor.getString(0);
            i++;
            //Log.i("TAG","cursor.getString(0)="+cursor.getString(0));
        }
        cursor.close();
        user_names=str_names;
        return user_names;
    }


}

package com.example.aid.data.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;

public class MWDAL {
    private DataBaseHelper dbhelper;
    private String[] user_names;
    private String last_message;
    String[] str_names=new String[10];
    int MW_ID;

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
    public boolean isMessageWindowExist(String User1ID,String User2ID,int TaskID){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql="select count(*) from messagewindow where MW_TaskID_fk='"+ TaskID +"' " +
                "and MW_UserID1_fk ='"+User1ID+"' and MW_UserID2_fk = '"+User2ID+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        if(count > 0) {
            cursor.close();
            return true;
        }
        else {cursor.close();return false;}
    }
    public void insertMessageWindow(String User1ID,String User2ID,int TaskID) {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql="select MAX(MW_ID) from message";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            MW_ID=cursor.getInt(0);
            MW_ID++;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new java.util.Date());
        ContentValues values = new ContentValues();
        values.put("MW_ID", MW_ID);
        values.put("MW_UserID1_fk", User1ID);
        values.put("MW_UserID2_fk", User2ID);
        values.put("MW_Time", date);
        values.put("MW_TaskID_fk", TaskID);

        //values.put("Comment_PreCmmtID_fk", 0);
        db.insert("messagewindow", null, values);

    }


}

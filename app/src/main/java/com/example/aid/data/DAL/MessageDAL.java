package com.example.aid.data.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageDAL {
    private DataBaseHelper dbhelper;
    private String[] message_contents;
    private String[] message_senders;
    private String last_message;
    List<Map<String, Object>> message_list = new ArrayList<Map<String, Object>>();
    String[] str_contents=new String[10];
    String[] str_senders=new String[10];
    int Message_ID;

    public MessageDAL(Context context) {
        dbhelper = new DataBaseHelper(context);
        Log.v("tag","msg");
    }

    public List<Map<String, Object>> getMessage_Contents(String User1_ID,String User2_Name) {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        //String sql = "select message.Message_Content,message.Message_UserID_fk from message,user where ((message.Message_UserID_fk='"+User1_ID+"')" +
                //" or (message.Message_UserID_fk=user.User_ID and user.User_Name='"+User2_Name+"')) ";
        String sql = "select message.Message_Content,message.Message_UserID_fk from message,messagewindow,user where (message.Message_WindowID_fk=messagewindow.MW_ID " +
                " and messagewindow.MW_UserID1_fk ='"+User1_ID+"' and (messagewindow.MW_UserID2_fk = user.User_ID and user.User_Name='"+User2_Name+"')) " +
                " or (message.Message_WindowID_fk=messagewindow.MW_ID and messagewindow.MW_UserID2_fk ='"+User1_ID+"' and " +
                "(messagewindow.MW_UserID1_fk = user.User_ID and user.User_Name='"+User2_Name+"'))";
        Cursor cursor = db.rawQuery(sql,null);
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message_content", cursor.getString(0));
            map.put("message_type", cursor.getString(1));
            message_list.add(map);
            //Log.i("TAG","cursor.getString(0)="+cursor.getString(0));
        }
        cursor.close();
        return message_list;
       //用map封装两个数组
    }

    public List<Map<String, Object>> getMessage_Contents() {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select message.Message_Content,message.Message_UserID_fk from message";
        Cursor cursor = db.rawQuery(sql,null);
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message_content", cursor.getString(0));
            map.put("message_type", cursor.getString(1));
            message_list.add(map);
            //Log.i("TAG","cursor.getString(0)="+cursor.getString(0));
        }
        cursor.close();
        return message_list;
        //用map封装两个数组
    }

    public void insertMessage(String Message_Content,String Message_UserID_fk, String Message_Time, int Message_WindowID_fk) {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql="select MAX(Message_ID) from message";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Message_ID=cursor.getInt(0);
            Message_ID++;
        }
        ContentValues values = new ContentValues();
        values.put("Message_ID", Message_ID);
        values.put("Message_Content", Message_Content);
        values.put("Message_Time", Message_Time);
        values.put("Message_UserID_fk", Message_UserID_fk);
        values.put("Message_WindowID_fk", Message_WindowID_fk);
        values.put("Message_State1", 1);
        values.put("Message_State2", 1);
        //values.put("Comment_PreCmmtID_fk", 0);
        db.insert("message", null, values);

    }

    public void getAllMessage() {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql="select Message_Content from message";
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()) {
            Log.i("content=",cursor.getString(0));
        }
    }
}

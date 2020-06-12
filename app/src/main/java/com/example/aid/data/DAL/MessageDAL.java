package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MessageDAL {
    private DataBaseHelper dbhelper;
    private String[] message_contents;
    private String[] message_senders;
    private String last_message;
    String[] str_contents=new String[10];
    String[] str_senders=new String[10];

    public MessageDAL(Context context) {
        dbhelper = new DataBaseHelper(context);
        Log.v("tag","msg");
    }

    public String[] getMessage_ContentsByWindowID(int Window_ID) {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select message.Message_Content,message.Message_UserID_fk from message,messagewindow where messagewindow.MW_ID=message.Message_WindowID_fk" +
                " and messagewindow.MW_ID='"+Window_ID+"' ";
        Cursor cursor = db.rawQuery(sql,null);
        int i=0;
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            str_contents[i]=cursor.getString(0);
            str_senders[i]=cursor.getString(1);
            i++;
            //Log.i("TAG","cursor.getString(0)="+cursor.getString(0));
        }
        cursor.close();
        message_contents=str_contents;
        message_senders=str_senders;
        return message_contents;
       //用map封装两个数组
    }
}

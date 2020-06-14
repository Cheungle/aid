package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aid.data.model.task;
import com.example.aid.data.model.taskView;

import java.sql.SQLException;
import java.util.ArrayList;

public class MarkDAL {
    private DataBaseHelper dbhelper;
    public MarkDAL(Context context) {
        dbhelper=new DataBaseHelper(context);
        Log.v("tag","success");
    }
    public ArrayList<taskView> selectMarkByOne(String id) throws SQLException {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select * from mark,task,user where Mark_UserID_fk='" + id + "' and Mark_TaskID_fk=Task_ID and Task_CreatorID_fk=User_ID";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<taskView> t = new ArrayList<taskView>();
        while (cursor.moveToNext()){
            taskView now = new taskView(cursor.getInt(cursor.getColumnIndex("Task_ID")),
                    cursor.getString(cursor.getColumnIndex("User_Name")),cursor.getString(cursor.getColumnIndex("Task_Content")),
                    cursor.getString(cursor.getColumnIndex("Task_Time")),cursor.getInt(cursor.getColumnIndex("Task_Type")),cursor.getString(cursor.getColumnIndex("Task_Place")));
            t.add(now);
        }
        sql = "select * from mark,receivedtask,user where Mark_UserID_fk='" + id + "' and RCT_ID_fk=Mark_TaskID_fk and RCT_ReceiverID_fk=User_ID";
        cursor = db.rawQuery(sql,null);
        int i = 0;
        while(cursor.moveToNext()){
            t.get(i).setRCT_ReceiverID_fk(cursor.getString(cursor.getColumnIndex("RCT_ReceiverID_fk")));
            i++;
        }
        sql="select * from mark,completedtask where Mark_UserID_fk='" + id + "' and CT_ID_fk = Mark_TaskID_fk";
        cursor = db.rawQuery(sql,null);
        int j = 0;
        while(cursor.moveToNext()){
            t.get(j).setCT_CompletedTime(cursor.getString(cursor.getColumnIndex("CT_CompletedTime")));
            j++;
        }
        cursor.close();
       // System.out.println(t);
        return t;

    }
}

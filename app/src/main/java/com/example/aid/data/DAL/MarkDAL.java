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
    public void deleteByID(String id,String marker){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        String sql = "delete from mark where Mark_TaskID_fk = '"+ id +"'and Mark_UserID_fk='"+ marker +"'";
        db.execSQL(sql);
    }
    public ArrayList<taskView> selectMarkByOne(String id) throws SQLException {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select Mark_TaskID_fk,Task_Content,Task_Place,Task_Time,Task_Type,Task_CreatorID_fk as PubPerson,Markinfo.User_Name as RecPerson,CT_CompletedTime " +
                "from (select Mark_UserID_fk,Mark_TaskID_fk,Task_Content,Task_Place,Task_Time,Task_Type,User_Name,Task_CreatorID_fk from mark,task,user where Mark_TaskID_fk=Task_ID and Task_CreatorID_fk=User_ID) as MarkTask " +
                "left join reviewedtask " +
                "on Mark_TaskID_fk=RVT_ID_fk " +
                "left join (select User_Name,RCT_ID_fk from receivedtask,user where User_ID=RCT_ReceiverID_fk) as Markinfo " +
                "on Mark_TaskID_fk=RCT_ID_fk " +
                "left join completedtask " +
                "on Mark_TaskID_fk=CT_ID_fk " +
                "where Mark_UserID_fk='"+ id +"'";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<taskView> t = new ArrayList<taskView>();
        while (cursor.moveToNext()){
            //Log.v("t",cursor.getString(cursor.getColumnIndex("PubPerson")));
            taskView now = new taskView(cursor.getInt(cursor.getColumnIndex("Mark_TaskID_fk")),
                    cursor.getString(cursor.getColumnIndex("PubPerson")),cursor.getString(cursor.getColumnIndex("Task_Content")),
                    cursor.getString(cursor.getColumnIndex("Task_Time")),cursor.getInt(cursor.getColumnIndex("Task_Type")),
                    cursor.getString(cursor.getColumnIndex("Task_Place")),cursor.getString(cursor.getColumnIndex("RecPerson")),cursor.getString(cursor.getColumnIndex("CT_CompletedTime")));
            t.add(now);
        }
        cursor.close();
       // System.out.println(t);
        return t;

    }
}

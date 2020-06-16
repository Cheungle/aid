package com.example.aid.data.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aid.data.model.taskView;
import com.example.aid.ui.dashboard.pubFun;
import com.google.android.gms.tasks.Task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TaskDAL {
    private DataBaseHelper dbhelper;
    int Task_ID;
    public TaskDAL(Context context) {
        dbhelper = new DataBaseHelper(context);
        Log.v("tag","success");
    }
    public void deleteByID(String id){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        String sql = "delete from task where Task_ID = '"+ id +"'";
        db.execSQL(sql);
    }
    public void insertTask(String Task_CreatorID_fk, String Task_Content,
                           String Task_Time, int Task_Type, String Task_Place) throws SQLException {

        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql="select count(*) from comment";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Task_ID=cursor.getInt(0);
            Task_ID++;
        }

        ContentValues values = new ContentValues();
        values.put("Task_ID", Task_ID);
        values.put("Task_CreatorID_fk", Task_CreatorID_fk);
        values.put("Task_Content", Task_Content);
        values.put("Task_Time", Task_Time);
        values.put("Task_Type", Task_Type);
        values.put("Task_Place", Task_Place);

        db.insert("task", null, values);
        db.close();

    }
    public ArrayList<taskView> selectTaskInfoByOne(String id){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql="select * " +
                "from task " +
                "left join reviewedtask " +
                "on Task_ID=RVT_ID_fk " +
                "left join (select User_Name,RCT_ID_fk from receivedtask,User where User_ID=RCT_ReceiverID_fk)as RCTinfo " +
                "on Task_ID=RCT_ID_fk " +
                "left join completedtask " +
                "on Task_ID=CT_ID_fk " +
                "where Task_CreatorID_fk='"+ id +"' ";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<taskView> t = new ArrayList<taskView>();
        while (cursor.moveToNext()){
            //Log.e("task",cursor.getString(cursor.getColumnIndex("Task_Place")));
            taskView now = new taskView(cursor.getInt(cursor.getColumnIndex("Task_ID")),
                    id,cursor.getString(cursor.getColumnIndex("Task_Content")),
                    cursor.getString(cursor.getColumnIndex("Task_Time")),cursor.getInt(cursor.getColumnIndex("Task_Type")),
                    cursor.getString(cursor.getColumnIndex("Task_Place")),cursor.getString(cursor.getColumnIndex("User_Name")),
                    cursor.getString(cursor.getColumnIndex("CT_CompletedTime")),cursor.getInt(cursor.getColumnIndex("RVT_State")));
            t.add(now);
        }
        cursor.close();
        return t;
    }
}

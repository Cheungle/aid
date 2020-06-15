package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aid.data.model.taskView;

import java.sql.SQLException;
import java.util.ArrayList;

public class RCTDAL {
    private DataBaseHelper dbhelper;
    int Task_ID;
    public RCTDAL(Context context) {
        dbhelper = new DataBaseHelper(context);
        Log.v("tag","success");
    }

    public ArrayList<taskView> selectRCTaskInfoByOne(String id){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql="select * from (select * from aid.receivedtask,aid.task,aid.user " +
                "where RCT_ReceiverID_fk = '"+ id +"' and Task_CreatorID_fk=User_ID and Task_ID=RCT_ID_fk) as RCTask " +
                "left join aid.completedtask " +
                "on Task_ID = CT_ID_fk";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<taskView> t = new ArrayList<taskView>();
        while (cursor.moveToNext()){
            //Log.e("task",cursor.getString(cursor.getColumnIndex("Task_Place")));
            taskView now = new taskView(cursor.getInt(cursor.getColumnIndex("Task_ID")),
                    cursor.getString(cursor.getColumnIndex("User_Name")),cursor.getString(cursor.getColumnIndex("Task_Content")),
                    cursor.getString(cursor.getColumnIndex("Task_Time")),cursor.getInt(cursor.getColumnIndex("Task_Type")),
                    cursor.getString(cursor.getColumnIndex("Task_Place")), cursor.getString(cursor.getColumnIndex("CT_CompletedTime")));
            t.add(now);
        }
        cursor.close();
        return t;
    }
}

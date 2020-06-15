package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aid.data.model.taskView;

import java.util.ArrayList;

public class RVTDAL {
    private DataBaseHelper dbhelper;
    int Task_ID;
    public RVTDAL(Context context) {
        dbhelper = new DataBaseHelper(context);
        Log.v("tag","success");
    }

    public ArrayList<taskView> selectRCTaskInfoByOne(String id){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql="select * from reviewedtask,task where RVT_ManagerID_fk = '"+ id +"' and RVT_ID_fk = Task_ID";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<taskView> t = new ArrayList<taskView>();
        while (cursor.moveToNext()){
            //Log.e("task",cursor.getString(cursor.getColumnIndex("Task_Place")));
            taskView now = new taskView(cursor.getInt(cursor.getColumnIndex("Task_ID")),
                    cursor.getString(cursor.getColumnIndex("Task_CreatorID_fk")),cursor.getString(cursor.getColumnIndex("Task_Content")),
                    cursor.getString(cursor.getColumnIndex("Task_Time")),cursor.getInt(cursor.getColumnIndex("Task_Type")),
                    cursor.getString(cursor.getColumnIndex("Task_Place")), cursor.getInt(cursor.getColumnIndex("RVT_State")));
            t.add(now);
        }
        cursor.close();
        return t;
    }
    public ArrayList<taskView> selectWaitForReviewTask(){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select * from task left join reviewedtask on RVT_ID_fk=Task_ID where RVT_State is null";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<taskView> t = new ArrayList<taskView>();
        while (cursor.moveToNext()){
            //Log.e("task",cursor.getString(cursor.getColumnIndex("Task_Place")));
            taskView now = new taskView(cursor.getInt(cursor.getColumnIndex("Task_ID")),
                    cursor.getString(cursor.getColumnIndex("Task_CreatorID_fk")),cursor.getString(cursor.getColumnIndex("Task_Content")),
                    cursor.getString(cursor.getColumnIndex("Task_Time")),cursor.getInt(cursor.getColumnIndex("Task_Type")),
                    cursor.getString(cursor.getColumnIndex("Task_Place")));
            t.add(now);
        }
        cursor.close();
        return t;
    }
}

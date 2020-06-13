package com.example.aid.data.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aid.ui.dashboard.pubFun;
import com.google.android.gms.tasks.Task;

import java.sql.SQLException;
import java.util.Date;

public class TaskDAL {
    private DataBaseHelper dbhelper;
    int Task_ID;
    public TaskDAL(Context context) {
        dbhelper = new DataBaseHelper(context);
        Log.v("tag","success");
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
}

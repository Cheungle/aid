package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aid.data.model.comment;
import com.example.aid.data.model.taskView;

import java.util.ArrayList;

public class RVCDAL {
    private DataBaseHelper dbhelper;
    int Task_ID;
    public RVCDAL(Context context) {
        dbhelper = new DataBaseHelper(context);
        Log.v("tag","success");
    }

    public ArrayList<comment> selectRVCommentByOne(String id){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql="select * from comment,reviewedcomment where RC_ManagerID_fk = '"+ id +"' and Comment_State = 1 and Comment_ID=RC_ID_fk";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<comment> t = new ArrayList<comment>();
        while (cursor.moveToNext()){
            //Log.e("task",cursor.getString(cursor.getColumnIndex("Task_Place")));
            comment now = new comment(cursor.getInt(cursor.getColumnIndex("Comment_ID")),
                    cursor.getString(cursor.getColumnIndex("Comment_Content")),cursor.getString(cursor.getColumnIndex("Comment_PublishTime")),
                    cursor.getString(cursor.getColumnIndex("Comment_Source_fk")),cursor.getInt(cursor.getColumnIndex("Comment_ThemeID_fk")),
                    cursor.getInt(cursor.getColumnIndex("RC_State")), cursor.getInt(cursor.getColumnIndex("Comment_PreCmmtID_fk")));
            t.add(now);
        }
        cursor.close();
        return t;
    }
    public ArrayList<comment> selectWaitForReviewComment(){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select * from comment where Comment_State=2";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<comment> t = new ArrayList<comment>();
        while (cursor.moveToNext()){
            //Log.e("task",cursor.getString(cursor.getColumnIndex("Task_Place")));
            comment now = new comment(cursor.getInt(cursor.getColumnIndex("Comment_ID")),
                    cursor.getString(cursor.getColumnIndex("Comment_Content")),cursor.getString(cursor.getColumnIndex("Comment_PublishTime")),
                    cursor.getString(cursor.getColumnIndex("Comment_Source_fk")),cursor.getInt(cursor.getColumnIndex("Comment_ThemeID_fk")),
                    cursor.getInt(cursor.getColumnIndex("Comment_PreCmmtID_fk")));
            t.add(now);
        }
        cursor.close();
        return t;
    }
}

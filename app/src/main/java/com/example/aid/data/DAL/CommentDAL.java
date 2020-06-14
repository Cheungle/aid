package com.example.aid.data.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDAL {
    private DataBaseHelper dbhelper;
    private String[] comments;
    List<Map<String, Object>> comment_list = new ArrayList<Map<String, Object>>();
    String[] str_comments=new String[10];
    int Comment_ID;

    public CommentDAL(Context context) {
        dbhelper = new DataBaseHelper(context);
        Log.v("tag","success");
    }

    public String[] getAllComments() throws SQLException {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select Comment_Content from comment";
        Cursor cursor = db.rawQuery(sql,null);
        int i=0;
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            str_comments[i]=cursor.getString(0);
            i++;
        }
        cursor.close();
        comments=str_comments;
        return comments;
    }

    public List<Map<String, Object>> getCommentsByThemeID(int Theme_ID) throws SQLException {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select Comment_Content,Comment_PublishTime,User_Name from comment,user " +
                "where Comment_ThemeID_fk= '"+Theme_ID+"' and user.User_ID=comment.Comment_Source_fk";
        Cursor cursor = db.rawQuery(sql,null);
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("c_name", cursor.getString(2));
            map.put("comment", cursor.getString(0));
            map.put("time", cursor.getString(1));
            comment_list.add(map);
        }
        cursor.close();
        return comment_list;
    }

    public void insertComment(String Comment_Content, String Comment_PublishTime,
                              String Comment_Source_fk,int Comment_ThemeID_fk) throws SQLException{

        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql="select MAX(Comment_ID) from comment";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Comment_ID=cursor.getInt(0);
            Comment_ID++;
        }
        ContentValues values = new ContentValues();
        values.put("Comment_ID", Comment_ID);
        values.put("Comment_Content", Comment_Content);
        values.put("Comment_PublishTime", Comment_PublishTime);
        values.put("Comment_Source_fk", Comment_Source_fk);
        values.put("Comment_ThemeID_fk", Comment_ThemeID_fk);
        values.put("Comment_State", 1);
        //values.put("Comment_PreCmmtID_fk", 0);

        db.insert("comment", null, values);
        //db.close();
    }

    public void deleteComment(int Theme_ID, String Comment_Content) {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql="delete from comment where Comment_ThemeID_fk = '"+Theme_ID+"' and Comment_Content = '"+Comment_Content+"' ";
        db.rawQuery(sql,null);

        String sql_test="select Comment_Content from comment";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()) {
            Log.i("TAG","cursor.getString(0)="+cursor.getString(0));
        }
    }
}

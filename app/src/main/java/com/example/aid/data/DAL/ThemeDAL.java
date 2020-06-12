package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ThemeDAL {
    private DataBaseHelper dbhelper;
        private String[] forum_titles;
        String forum_content;
        String[] str_titles=new String[10];

    public ThemeDAL(Context context) {
            dbhelper = new DataBaseHelper(context);
            Log.v("tag","msg");
        }

        public String[] getAllTitles() throws SQLException {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            String sql = "select Theme_Content from theme";
            Cursor cursor = db.rawQuery(sql,null);
            int i=0;
            //cursor.moveToFirst();
            while (cursor.moveToNext()) {
                str_titles[i]=cursor.getString(0);
                i++;
                //Log.i("TAG","cursor.getString(0)="+cursor.getString(0));
            }
            cursor.close();
            forum_titles=str_titles;
        return forum_titles;
    }

    public String getContentByThemeID(int Theme_ID) throws SQLException {
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select Theme_Content from theme where Theme_ID = '"+Theme_ID+"' ";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            forum_content = cursor.getString(0);
        }
        cursor.close();
        return forum_content;

    }
}

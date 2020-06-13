package com.example.aid.ui.notifications;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aid.R;
import com.example.aid.data.DAL.DataBaseHelper;
import com.example.aid.data.DAL.UserDAL;
import com.example.aid.ui.dashboard.DashboardNewTaskReources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class markActivity extends AppCompatActivity {
    private List<Map<String, Object>> taskresources_list = new ArrayList<Map<String, Object>>();
    private String[] sponsor_list ;
    private String[] sponsor_time ;
    private String[] place ;
    private String[] time ;
    private String[] content ;
    private Button new_taskresources;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_mark);
        getTaskData();
        SimpleAdapter adapter = new SimpleAdapter(this
                , taskresources_list
                , R.layout.fragment_dashboard_task_item
                , new String[]{"name","place","time","content"}
                , new int[]{R.id.task_sponsor_detail,R.id.task_location_detail,R.id.task_task_time_detail,R.id.task_content_detail});

        final ListView listView = (ListView) findViewById(R.id.show_mark_list);
        listView.setAdapter(adapter);

        Button reviewd_button = (Button)findViewById(R.id.reviewd);
        reviewd_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                taskresources_list.clear();
                Toast.makeText(markActivity.this, "任务", Toast.LENGTH_LONG).show();
                getTaskData();
                SimpleAdapter adapter = new SimpleAdapter(markActivity.this
                        , taskresources_list
                        , R.layout.fragment_dashboard_task_item
                        , new String[]{"name","place","time","content"}
                        , new int[]{R.id.task_sponsor_detail,R.id.task_location_detail,R.id.task_task_time_detail,R.id.task_content_detail});

                ListView listView = (ListView) findViewById(R.id.show_mark_list);
                listView.setAdapter(adapter);
            }
        });

        Button review_button = (Button)findViewById(R.id.review);
        review_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                taskresources_list.clear();

                Toast.makeText(markActivity.this, "资源", Toast.LENGTH_LONG).show();
                getResourcesData();
                SimpleAdapter adapter = new SimpleAdapter(markActivity.this
                        , taskresources_list
                        , R.layout.fragment_dashboard_resources_item
                        , new String[]{"name_1","place_1","time_1","content_1"}
                        , new int[]{R.id.resources_sponsor_detail, R.id.resources_location_detail, R.id.resources_resources_time_detail, R.id.resources_content_detail});

                ListView listView = (ListView) findViewById(R.id.show_mark_list);
                listView.setAdapter(adapter);
            }
        });

    }

    private void getTaskData(){
        //call DBOpenHelper
        DataBaseHelper helper = new DataBaseHelper(getActivity());
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "select User_Name, Task_Place, Task_Time, Task_Content from user,task where Task_Type=1 and Task_CreatorID_fk = User_ID";
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        int iColCount = c.getColumnCount();
        int iNumber = 0;
        while (iNumber < c.getCount()){
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("name",  c.getString(c.getColumnIndex("User_Name")));
            //map.put("sponsor_time", c.getString(c.getColumnIndex("")));
            map.put("place", c.getString(c.getColumnIndex("Task_Place")));
            map.put("time", c.getString(c.getColumnIndex("Task_Time")));
            map.put("content", c.getString(c.getColumnIndex("Task_Content")));

            c.moveToNext();
            taskresources_list.add(map);
            iNumber++;
            System.out.println(taskresources_list);
        }
        c.close();
        db.close();
    }

    private void getResourcesData(){
        //call DBOpenHelper
        DataBaseHelper helper = new DataBaseHelper(getActivity());
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "select User_Name, Task_Place, Task_Time, Task_Content from user,task where Task_Type=2 and Task_CreatorID_fk = User_ID";
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        int iColCount = c.getColumnCount();
        int iNumber = 0;
        while (iNumber < c.getCount()){
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("name_1",  c.getString(c.getColumnIndex("User_Name")));
            //map.put("sponsor_time", c.getString(c.getColumnIndex("")));
            map.put("place_1", c.getString(c.getColumnIndex("Task_Place")));
            map.put("time_1", c.getString(c.getColumnIndex("Task_Time")));
            map.put("content_1", c.getString(c.getColumnIndex("Task_Content")));

            c.moveToNext();
            taskresources_list.add(map);
            iNumber++;
            System.out.println(taskresources_list);
        }
        c.close();
        db.close();
    }
}

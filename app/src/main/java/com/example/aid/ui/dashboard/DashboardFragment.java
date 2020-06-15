package com.example.aid.ui.dashboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.example.aid.R;
import com.example.aid.data.DAL.DataBaseHelper;
import com.example.aid.ui.notifications.identityEditActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardFragment<string> extends Fragment {

    private List<Map<String, Object>> taskresources_list = new ArrayList<Map<String, Object>>();
    private String[] sponsor_list ;
    private String[] sponsor_time ;
    private String[] place ;
    private String[] time ;
    private String[] content ;
    private AlertDialog alertDialog_AddRecord;//点击新建按钮时弹出提示框
    private Button new_taskresources,chat_with_sponsor;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View taskresources_view = inflater.inflate(R.layout.fragment_dashboard, container, false);

       /* sponsor_list = new String[] {"张三", "李四", "王五","hhhh"};
        time = new String[] {"2020-5-11-15:00", "2020-5-10-16:00","2020-4-7-20:00","2020-5-10-16:00"};

        for (int i = 0; i < sponsor_list.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", sponsor_list[i]);
            map.put("time", time[i]);
            taskresources_list.add(map);
        }*/

        getTaskData();
        SimpleAdapter adapter = new SimpleAdapter(getActivity()
                , taskresources_list
                , R.layout.fragment_dashboard_task_item
                , new String[]{"name","place","time","content"}
                , new int[]{R.id.task_sponsor_detail,R.id.task_location_detail,R.id.task_task_time_detail,R.id.task_content_detail});

        final ListView listView = (ListView) taskresources_view.findViewById(R.id.task_resources_list);
        listView.setAdapter(adapter);

        Button task_button = (Button)taskresources_view.findViewById(R.id.task_list);
        task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                taskresources_list.clear();

                //String[] forum_titles = new String[]{"张三", "李四", "王五","hsdaks"};
                Toast.makeText(getActivity(), "任务", Toast.LENGTH_LONG).show();
               /* for (int i = 0; i < forum_titles.length; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", forum_titles[i]);
                    taskresources_list.add(map);
                }*/

                getTaskData();
                SimpleAdapter adapter = new SimpleAdapter(getActivity()
                        , taskresources_list
                        , R.layout.fragment_dashboard_task_item
                        , new String[]{"name","place","time","content","id"}
                        , new int[]{R.id.task_sponsor_detail,R.id.task_location_detail,R.id.task_task_time_detail,R.id.task_content_detail,R.id.task_id})
                {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        final int p=position;
                        final View view=super.getView(position, convertView, parent);
                        Button useBtn=(Button)view.findViewById(R.id.chat_with_task_sponsor);
                        useBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), DashboardNewTaskReources.class);
                                startActivity(intent);
                            }
                        });
                        return view;
                    }
                };

                ListView listView = (ListView) taskresources_view.findViewById(R.id.task_resources_list);
                listView.setAdapter(adapter);
            }
        });

        Button resources_button = (Button)taskresources_view.findViewById(R.id.resources_list);
        resources_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                taskresources_list.clear();

                //String[] forum_titles = new String[]{"张三1", "张三2", "张三3","dhfaskfl"};
                Toast.makeText(getActivity(), "资源", Toast.LENGTH_LONG).show();
                /*for (int i = 0; i < forum_titles.length; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name_1", forum_titles[i]);
                    taskresources_list.add(map);
                }*/

                getResourcesData();
                SimpleAdapter adapter = new SimpleAdapter(getActivity()
                        , taskresources_list
                        , R.layout.fragment_dashboard_resources_item
                        , new String[]{"name_1","place_1","time_1","content_1"}
                        , new int[]{R.id.resources_sponsor_detail, R.id.resources_location_detail, R.id.resources_resources_time_detail, R.id.resources_content_detail});

                ListView listView = (ListView) taskresources_view.findViewById(R.id.task_resources_list);
                listView.setAdapter(adapter);
            }
        });



        Button new_taskresources = (Button)taskresources_view.findViewById(R.id.new_task_resources);

        //new_taskresources = (Button)taskresources_view.findViewById(R.id.new_task_resources);

        new_taskresources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DashboardNewTaskReources.class);
                startActivity(intent);
            }
        });

        return taskresources_view;

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
            map.put("id",  c.getString(c.getColumnIndex("Task_ID")));
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

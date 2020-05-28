package com.example.aid.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aid.R;
import com.example.aid.data1Activity;
import com.example.aid.ui.dashboard.DashboardNewTaskReources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
    private Button new_taskresources;
    public DashboardFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View taskresources_view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        sponsor_list = new String[] {"张三", "李四", "王五","hhhh"};
        time = new String[] {"2020-5-11-15:00", "2020-5-10-16:00","2020-4-7-20:00","2020-5-10-16:00"};

        for (int i = 0; i < sponsor_list.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", sponsor_list[i]);
            map.put("time", time[i]);
            taskresources_list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity()
                , taskresources_list
                , R.layout.fragment_dashboard_task_item
                , new String[]{"name","time"}
                , new int[]{R.id.task_sponsor_detail,R.id.task_time_detail});

        final ListView listView = (ListView) taskresources_view.findViewById(R.id.task_resources_list);
        listView.setAdapter(adapter);

        Button task_button = (Button)taskresources_view.findViewById(R.id.task_list);
        task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                taskresources_list.clear();

                String[] forum_titles = new String[]{"张三", "李四", "王五","hsdaks"};
                Toast.makeText(getActivity(), "任务", Toast.LENGTH_LONG).show();
                for (int i = 0; i < forum_titles.length; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", forum_titles[i]);
                    taskresources_list.add(map);
                }

                SimpleAdapter adapter = new SimpleAdapter(getActivity()
                        , taskresources_list
                        , R.layout.fragment_dashboard_task_item
                        , new String[]{"title"}
                        , new int[]{R.id.task_sponsor_detail});

                ListView listView = (ListView) taskresources_view.findViewById(R.id.task_resources_list);
                listView.setAdapter(adapter);
            }
        });

        Button resources_button = (Button)taskresources_view.findViewById(R.id.resources_list);
        resources_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                taskresources_list.clear();

                String[] forum_titles = new String[]{"张三1", "张三2", "张三3","dhfaskfl"};
                Toast.makeText(getActivity(), "资源", Toast.LENGTH_LONG).show();
                for (int i = 0; i < forum_titles.length; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", forum_titles[i]);
                    taskresources_list.add(map);
                }

                SimpleAdapter adapter = new SimpleAdapter(getActivity()
                        , taskresources_list
                        , R.layout.fragment_dashboard_resources_item
                        , new String[]{"title"}
                        , new int[]{R.id.resources_sponsor_detail});

                ListView listView = (ListView) taskresources_view.findViewById(R.id.task_resources_list);
                listView.setAdapter(adapter);
            }
        });

        new_taskresources = (Button)taskresources_view.findViewById(R.id.new_task_resources);
        new_taskresources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DashboardNewTaskReources.class);
                startActivity(intent);
            }
        });

        return taskresources_view;

    }

}

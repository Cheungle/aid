package com.example.aid.ui.notifications;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aid.R;
import com.example.aid.data.DAL.DataBaseHelper;
import com.example.aid.data.DAL.RVCDAL;
import com.example.aid.data.DAL.RVTDAL;
import com.example.aid.data.model.comment;
import com.example.aid.data.model.taskView;
import com.example.aid.ui.dashboard.DashboardNewTaskReources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class managerMineActivity extends AppCompatActivity {
    private List<Map<String, Object>> taskresources_list = new ArrayList<Map<String, Object>>();
    private String id;
    private String page_type;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_manager);
        this.id = getIntent().getStringExtra("id");
        if(getIntent().getStringExtra("type")!=null)this.page_type = getIntent().getStringExtra("type");
        if(this.page_type.equals("comment")){
            getReviewdComment();
            SimpleAdapter adapter = new SimpleAdapter(managerMineActivity.this
                    , taskresources_list
                    , R.layout.comment_reviewd
                    , new String[]{"id","name","theme","last","time","content","state"}
                    , new int[]{R.id.reviewd_comment_ID,R.id.reviewd_comment_sponsor_detail,R.id.reviewd_comment_theme_text,
                    R.id.reviewd_comment_last_detail, R.id.reviewd_comment_time_detail,R.id.reviewd_comment_content_detail,R.id.reviewd_comment_status_detail});

            final ListView listView = (ListView) findViewById(R.id.task_resources_list);
            listView.setAdapter(adapter);

            Button task_button = (Button)findViewById(R.id.reviewd_list);
            task_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    taskresources_list.clear();
                    getReviewdComment();
                    SimpleAdapter adapter = new SimpleAdapter(managerMineActivity.this
                            , taskresources_list
                            , R.layout.comment_reviewd
                            , new String[]{"id","name","theme","last","time","content","state"}
                            , new int[]{R.id.reviewd_comment_ID,R.id.reviewd_comment_sponsor_detail,R.id.reviewd_comment_theme_text,
                            R.id.reviewd_comment_last_detail, R.id.reviewd_comment_time_detail,R.id.reviewd_comment_content_detail,R.id.reviewd_comment_status_detail});

                    ListView listView = (ListView) findViewById(R.id.task_resources_list);
                    listView.setAdapter(adapter);
                }
            });

            Button resources_button = (Button)findViewById(R.id.review_list);
            resources_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    taskresources_list.clear();
                    getReviewComment();
                    MySimpleAdapter adapter = new MySimpleAdapter(managerMineActivity.this
                            ,id, taskresources_list
                            , R.layout.comment_review
                            , new String[]{"id","name","theme","last","time","content"}
                            , new int[]{R.id.review_comment_ID,R.id.review_comment_sponsor_detail,R.id.review_comment_theme_text,
                            R.id.review_comment_last_detail, R.id.review_comment_time_detail,R.id.review_comment_content_detail});

                    ListView listView = (ListView) findViewById(R.id.task_resources_list);
                    listView.setAdapter(adapter);
                }
            });

        }
        else{
            getReviewdData();
            SimpleAdapter adapter = new SimpleAdapter(managerMineActivity.this
                    , taskresources_list
                    , R.layout.reviewd_task
                    , new String[]{"id","name","type","place","time","content","state"}
                    , new int[]{R.id.reviewd_task_ID,R.id.reviewd_sponsor_detail,R.id.reviewd_type_text,
                    R.id.reviewd_location_detail,R.id.reviewd_task_time_detail,R.id.reviewd_content_detail,R.id.reviewd_status_detail});

            final ListView listView = (ListView) findViewById(R.id.task_resources_list);
            listView.setAdapter(adapter);

            Button task_button = (Button)findViewById(R.id.reviewd_list);
            task_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    taskresources_list.clear();
                    getReviewdData();
                    SimpleAdapter adapter = new SimpleAdapter(managerMineActivity.this
                            , taskresources_list
                            , R.layout.reviewd_task
                            , new String[]{"id","name","type","place","time","content","state"}
                            , new int[]{R.id.reviewd_task_ID,R.id.reviewd_sponsor_detail,R.id.reviewd_type_text,
                            R.id.reviewd_location_detail,R.id.reviewd_task_time_detail,R.id.reviewd_content_detail,R.id.reviewd_status_detail});

                    ListView listView = (ListView) findViewById(R.id.task_resources_list);
                    listView.setAdapter(adapter);
                }
            });

            Button resources_button = (Button)findViewById(R.id.review_list);
            resources_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    taskresources_list.clear();
                    getReviewData();
                    MySimpleAdapter adapter = new MySimpleAdapter(managerMineActivity.this
                            ,id, taskresources_list
                            , R.layout.review_task
                            , new String[]{"id","name","type","place","time","content"}
                            , new int[]{R.id.review_task_ID,R.id.review_sponsor_detail,R.id.review_type_text,
                            R.id.review_location_detail,R.id.review_task_time_detail,R.id.review_content_detail});

                    ListView listView = (ListView) findViewById(R.id.task_resources_list);
                    listView.setAdapter(adapter);
                }
            });

        }

    }

    private void getReviewdData(){
        RVTDAL rvtdal = new RVTDAL(managerMineActivity.this);
        ArrayList<taskView> t = rvtdal.selectRCTaskInfoByOne(this.id);
        int i = 0;
        while (i < t.size()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", t.get(i).getTask_ID());
            map.put("name",t.get(i).getTask_CreatorID_fk());
            if(t.get(i).getTask_Type()==1)
            {map.put("type","任务");}
            else{map.put("type","资源");}
            map.put("place", t.get(i).getTask_Place());
            map.put("time", t.get(i).getTask_Time());
            map.put("content", t.get(i).getTask_Content());
            if(t.get(i).getRVT_State()==1){ map.put("state", "审核通过"); }
            else {
                map.put("state", "审核失败");
            }
            taskresources_list.add(map);
            i++;
            //  System.out.println(taskresources_list);
        }
    }

    private void getReviewData(){
        RVTDAL rvtdal = new RVTDAL(managerMineActivity.this);
        ArrayList<taskView> t = rvtdal.selectWaitForReviewTask();
        int i = 0;
        while (i < t.size()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", t.get(i).getTask_ID());
            map.put("name",t.get(i).getTask_CreatorID_fk());
            if(t.get(i).getTask_Type()==1)
            {map.put("type","任务");}
            else{map.put("type","资源");}
            map.put("place", t.get(i).getTask_Place());
            map.put("time", t.get(i).getTask_Time());
            map.put("content", t.get(i).getTask_Content());
            taskresources_list.add(map);
            i++;
            //  System.out.println(taskresources_list);
        }
    }
    private void getReviewdComment(){
        RVCDAL rvcdal = new RVCDAL(managerMineActivity.this);
        ArrayList<comment> t = rvcdal.selectRVCommentByOne(this.id);
        int i = 0;
        while (i < t.size()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", t.get(i).getID());
            map.put("name",t.get(i).getSource());
            map.put("last",t.get(i).getPreCmmtID());
            map.put("theme", t.get(i).getThemeID());
            map.put("time", t.get(i).getPublishTime());
            map.put("content", t.get(i).getContent());
            if(t.get(i).getState()==1){ map.put("state", "审核通过"); }
            else {
                map.put("state", "审核失败");
            }
            taskresources_list.add(map);
            i++;
            //  System.out.println(taskresources_list);
        }
    }

    private void getReviewComment(){
        RVCDAL rvcdal = new RVCDAL(managerMineActivity.this);
        ArrayList<comment> t = rvcdal.selectWaitForReviewComment();
        int i = 0;
        while (i < t.size()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", t.get(i).getID());
            map.put("name",t.get(i).getSource());
            map.put("last",t.get(i).getPreCmmtID());
            map.put("theme", t.get(i).getThemeID());
            map.put("time", t.get(i).getPublishTime());
            map.put("content", t.get(i).getContent());
            taskresources_list.add(map);
            i++;
            //  System.out.println(taskresources_list);
        }
    }
}

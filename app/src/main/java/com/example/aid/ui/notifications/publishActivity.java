package com.example.aid.ui.notifications;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aid.R;
import com.example.aid.data.DAL.MarkDAL;
import com.example.aid.data.DAL.TaskDAL;
import com.example.aid.data.model.taskView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class publishActivity extends AppCompatActivity {

    private List<Map<String, Object>> taskresources_list = new ArrayList<Map<String, Object>>();
    private String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_mark);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.id = getIntent().getStringExtra("id");
        try {
            publishActivity.this.getPublishData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MySimpleAdapter adapter = new MySimpleAdapter(this
                ,this.id, taskresources_list
                , R.layout.mine_publish_item
                , new String[]{"id","type","place","time","content","rece","finish","review"}
                , new int[]{R.id.pub_task_ID,R.id.pub_type_text,R.id.pub_location_detail, R.id.pub_task_time_detail,
                R.id.pub_content_detail,R.id.pub_status_detail,R.id.pub_recipient_detail,R.id.pub_review_detail});

        final ListView listView = (ListView) findViewById(R.id.show_mark_list);
        listView.setAdapter(adapter);
    }

    private void getPublishData() throws SQLException {
        TaskDAL taskDAL = new TaskDAL(publishActivity.this);
        ArrayList<taskView> t = taskDAL.selectTaskInfoByOne(publishActivity.this.id);
        int i = 0;
        while (i < t.size()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", t.get(i).getTask_ID());
            if(t.get(i).getTask_Type()==1)
            {map.put("type","任务");}
            else{map.put("type","资源");}
            map.put("place", t.get(i).getTask_Place());
            map.put("time", t.get(i).getTask_Time());
            map.put("content", t.get(i).getTask_Content());
            map.put("rece", t.get(i).getRCT_ReceiverID_fk());
            map.put("finish", t.get(i).getCT_CompletedTime());
            if(t.get(i).getRVT_State()==1){ map.put("review", "审核通过"); }
            else {
                if(t.get(i).getRVT_State()==2){ map.put("review", "审核失败"); }
                else{ map.put("review", "待审核"); }
            }
            taskresources_list.add(map);
            i++;
            //  System.out.println(taskresources_list);
        }

    }
}

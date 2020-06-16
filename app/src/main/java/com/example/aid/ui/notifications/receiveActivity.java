package com.example.aid.ui.notifications;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aid.R;
import com.example.aid.data.DAL.TaskDAL;
import com.example.aid.data.model.taskView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class receiveActivity extends AppCompatActivity {
    private List<Map<String, Object>> taskresources_list = new ArrayList<Map<String, Object>>();
    private String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_mark);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.id = getIntent().getStringExtra("id");
        try {
            receiveActivity.this.getReclishData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MySimpleAdapter adapter = new MySimpleAdapter(this
                , this.id,taskresources_list
                , R.layout.receive_item
                , new String[]{"id","pub","type","place","time","content","finish"}
                , new int[]{R.id.rec_task_ID,R.id.rec_sponsor_detail,R.id.rec_type_text,R.id.rec_location_detail, R.id.rec_task_time_detail,
                R.id.rec_content_detail,R.id.rec_recipient_detail});

        final ListView listView = (ListView) findViewById(R.id.show_mark_list);
        listView.setAdapter(adapter);
    }

    private void getReclishData() throws SQLException {
        TaskDAL taskDAL = new TaskDAL(receiveActivity.this);
        ArrayList<taskView> t = taskDAL.selectTaskInfoByOne(receiveActivity.this.id);
        int i = 0;
        while (i < t.size()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", t.get(i).getTask_ID());
            map.put("pub", t.get(i).getTask_CreatorID_fk());
            if(t.get(i).getTask_Type()==1)
            {map.put("type","任务");}
            else{map.put("type","资源");}
            map.put("place", t.get(i).getTask_Place());
            map.put("time", t.get(i).getTask_Time());
            map.put("content", t.get(i).getTask_Content());
            map.put("finish", t.get(i).getCT_CompletedTime());
            taskresources_list.add(map);
            i++;
            //  System.out.println(taskresources_list);
        }

    }
}

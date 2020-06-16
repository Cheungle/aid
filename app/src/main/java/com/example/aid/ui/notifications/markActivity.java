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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aid.R;
import com.example.aid.data.DAL.DataBaseHelper;
import com.example.aid.data.DAL.MarkDAL;
import com.example.aid.data.DAL.UserDAL;
import com.example.aid.data.model.task;
import com.example.aid.data.model.taskView;
import com.example.aid.ui.dashboard.DashboardNewTaskReources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class markActivity extends AppCompatActivity {
    private List<Map<String, Object>> taskresources_list = new ArrayList<Map<String, Object>>();
    private String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_mark);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.id = getIntent().getStringExtra("id");
        try {
            markActivity.this.getMarkData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MySimpleAdapter adapter = new MySimpleAdapter(this
                ,this.id, taskresources_list
                , R.layout.mark_task
                , new String[]{"id","creator","name","type","place","time","content","rece","finish"}
                , new int[]{R.id.mark_task_ID,R.id.mark_chat_ID,R.id.mark_sponsor_detail,R.id.mark_type_text,R.id.mark_location_detail,
                R.id.mark_task_time_detail,R.id.mark_content_detail,R.id.mark_status_detail,R.id.mark_recipient_detail});

        final ListView listView = (ListView) findViewById(R.id.show_mark_list);
        listView.setAdapter(adapter);

    }

    private void getMarkData() throws SQLException {
        MarkDAL markDAL = new MarkDAL(markActivity.this);
        ArrayList<taskView> t = markDAL.selectMarkByOne(markActivity.this.id);
        int i = 0;
        while (i < t.size()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", t.get(i).getTask_ID());
            map.put("creator",  t.get(i).getTask_CreatorID_fk());
            UserDAL userDAL = new UserDAL(this);
            String name = userDAL.selectName(t.get(i).getTask_CreatorID_fk());
            map.put("name",  name);
            if(t.get(i).getTask_Type()==1)
            {map.put("type","任务");}
            else{map.put("type","资源");}
            map.put("place", t.get(i).getTask_Place());
            map.put("time", t.get(i).getTask_Time());
            map.put("content", t.get(i).getTask_Content());
            map.put("rece", t.get(i).getRCT_ReceiverID_fk());
            map.put("finish", t.get(i).getCT_CompletedTime());
            taskresources_list.add(map);
            i++;
          //  System.out.println(taskresources_list);
        }

    }

}

package com.example.aid.ui.notifications;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aid.R;
import com.example.aid.data.DAL.TaskDAL;
import com.example.aid.data.DAL.ThemeDAL;
import com.example.aid.data.model.taskView;
import com.example.aid.data.model.theme;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class themeActivity extends AppCompatActivity {
    private List<Map<String, Object>> taskresources_list = new ArrayList<Map<String, Object>>();
    private String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_mark);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.id = getIntent().getStringExtra("id");
        try {
            themeActivity.this.getPublishData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SimpleAdapter adapter = new SimpleAdapter(this
                , taskresources_list
                , R.layout.mine_theme_item
                , new String[]{"id","time","content"}
                , new int[]{R.id.theme_ID,R.id.theme_time_text,R.id.theme_content_detail});

        final ListView listView = (ListView) findViewById(R.id.show_mark_list);
        listView.setAdapter(adapter);
    }

    private void getPublishData() throws SQLException {
        ThemeDAL themeDAL = new ThemeDAL(themeActivity.this);
        ArrayList<theme> t= themeDAL.selectThemeByOne(this.id);
        int i = 0;
        while (i < t.size()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", t.get(i).getID());
            map.put("content", t.get(i).getContent());
            map.put("time", t.get(i).getTime());
            taskresources_list.add(map);
            i++;
            //  System.out.println(taskresources_list);
        }

    }
}

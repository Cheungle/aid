package com.example.aid.ui.forum;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForumContentActivity extends AppCompatActivity {
    private String[] c_user_names = {"龙宝", "阿轩"};
    private String[] comments = {"#####","*****"};
    private String[] comment_times = {"05-18 20:49","05-18 21:36"};

    List<Map<String, Object>> fc_list = new ArrayList<Map<String, Object>>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_forum_content);

        Bundle bundle=getIntent().getExtras();

        for (int i = 0; i < comments.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("c_name", c_user_names[i]);
            map.put("comment", comments[i]);
            map.put("time", comment_times[i]);
            fc_list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this
                , fc_list
                , R.layout.fragment_fclv_item
                , new String[]{"c_name","comment","time"}
                , new int[]{R.id.c_user_name, R.id.comment, R.id.comment_time});

        ListView listView = (ListView) findViewById(R.id.fc_lv);
        listView.setAdapter(adapter);


    }
}

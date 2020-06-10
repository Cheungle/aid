package com.example.aid.ui.forum;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aid.R;
import com.example.aid.data.DAL.ThemeDAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForumActivity extends AppCompatActivity {
    private List<Map<String, Object>> forum_list = new ArrayList<Map<String, Object>>();
    private ThemeDAL themeDAL=new ThemeDAL(ForumActivity.this);
    public String[] forum_titles= themeDAL.getAllTitles() ;//= {"英国民众上街抗议", "疫情催涨日本虐童案", "高校复学2.6万学生做心理测试"};


    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_forum);

        //forum_titles = new String[] {"英国民众上街抗议", "疫情催涨日本虐童案", "高校复学2.6万学生做心理测试"};
        //forum_titles = themeDAL.getAllTitles();

        for (int i = 0; i < forum_titles.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", forum_titles[i]);
            forum_list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this
                , forum_list
                , R.layout.fragment_flv_item
                , new String[]{"title"}
                , new int[]{R.id.forum_title});

        final ListView listView = (ListView) findViewById(R.id.forum_lv);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置监听器
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (id == 0) {

                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(ForumActivity.this, ForumContentActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        Button medicine_button = (Button) findViewById(R.id.forum_medicine_bt);
        medicine_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                forum_list.clear();

                String[] forum_titles = new String[]{"中国新冠疫苗作为全球公共产品", "我国四个新冠灭活疫苗开展临床试验", "牛津新冠疫苗遭质疑"};
                Toast.makeText(ForumActivity.this, "药物", Toast.LENGTH_LONG).show();
                for (int i = 0; i < forum_titles.length; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", forum_titles[i]);
                    forum_list.add(map);
                }

                SimpleAdapter adapter = new SimpleAdapter(ForumActivity.this
                        , forum_list
                        , R.layout.fragment_flv_item
                        , new String[]{"title"}
                        , new int[]{R.id.forum_title});

                ListView listView = (ListView) findViewById(R.id.forum_lv);
                listView.setAdapter(adapter);
            }
        });

        Button others_button = (Button) findViewById(R.id.forum_others_bt);
        others_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                forum_list.clear();

                String[] forum_titles = new String[]{"疫苗刺激美股大涨", "上海公布中小学放暑假时间", "武汉欢乐谷重新开业"};
                Toast.makeText(ForumActivity.this, "其他", Toast.LENGTH_LONG).show();
                for (int i = 0; i < forum_titles.length; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", forum_titles[i]);
                    forum_list.add(map);
                }

                SimpleAdapter adapter = new SimpleAdapter(ForumActivity.this
                        , forum_list
                        , R.layout.fragment_flv_item
                        , new String[]{"title"}
                        , new int[]{R.id.forum_title});

                ListView listView = (ListView) findViewById(R.id.forum_lv);
                listView.setAdapter(adapter);
            }
        });

        Button people_button = (Button) findViewById(R.id.forum_people_bt);
        people_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                forum_list.clear();

                String[] forum_titles = new String[]{"英国民众上街抗议", "疫情催涨日本虐童案", "高校复学2.6万学生做心理测试"};
                Toast.makeText(ForumActivity.this, "人员", Toast.LENGTH_LONG).show();
                for (int i = 0; i < forum_titles.length; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", forum_titles[i]);
                    forum_list.add(map);
                }

                SimpleAdapter adapter = new SimpleAdapter(ForumActivity.this
                        , forum_list
                        , R.layout.fragment_flv_item
                        , new String[]{"title"}
                        , new int[]{R.id.forum_title});

                ListView listView = (ListView) findViewById(R.id.forum_lv);
                listView.setAdapter(adapter);
            }
        });

    }*/

}

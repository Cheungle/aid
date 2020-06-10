package com.example.aid.ui.forum;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aid.R;
import com.example.aid.data.DAL.CommentDAL;
import com.example.aid.data.DAL.ThemeDAL;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForumContentActivity extends AppCompatActivity {
    private CommentDAL commentDAL=new CommentDAL(ForumContentActivity.this);
    private ThemeDAL themeDAL=new ThemeDAL(ForumContentActivity.this);
    private String Theme_Title;
    private String Theme_Content;
    private String[] c_user_names = {"龙宝", "阿轩"};
    private String[] comments = {"#####","*****"};
    private String[] comment_times = {"05-18 20:49","05-18 21:36"};
    //private String[] comments;
    private String[] tool = {"1","2"};
    int id;

    List<Map<String, Object>> fc_list = new ArrayList<Map<String, Object>>();


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_forum_content);

        Bundle bundle=getIntent().getExtras();
        id = bundle.getInt("id");
        id++;
        //comments=commentDAL.getAllComments();

        fc_list=commentDAL.getCommentsByThemeID(id);
        TextView tv_title=findViewById(R.id.ft);
        TextView tv_content=findViewById(R.id.fc);
        tv_title.setText(themeDAL.getContentByThemeID(id));
        tv_content.setText(themeDAL.getContentByThemeID(id));

        /*
        for (int i = 0; i < tool.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("c_name", c_user_names[i]);
            map.put("comment", comments[i]);
            map.put("time", comment_times[i]);
            fc_list.add(map);
            }
        */

        SimpleAdapter adapter = new SimpleAdapter(this
                , fc_list
                , R.layout.fragment_fclv_item
                , new String[]{"c_name","comment","time"}
                , new int[]{R.id.c_user_name, R.id.comment, R.id.comment_time});

        ListView listView = (ListView) findViewById(R.id.fc_lv);
        listView.setAdapter(adapter);

        Button comment_button = (Button)findViewById(R.id.comment_button);
        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText et_comment = findViewById(R.id.comment_edit);
                final String e_comment = et_comment.getText().toString();

                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String date=sdf.format(new java.util.Date());

                commentDAL.insertComment(e_comment,date,
                        "15186861111",id);

                fc_list.clear();

                fc_list=commentDAL.getCommentsByThemeID(id);

                SimpleAdapter adapter = new SimpleAdapter(ForumContentActivity.this
                        , fc_list
                        , R.layout.fragment_fclv_item
                        , new String[]{"c_name","comment","time"}
                        , new int[]{R.id.c_user_name, R.id.comment, R.id.comment_time});

                ListView listView = (ListView) findViewById(R.id.fc_lv);
                listView.setAdapter(adapter);
            }
        });


    }
}

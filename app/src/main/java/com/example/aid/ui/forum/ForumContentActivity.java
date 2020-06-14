package com.example.aid.ui.forum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
import java.util.zip.Inflater;

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
    private String user_id;
    int id;

    List<Map<String, Object>> fc_list = new ArrayList<Map<String, Object>>();


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_forum_content);

        Bundle bundle=getIntent().getExtras();
        id = bundle.getInt("id");
        id++;

        user_id=bundle.getString("user_id");
        //Log.i("user_id=",user_id);
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

        /*SimpleAdapter adapter = new SimpleAdapter(this
                , fc_list
                , R.layout.fragment_fclv_item
                , new String[]{"c_name","comment","time"}
                , new int[]{R.id.c_user_name, R.id.comment, R.id.comment_time});*/

        ListView listView = (ListView) findViewById(R.id.fc_lv);
        commentAdapter adapter = new commentAdapter(ForumContentActivity.this,fc_list);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        Button comment_button = (Button)findViewById(R.id.comment_button);
        EditText et_comment= findViewById(R.id.comment_edit);
        if(user_id.length() == 12) {
            comment_button.setVisibility(comment_button.GONE);
            et_comment.setVisibility(et_comment.GONE);
        }
        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText et_comment = findViewById(R.id.comment_edit);
                final String e_comment = et_comment.getText().toString();

                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String date=sdf.format(new java.util.Date());

                commentDAL.insertComment(e_comment,date,
                        user_id,id);

                fc_list.clear();

                fc_list=commentDAL.getCommentsByThemeID(id);

                /*SimpleAdapter adapter = new SimpleAdapter(ForumContentActivity.this
                        , fc_list
                        , R.layout.fragment_fclv_item
                        , new String[]{"c_name","comment","time"}
                        , new int[]{R.id.c_user_name, R.id.comment, R.id.comment_time});*/

                ListView listView = (ListView) findViewById(R.id.fc_lv);
                commentAdapter adapter = new commentAdapter(ForumContentActivity.this,fc_list);
                listView.setAdapter(adapter);

            }
        });


    }

    class commentAdapter extends BaseAdapter {
        List<Map<String, Object>> mlist;
        Context mContext;

        public commentAdapter(Context context, List<Map<String, Object>> list) {
            mlist = list;
            mContext = context;
        }

        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public Object getItem(int i) {
            return mlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        ViewHolder mHolder;

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            mHolder = new ViewHolder();
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.fragment_fclv_item, null);
                mHolder.tv_name = (TextView) view.findViewById(R.id.c_user_name);
                mHolder.tv_content = (TextView) view.findViewById(R.id.comment);
                mHolder.tv_time = (TextView) view.findViewById(R.id.comment_time);
                mHolder.btn_reply = view.findViewById(R.id.reply_button);
                mHolder.btn_delete = view.findViewById(R.id.delete_button);
                view.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) view.getTag();
            }
            if (user_id.length() == 11) {
                mHolder.btn_delete.setVisibility(View.GONE);
                mHolder.btn_reply.setVisibility(View.GONE);
            }
            if (user_id.length() == 12) {
                mHolder.btn_reply.setVisibility(View.GONE);
            }
            //如果没有这些内容，将会显示布局文件中的内容
            mHolder.tv_name.setText(mlist.get(position).get("c_name").toString());
            mHolder.tv_content.setText(mlist.get(position).get("comment").toString());
            mHolder.tv_time.setText(mlist.get(position).get("time").toString());
            //头像的点击事件并传值
            mHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    commentDAL.deleteComment(id,mlist.get(position).get("comment").toString());
                    Toast.makeText(ForumContentActivity.this, "删除成功", Toast.LENGTH_LONG).show();
                }
            });
            return view;
        }

        class ViewHolder {
            TextView tv_name;
            TextView tv_content;
            TextView tv_time;
            Button btn_reply;
            Button btn_delete;
        }
    }
}




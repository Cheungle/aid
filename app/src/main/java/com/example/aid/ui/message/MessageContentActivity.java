package com.example.aid.ui.message;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aid.R;
import com.example.aid.data.DAL.MessageDAL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageContentActivity extends AppCompatActivity {

    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private MsgAdapter adapter;
    private String user_id;
    private String opposite_name;
    private MessageDAL messageDAL = new MessageDAL(MessageContentActivity.this);
    int id;
    List<Map<String, Object>> message_list = new ArrayList<Map<String, Object>>();
    private List<Msg> msgList = new ArrayList<Msg>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.fragment_message_content);

        Bundle bundle=getIntent().getExtras();
        id = bundle.getInt("id");
        id++;
        user_id=bundle.getString("user_id");
        opposite_name=bundle.getString("opposite_name");

        TextView tv_name= findViewById(R.id.m_user_name);
        tv_name.setText(opposite_name);

        initMsgs();
        adapter = new MsgAdapter(MessageContentActivity.this, R.layout.fragment_mclv_item, msgList);
        inputText = (EditText)findViewById(R.id.input_message);
        send = (Button)findViewById(R.id.send);
        msgListView = (ListView)findViewById(R.id.mc_lv);
        msgListView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String content = inputText.getText().toString();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String date=sdf.format(new java.util.Date());
                if(!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SEND);
                    msgList.add(msg);
                    messageDAL.insertMessage(content,user_id,date,id);
                    adapter.notifyDataSetChanged();
                    msgListView.setSelection(msgList.size());
                    inputText.setText("");
                }

                //msgList.clear();
                //initMsgs();
                //adapter = new MsgAdapter(MessageContentActivity.this, R.layout.fragment_mclv_item, msgList);
                //msgListView = (ListView)findViewById(R.id.mc_lv);
                //msgListView.setAdapter(adapter);
            }
        });
    }

    private void initMsgs() {
        message_list.clear();
        //message_list = messageDAL.getMessage_Contents(user_id,opposite_name);
        message_list = messageDAL.getMessage_Contents();
        //Log.i("msg",message_list.get(1).get("message_type").toString());
        //Log.i("msg",message_list.get(2).toString());
        for (int i = 0; i < message_list.size(); i++) {
            if (message_list.get(i).get("message_type").toString().equals(user_id)) {
                Log.i("msg", "send");
                Msg msg = new Msg(message_list.get(i).get("message_content").toString(), Msg.TYPE_SEND);
                msgList.add(msg);
            } else {
                Log.i("msg", "receive");
                Msg msg = new Msg(message_list.get(i).get("message_content").toString(), Msg.TYPE_RECEIVED);
                msgList.add(msg);
            }
            Log.i("msgList=", msgList.get(i).getContent());
        }
        messageDAL.getAllMessage();
/*
        Msg msg1 = new Msg("Hello, how are you?", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Fine, thank you, and you?", Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg("I am fine, too!", Msg.TYPE_RECEIVED);
        msgList.add(msg3);

 */
    }
}

package com.example.aid.ui.message;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.aid.R;
import com.example.aid.data.DAL.DataBaseHelper;
import com.example.aid.data.DAL.MWDAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageFragment extends Fragment {
    /*
    private MessageViewModel messageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        messageViewModel =
                ViewModelProviders.of(this).get(MessageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_message, container, false);
        final TextView textView = root.findViewById(R.id.text_message);
        messageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    */
    private MWDAL mwdal=new MWDAL(this.getContext());
    private DataBaseHelper dbhelper;
    private SQLiteDatabase db;
    private List<Map<String,Object>> mw_list=new ArrayList<Map<String, Object>>();
    private int[] user_images={R.mipmap.ic_message};//{R.mipmap.ic_forum, R.mipmap.ic_message};
    private String[] user_names;//={"龙宝","阿轩"};
    private String[] user_last_messages;//={"hhhhh"};//{"螺蛳粉", "猫咪图片"};
    private String[] message_contents;
    String[] str_names=new String[10];
    String[] str_contents=new String[10];


    public MessageFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View mw_view = inflater.inflate(R.layout.fragment_message, container, false);

        getUser_Names("15186861111");
        message_contents=getMessagesByUserIDs("15186861111","15186862222");
        Log.i("TAG","message_contents="+message_contents);
        user_last_messages= new String[]{message_contents[1]}; //Arrays.copyOfRange(message_contents,message_contents.length-2,message_contents.length-1);
        Log.i("TAG","user_last_message="+user_last_messages);


        for(int j=0; j<user_images.length; j++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("image",user_images[j]);
            map.put("name",user_names[j]);
            map.put("last_message",user_last_messages[j]);
            mw_list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity()
                , mw_list
                , R.layout.fragment_mwlv_item
                , new String[]{"image", "name", "last_message"}
                , new int[]{R.id.user_image, R.id.user_name, R.id.user_last_message});

        final ListView listView = (ListView) mw_view.findViewById(R.id.mw_lv);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置监听器
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(), map.get("title").toString(), Toast.LENGTH_LONG).show();*/

                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction ft=fragmentManager.beginTransaction();

                if(id == 0) {
                    /*
                    Fragment fragment=new ForumContentFragment();
                    ft.replace(R.layout.fragment_forum,fragment);
                    ft.commit();
                     */
                    Bundle bundle = new Bundle();
                    Intent intent= new Intent(getActivity(), MessageContentActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        return mw_view;
    }

 /*   @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);


        //getUser_Names("15186861111");

        for(int j=0; j<user_images.length; j++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("image",user_images[j]);
            map.put("name",user_names[j]);
            map.put("last_message",user_last_messages[j]);
            mw_list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity()
                , mw_list
                , R.layout.fragment_mwlv_item
                , new String[]{"image", "name", "last_message"}
                , new int[]{R.id.user_image, R.id.user_name, R.id.user_last_message});

        final ListView listView = (ListView) getActivity().findViewById(R.id.mw_lv);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置监听器
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction ft=fragmentManager.beginTransaction();

                if(id == 0) {

                    Bundle bundle = new Bundle();
                    Intent intent= new Intent(getActivity(), MessageContentActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

    }*/

    public void getUser_Names(String User_ID) throws SQLException{
        dbhelper=new DataBaseHelper(getActivity());
        db=dbhelper.getReadableDatabase();
        String sql = "select user.User_Name from user,messagewindow where user.User_ID=messagewindow.MW_UserID2_fk" +
                " and messagewindow.MW_UserID1_fk='"+User_ID+"' ";
        Cursor cursor = db.rawQuery(sql,null);
        int i=0;
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            str_names[i]=cursor.getString(0);
            i++;
            Log.i("TAG","cursor.getString(0)="+cursor.getString(0));
        }
        cursor.close();
        user_names=str_names;
    }

    public String[] getMessagesByUserIDs(String User1_ID, String User2_ID) throws SQLException{
        dbhelper=new DataBaseHelper(getActivity());
        db=dbhelper.getReadableDatabase();
        String sql = "select message.Message_Content from message,messagewindow where message.Message_WindowID_fk=messagewindow.MW_ID" +
                " and messagewindow.MW_UserID1_fk='"+User1_ID+"' and messagewindow.MW_UserID2_fk='"+User2_ID+"' ";
        Cursor cursor = db.rawQuery(sql,null);
        int i=0;
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            str_contents[i]=cursor.getString(0);
            i++;
            Log.i("TAG","cursor.getString(0)="+cursor.getString(0));
        }
        cursor.close();
        return str_contents;
    }

}

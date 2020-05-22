package com.example.aid.ui.message;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
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

    private int[] user_images={R.mipmap.ic_forum, R.mipmap.ic_message};
    private String[] user_names={"龙宝","阿轩"};
    private String[] user_last_messages={"螺蛳粉", "猫咪图片"};

    List<Map<String,Object>> mw_list=new ArrayList<Map<String, Object>>();

    public MessageFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View mw_view = inflater.inflate(R.layout.fragment_message, container, false);
        for(int i=0;i<user_names.length ;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("image",user_images[i]);
            map.put("name",user_names[i]);
            map.put("last_message",user_last_messages[i]);
            mw_list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity()
                , mw_list
                , R.layout.fragment_mwlv_item
                , new String[]{"image", "name", "last_message"}
                , new int[]{R.id.user_image, R.id.user_name, R.id.user_last_message});

        ListView listView = (ListView) mw_view.findViewById(R.id.mw_lv);
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











}

package com.example.aid.ui.forum;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.aid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForumContentFragment extends Fragment {
    private String[] c_user_names = {"龙宝", "阿轩"};
    private String[] comments = {"#####","*****"};
    private String[] comment_times = {"05-18 20:49","05-18 21:36"};

    List<Map<String, Object>> fc_list = new ArrayList<Map<String, Object>>();

    public ForumContentFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View fc_view = inflater.inflate(R.layout.fragment_forum_content, container, false);
        for (int i = 0; i < comments.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("c_name", c_user_names[i]);
            map.put("comment", comments[i]);
            map.put("time", comment_times[i]);
            fc_list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity()
                , fc_list
                , R.layout.fragment_fclv_item
                , new String[]{"c_name","comment","time"}
                , new int[]{R.id.c_user_name,R.id.comment,R.id.comment_time});

        ListView listView = (ListView) fc_view.findViewById(R.id.fc_lv);
        listView.setAdapter(adapter);

        return fc_view;

    }

}

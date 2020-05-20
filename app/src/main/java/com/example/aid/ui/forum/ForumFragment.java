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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.aid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ForumFragment<string> extends Fragment {
/*
    private ForumViewModel forumViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forumViewModel =
                ViewModelProviders.of(this).get(ForumViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum, container, false);
        final TextView textView = root.findViewById(R.id.text_forum);
        forumViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
 */

    private List<Map<String, Object>> forum_list = new ArrayList<Map<String, Object>>();
    private String[] forum_titles ;//= {"英国民众上街抗议", "疫情催涨日本虐童案", "高校复学2.6万学生做心理测试"};


    public ForumFragment() {
        // Required empty public constructor
    }

/*
    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button people_button = (Button) requireActivity().findViewById(R.id.forum_people_bt);
        people_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forum_titles = new String[]{"英国民众上街抗议", "疫情催涨日本虐童案", "高校复学2.6万学生做心理测试"};

            }
        });

        Button medicine_button = (Button) getActivity().findViewById(R.id.forum_medicine_bt);
        medicine_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forum_titles = new String[]{"中国新冠疫苗作为全球公共产品", "我国四个新冠灭活疫苗开展临床试验", "牛津新冠疫苗遭质疑"};
            }
        });

        Button others_button = (Button) getActivity().findViewById(R.id.forum_others_bt);
        others_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forum_titles = new String[]{"疫苗刺激美股大涨", "上海公布中小学放暑假时间", "武汉欢乐谷重新开业"};
            }
        });

    }
*/
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View forum_view = inflater.inflate(R.layout.fragment_forum, container, false);

        forum_titles = new String[] {"英国民众上街抗议", "疫情催涨日本虐童案", "高校复学2.6万学生做心理测试"};

        for (int i = 0; i < forum_titles.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", forum_titles[i]);
            forum_list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity()
                , forum_list
                , R.layout.fragment_flv_item
                , new String[]{"title"}
                , new int[]{R.id.forum_title});

        final ListView listView = (ListView) forum_view.findViewById(R.id.forum_lv);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置监听器
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(), map.get("title").toString(), Toast.LENGTH_LONG).show();*/

                /*FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction ft=fragmentManager.beginTransaction();*/

                if(id == 0) {
                    /*
                    Fragment fragment=new ForumContentFragment();
                    ft.replace(R.layout.fragment_forum,fragment);
                    ft.commit();
                     */
                    Bundle bundle = new Bundle();
                    Intent intent= new Intent(getActivity(),ForumContentActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        Button medicine_button = (Button)forum_view.findViewById(R.id.forum_medicine_bt);
        medicine_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                forum_list.clear();

                String[] forum_titles = new String[]{"中国新冠疫苗作为全球公共产品", "我国四个新冠灭活疫苗开展临床试验", "牛津新冠疫苗遭质疑"};
                Toast.makeText(getActivity(), "药物", Toast.LENGTH_LONG).show();
                for (int i = 0; i < forum_titles.length; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", forum_titles[i]);
                    forum_list.add(map);
                }

                SimpleAdapter adapter = new SimpleAdapter(getActivity()
                        , forum_list
                        , R.layout.fragment_flv_item
                        , new String[]{"title"}
                        , new int[]{R.id.forum_title});

                ListView listView = (ListView) forum_view.findViewById(R.id.forum_lv);
                listView.setAdapter(adapter);
            }
        });

        Button others_button = (Button)forum_view.findViewById(R.id.forum_others_bt);
        others_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                forum_list.clear();

                String[] forum_titles = new String[]{"疫苗刺激美股大涨", "上海公布中小学放暑假时间", "武汉欢乐谷重新开业"};
                Toast.makeText(getActivity(), "其他", Toast.LENGTH_LONG).show();
                for (int i = 0; i < forum_titles.length; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", forum_titles[i]);
                    forum_list.add(map);
                }

                SimpleAdapter adapter = new SimpleAdapter(getActivity()
                        , forum_list
                        , R.layout.fragment_flv_item
                        , new String[]{"title"}
                        , new int[]{R.id.forum_title});

                ListView listView = (ListView) forum_view.findViewById(R.id.forum_lv);
                listView.setAdapter(adapter);
            }
        });

        Button people_button = (Button)forum_view.findViewById(R.id.forum_people_bt);
        people_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                forum_list.clear();

                String[] forum_titles = new String[]{"英国民众上街抗议", "疫情催涨日本虐童案", "高校复学2.6万学生做心理测试"};
                Toast.makeText(getActivity(), "人员", Toast.LENGTH_LONG).show();
                for (int i = 0; i < forum_titles.length; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", forum_titles[i]);
                    forum_list.add(map);
                }

                SimpleAdapter adapter = new SimpleAdapter(getActivity()
                        , forum_list
                        , R.layout.fragment_flv_item
                        , new String[]{"title"}
                        , new int[]{R.id.forum_title});

                ListView listView = (ListView) forum_view.findViewById(R.id.forum_lv);
                listView.setAdapter(adapter);
            }
        });

        return forum_view;

    }

}

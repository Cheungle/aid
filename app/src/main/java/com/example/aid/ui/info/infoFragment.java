package com.example.aid.ui.info;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.aid.MainActivity;
import com.example.aid.R;
import com.example.aid.data1Activity;
import com.example.aid.detailActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class infoFragment extends Fragment {
    private Button mBtntitle1;
    private Button mBtntitle2;
    private Spinner mspinner;
    private String zhye;
    private ArrayAdapter<String> adapter;

    private String[] Title={"人民日报","央视新闻","人民日报","澎湃新闻"};

    private int[] Images={R.drawable.ic_dashboard_black_24dp,R.drawable.ic_home_black_24dp,R.drawable.ic_notifications_black_24dp,R.drawable.ic_dashboard_black_24dp};
    private int[] Images1={R.drawable.num1,R.drawable.ic_home_black_24dp,R.drawable.ic_notifications_black_24dp,R.drawable.ic_dashboard_black_24dp};
    private String[] Content={"【最新数据：全球新冠肺炎超415万例】截至北京时间5月12日18时，214个国家和地区累计确诊4154730例，“钻石公主”号邮轮712例，全球新冠肺炎累计死亡286360例，其中，美国新冠肺炎病例超134万。",
            "广东疫情最新消息",
            "北京疫情最新消息",
            "上海疫情最新消息"};

    //将数据封装成数据源
    List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
    private infoViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(infoViewModel.class);
       final View root = inflater.inflate(R.layout.fragment_info,container,false);

        BottomNavigationView navView = root.findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();


        mBtntitle1 = (Button) root.findViewById(R.id.title_data);
        mBtntitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        mBtntitle2 = (Button) root.findViewById(R.id.title_data1);
        mBtntitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), data1Activity.class);
                startActivity(intent);
            }
        });

        for(int i=0;i<Title.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("title",Title[i]);
            map.put("img",Images[i]);
            map.put("img1",Images1[i]);
            map.put("content",Content[i]);
            list.add(map);
        }
        ListView listview=(ListView)root.findViewById(R.id.listView);
        listview.setAdapter(new MyAdapter());



        String[] ctype = new String[]{"全部", "数据", "预防", "重要"};
        //创建一个数组适配器
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ctype);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式

        mspinner = root.findViewById(R.id.spinner);
        mspinner.setAdapter(adapter);
//条目点击事件
        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            private String positions;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positions = adapter.getItem(position);
                if (positions.equals("全部")){
                    zhye=2+"";

                    Toast.makeText(getActivity(), "全部", Toast.LENGTH_SHORT).show();
                }else if (positions.equals("数据")){
                    zhye=3+"";
                    Toast.makeText(getActivity(), "数据", Toast.LENGTH_SHORT).show();

                }else if (positions.equals("预防")){
                    zhye=4+"";
                    Toast.makeText(getActivity(), "预防", Toast.LENGTH_SHORT).show();

                }else if (positions.equals("重要")){
                    zhye=5+"";
                    Toast.makeText(getActivity(), "重要", Toast.LENGTH_SHORT).show();

                }
                parent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parent.setVisibility(View.VISIBLE);
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("photo", Images[position]);
                bundle.putInt("photo2", Images1[position]);
                bundle.putString("title", Title[position]);
                bundle.putString("message", Content[position]);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(), detailActivity.class);
                Log.i("message", Content[position]);
                startActivity(intent);

            }
        });



        return root;
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder mHolder;
            if(convertView==null){
                view= LayoutInflater.from(getActivity()).inflate(R.layout.list_item,null);
                mHolder=new ViewHolder();
                mHolder.card_title=(TextView)view.findViewById(R.id.cardTitle);
                mHolder.card_image=(ImageView)view.findViewById(R.id.cardImg);
                mHolder.card_image1=(ImageView)view.findViewById(R.id.cardImg1);
                mHolder.card_content=(TextView)view.findViewById(R.id.cardContent);
                view.setTag(mHolder);  //将ViewHolder存储在View中
            }else {
                view=convertView;
                mHolder=(ViewHolder)view.getTag();  //重新获得ViewHolder
            }
            mHolder.card_title.setText(list.get(position).get("title").toString());
            mHolder.card_image.setImageResource((int)list.get(position).get("img"));
            mHolder.card_image1.setImageResource((int)list.get(position).get("img1"));
            mHolder.card_content.setText(list.get(position).get("content").toString());
            return view;
        }
    }

    class ViewHolder{
        TextView card_title;
        ImageView card_image;
        ImageView card_image1;
        TextView card_content;
    }

}

package com.example.aid.ui.info;

import android.content.Intent;
import android.database.Cursor;
import android.icu.text.CaseMap;
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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.aid.MainActivity;
import com.example.aid.R;
import com.example.aid.data.DAL.DataBaseHelper;
import com.example.aid.data1Activity;
import com.example.aid.detailActivity;
import com.example.aid.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.aid.data.DAL.InfoDAL;
import com.example.aid.data.DAL.DataBaseHelper;

import java.sql.SQLException;
import android.database.Cursor;


public class infoFragment extends Fragment {
    private int j=0;
    private String Source1[]=new String[100];
    private String Title1[]=new String[100];
    private String Content1[]=new String[100];
    private String Images11[]=new String[100];
    private String Images21[]=new String[100];
    int Images12[]=new int[100];
    int Images22[]=new int[100];
    private Button mBtntitle1;
    private Button mBtntitle2;
    private Button deleteinfo;
    private Spinner mspinner;
    private int zhye;
    private ArrayAdapter<String> adapter;
    final InfoDAL InfoDAL = new InfoDAL(getActivity());
    InfoDAL dbHelper;
    private String id;
    private View root;
    private String a;
    private String[] Title={"人民日报"};
    private String[] Source={"人民日报"};
    private int[] Images={R.drawable.ic_dashboard_black_24dp,R.drawable.ic_dashboard_black_24dp,R.drawable.ic_dashboard_black_24dp};
    private int[] Images1={R.drawable.num1,R.drawable.ic_dashboard_black_24dp,R.drawable.ic_dashboard_black_24dp};
    private String[] Content={"【最新数据：全球新冠肺炎超415万例】截至北京时间5月12日18时，214个国家和地区累计确诊4154730例，“钻石公主”号邮轮712例，全球新冠肺炎累计死亡286360例，其中，美国新冠肺炎病例超134万。"};


    //将数据封装成数据源
    List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
    //private infoViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel =
        //        ViewModelProviders.of(this).get(infoViewModel.class);
        NavController nav = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        Map<String, NavArgument> id = nav.getCurrentDestination().getArguments();
        NavArgument argument = id.get("id");
        String res = argument.getDefaultValue().toString();
        this.id = res;
        a=res;
        if(this.id.length()==11) {
            root = inflater.inflate(R.layout.fragment_info, container, false);

        }
        else {
            root = inflater.inflate(R.layout.fragment_info_manager, container, false);

        }
        BottomNavigationView navView = root.findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        dbHelper = new InfoDAL(getActivity());

        mBtntitle1 = (Button) root.findViewById(R.id.title_data);
        mBtntitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "当前页面为资讯" , Toast.LENGTH_SHORT).show();
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
                    zhye=2;
                }else if (positions.equals("数据")){
                    zhye=3;

                }else if (positions.equals("预防")){
                    zhye=4;

                }else if (positions.equals("重要")){
                    zhye=5;

                }
                parent.setVisibility(View.VISIBLE);




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parent.setVisibility(View.VISIBLE);
            }
        });
        Log.v("zhye",String.valueOf(zhye));

        ListView listview=(ListView)root.findViewById(R.id.listView);
        listview.setAdapter(new MyAdapter());
        Cursor cursor = dbHelper.info(zhye);
        while (cursor.moveToNext()){
            Source1[j]=cursor.getString(cursor.getColumnIndexOrThrow("Info_Source"));
            Title1[j]=cursor.getString(cursor.getColumnIndexOrThrow("Info_Title"));
            Content1[j]=cursor.getString(cursor.getColumnIndexOrThrow("Info_Content"));
            Images11[j]= cursor.getString(cursor.getColumnIndexOrThrow("Info_Image1"));
            Images21[j]= cursor.getString(cursor.getColumnIndexOrThrow("Info_Image2"));
            Log.v("tupian", String.valueOf(Images11[0]));
            j=j+1;
        }
        for(int i=0;i<j;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("source",Source1[i]);
            map.put("title",Title1[i]);
            map.put("img",Images[i]);
            map.put("img1",Images1[i]);
            map.put("content",Content1[i]);
            list.add(map);
        }

        if(a.length()==11) {
            ListView listView = (ListView) root.findViewById(R.id.listView);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("photo", Images[position]);
                    bundle.putInt("photo2", Images1[position]);
                    bundle.putString("source", Source1[position]);
                    bundle.putString("title", Title1[position]);
                    bundle.putString("message", Content1[position]);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    intent.setClass(getActivity(), detailActivity.class);
                    Log.i("message", Content1[position]);
                    startActivity(intent);
                }
            });
        }
        else{
            //deleteinfo=(Button) root.findViewById(R.id.deleteinfo);
            ListView listView = (ListView) root.findViewById(R.id.listView);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    bundle.putString("title", Title1[position]);
                    Log.v("delete",Title1[position]);
                    dbHelper.deleteinfo(Title1[position]);
                    String Source2[]=new String[100];
                    String Title2[]=new String[100];
                    String Content2[]=new String[100];
                    ListView listview=(ListView)root.findViewById(R.id.listView);
                    listview.setAdapter(new MyAdapter());
                    Cursor cursor2 = dbHelper.info(zhye);
                    int i=0;
                    while (cursor2.moveToNext()){
                        Source2[i]=cursor2.getString(cursor2.getColumnIndexOrThrow("Info_Source"));
                        Title2[i]=cursor2.getString(cursor2.getColumnIndexOrThrow("Info_Title"));
                        Content2[i]=cursor2.getString(cursor2.getColumnIndexOrThrow("Info_Content"));
                        Log.v("title", String.valueOf(Title2[i]));
                        i=i+1;
                    }
                }
            });
        }

        return root;
    }

    private void shuzu(){
        String Source1[]=new String[100];
        String Title1[]=new String[100];
        String Content1[]=new String[100];
        String Images11[]=new String[100];
        String Images21[]=new String[100];
        ListView listview=(ListView)root.findViewById(R.id.listView);
        listview.setAdapter(new MyAdapter());
        Cursor cursor = dbHelper.info(zhye);
        while (cursor.moveToNext()){
            Source1[j]=cursor.getString(cursor.getColumnIndexOrThrow("Info_Source"));
            Title1[j]=cursor.getString(cursor.getColumnIndexOrThrow("Info_Title"));
            Content1[j]=cursor.getString(cursor.getColumnIndexOrThrow("Info_Content"));
            Images11[j]= cursor.getString(cursor.getColumnIndexOrThrow("Info_Image1"));
            Images21[j]= cursor.getString(cursor.getColumnIndexOrThrow("Info_Image2"));
            Log.v("tupian", String.valueOf(Images11[0]));
            j=j+1;
        }
        for(int i=0;i<j;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("source",Source1[i]);
            map.put("title",Title1[i]);
            map.put("img",Images[i]);
            map.put("img1",Images1[i]);
            map.put("content",Content1[i]);
            list.add(map);
        }
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
            if(convertView==null||a.length()==11){
                view= LayoutInflater.from(getActivity()).inflate(R.layout.list_item,null);
                mHolder=new ViewHolder();
                mHolder.card_source=(TextView)view.findViewById(R.id.cardSource);
                mHolder.card_title=(TextView)view.findViewById(R.id.cardTitle);
                mHolder.card_image=(ImageView)view.findViewById(R.id.cardImg);
                mHolder.card_image1=(ImageView)view.findViewById(R.id.cardImg1);
                mHolder.card_content=(TextView)view.findViewById(R.id.cardContent);
                view.setTag(mHolder);  //将ViewHolder存储在View中
            }
            else if(convertView==null||a.length()!=11){
                view= LayoutInflater.from(getActivity()).inflate(R.layout.list_item_manager,null);
                mHolder=new ViewHolder();
                mHolder.card_source=(TextView)view.findViewById(R.id.cardSource);
                mHolder.card_title=(TextView)view.findViewById(R.id.cardTitle);
                mHolder.card_image=(ImageView)view.findViewById(R.id.cardImg);
                mHolder.card_image1=(ImageView)view.findViewById(R.id.cardImg1);
                mHolder.card_content=(TextView)view.findViewById(R.id.cardContent);
                mHolder.deleteinfo=(Button)view.findViewById(R.id.deleteinfo);
                view.setTag(mHolder);  //将ViewHolder存储在View中
            }
            else {
                view=convertView;
                mHolder=(ViewHolder)view.getTag();  //重新获得ViewHolder
            }
            mHolder.card_source.setText(list.get(position).get("source").toString());
            mHolder.card_title.setText(list.get(position).get("title").toString());
            mHolder.card_image.setImageResource((int) list.get(position).get("img"));
            mHolder.card_image1.setImageResource((int) list.get(position).get("img1"));
            mHolder.card_content.setText(list.get(position).get("content").toString());
            return view;
        }
    }

    class ViewHolder{
        TextView card_source;
        TextView card_title;
        ImageView card_image;
        ImageView card_image1;
        TextView card_content;
        Button deleteinfo;
    }

}

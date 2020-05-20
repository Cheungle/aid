package com.example.aid.ui.message;

import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.app.Activity;
import android.os.Bundle;

import com.example.aid.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class MessageViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public MessageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is message fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


    /*
    private int[] user_images={R.mipmap.ic_forum,R.mipmap.ic_message};
    private String[] user_names={"龙宝","阿轩"};
    private String[] user_last_messages={"螺蛳粉", "猫咪图片"};

    List<Map<String,Object>> mw_list=new ArrayList<Map<String, Object>>();

    private void initlist(){
        for(int i=0;i<user_names.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("image",user_images[i]);
            map.put("name",user_names[i]);
            map.put("last_message",user_last_messages[i]);
            mw_list.add(map);
        }
        ListView listview = (ListView) this.findViewById(R.id.mw_lv);
        listview.setAdapter(new MyAdapter());
    }


    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mw_list.size();
        }

        @Override
        public Object getItem(int position) {
            return mw_list.get(position);
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
                view= LayoutInflater.from(MessageViewModel.this).inflate(R.layout.fragment_mwlv_item,null);
                mHolder=new ViewHolder();
                mHolder.card_images=(ImageView)view.findViewById(R.id.user_image);
                mHolder.card_names=(TextView)view.findViewById(R.id.user_name);
                mHolder.card_last_messages=(TextView)view.findViewById(R.id.user_last_message);
                view.setTag(mHolder);
            }else {
                view=convertView;
                mHolder=(ViewHolder)view.getTag();
            }
            mHolder.card_images.setImageResource((int)mw_list.get(position).get("image"));
            mHolder.card_names.setText(mw_list.get(position).get("name").toString());
            mHolder.card_last_messages.setText(mw_list.get(position).get("last_message").toString());
            return view;
        }
    }

    class ViewHolder{
        ImageView card_images;
        TextView card_names;
        TextView card_last_messages;
    }
*/

}

package com.example.aid.ui.notifications;
import com.example.aid.R;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class MySimpleAdapter extends SimpleAdapter {
    Context context;

    public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data,
                           int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
        // TODO Auto-generated constructor stub
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = super.getView(position, convertView, parent);

        Button btn=(Button) v.findViewById(R.id.mark_chat_with_task_sponsor);
        Button btn_accept=(Button) v.findViewById(R.id.mark_accept_task);
        Button btn_star=(Button) v.findViewById(R.id.mark_star_task);

        final int p = position;
        Log.d("Position", Integer.toString(position));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //联系发起人
            }
        });
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.v("PositionTHE", "here");
            }
        });
        btn_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.v("PositionTHE", "here");
            }
        });
        return v;
    }

}


package com.example.aid.ui.notifications;
import com.example.aid.R;
import com.example.aid.data.DAL.CTDAL;
import com.example.aid.data.DAL.MWDAL;
import com.example.aid.data.DAL.MarkDAL;
import com.example.aid.data.DAL.RCTDAL;
import com.example.aid.data.DAL.RVCDAL;
import com.example.aid.data.DAL.RVTDAL;
import com.example.aid.data.DAL.TaskDAL;
import com.example.aid.data.DAL.ThemeDAL;
import com.example.aid.data.DAL.UserDAL;
import com.example.aid.ui.forum.ForumContentActivity;
import com.example.aid.ui.message.MessageContentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MySimpleAdapter extends SimpleAdapter {
    Context context;
    private String id;
    private List<? extends Map<String, ?>> data;
    public MySimpleAdapter(Context context, String id ,List<? extends Map<String, ?>> data,
                           int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
        this.id = id;
        this.data =data;
        // TODO Auto-generated constructor stub
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = super.getView(position, convertView, parent);
        //用户
        final TextView mark_task_ID = v.findViewById(R.id.mark_task_ID);
        final TextView mark_creator_ID = v.findViewById(R.id.mark_chat_ID);
        final TextView mark_rece = v.findViewById(R.id.mark_status_detail);
        final TextView pub_task_ID = v.findViewById(R.id.pub_task_ID);
        final TextView pub_rece = v.findViewById(R.id.pub_status_detail);
        final TextView pub_finishState = v.findViewById(R.id.pub_recipient_detail);
        final TextView pub_review = v.findViewById(R.id.pub_review_detail);
        //管理员
        final TextView rev_task_ID = v.findViewById(R.id.review_task_ID);
        final TextView rev_comment_ID = v.findViewById(R.id.review_comment_ID);
        final TextView theme_ID = v.findViewById(R.id.theme_ID);

        //用户
        Button btn=(Button) v.findViewById(R.id.mark_chat_with_task_sponsor);
        Button btn_accept=(Button) v.findViewById(R.id.mark_accept_task);
        Button btn_star=(Button) v.findViewById(R.id.mark_star_task);
        Button btn_finish=(Button) v.findViewById(R.id.pub_chat_with_task_sponsor);
        Button btn_pub_delete=(Button) v.findViewById(R.id.pub_accept_task);
        Button btn_rec_chat = v.findViewById(R.id.rec_chat_with_task_sponsor);
        //管理员
        Button btn_rev_success = v.findViewById(R.id.review_success);
        Button btn_rev_fail = v.findViewById(R.id.review_fail);
        Button btn_rev_comment_success = v.findViewById(R.id.review_comment_success);
        Button btn_rev_comment_fail = v.findViewById(R.id.review_comment_fail);
        Button btn_theme_select = v.findViewById(R.id.theme_info);
        Button btn_theme_delete = v.findViewById(R.id.theme_delete);

        final int p = position;
        Log.d("Position", Integer.toString(position));
        //联系发起人，收藏
        if(btn!=null){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    MWDAL mwdal = new MWDAL(context);
                    if(mwdal.isMessageWindowExist(id,mark_creator_ID.getText().toString(),Integer.valueOf(mark_task_ID.getText().toString()))){
                        //存在窗口
                    }else{
                        //不存在窗口
                        mwdal.insertMessageWindow(id,mark_creator_ID.getText().toString(),Integer.valueOf(mark_task_ID.getText().toString()));
                    }
//                    Bundle bundle = new Bundle();
//                    bundle.putString("user_id",id);
//                    bundle.putInt("id",position);
//                    bundle.putString("opposite_name",user_names[position]);
//                    Intent intent= new Intent(context, MessageContentActivity.class);
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
                }
            });
        }
        if(btn_rec_chat!=null){
            btn_rec_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    //联系发起人
                }
            });
        }

        //认领
        if(btn_accept!=null){
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.v("PositionTHE", "here");
                if(mark_rece.getText()==null ||mark_rece.getText().equals("")){
                    RCTDAL rctdal = new RCTDAL(context);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                    rctdal.addRCTask(mark_task_ID.getText().toString(),id,df.format(new Date()));
                    UserDAL userDAL = new UserDAL(context);
                    String name = userDAL.selectName(id);
                    mark_rece.setText(name);
                }
            }
        });}
        //取消收藏
        if(btn_star!=null){
        btn_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Log.v("PositionTHE", "here");
                MarkDAL markDAL = new MarkDAL(context);
                Log.v("deletemarkTT",mark_task_ID.getText().toString());
                Log.v("deleteper",id);

                markDAL.deleteByID(mark_task_ID.getText().toString(),id);
                //data.remove(position);
                Log.v("deletemark","");
            }
        });}
        //确认完成
        if(btn_finish!=null){
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(pub_rece.getText()!=null &&!pub_rece.getText().equals("")&&
                        (pub_finishState.getText()==null||pub_finishState.getText().equals(""))){
                    CTDAL ctdal = new CTDAL(context);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                    String time = df.format(new Date());
                    ctdal.addCTask(pub_task_ID.getText().toString(),time);
                    pub_finishState.setText(time);
                }

            }
        });}
        //删除发布
        if(btn_pub_delete!=null){
        btn_pub_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(pub_rece.getText()!=null &&!pub_rece.getText().equals("")&&
                        (pub_finishState.getText()==null||pub_finishState.getText().equals(""))){
                 //   Log.v("deletemark","");

                }else{
                    if(pub_finishState.getText()!=null&&!pub_finishState.getText().equals("")){
                        CTDAL ctdal = new CTDAL(context);
                        //Log.v("finishtask",id);
                        ctdal.deleteCTask(pub_task_ID.getText().toString());
                       // Log.v("finishtask","");

                    }
                    if(pub_review.getText()!=null&&!pub_finishState.getText().equals("")&&pub_review.getText()!="待审核"){
                        RVTDAL rvtdal = new RVTDAL(context);
                       // Log.v("reviewtask",id);
                        rvtdal.deleteByID(pub_task_ID.getText().toString());
                       // Log.v("reviewtask","");

                    }
                  //  Log.v("task","");
                    TaskDAL taskDAL = new TaskDAL(context);
                    taskDAL.deleteByID(pub_task_ID.getText().toString());
                }

                }
        });}
        //审核通过,任务
        if(btn_rev_success!=null){
        btn_rev_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Log.v("PositionTHE", "here");
                RVTDAL rvtdal = new RVTDAL(context);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                String time = df.format(new Date());
                rvtdal.addRVTask(rev_task_ID.getText().toString(),id,time,"1");
            }
        });}
        //审核失败,任务
        if(btn_rev_fail!=null){
        btn_rev_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Log.v("PositionTHE", "here");
                RVTDAL rvtdal = new RVTDAL(context);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                String time = df.format(new Date());
                rvtdal.addRVTask(rev_task_ID.getText().toString(),id,time,"2");
            }
        });}
        //审核通过,评论
        if(btn_rev_comment_success!=null){
        btn_rev_comment_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Log.v("PositionTHE", "here");
                RVCDAL rvtdal = new RVCDAL(context);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                String time = df.format(new Date());
                rvtdal.addRComment(rev_comment_ID.getText().toString(),id,time,"1");
            }
        });}
        //审核失败,评论
        if(btn_rev_comment_fail!=null){
        btn_rev_comment_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Log.v("PositionTHE", "here");
                RVCDAL rvtdal = new RVCDAL(context);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                String time = df.format(new Date());
                rvtdal.addRComment(rev_comment_ID.getText().toString(),id,time,"2");
            }
        });}
        //查看主题
        if(btn_theme_select!=null){
        btn_theme_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Log.v("PositionTHE", "here");
                Bundle bundle = new Bundle();
                bundle.putInt("id",Integer.valueOf(theme_ID.getText().toString()));
                bundle.putString("user_id",id);
                Intent intent= new Intent(context, ForumContentActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });}
        //删除主题
        if(btn_theme_delete!=null){
        btn_theme_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Log.v("PositionTHE", "here");
                ThemeDAL themeDAL = new ThemeDAL(context);
                themeDAL.deleteByID(theme_ID.getText().toString());
            }
        });}
        return v;
    }

}


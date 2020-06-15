package com.example.aid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aid.data.DAL.InfoDAL;

public class detailActivity2 extends AppCompatActivity {
    private Button mBtntitle;
    private Button deleteinfo;
    private int i=1;
    final com.example.aid.data.DAL.InfoDAL DataDAL = new InfoDAL(this);
    InfoDAL dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_main2);
       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        dbHelper = new InfoDAL(this);
        Bundle bundle=getIntent().getExtras();
        int id=bundle.getInt("photo");
        int id2=bundle.getInt("photo2");
        String message=bundle.getString("message");
        final String title=bundle.getString("title");
        String source=bundle.getString("source");
        String date=bundle.getString("date");
        ImageView Iv=(ImageView) findViewById(R.id.Iv2);
        Iv.setImageResource(id);
        ImageView Iv2=(ImageView) findViewById(R.id.Iv22);
        Iv2.setImageResource(id2);
        TextView tv=(TextView) findViewById(R.id.tv_message2);
        tv.setText(message);
        TextView name=(TextView) findViewById(R.id.name2);
        name.setText(source);
        TextView tv_title=(TextView) findViewById(R.id.tv_title2);
        tv_title.setText(title);
        TextView tv_date=(TextView) findViewById(R.id.cardDate);
        tv_date.setText(date);
       deleteinfo = (Button) findViewById(R.id.deleteinfo);
       deleteinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteinfo(title);
                Toast.makeText(detailActivity2.this, "删除成功", Toast.LENGTH_LONG).show();
            }
        });



    }


}

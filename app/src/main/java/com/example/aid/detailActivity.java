package com.example.aid;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class detailActivity extends AppCompatActivity {
    private Button mBtntitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_main);
       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        Bundle bundle=getIntent().getExtras();
        int id=bundle.getInt("photo");
        int id2=bundle.getInt("photo2");
        String message=bundle.getString("message");
        String title=bundle.getString("title");
        String source=bundle.getString("source");
        String date=bundle.getString("date");
        ImageView Iv=(ImageView) findViewById(R.id.Iv);
        Iv.setImageResource(id);
        ImageView Iv2=(ImageView) findViewById(R.id.Iv2);
        Iv2.setImageResource(id2);
        TextView tv=(TextView) findViewById(R.id.tv_message);
        tv.setText(message);
        TextView name=(TextView) findViewById(R.id.name);
        name.setText(source);
        TextView tv_title=(TextView) findViewById(R.id.tv_title);
        tv_title.setText(title);
        TextView tv_date=(TextView) findViewById(R.id.cardDate);
        tv_date.setText(date);





    }


}

package com.example.aid.ui.forum;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aid.R;
import com.example.aid.data.DAL.ThemeDAL;

import java.text.SimpleDateFormat;

public class ForumAddActivity extends AppCompatActivity {

    private String manager_id;
    private ThemeDAL themeDAL = new ThemeDAL(ForumAddActivity.this);

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_forum_add);

        Bundle bundle=getIntent().getExtras();
        manager_id = bundle.getString("user_id");

        final Button theme_confirm_btn = findViewById(R.id.forum_add_confirm);
        theme_confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText et_title= findViewById(R.id.forum_edit_title);
                EditText et_content= findViewById(R.id.forum_edit_title);

                final String edit_title=et_title.getText().toString();
                final String edit_content=et_content.getText().toString();

                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String date=sdf.format(new java.util.Date());

                themeDAL.addTheme(edit_title,date, manager_id);
                Toast.makeText(ForumAddActivity.this, "添加成功", Toast.LENGTH_LONG).show();

                themeDAL.getAllTitles();

            }
        });


    }
}

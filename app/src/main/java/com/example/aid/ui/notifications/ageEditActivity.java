package com.example.aid.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aid.R;
import com.example.aid.data.DAL.UserDAL;

public class ageEditActivity extends AppCompatActivity {
    private String id;
    private String age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        this.id = getIntent().getStringExtra("id");
        this.age = getIntent().getStringExtra("age");
        final ClearEditText ageEdit = findViewById(R.id.age_edit);
        ageEdit.setText(this.age);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.name_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.e("the",ageEdit.getText().toString());
                String ageAfter = ageEdit.getText().toString();
                if(ageAfter==null||ageAfter=="")finish();
                if(!ageEditActivity.this.age.equals(ageAfter)){
                    UserDAL userDAL = new UserDAL(ageEditActivity.this);
                    userDAL.updateAge(ageAfter,ageEditActivity.this.id);
                    ageEditActivity.this.age = ageAfter;
                    Intent i = new Intent();
                    i.putExtra("age_return",ageAfter);
                    setResult(RESULT_OK,i);
                }
                finish();
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.name_menu, menu);
        return true;
    }
}

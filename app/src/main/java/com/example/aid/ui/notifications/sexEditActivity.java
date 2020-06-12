package com.example.aid.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aid.R;
import com.example.aid.data.DAL.UserDAL;

public class sexEditActivity extends AppCompatActivity {
    private String id;
    private String sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex_edit);
        final Switch sexChoose = findViewById(R.id.switch_sex);
        this.id = getIntent().getStringExtra("id");
        this.sex = getIntent().getStringExtra("sex");
        final String before = this.sex;
        if(this.sex.equals("0")){
            sexChoose.setText("男");
            sexChoose.setChecked(false);
        }else{
            sexChoose.setText("女");
            sexChoose.setChecked(true);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.name_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(!before.equals(sexEditActivity.this.sex)){
                    UserDAL userDAL = new UserDAL(sexEditActivity.this);
                    userDAL.updateSex(sexEditActivity.this.sex,sexEditActivity.this.id);
                    Intent i = new Intent();
                    i.putExtra("sex_return",sexEditActivity.this.sex);
                    setResult(RESULT_OK,i);
                }
                finish();
                return false;
            }
        });

        sexChoose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!buttonView.isPressed()) {
                    return;
                }
                if(isChecked){
                    sexChoose.setText("女");
                    sexEditActivity.this.sex = "1";
                }
                else{
                    sexChoose.setText("男");
                    sexEditActivity.this.sex = "0";
                }

            }
        });
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.name_menu, menu);
        return true;
    }
}

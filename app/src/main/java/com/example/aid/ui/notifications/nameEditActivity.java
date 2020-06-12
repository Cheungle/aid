package com.example.aid.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aid.R;
import com.example.aid.Register;
import com.example.aid.data.DAL.UserDAL;

public class nameEditActivity extends AppCompatActivity {
    private String id;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        this.id = getIntent().getStringExtra("id");
        this.name = getIntent().getStringExtra("name");
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.name_menu);
        final ClearEditText nameEdit = findViewById(R.id.name_edit);
        nameEdit.setText(this.name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
        public boolean onMenuItemClick(MenuItem item) {
                String nameAfter = nameEdit.getText().toString();
                if(nameAfter==null||nameAfter==""){
                    Toast.makeText(nameEditActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                }else{
                    if(!nameEditActivity.this.name.equals(nameAfter)){
                        UserDAL userDAL = new UserDAL(nameEditActivity.this);
                        userDAL.updateName(nameAfter,nameEditActivity.this.id);
                        nameEditActivity.this.name = nameAfter;
                        Intent i = new Intent();
                        i.putExtra("name_return",nameAfter);
                        setResult(RESULT_OK,i);
                    }
                    finish();
                }
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

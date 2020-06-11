package com.example.aid.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aid.R;
import com.example.aid.data.DAL.UserDAL;

public class identityEditActivity extends AppCompatActivity {
    private String id;
    private String identity_id;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        this.id = getIntent().getStringExtra("id");
        final ClearEditText idEdit = findViewById(R.id.identity_ID_edit);
        final ClearEditText nameEdit = findViewById(R.id.identity_name_edit);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.name_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String idAfter = idEdit.getText().toString();
                if(idAfter==null||idAfter=="")finish();
                if(!identityEditActivity.this.identity_id.equals(idAfter)){
                    UserDAL userDAL = new UserDAL(identityEditActivity.this);
                    userDAL.updateAge(idAfter,identityEditActivity.this.identity_id);
                    identityEditActivity.this.identity_id = idAfter;
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

package com.example.aid.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aid.R;
import com.example.aid.data.DAL.IdentityDAL;
import com.example.aid.data.DAL.UserDAL;
import com.example.aid.data.model.identity;
import com.example.aid.ui.login.LoginActivity;

import java.sql.SQLException;

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
        final UserDAL userDAL = new UserDAL(identityEditActivity.this);
        identity iden = userDAL.selectIdentity(this.id);
        idEdit.setText(iden.getID());
        nameEdit.setText(iden.getName());
        this.identity_id = idEdit.getText().toString();
        this.name = nameEdit.getText().toString();
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.name_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String idAfter = idEdit.getText().toString();
                String nameAfter = nameEdit.getText().toString();
                boolean login = false;
                if((idAfter==null||idAfter=="")&&(nameAfter==null||nameAfter=="")){
                    finish();
                    return false;
                }else{
                    if(idAfter==null||idAfter==""){
                        Toast.makeText(identityEditActivity.this, "请输入正确的实名认证信息", Toast.LENGTH_SHORT).show();
                    }else{
                        if(nameAfter==null||nameAfter=="")Toast.makeText(identityEditActivity.this, "请输入正确的实名认证信息", Toast.LENGTH_SHORT).show();
                    }
                }
                if(!identityEditActivity.this.identity_id.equals(idAfter)||!identityEditActivity.this.name.equals(nameAfter)){
                    IdentityDAL identityDAL = new IdentityDAL(identityEditActivity.this);
                    try {
                        login = identityDAL.login(idAfter,nameAfter);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if(login){
                        identityEditActivity.this.identity_id = idAfter;
                        identityEditActivity.this.name = nameAfter;
                        userDAL.updateIdentity(identityEditActivity.this.id,idAfter,nameAfter);
                        finish();
                    }else{
                        Toast.makeText(identityEditActivity.this, "实名认证失败", Toast.LENGTH_SHORT).show();
                    }

                }else{finish();}
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

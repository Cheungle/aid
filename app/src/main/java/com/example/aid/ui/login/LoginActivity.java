package com.example.aid.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.aid.MainActivity;
import com.example.aid.R;
import com.example.aid.Register;
import com.example.aid.data.DAL.ManagerDAL;
import com.example.aid.data.DAL.UserDAL;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        ((TextView)findViewById(R.id.icon_inputID)).setTypeface(font);
        ((TextView)findViewById(R.id.icon_inputpass)).setTypeface(font);
        editColorChange();
        waitBtns();
    }
    private void waitBtns() {
        //跳转注册页面
        Button btn_register = (Button)findViewById(R.id.register);
        btn_register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(LoginActivity.this , Register.class);
                startActivity(i);
            }
        });
        //跳转登录
        Button btn_login = (Button)findViewById(R.id.login);
        final EditText usernameEditText = findViewById(R.id.ID);
        final EditText passwordEditText = findViewById(R.id.password);
        Log.v("tag",usernameEditText.getText().toString());
        Log.v("tag",passwordEditText.getText().toString());
        btn_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Boolean login = false;

                try {
                    String id = usernameEditText.getText().toString();
                    if(id.length()==11){
                        final UserDAL userDAL = new UserDAL(LoginActivity.this);
                        login = userDAL.login(id,passwordEditText.getText().toString());
                    }else {
                        ManagerDAL managerDAL = new ManagerDAL(LoginActivity.this);
                        login = managerDAL.login(id,passwordEditText.getText().toString());
                    }
                    if(login){
                        Intent i = new Intent(LoginActivity.this , MainActivity.class);
                        i.putExtra("id",id);
                        startActivity(i);
                        Log.i("tag","succses"+" id");
                    }else{
                        Log.i("tag","sry");
                        Toast.makeText(LoginActivity.this, "账号或密码有误", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        //忘记密码
        TextView forget = findViewById(R.id.forgetPwd);
        forget.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(LoginActivity.this , ForgetPwdActivity.class);
                startActivity(i);
            }
        });
    }
    private void editColorChange() {
        EditText inputID = (EditText) findViewById(R.id.ID);
        EditText inputPass = (EditText) findViewById(R.id.password);
        inputID.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                TextView ID = (TextView) findViewById(R.id.icon_inputID);
                if (hasFocus) {
                    ID.setTextColor(Color.rgb(239,170,82));
                } else {
                    ID.setTextColor(Color.GRAY);
                }
            }
        });
        inputPass.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                TextView Pass = (TextView) findViewById(R.id.icon_inputpass);
                if (hasFocus) {
                    Pass.setTextColor(Color.rgb(239,170,82));
                } else {
                    Pass.setTextColor(Color.GRAY);
                }
            }
        });
    }

    public void onResume(Bundle savedInstanceState) {
        super.onResume();
        editColorChange();
        waitBtns();
    }
}

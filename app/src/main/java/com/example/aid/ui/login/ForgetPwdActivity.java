package com.example.aid.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aid.MainActivity;
import com.example.aid.R;
import com.example.aid.Register;
import com.example.aid.data.DAL.ManagerDAL;
import com.example.aid.data.DAL.UserDAL;

import java.sql.SQLException;

public class ForgetPwdActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pwd);
        buttonColorChange();
        waitBtns();
    }
    private void waitBtns() {
        //发送验证码
        Button btn_forget = (Button) findViewById(R.id.sendPwd);
        btn_forget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }

    private void buttonColorChange() {
        EditText inputID = (EditText) findViewById(R.id.forget_ID_edit);
        Button send = (Button)findViewById(R.id.sendPwd);
        if(!inputID.getText().toString().equals("")||inputID.getText()!=null){
            send.setBackgroundResource(R.drawable.btn_circle);
        }

    }

    public void onResume(Bundle savedInstanceState) {
        super.onResume();
        buttonColorChange();
        waitBtns();
    }}


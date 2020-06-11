package com.example.aid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aid.data.DAL.UserDAL;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn_back = findViewById(R.id.register_back);
        btn_back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
        Button btn_up = findViewById(R.id.register_btn);
        btn_up.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText ID = findViewById(R.id.input_identity_text);
                EditText pwd = findViewById(R.id.input_password);
                EditText name = findViewById(R.id.input_name_text);
                RadioButton woman = findViewById(R.id.radioButtonWoman);
                String id = ID.getText().toString();
                String pass = pwd.getText().toString();
                String Name = name.getText().toString();
                String sex = "0";//man
                if(woman.isChecked())sex="1";
                if(isNum(id)&&id.length()!=11){
                    Log.e("legth", String.valueOf(id.length()));
                    Toast.makeText(Register.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }else{
                    if(Name.equals("")){
                        Toast.makeText(Register.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                    }else{
                        UserDAL userDAL = new UserDAL(Register.this);
                        if(!userDAL.idIsExist(id)){
                            userDAL.register(id,pass,Name,sex);
                            finish();
                        }else{
                            Toast.makeText(Register.this, "该账号已存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
    public boolean isNum(String phone){
        if(phone!=null && !"".equals(phone.trim()))
            return phone.matches("^[0-9]*$");
        else
            return false;
    }
}

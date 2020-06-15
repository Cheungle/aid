package com.example.aid.ui.dashboard;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.aid.R;
import com.example.aid.data.DAL.DataBaseHelper;
import com.example.aid.data.DAL.TaskDAL;
import com.example.aid.data.DAL.UserDAL;
import com.example.aid.data.model.identity;
import com.example.aid.ui.login.LoginActivity;
import com.example.aid.ui.notifications.identityEditActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;

import static java.security.AccessController.getContext;

public class DashboardNewTaskReources extends AppCompatActivity {
    private Button new_time_btn = null;
    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog datePicker = null;
    private Button save_btn = null;
    private Button cancel_btn = null;
    private String id;
    private String TR_content,TR_place,TR_time;
    private TaskDAL taskDAL=new TaskDAL(DashboardNewTaskReources.this);
    private UserDAL userDAL=new UserDAL(DashboardNewTaskReources.this);
    private RadioButton task_select;
    private RadioButton resources_select;
    private int type=0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard_new_task_resources);

        Toolbar toolbar = findViewById(R.id.toolbar);
        this.id = getIntent().getStringExtra("id");
        identity iden = userDAL.selectIdentity(this.id);
        final String identity_id = iden.getID();
        new_time_btn = findViewById(R.id.new_time_btn);
        new_time_btn.setText(pubFun.format(calendar.getTime()));

        new_time_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                openDate();
            }
        });

        task_select = (RadioButton)findViewById(R.id.rg_type_task);
        resources_select = (RadioButton)findViewById(R.id.rg_type_resources);

        save_btn = findViewById(R.id.new_TR_save_button);
        save_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                EditText content = findViewById(R.id.new_TR_content_detail);
                TR_content = content.getText().toString();
                EditText place = findViewById(R.id.new_TR_location_detail);
                TR_place = content.getText().toString();
                TR_time = pubFun.format(calendar.getTime()).toString();

                if (task_select.isChecked()){
                    type=1;
                }
                if (resources_select.isChecked()){
                    type=2;
                }

                try {
                    saveInfo(identity_id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        cancel_btn = findViewById(R.id.new_TR_cancel_button);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                exit();
            }
        });
    }

    private void loadingFormation(){
        new_time_btn=(Button)findViewById(R.id.new_time_btn);
        save_btn=(Button)findViewById(R.id.new_TR_save_button);
        cancel_btn=(Button)findViewById(R.id.new_TR_cancel_button);
    }

    private DatePickerDialog.OnDateSetListener mDateSetListenerSatrt = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.YEAR, year);
            new_time_btn.setText(pubFun.format(calendar.getTime()));
        }
    };

    private void openDate() {
        datePicker = new DatePickerDialog(this, mDateSetListenerSatrt,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

    private void exit() {
        Intent intent = new Intent(this,DashboardFragment.class);
        startActivity(intent);
        finish();
    }

    private void saveInfo(String identity_id) throws SQLException {
        //Save之前判断用户是否进行了身份验证
        //SharedPreferences sharedPreferences= getSharedPreferences("setting",Activity.MODE_PRIVATE);
        //String userID =sharedPreferences.getString("userID", "");

        //Log.i("info", "此次登录的用户是" + userID);

        if(identity_id==null){
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("您还未实名认证，请点击确定按钮进行认证！")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            setResult(RESULT_OK);
                            Intent intent=new Intent(DashboardNewTaskReources.this, identityEditActivity.class);
                            DashboardNewTaskReources.this.startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            return;
                        }
                    })
                    .show();
        }else{
            taskDAL.insertTask(id,TR_content,TR_time,type,TR_place);
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();

        }

    }

}
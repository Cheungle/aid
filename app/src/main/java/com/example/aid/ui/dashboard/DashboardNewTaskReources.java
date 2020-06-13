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
import android.widget.Toast;

import com.example.aid.R;
import com.example.aid.data.DAL.DataBaseHelper;
import com.example.aid.ui.login.LoginActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.ParseException;
import java.util.Calendar;

import static java.security.AccessController.getContext;

public class DashboardNewTaskReources extends AppCompatActivity {
    private Button new_time_btn = null;
    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog datePicker = null;
    private Button save_btn = null;
    private Button cancel_btn = null;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard_new_task_resources);
        Log.v("tag","here");
        new_time_btn = findViewById(R.id.new_time_btn);
        new_time_btn.setText(pubFun.format(calendar.getTime()));

        new_time_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                openDate();
            }
        });

        /*save_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                saveInfo();
            }
        });*/
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

    private void saveInfo() {

    }

}

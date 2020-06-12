package com.example.aid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aid.data.DAL.DataDAL;
import com.example.aid.data.DAL.InfoDAL;
import com.example.aid.ui.data.ChinaMapView;
import com.example.aid.ui.data.CustomBarChart;
import com.example.aid.ui.data.LineView;
import com.example.aid.ui.data.PieEntry;
import com.example.aid.ui.data.PieView;
import com.example.aid.ui.data.SimpleSeekBarListener;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

import static java.lang.StrictMath.max;

public class data1Activity extends AppCompatActivity {
    private SeekBar mAlphaRadiusSb;
    private SeekBar mHoleRadiusSb;
    private SeekBar mAlphaSb;
    private TextView mPercentage;
    private TextView mBlockUpTv;
    private TextView mDefaultTv;
    private TextView mCenterTextTv;
    private PieView mPieView;
    private boolean disPlayPercentage  = true;
    private boolean disPlayCenterText  = false;

    final DataDAL DataDAL = new DataDAL(this);
    DataDAL dbHelper;
    DataDAL dbHelper1;


    private LinearLayout customBarChart1, customBarChart2;
    private Button mBtntitle1;
    private Button mBtntitle2;
    ChinaMapView lView;

    LineView chartView;
    List<String> xValues = new ArrayList<>();   //x轴数据集合
    List<Integer> yValues = new ArrayList<>();  //y轴数据集合*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data1);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        dbHelper = new DataDAL(this);
        dbHelper1 = new DataDAL(this);
        mBtntitle1 = (Button) findViewById(R.id.title_data);
        mBtntitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  data1Activity.this.finish();
//                Intent intent = new Intent(data1Activity.this, MainActivity.class);
//                startActivity(intent);
            }
        });
        mBtntitle2 = (Button) findViewById(R.id.title_data1);
        mBtntitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data1Activity.this, data1Activity.class);
                startActivity(intent);
            }
        });


        xValues.add("5.2");
        yValues.add(17);

        customBarChart1 = (LinearLayout) findViewById(R.id.customBarChart1);
        initBarChart1();

        customBarChart2 = (LinearLayout) findViewById(R.id.customBarChart2);
        initBarChart2();

        initWidget();
        //initListener();
        processLogic();
        //initData1();


        chartView = findViewById(R.id.customView1);
        // xy轴集合自己添加数据
        chartView.setXValues(xValues);
        chartView.setYValues(yValues);


        lView = (ChinaMapView) findViewById(R.id.vp);
        lView.setOnProvinceSelectedListener(new ChinaMapView.OnProvinceSelectedListener() {
            @Override
            public void onprovinceSelected(ChinaMapView.Area pArea) {
                /*Toast.makeText(data1Activity.this, "您选择了-->" + pArea.name(), Toast.LENGTH_SHORT).show();*/
                String place1=pArea.name();
                Cursor cursor = dbHelper.chaxun(place1);
                String data[]=new String[5];
                data[0]=cursor.getString(cursor.getColumnIndex("Data_Place"));
                data[1]=String.valueOf(cursor.getInt(cursor.getColumnIndex("Data_Data1")));
                data[2]=String.valueOf(cursor.getInt(cursor.getColumnIndex("Data_Data2")));
                data[3]=String.valueOf(cursor.getInt(cursor.getColumnIndex("Data_Data3")));
                data[4]=String.valueOf(cursor.getInt(cursor.getColumnIndex("Data_Data4")));
                TextView didian=findViewById(R.id.didian);
                didian.setText(place1);
                TextView dienum=findViewById(R.id.dienum);
                dienum.setText(data[1]);
                TextView zhiyunum=findViewById(R.id.zhiyunum);
                zhiyunum.setText(data[2]);
                TextView xinzengnum=findViewById(R.id.xinzengnum);
                xinzengnum.setText(data[3]);
                Cursor cursor5 = dbHelper.zhexian();
                String xzhou[]=new String[7];
                int yzhou[]=new int[7];
                String placeid="Daydata_"+pArea.ordinal();
                int jk=6;
                while (cursor5.moveToNext()){
                    xzhou[jk]=cursor5.getString(cursor5.getColumnIndex("Daydata_date"));
                    yzhou[jk]= cursor5.getInt(cursor5.getColumnIndex(placeid));
                    jk=jk-1;
                }
                xValues.clear();
                yValues.clear();
                int i;
                for(i=6;jk<=i;jk++){
                    xValues.add(xzhou[jk+1]);
                    yValues.add(yzhou[jk+1]);}
            }

        });

        lView.setMapColor(Color.BLUE);
        findViewById(R.id.fangda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.zoomIn();
            }
        });
        findViewById(R.id.suoxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.zoomOut();
            }
        });
        findViewById(R.id.xiangshang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.up();
            }
        });
        findViewById(R.id.xiangxia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.down();
            }
        });
        findViewById(R.id.xiangzuo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.left();
            }
        });
        findViewById(R.id.xiangyou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.right();
            }
        });
        findViewById(R.id.fuwei).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.restPosition();
            }
        });
        findViewById(R.id.yaunshidaxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.restScale();
            }
        });




    }

    private void initData1() {
        xValues.add("5.2");
        xValues.add("5.3");
        xValues.add("5.4");
        xValues.add("5.5");
        xValues.add("5.6");
        xValues.add("5.7");
        xValues.add("5.8");
        yValues.add(5);
        yValues.add(14);
        yValues.add(8);
        yValues.add(12);
        yValues.add(7);
        yValues.add(17);
        yValues.add(17);
    }
    private void initData() {
        xValues.add("1.0");
        xValues.add("2.0");
        xValues.add("3.0");
        xValues.add("4.0");
        xValues.add("5.0");
        xValues.add("6.0");
        xValues.add("7.0");
        yValues.add(5);
        yValues.add(14);
        yValues.add(8);
        yValues.add(12);
        yValues.add(7);
        yValues.add(17);
        yValues.add(17);
    }
    //初始化柱状图1数据

    private void initBarChart1() {
        String xLabel[]=new String[10];

        xLabel[0]="0";
        int data1[]=new int[10];
        int j=0;
        int i=1;
        int n;
        dbHelper = new DataDAL(this);
        Cursor Data = dbHelper.zhufirst();
        while (Data.moveToNext()){
            xLabel[i] =Data.getString(Data.getColumnIndex("Data_Place"));
            data1[j] = Integer.parseInt(Data.getString(Data.getColumnIndex("Data_Data1")));
            j=j+1;
            i=i+1;
        }
        int k=data1[0];
        int m;
        for(m=0;k/10>10;m=m+1){
            k=k/10;
        }
        k=k/10+1;
        m=m+1;
        int t=1;
        for(int h=0;h<m;h++){
            t=t*10;
        }
        String yLabel[]=new String[k+1];
        for(n=0;n<=k;n=n+1){
            yLabel[n]=String.valueOf(n*t);
        }
        //String[] xLabel = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        //String[] yLabel = {"0", String.valueOf(data1[0]/5), String.valueOf(data1[0]/5*2), String.valueOf(data1[0]/5*3), String.valueOf(data1[0]/5*4), String.valueOf(data1[0])};
        // int[] data1 = {300, 500, 550, 500, 300, 700, 800, 750,200};
        List<int[]> data = new ArrayList<>();
        data.add(data1);
        List<Integer> color = new ArrayList<>();
        color.add(R.color.color12);
        color.add(R.color.color13);
        color.add(R.color.color16);
        customBarChart1.addView(new CustomBarChart(this, xLabel, yLabel, data, color));
    }

    //初始化柱状图2数据

    private void initBarChart2() {
        String xLabel[]=new String[6];
        xLabel[0]="0";
        int data1[]=new int[6];
        int data2[]=new int[6];
        int j=0;
        int i=1;
        int n;
        dbHelper = new DataDAL(this);
        Cursor Data = dbHelper.zhusecond();
        while (Data.moveToNext()){
            xLabel[i] =Data.getString(Data.getColumnIndex("Data_Place"));
            data1[j] = Integer.parseInt(Data.getString(Data.getColumnIndex("Data_Data1")));
            data2[j] = Integer.parseInt(Data.getString(Data.getColumnIndex("Data_Data2")));
            j=j+1;
            i=i+1;
        }
        int k=max(data1[0],data2[0]);
        int m;
        for(m=0;k/10>10;m=m+1){
            k=k/10;
        }
        k=k/10+1;
        m=m+1;
        int t=1;
        for(int h=0;h<m;h++){
            t=t*10;
        }
        String yLabel[]=new String[k+1];
        for(n=0;n<=k;n=n+1){
            yLabel[n]=String.valueOf(n*t);
        }
        // String[] xLabel = {"0", "1", "2", "3", "4", "5", "6"};
        // String[] yLabel = {"0", "100", "200", "300", "400", "500", "600", "700", "800", "900"};
        //int[] data1 = {300, 500, 550, 500, 300, 700, 800};
        //int[] data2 = {400, 600, 500, 700, 300, 500, 550};
        List<int[]> data = new ArrayList<>();
        data.add(data1);
        data.add(data2);
        List<Integer> color = new ArrayList<>();
        color.add(R.color.color14);
        color.add(R.color.color15);
        color.add(R.color.color11);
        customBarChart2.addView(new CustomBarChart(this, xLabel, yLabel, data, color));
    }





    private void processLogic() {
        dbHelper = new DataDAL(this);

        Cursor Data1 = dbHelper.die();
        Cursor Data2 = dbHelper.zhiyu();
        int j=0;
        int bili[]=new int[2];
        while (Data1.moveToNext()){
            bili[0]= Data1.getInt(0);
        }
        while (Data2.moveToNext()){
            bili[1]= Data2.getInt(0);
        }
        List<PieEntry> list = new ArrayList<>();


        list.add(new PieEntry(bili[0], String.format("死亡", bili[0])));
        list.add(new PieEntry(bili[1], String.format("治愈", bili[1])));
        mPieView.setData(list)
                .setShowAnimator(true)
                .refresh();
    }

    private void initWidget() {
        //mAlphaRadiusSb = findViewById(R.id.sb_alpha_circle_radius);
        //mAlphaSb = findViewById(R.id.sb_alpha_circle_alpha);
        // mHoleRadiusSb = findViewById(R.id.sb_hole_circle_radius);
        // mPercentage = findViewById(R.id.tv_show_percentage);
        //mBlockUpTv = findViewById(R.id.tv_block_up);
        //mDefaultTv = findViewById(R.id.tv_default_data);
        //mCenterTextTv = findViewById(R.id.tv_center_text);
        mPieView = findViewById(R.id.pie_view);

        //mAlphaRadiusSb.setProgress(0);
        // mHoleRadiusSb.setProgress(0);
        //mAlphaSb.setProgress(40);
    }
    //private void initListener() {
        /*mPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disPlayPercentage = !disPlayPercentage;
                mPieView.setDisPlayPercent(disPlayPercentage).refresh();
            }
        });*/

       /* mBlockUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor Data1 = dbHelper.die();
                Cursor Data2 = dbHelper.zhiyu();
                int j=0;
                int bili[]=new int[2];
                while (Data1.moveToNext()){
                    bili[0]= Data1.getInt(0);
                }
                while (Data2.moveToNext()){
                    bili[1]= Data2.getInt(0);
                }
                List<PieEntry> list = new ArrayList<>();


                list.add(new PieEntry(bili[0], String.format("死亡", bili[0],bili[0])));
                list.add(new PieEntry(bili[1], String.format("治愈", bili[1])));

                mPieView.setData(list)
                        .setShowAnimator(true)
                        .refresh();
            }
        });*/

      /*  mDefaultTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PieEntry> list = new ArrayList<>();
                for (int i = 1; i < 7; i ++) {
                    list.add(new PieEntry(i * 20, String.format("第%s区", i)));
                }
                mPieView.setData(list)
                        .setShowAnimator(true)
                        .refresh();
            }
        });*/

       /* mCenterTextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disPlayCenterText = !disPlayCenterText;
                mPieView.setShowCenterText(disPlayCenterText).refresh();
            }
        });*/

        /*mAlphaSb.setOnSeekBarChangeListener(new SimpleSeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPieView.setCenterAlpha((float) progress / 100).refresh();
            }
        });*/

        /*mAlphaRadiusSb.setOnSeekBarChangeListener(new SimpleSeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPieView.setAlphaRadiusPercent((float) progress / 100).refresh();
            }
        });

        mHoleRadiusSb.setOnSeekBarChangeListener(new SimpleSeekBarListener() {
            @Override

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPieView.setHoleRadiusPercent((float) progress / 100).refresh();
            }
        });*/
    // }






}

package com.example.aid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aid.ui.data.ChinaMapView;
import com.example.aid.ui.data.CustomBarChart;
import com.example.aid.ui.data.LineView;
import com.example.aid.ui.data.PieEntry;
import com.example.aid.ui.data.PieView;
import com.example.aid.ui.data.SimpleSeekBarListener;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

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



    private LinearLayout customBarChart1, customBarChart2;
    private Button mBtntitle1;
    private Button mBtntitle2;
    ChinaMapView lView;

    LineView chartView;
    List<String> xValues = new ArrayList<>();   //x轴数据集合
    List<Float> yValues = new ArrayList<>();  //y轴数据集合*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data1);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        mBtntitle1 = (Button) findViewById(R.id.title_data);
        mBtntitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data1Activity.this, MainActivity.class);
                startActivity(intent);
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




        customBarChart1 = (LinearLayout) findViewById(R.id.customBarChart1);
        initBarChart1();

        customBarChart2 = (LinearLayout) findViewById(R.id.customBarChart2);
        initBarChart2();

        initWidget();
        initListener();
        processLogic();

        initData();

        chartView = findViewById(R.id.customView1);
        // xy轴集合自己添加数据
        chartView.setXValues(xValues);
        chartView.setYValues(yValues);


        lView = (ChinaMapView) findViewById(R.id.vp);
        lView.setOnProvinceSelectedListener(new ChinaMapView.OnProvinceSelectedListener() {
            @Override
            public void onprovinceSelected(ChinaMapView.Area pArea) {
                Toast.makeText(data1Activity.this, "您选择了-->" + pArea.name(), Toast.LENGTH_SHORT).show();
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

    private void initData() {
        xValues.add("5.2");
        xValues.add("5.3");
        xValues.add("5.4");
        xValues.add("5.5");
        xValues.add("5.6");
        xValues.add("5.7");
        xValues.add("5.8");
        xValues.add("5.9");
        xValues.add("5.10");
        xValues.add("5.11");
        xValues.add("5.12");
        yValues.add(5f);
        yValues.add(14f);
        yValues.add(8f);
        yValues.add(12f);
        yValues.add(7f);
        yValues.add(17f);
        yValues.add(17f);
        yValues.add(17f);
        yValues.add(17f);
        yValues.add(17f);
        yValues.add(17f);
    }
    //初始化柱状图1数据

    private void initBarChart1() {
        String[] xLabel = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
                "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
                "28", "29", "30", "31"};
        String[] yLabel = {"0", "100", "200", "300", "400", "500", "600", "700", "800", "900"};
        int[] data1 = {300, 500, 550, 500, 300, 700, 800, 750, 550, 600, 400, 300, 400, 600, 500,
                700, 300, 500, 550, 500, 300, 700, 800, 750, 550, 600, 400, 300, 400, 600, 500};
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
        String[] xLabel = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] yLabel = {"0", "100", "200", "300", "400", "500", "600", "700", "800", "900"};
        int[] data1 = {300, 500, 550, 500, 300, 700, 800, 750, 550, 600, 400, 300};
        int[] data2 = {400, 600, 500, 700, 300, 500, 550, 500, 300, 700, 800, 750};
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
        List<PieEntry> list = new ArrayList<>();
        for (int i = 1; i < 7; i ++) {
            list.add(new PieEntry(i * 20, String.format("第%s区", i)));
        }
        mPieView.setData(list)
                .setShowAnimator(true)
                .refresh();
    }

    private void initWidget() {
        mAlphaRadiusSb = findViewById(R.id.sb_alpha_circle_radius);
        mAlphaSb = findViewById(R.id.sb_alpha_circle_alpha);
        mHoleRadiusSb = findViewById(R.id.sb_hole_circle_radius);
        mPercentage = findViewById(R.id.tv_show_percentage);
        mBlockUpTv = findViewById(R.id.tv_block_up);
        mDefaultTv = findViewById(R.id.tv_default_data);
        mCenterTextTv = findViewById(R.id.tv_center_text);
        mPieView = findViewById(R.id.pie_view);

        mAlphaRadiusSb.setProgress(50);
        mHoleRadiusSb.setProgress(60);
        mAlphaSb.setProgress(40);
    }

    private void initListener() {
        mPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disPlayPercentage = !disPlayPercentage;
                mPieView.setDisPlayPercent(disPlayPercentage).refresh();
            }
        });

        mBlockUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PieEntry> list = new ArrayList<>();
                for (int i = 1; i < 7; i ++) {
                    list.add(new PieEntry(i * 20, String.format("第%s区", i), i == 4));
                }
                mPieView.setData(list)
                        .setShowAnimator(true)
                        .refresh();
            }
        });

        mDefaultTv.setOnClickListener(new View.OnClickListener() {
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
        });

        mCenterTextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disPlayCenterText = !disPlayCenterText;
                mPieView.setShowCenterText(disPlayCenterText).refresh();
            }
        });

        mAlphaSb.setOnSeekBarChangeListener(new SimpleSeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPieView.setCenterAlpha((float) progress / 100).refresh();
            }
        });

        mAlphaRadiusSb.setOnSeekBarChangeListener(new SimpleSeekBarListener() {
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
        });
    }



}

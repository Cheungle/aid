package com.example.aid.ui.notifications;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aid.R;
import com.example.aid.ui.notifications.ageEditActivity;
import com.example.aid.ui.notifications.nameEditActivity;
import com.example.aid.ui.notifications.photoEditActivity;
import com.example.aid.ui.notifications.sexEditActivity;

public class photoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        ((TextView)findViewById(R.id.info_arrowphoto)).setTypeface(font);
        ((TextView)findViewById(R.id.info_arrowname)).setTypeface(font);
        ((TextView)findViewById(R.id.arrow_Sex)).setTypeface(font);
        ((TextView)findViewById(R.id.arrow_age)).setTypeface(font);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        waitAge();
        waitName();
        waitPhoto();
        waitSex();
    }
    private void waitAge() {
        TextView word =  findViewById(R.id.info_age);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , ageEditActivity.class);
                startActivity(i);
            }
        });
        TextView text =  findViewById(R.id.info_showage);
        text.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , ageEditActivity.class);
                startActivity(i);
            }

        });
        TextView icon =  findViewById(R.id.arrow_age);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , ageEditActivity.class);
                startActivity(i);
            }

        });
    }
    private void waitSex() {
        TextView word =  findViewById(R.id.info_sex);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , sexEditActivity.class);
                startActivity(i);
            }
        });
        TextView text =  findViewById(R.id.info_showsex);
        text.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , sexEditActivity.class);
                startActivity(i);
            }

        });
        TextView icon =  findViewById(R.id.arrow_Sex);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , sexEditActivity.class);
                startActivity(i);
            }

        });
    }
    private void waitName() {
        TextView word =  findViewById(R.id.info_name);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , nameEditActivity.class);
                startActivity(i);
            }
        });
        TextView text =  findViewById(R.id.info_showname);
        text.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , nameEditActivity.class);
                startActivity(i);
            }

        });
        TextView icon = findViewById(R.id.info_arrowname);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , nameEditActivity.class);
                startActivity(i);
            }

        });
    }
    private void waitPhoto() {
        TextView word = findViewById(R.id.info_photo);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this, photoEditActivity.class);
                startActivity(i);
            }
        });
        TextView icon =  findViewById(R.id.info_arrowphoto);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , photoEditActivity.class);
                startActivity(i);
            }

        });
        ImageView photo = findViewById(R.id.info_showphoto);
        photo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , photoEditActivity.class);
                startActivity(i);
            }

        });
    }
}

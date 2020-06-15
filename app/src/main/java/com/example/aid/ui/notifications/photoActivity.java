package com.example.aid.ui.notifications;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aid.MainActivity;
import com.example.aid.R;
import com.example.aid.data.DAL.UserDAL;
import com.example.aid.data.model.user;

public class photoActivity extends AppCompatActivity {
    private String id;
    private String name;
    private byte[] photo = null;
    private String age;
    private String sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        String id = getIntent().getStringExtra("id");
        this.id=id;
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        ((TextView)findViewById(R.id.info_arrowphoto)).setTypeface(font);
        ((TextView)findViewById(R.id.info_arrowname)).setTypeface(font);
        ((TextView)findViewById(R.id.arrow_Sex)).setTypeface(font);
        ((TextView)findViewById(R.id.arrow_age)).setTypeface(font);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //从数据库中读出数据显示
        ImageView photo = findViewById(R.id.info_showphoto);
        TextView name = findViewById(R.id.info_showname);
        TextView number = findViewById(R.id.info_shownum);
        TextView sex = findViewById(R.id.info_showsex);
        TextView age = findViewById(R.id.info_showage);
        number.setText(this.id);
        UserDAL userDAL = new UserDAL(this);
        user user = userDAL.selectPhotoPage(this.id);
        //Log.v("photof",String.valueOf(this.photo));

        this.photo = user.getHead();
        if(this.photo==null){
            photo.setImageResource(R.mipmap.photo);
        }else{
            //Log.v("photo",String.valueOf(this.photo));
            BitmapFactory.Options opts = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeByteArray(this.photo, 0, this.photo.length, opts);
            photo.setImageBitmap(bitmap);
        }
        this.name = user.getName();
        this.age = String.valueOf(user.getAge());
        this.sex = String.valueOf(user.getSex());
        name.setText(this.name);
        if(this.sex == "1"){
            sex.setText("女");
        }else{
            if(this.sex == "0"){
                sex.setText("男");
            }else{

            }
        }
        age.setText(this.age);
        waitAge();
        waitName();
        waitPhoto();
        waitSex();
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 1:{
                    TextView age = findViewById(R.id.info_showage);
                    age.setText(data.getStringExtra("age_return"));
                    this.age = data.getStringExtra("age_return");
                    break;
                }
                case 2:{
                    TextView sex = findViewById(R.id.info_showsex);
                    String sexEdit =  data.getStringExtra("sex_return");
                    if(sexEdit=="0"){
                        sex.setText("男");
                    }else{
                        sex.setText("女");
                    }
                    this.sex = data.getStringExtra("sex_return");
                    break;
                }
                case 3:{
                    TextView name = findViewById(R.id.info_showname);
                    name.setText(data.getStringExtra("name_return"));
                    this.name = data.getStringExtra("name_return");
                    break;
                }

                default:
                    break;
            }
        }
    }
    private void waitAge() {
        TextView word =  findViewById(R.id.info_age);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , ageEditActivity.class);
                i.putExtra("id",photoActivity.this.id);
                i.putExtra("age",photoActivity.this.age);
                //startActivity(i);
                startActivityForResult(i,1);
            }
        });
        TextView text =  findViewById(R.id.info_showage);
        text.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , ageEditActivity.class);
                i.putExtra("id",photoActivity.this.id);
                i.putExtra("age",photoActivity.this.age);
                startActivityForResult(i,1);

            }

        });
        TextView icon =  findViewById(R.id.arrow_age);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , ageEditActivity.class);
                i.putExtra("id",photoActivity.this.id);
                i.putExtra("age",photoActivity.this.age);
                startActivityForResult(i,1);

            }

        });
    }
    private void waitSex() {
        TextView word =  findViewById(R.id.info_sex);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , sexEditActivity.class);
                i.putExtra("id",photoActivity.this.id);
                i.putExtra("sex",photoActivity.this.sex);
                startActivityForResult(i,2);
            }
        });
        TextView text =  findViewById(R.id.info_showsex);
        text.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , sexEditActivity.class);
                i.putExtra("id",photoActivity.this.id);
                i.putExtra("sex",photoActivity.this.sex);
                startActivityForResult(i,2);
            }

        });
        TextView icon =  findViewById(R.id.arrow_Sex);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , sexEditActivity.class);
                i.putExtra("id",photoActivity.this.id);
                i.putExtra("sex",photoActivity.this.sex);
                startActivityForResult(i,2);
            }

        });
    }
    private void waitName() {
        TextView word =  findViewById(R.id.info_name);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , nameEditActivity.class);
                i.putExtra("id",photoActivity.this.id);
                i.putExtra("name",photoActivity.this.name);
                startActivityForResult(i,3);
            }
        });
        TextView text =  findViewById(R.id.info_showname);
        text.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , nameEditActivity.class);
                i.putExtra("id",photoActivity.this.id);
                i.putExtra("name",photoActivity.this.name);
                startActivityForResult(i,3);
            }

        });
        TextView icon = findViewById(R.id.info_arrowname);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(photoActivity.this , nameEditActivity.class);
                i.putExtra("id",photoActivity.this.id);
                i.putExtra("name",photoActivity.this.name);
                startActivityForResult(i,3);
            }

        });
    }
    private void waitPhoto() {
        TextView word = findViewById(R.id.info_photo);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
                Intent i = new Intent(photoActivity.this, photoEditActivity.class);
                i.putExtra("id",photoActivity.this.id);
                i.putExtra("photo",photoActivity.this.photo);
                startActivity(i);
            }
        });
        TextView icon =  findViewById(R.id.info_arrowphoto);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
                Intent i = new Intent(photoActivity.this , photoEditActivity.class);
                i.putExtra("id",photoActivity.this.id);
                i.putExtra("photo",photoActivity.this.photo);
                startActivity(i);
            }

        });
        ImageView photo = findViewById(R.id.info_showphoto);
        photo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
                Intent i = new Intent(photoActivity.this , photoEditActivity.class);
                i.putExtra("id",photoActivity.this.id);
                i.putExtra("photo",photoActivity.this.photo);
                startActivity(i);
            }

        });
    }

}

package com.example.loginui.ui.notifications;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.loginui.R;

import java.io.File;
import java.util.List;

public class photoEditActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.photo_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.photoedit_take:
                    {Toast.makeText(photoEditActivity.this, "拍照", Toast.LENGTH_SHORT).show();

                        }

                        break;
                    case R.id.photoedit_choose:
                        Toast.makeText(photoEditActivity.this, "选择", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.photoedit_former:
                        Toast.makeText(photoEditActivity.this, "查看上一张", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.photoedit_save:
                        Toast.makeText(photoEditActivity.this, "保存", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.photoedit_cancel:
                        Toast.makeText(photoEditActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.photo_menu, menu);
        return true;
    }
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

}


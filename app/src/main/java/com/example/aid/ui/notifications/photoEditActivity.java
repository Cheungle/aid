package com.example.aid.ui.notifications;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.UiAutomation;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.aid.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class photoEditActivity extends AppCompatActivity {

    //相册请求码
    private static final int ALBUM_REQUEST_CODE = 7;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 8;
    //剪裁请求码
    private static final int CROP_SMALL_PICTURE = 9;
    //从相册获得图片
    Bitmap bitmap;
    //判断返回到的Activity
    private static final int IMAGE_REQUEST_CODE = 0;
    Uri imageUri;
    File outputimage;
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
                setDialog();
                return false;
                }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.photo_menu, menu);
        return true;
    }
    private void setDialog(){
        final Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.photo_menu, null);
        //初始化视图
        waitClick(root);
        Button cancel = root.findViewById(R.id.photoedit_cancel);
        cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mCameraDialog.dismiss();
            }
        });
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

   public void waitClick(LinearLayout root){
        Button album = root.findViewById(R.id.photoedit_album);
        album.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
               i.addCategory(Intent.CATEGORY_OPENABLE);
               i.setType("image/*") ;
               startActivityForResult(i,ALBUM_REQUEST_CODE);
           }
       });
       Button camera = root.findViewById(R.id.photoedit_take);
       camera.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               photoEditActivity.this.outputimage = new File(getExternalCacheDir(),"output_image.jpg");
               if(photoEditActivity.this.outputimage.exists()){
                   photoEditActivity.this.outputimage.delete();
               }
               try {
                   photoEditActivity.this.outputimage.createNewFile();
                   if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                       photoEditActivity.this.imageUri = FileProvider.getUriForFile(photoEditActivity.this,"com.example.cameraalbumtest.fileprovider",photoEditActivity.this.outputimage);
                   }else{
                       photoEditActivity.this.imageUri = Uri.fromFile(photoEditActivity.this.outputimage);
                   }
                   Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
                   i.putExtra(MediaStore.EXTRA_OUTPUT,CAMERA_REQUEST_CODE);
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }
       });
   }
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            // 用户没有进行有效的设置操作，返回
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
                return;
            }
            ImageView show = findViewById(R.id.photo);
            switch (requestCode) {
                case ALBUM_REQUEST_CODE:
                    {
                        if(requestCode==RESULT_OK &&data!=null){
                           Uri uri = data.getData();
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                                show.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                case CAMERA_REQUEST_CODE:
                  {
                      if(resultCode==RESULT_OK){
                          Bitmap bitmap = null;
                          try {
                              bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(photoEditActivity.this.imageUri));
                          } catch (FileNotFoundException e) {
                              e.printStackTrace();
                          }

                          try {
                              show.setImageBitmap(rotateIfRequired(bitmap));
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                      }
                  }

                case CROP_SMALL_PICTURE:
                    break;
            }


    }
//    private Uri getBitmapFromUri(){
//
//    }
    private Bitmap rotateIfRequired(Bitmap bitmap) throws IOException {
        ExifInterface exif = new ExifInterface(photoEditActivity.this.outputimage.getPath());
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
        switch (orientation){
            case ExifInterface.ORIENTATION_ROTATE_90 :
                return rotateBitmap(bitmap,90);
            case ExifInterface.ORIENTATION_ROTATE_180 :
                return rotateBitmap(bitmap,180);
            case ExifInterface.ORIENTATION_ROTATE_270 :
                return rotateBitmap(bitmap,270);
            default:
                return bitmap;
        }
    }
    private Bitmap rotateBitmap(Bitmap bitmap,int degree){
        Matrix matrix =new Matrix();
        matrix.postRotate(Float.intBitsToFloat(degree));
        Bitmap rotate = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        bitmap.recycle();
        return rotate;
    }

}


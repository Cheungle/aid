package com.example.aid.ui.message;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aid.R;

public class MessageContentActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_message_content);

        Bundle bundle=getIntent().getExtras();

    }
}

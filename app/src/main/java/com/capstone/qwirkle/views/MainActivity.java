package com.capstone.qwirkle.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.capstone.qwirkle.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View view){
        Intent intent=new Intent(getApplicationContext(),ConnectActivity.class);
        startActivity(intent);
    }
    public void exit(View view){
        finishAffinity();
    }
}
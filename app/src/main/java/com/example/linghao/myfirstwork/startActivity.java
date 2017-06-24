package com.example.linghao.myfirstwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.linghao.myfirstwork.game1.*;

public class startActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_start2);
        Handler handler = new Handler();
        handler.postDelayed(new startActivity.Loading(), 2000);
    }

    private class Loading implements Runnable {
        public void run() {
            Intent intent = new Intent();
            intent.setClass(startActivity.this, loginActivity.class);
            startActivity(intent);
            startActivity.this.finish();
        }
    }

}


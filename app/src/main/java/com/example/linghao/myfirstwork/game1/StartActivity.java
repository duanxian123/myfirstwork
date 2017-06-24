package com.example.linghao.myfirstwork.game1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.linghao.myfirstwork.R;

/**
 * ��ӭ����
 * @author Administrator
 *
 */
public class StartActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_start);
		Handler handler = new Handler();
		handler.postDelayed(new Loading(), 2000);
	}
	
	private class Loading implements Runnable {
		public void run() {
			Intent intent = new Intent();
			intent.setClass(StartActivity.this, MainActivity.class);
			startActivity(intent);
			StartActivity.this.finish();
		}
	}

}

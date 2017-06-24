package com.example.linghao.myfirstwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.linghao.myfirstwork.R.id.ed1;

public class tloginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText ed1,ed2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tlogin);
        ed1= (EditText) findViewById(R.id.edit_tzh);
        ed2= (EditText) findViewById(R.id.edit_tpsw);
        Button bt1= (Button) findViewById(R.id.bt_dengluok);
        Button bt2= (Button) findViewById(R.id.bt_denglucancel);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
        case R.id.bt_dengluok:
            if(ed1.getText().toString().equals("root")&&ed2.getText().toString().equals("root")){
                Intent intent=new Intent(tloginActivity.this,MainActivity.class);
                startActivity(intent);}
                else
                Toast.makeText(tloginActivity.this, "帐号或者密码错误", Toast.LENGTH_SHORT).show();


        break;
            case  R.id.bt_denglucancel:
                Intent intent1=new Intent(tloginActivity.this,loginActivity.class);
                startActivity(intent1);
                break;}
        }
    }


package com.example.linghao.myfirstwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.linghao.myfirstwork.shujuku.Student;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ed_zh,ed_psw;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "c7f34b02339951edab89fa423df45013");
        ed_zh= (EditText) findViewById(R.id.edit_zh);
        ed_psw= (EditText) findViewById(R.id.edit_psw);
        Button bt_zc= (Button) findViewById(R.id.bt_zhuce);
        Button bt_denglu= (Button) findViewById(R.id.bt_denglu);
        Button bt_jsdenglu= (Button) findViewById(R.id.bt_jsdenglu);
        bt_zc.setOnClickListener(this);
        bt_denglu.setOnClickListener(this);
        bt_jsdenglu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt_zhuce:
                Intent intent=new Intent(loginActivity.this,zhuceActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_denglu:
                final String gzh=ed_zh.getText().toString();
                final String gpsw=ed_psw.getText().toString();
                BmobQuery<Student> query=new BmobQuery<Student>();
                query.addWhereEqualTo("zhanghao",gzh );
                query.addWhereEqualTo("psw", gpsw);
                query.findObjects(new FindListener<Student>() {

                    public void done(List<Student> list, BmobException e) {
                        if (e==null)
                        {
                            String zh=list.get(0).getZhanghao().toString();
                            String psw=list.get(0).getPsw().toString();
                            if(gzh.equals(zh)==true&&gpsw.equals(psw)==true){

                                Intent success=new Intent(loginActivity.this,Main2Activity.class);
                                success.putExtra("zhanghao",zh);

                                startActivity(success);
                            }
                        }  else
                        {
                            Toast.makeText(getApplication(), "帐号密码错误", Toast.LENGTH_LONG).show();
                        }

                    }});
                break;
            case R.id.bt_jsdenglu:
                Intent intent1=new Intent(loginActivity.this,tloginActivity.class);
                startActivity(intent1);
                break;

        }}}

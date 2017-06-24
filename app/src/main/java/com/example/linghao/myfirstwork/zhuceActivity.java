package com.example.linghao.myfirstwork;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import cn.bmob.v3.listener.SaveListener;

public class zhuceActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText ed_zczh,ed_zcpsw,ed_zcpswagain,ed_class,ed_sname,ed_tel;
    private Button bt_ok,bt_cancel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        Bmob.initialize(this, "c7f34b02339951edab89fa423df45013");
        bt_ok= (Button) findViewById(R.id.bt_ok);
        bt_cancel=(Button)findViewById(R.id.bt_cancel);
        ed_zczh= (EditText) findViewById(R.id.edit_zczh);
        ed_tel= (EditText) findViewById(R.id.edit_tel);
        ed_zcpsw= (EditText) findViewById(R.id.edit_zcpsw);
        ed_zcpswagain= (EditText) findViewById(R.id.edit_zcpswagain);
        ed_class= (EditText) findViewById(R.id.edit_class);
        ed_sname= (EditText) findViewById(R.id.edit_name);
        bt_ok.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_ok:

          if (ed_tel.getText().toString().equals("")==false) {
              BmobQuery<Student> query = new BmobQuery<Student>();
              query.addWhereEqualTo("zhanghao", ed_zczh.getText().toString());
              query.findObjects(new FindListener<Student>() {
                  public void done(List<Student> list, BmobException e) {
                      if (e == null) {
                          String b = list.get(0).getZhanghao().toString();
                          Log.i("info", "2");
                          if (b.equals(ed_zczh.getText().toString()) == true) {
                              Toast.makeText(getApplication(), "帐号已存在", Toast.LENGTH_LONG).show();
                              ed_zcpsw.setText("");
                              ed_zczh.setText("");
                              ed_zcpswagain.setText("");
                          }
                      } else if (ed_zcpsw.getText().toString().equals(ed_zcpswagain.getText().toString()) == true) {
                          Log.i("info", "1");
                          Student student1 = new Student();
                          student1.setZhanghao(ed_zczh.getText().toString());
                          student1.setPsw(ed_zcpsw.getText().toString());
                          student1.setAclass(ed_class.getText().toString());
                          student1.setName(ed_sname.getText().toString());
                          student1.setTel(ed_tel.getText().toString());
                          student1.save(new SaveListener<String>() {
                              public void done(String objectId, BmobException e) {
                                  if (e == null) {
                                      Toast.makeText(getApplication(), "注册成功", Toast.LENGTH_SHORT).show();
                                      Intent intent1 = new Intent(zhuceActivity.this, loginActivity.class);
                                      startActivity(intent1);
                                  } else {
                                      Toast.makeText(getApplication(), "注册失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                  }
                              }
                          });
                          Student s1 = new Student();

                      } else {
                          Toast.makeText(getApplication(), "两次密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
                          ed_zcpsw.setText(null);
                          Log.i("info", "3");
                          ed_zcpswagain.setText(null);
                      }
                  }
              });


          }
          else
              Toast.makeText(this,"请输入手机号码",Toast.LENGTH_SHORT).show();

                break;
            case R.id.bt_cancel:
                Intent intent=new Intent(zhuceActivity.this,loginActivity.class);
                startActivity(intent);
                break;
        }
    }

}

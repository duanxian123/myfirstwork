package com.example.linghao.myfirstwork;

import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.linghao.myfirstwork.shujuku.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class testanpaiActivity extends AppCompatActivity {
    Button bt_sc, bt_zj;
    EditText ed_type, ed_address, ed_time;
    ListView listView1;
    SimpleAdapter adapter;
  private SwipeRefreshLayout swipeRefresh;
    protected void onCreate(Bundle savedInstanceState) {
        Bmob.initialize(this, "c7f34b02339951edab89fa423df45013");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testanpai);
        bt_sc = (Button) findViewById(R.id.sc);
        bt_zj = (Button) findViewById(R.id.zj);
        ed_address = (EditText) findViewById(R.id.ed_adress);
        ed_type = (EditText) findViewById(R.id.ed_type);
        ed_time = (EditText) findViewById(R.id.ed_time);


        bt_zj.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Test test = new Test();

                test.setTestaddress(ed_address.getText().toString());
                test.setTesttime(ed_time.getText().toString());
                test.setTesttype(ed_type.getText().toString());

                test.save(new SaveListener<String>() {
                    public void done(String objectId, BmobException e) {
                        if (e == null) {
                            shanchu();
                            Toast.makeText(testanpaiActivity.this, "已安排" + ed_type.getText().toString() + "考试", Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplication(), "安排失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        });
        bt_sc.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(testanpaiActivity.this);
                dialog.setTitle("警告");
                dialog.setMessage("确定删除"+ed_type.getText().toString()+"吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BmobQuery<Test> query=new BmobQuery<Test>();

                        query.addWhereEqualTo("testtype", ed_type.getText().toString());
                        query.findObjects(new FindListener<Test>() {

                            public void done(List<Test> list, BmobException e) {
                                if(e==null){
                                    String  b = list.get(0).getObjectId().toString();


                                    Test test=new Test();
                                    test.setObjectId(b);
                                    test.delete(new UpdateListener() {
                                        public void done(BmobException e) {
                                            if(e==null){
                                                shanchu();
                                                Toast.makeText(testanpaiActivity.this, "已删除" + ed_type.getText().toString() + "考试", Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(testanpaiActivity.this, "未删除" + ed_type.getText().toString() + "考试", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    });}
                            }
                        });

                    }

                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();



            }
        });



shanchu();

    }
    private  void shanchu(){
        BmobQuery<Test> query = new BmobQuery<Test>();
        query.addQueryKeys("testtime,testtype,testaddress");
        query.findObjects(new FindListener<Test>()

        {

            public void done(List<Test> list, BmobException e) {
                if (e == null) {

                    List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
                    for (int i = 0; i < list.size(); i++) {
                        Map<String, Object> m = new HashMap<String, Object>();
                        m.put("first", list.get(i).getTesttype().toString());

                        m.put("second", list.get(i).getTesttime().toString());
                        m.put("third", list.get(i).getTestaddress().toString());

                        mm.add(m);

                    }
                    adapter = new SimpleAdapter(testanpaiActivity.this, mm, R.layout.item, new String[]{"first", "second", "third"}, new int[]{R.id.aclass, R.id.name, R.id.zhanghao});


                    listView1= (ListView) findViewById(R.id.list_view);
                    Log.i("afdasdfdsf","5");
                    listView1.setAdapter(adapter);
                    Log.i("afdasdfdsf","6");
                }

            }
        });
    }

}
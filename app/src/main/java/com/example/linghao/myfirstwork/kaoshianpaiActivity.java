package com.example.linghao.myfirstwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.linghao.myfirstwork.shujuku.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class kaoshianpaiActivity extends AppCompatActivity {
    ListView listView1;
    SimpleAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaoshianpai);
        Bmob.initialize(this, "c7f34b02339951edab89fa423df45013");
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
                    adapter = new SimpleAdapter(kaoshianpaiActivity.this, mm, R.layout.item, new String[]{"first", "second", "third"}, new int[]{R.id.aclass, R.id.name, R.id.zhanghao});


                    listView1= (ListView) findViewById(R.id.list_view);
                    Log.i("afdasdfdsf","5");
                    listView1.setAdapter(adapter);
                    Log.i("afdasdfdsf","6");
                }

            }
        });
    }
}

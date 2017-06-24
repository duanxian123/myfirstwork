package com.example.linghao.myfirstwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.linghao.myfirstwork.shujuku.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class studentinfoActivity extends AppCompatActivity {
    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        Bmob.initialize(this, "c7f34b02339951edab89fa423df45013");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_studentinfo);

        BmobQuery<Student> query = new BmobQuery<Student>();
        query.addQueryKeys("aclass,zhanghao,name");
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if(e==null){

//                    ArrayList<String> m = new ArrayList<String>();
//                    {m.add(list.get(i).getZhanghao().toString());
//                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(studentinfoActivity.this,
//                            android.R.layout.simple_list_item_1,m);
                    List<Map<String, Object>> mm=new ArrayList<Map<String,Object>>();
                    for (int i = 0; i < list.size() ; i++)
                    {Map<String, Object> m=new HashMap<String,Object>();
                        m.put("first",list.get(i).getAclass().toString());

                        m.put("second",list.get(i).getName().toString());
                        m.put("third",list.get(i).getZhanghao().toString());
                        Log.i("class",list.get(i).getAclass().toString());
                        Log.i("name",list.get(i).getName().toString());
                        Log.i("zhanghao",list.get(i).getZhanghao().toString());
                        mm.add(m);

                    }
                    SimpleAdapter adapter=new SimpleAdapter(studentinfoActivity.this,mm,R.layout.item,new String[]{"first","second","third"},new int[]{R.id.aclass,R.id.name,R.id.zhanghao});


                    listView= (ListView) findViewById(R.id.list_view);
                    listView.setAdapter(adapter);

                }

            }
        });

    }
}

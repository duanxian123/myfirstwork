package com.example.linghao.myfirstwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.linghao.myfirstwork.shujuku.Post;
import com.example.linghao.myfirstwork.shujuku.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class PostActivity extends AppCompatActivity {
EditText ed1;
    Button bt1;
    ListView listview;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ed1= (EditText) findViewById(R.id.liuyan);
        bt1= (Button) findViewById(R.id.send);
        Bmob.initialize(this, "c7f34b02339951edab89fa423df45013");

        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent=getIntent();
                String value = intent.getStringExtra("zhanghao");
                Log.i("zhanghao",value);
                BmobQuery<Student> query=new BmobQuery<Student>();
                query.addWhereEqualTo("zhanghao", value);
                query.findObjects(new FindListener<Student>() {
                    public void done(List<Student> list, BmobException e) {
                        if (e == null) {
                            String  a = list.get(0).getName().toString();
                            Log.i("a=",a);
                            Post post=new Post();
                            String liuyan=ed1.getText().toString();
                            post.setContent(liuyan);
                            post.setAuthor(a);
                            post.save(new SaveListener<String>() {
                                public void done(String objectId,BmobException e) {
                                    if(e==null){
                                        Log.i("bmob","保存成功");
                                    }else{
                                        Log.i("bmob","保存失败："+e.getMessage());
                                    }
                                }
                            });
                        }}
                });
                liuyanxianshi();

            }
        });
        liuyanxianshi();

    }
    //社区显示
    public  void liuyanxianshi(){
        BmobQuery<Post> query = new BmobQuery<Post>();
        query.addQueryKeys("author,content,createdAt");
        query.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                if(e==null){

                    List<Map<String, Object>> mm=new ArrayList<Map<String,Object>>();
                    for (int i = list.size()-1; i >=0 ; i--)
                    {Map<String, Object> m=new HashMap<String,Object>();
                        m.put("first",list.get(i).getAuthor().toString());
                        m.put("third",list.get(i).getCreatedAt().toString());

                        m.put("second",list.get(i).getContent().toString());
                        Log.i("time",list.get(i).getCreatedAt().toString());
                        Log.i("author",list.get(i).getAuthor().toString());
                        Log.i("content",list.get(i).getContent().toString());
                        mm.add(m);

                    }
                    SimpleAdapter adapter=new SimpleAdapter(PostActivity.this,mm,R.layout.item_liuyan,new String[]{"first","third","second"},new int[]{R.id.author,R.id.time,R.id.content});


                    listview= (ListView) findViewById(R.id.list_view);
                    listview.setAdapter(adapter);

                }

            }
        });

    }
}

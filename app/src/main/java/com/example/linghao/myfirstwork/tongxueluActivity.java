package com.example.linghao.myfirstwork;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.linghao.myfirstwork.shujuku.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class tongxueluActivity extends Activity {
    private ListView lv;
    String number;
    /*定义一个动态数组*/
    String a;
   ArrayList<Map<String, Object>> mm=new ArrayList<Map<String,Object>>();/** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongxuelu);







        Bmob.initialize(this, "c7f34b02339951edab89fa423df45013");
        BmobQuery<Student> query = new BmobQuery<Student>();

        query.addQueryKeys("tel,name,zhanghao,aclass");
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if(e==null){

//                    ArrayList<String> m = new ArrayList<String>();
//                    {m.add(list.get(i).getZhanghao().toString());
//                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(studentinfoActivity.this,
//                            android.R.layout.simple_list_item_1,m);
                    Log.i("11111","1111111");
                    for (int i = 0; i < list.size() ; i++)
                    {Map<String, Object> m=new HashMap<String,Object>();
                        m.put("first",list.get(i).getName().toString());
                        m.put("second",list.get(i).getTel().toString());
                        m.put("third",list.get(i).getAclass().toString());
                        m.put("forth",list.get(i).getZhanghao().toString());
                        Log.i("name",list.get(i).getName().toString());

                        Log.i("tel",list.get(i).getTel().toString());
                        mm.add(m);

                    }

                    lv = (ListView) findViewById(R.id.list);
                   registerForContextMenu(lv);
                    MyAdapter mAdapter = new MyAdapter(tongxueluActivity.this,mm);//得到一个MyAdapter对象
                    lv.setAdapter(mAdapter);//为ListView绑定Adapter /*为ListView添加点击事件*/
//                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                                long arg3) {
//
//
//                             number = mm.get(arg2).get("second").toString();
//
//                            Log.v("MyListViewBase", "你点击了ListView条目" + arg2);//在LogCat中输出信息
//                        }
//                    });

                }

            }
        });
        Log.i("info", String.valueOf(mm.size()));




    }
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        //设置Menu显示内容
        menu.setHeaderTitle("menu");
        menu.add(1, 1, 1, "call");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = menuInfo.position;
        number = mm.get(position).get("second").toString();

        switch (item.getItemId()){
            case 1:
                Log.i("num",number);
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                Uri tel = Uri.parse("tel:" +number);
                intent1.setData(tel);
                startActivity(intent1);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
//    private ArrayList<HashMap<String, Object>> getDate(){
//
//        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,Object>>();
//    /*为动态数组添加数据*/
//        for(int i=0;i<30;i++)
//        {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("ItemTitle", "第"+i+"行");
//            map.put("ItemText", "这是第"+i+"行");
//            listItem.add(map);
//        }
//        return listItem;
//
//    }}

package com.example.linghao.myfirstwork.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.linghao.myfirstwork.R;
import com.example.linghao.myfirstwork.shujuku.Score;
import com.example.linghao.myfirstwork.shujuku.Test;
import com.example.linghao.myfirstwork.base.BaseFragment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/5/23.
 */

public class ScoreCountFragment extends BaseFragment {
    ListView listView;
    Button bt_fw,bt_cx;
    TextView tv1,tv2,tv3,tv4;
    TextView t1,t2,t3,t4;
    EditText ed1,ed2,ed3,ed4;
    @Override
    protected void initInject() {
        tv1= (TextView) mView.findViewById(R.id.tv1);
        tv2= (TextView) mView.findViewById(R.id.tv2);
        tv3= (TextView) mView.findViewById(R.id.tv3);
        tv4= (TextView) mView.findViewById(R.id.tv4);
        t1= (TextView) mView.findViewById(R.id.t1);
        t2= (TextView) mView.findViewById(R.id.t2);
        t3= (TextView) mView.findViewById(R.id.t3);
        t4= (TextView) mView.findViewById(R.id.t4);
        ed1= (EditText) mView.findViewById(R.id.ed1);
        ed2= (EditText) mView.findViewById(R.id.ed2);
        ed3= (EditText) mView.findViewById(R.id.ed3);
        ed4= (EditText) mView.findViewById(R.id.ed4);
        bt_cx= (Button) mView.findViewById(R.id.cx);
        bt_fw= (Button) mView.findViewById(R.id.bt_fw);

        bt_fw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(null);
                ed2.setText(null);
                ed3.setText(null);
                ed4.setText(null);
                chaxun();
            }
        });
        bt_cx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("实现","1");
                BmobQuery<Score> eq1 = new BmobQuery<Score>();
                Log.i("实现","2");
                if(ed1.getText().toString().length()==0){ed1.setText("0");}
                if(ed2.getText().toString().length()==0){ed2.setText("0");}
                if(ed3.getText().toString().length()==0){ed3.setText("0");}
                if(ed4.getText().toString().length()==0){ed4.setText("0");}

                eq1.addWhereGreaterThan("test1", Integer.parseInt(ed1.getText().toString()));
                Log.i("shuru",ed1.getText().toString());
                BmobQuery<Score> eq2 = new BmobQuery<Score>();
                eq2.addWhereGreaterThan("test2",Integer.parseInt(ed2.getText().toString()) );
                BmobQuery<Score> eq3 = new BmobQuery<Score>();
                eq2.addWhereGreaterThan("test3",Integer.parseInt(ed3.getText().toString()) );
                BmobQuery<Score> eq4 = new BmobQuery<Score>();
                eq2.addWhereGreaterThan("test4",Integer.parseInt(ed4.getText().toString()) );
                Log.i("shuru",ed2.getText().toString());
                List<BmobQuery<Score>> andQuerys = new ArrayList<BmobQuery<Score>>();
                andQuerys.add(eq1);
                andQuerys.add(eq2);
                BmobQuery<Score> query = new BmobQuery<Score>();
                query.and(andQuerys);
                query.findObjects(new FindListener<Score>() {
                    @Override
                    public void done(List<Score> list, BmobException e) {
                        Log.i("实现","3");
                        if(e==null){List<Map<String, Object>> mm=new ArrayList<Map<String,Object>>();
                            for (int i = 0; i < list.size() ; i++)
                            {Map<String, Object> m=new HashMap<String,Object>();
                                m.put("name",list.get(i).getName().toString());
                                m.put("first",list.get(i).getTest1().toString());
                                m.put("second",list.get(i).getTest2().toString());
                                m.put("third",list.get(i).getTest3().toString());
                                m.put("forth",list.get(i).getTest4().toString());
                                mm.add(m);

                            }
                            SimpleAdapter adapter=new SimpleAdapter(getActivity(),mm,R.layout.item5,new String[]{"name","first","second","third","forth"},new int[]{R.id.name,R.id.first,R.id.second,R.id.third,R.id.forth});
                            listView= (ListView) mView.findViewById(R.id.list_view);
                            Log.i("实现","4");
                            listView.setAdapter(adapter);
                            Log.i("实现","6");
                        }
                    }

                });}});
//                BmobQuery<Score> query = new BmobQuery<Score>();
//                query.addWhereLessThan("test4", 50);
//                query.findObjects(new FindListener<Score>() {
//                    @Override
//                    public void done(List<Score> list, BmobException e) {
//                        if(e==null){
//
//                            List<Map<String, Object>> mm=new ArrayList<Map<String,Object>>();
//                            for (int i = 0; i < list.size() ; i++)
//                            {Map<String, Object> m=new HashMap<String,Object>();
//                                m.put("name",list.get(i).getName().toString());
//                                m.put("first",list.get(i).getTest1().toString());
//                                m.put("second",list.get(i).getTest2().toString());
//                                m.put("third",list.get(i).getTest3().toString());
//                                m.put("forth",list.get(i).getTest4().toString());
//                                mm.add(m);
//
//                            }
//                            Log.i("实现","6");
//                            SimpleAdapter adapter=new SimpleAdapter(ScoreCountActivity.this,mm,R.layout.item5,new String[]{"name","first","second","third","forth"},new int[]{R.id.name,R.id.first,R.id.second,R.id.third,R.id.forth});
//                            Log.i("实现","1");
//
//                            listView= (ListView) findViewById(R.id.list_view);
//                            Log.i("实现","2");
//                            listView.setAdapter(adapter);
//                            Log.i("实现","3");
//
//                        }
//
//                    }
//                });


        Bmob.initialize(getActivity(), "c7f34b02339951edab89fa423df45013");
//将学生表姓名放入成绩表中
//        BmobQuery<Student> query = new BmobQuery<Student>();
//        query.addQueryKeys("name");
//        query.findObjects(new FindListener<Student>(){
//
//            public void done(List<Student> list, BmobException e) {
//                if(e==null){
//                    for (int i = 0; i < list.size() ; i++){
//                   String m=list.get(i).getName().toString();
//
//                        Log.i("sadas",m);
//

//                    });
//                }
//            }}
//        });
        BmobQuery<Test> query1 = new BmobQuery<Test>();
        query1.addQueryKeys("testtype");
        query1.findObjects(new FindListener<Test>() {

            public void done(List<Test> list, BmobException e) {
                for (int i = 0; i < 4; i++){
                    switch (i){
                        case 0:

                            tv1.setText(list.get(i).getTesttype().toString());
                            t1.setText(list.get(i).getTesttype().toString()+">");
                            break;
                        case 1:

                            tv2.setText(list.get(i).getTesttype().toString());
                            t2.setText(list.get(i).getTesttype().toString()+">");
                            break;
                        case 2:

                            tv3.setText(list.get(i).getTesttype().toString());
                            t3.setText(list.get(i).getTesttype().toString()+">");
                            break;
                        case 3:

                            tv4.setText(list.get(i).getTesttype().toString());
                            t4.setText(list.get(i).getTesttype().toString()+">");
                            break;
                    }

                }
            }
        });
        chaxun();
    }
    //查询整个成绩表
    private void chaxun(){
        BmobQuery<Score> query = new BmobQuery<Score>();

        query.addQueryKeys("name,test1,test2,test3,test4");

        query.findObjects(new FindListener<Score>() {
            @Override
            public void done(List<Score> list, BmobException e) {
                if(e==null){

                    List<Map<String, Object>> mm=new ArrayList<Map<String,Object>>();
                    for (int i = 0; i < list.size() ; i++)
                    {Map<String, Object> m=new HashMap<String,Object>();
                        m.put("name",list.get(i).getName().toString());
                        m.put("first",list.get(i).getTest1().toString());
                        m.put("second",list.get(i).getTest2().toString());
                        m.put("third",list.get(i).getTest3().toString());
                        m.put("forth",list.get(i).getTest4().toString());
//                        Log.i("name",list.get(i).getName().toString());
//                        Log.i("first",list.get(i).getTest1().toString());
//                        Log.i("second",list.get(i).getTest2().toString());
//                        Log.i("third",list.get(i).getTest3().toString());
//                        Log.i("forth",list.get(i).getTest4().toString());
                        mm.add(m);

                    }

                    SimpleAdapter adapter=new SimpleAdapter(getActivity(),mm,R.layout.item5,new String[]{"name","first","second","third","forth"},new int[]{R.id.name,R.id.first,R.id.second,R.id.third,R.id.forth});


                    listView= (ListView) mView.findViewById(R.id.list_view);

                    listView.setAdapter(adapter);


                }

            }
        });
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_score_count;
    }
}

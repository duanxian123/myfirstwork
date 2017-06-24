package com.example.linghao.myfirstwork;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.linghao.myfirstwork.shujuku.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

class MyAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局 /*构造函数*/
    private ArrayList<Map<String, Object>> listItems;
    public MyAdapter(Context context, ArrayList<Map<String, Object>> listItems) {
        this.context=context;
        this.mInflater = LayoutInflater.from(context);
        this.listItems=listItems;
    }

    @Override
    public int getCount() {

        return listItems.size();//返回数组的长度
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //观察convertView随ListView滚动情况

        Log.v("MyListViewBase", "getView " + position + " " + convertView);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_tongxuelu,null);
            holder = new ViewHolder();
                     /*得到各个控件的对象*/

            holder.title = (TextView) convertView.findViewById(R.id.ItemTitles);
            holder.text = (TextView) convertView.findViewById(R.id.ItemTexts);
            holder.bt = (Button) convertView.findViewById(R.id.ItemButton);
            convertView.setTag(holder);//绑定ViewHolder对象
        }else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/

        holder.title.setText("姓名："+listItems.get(position).get("first").toString());
        holder.text.setText("电话"+listItems.get(position).get("second").toString());

            /*为Button添加点击事件*/
        holder.bt.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.v("MyListViewBase", "你点击了按钮" + position);
                AlertDialog.Builder dialog=new AlertDialog.Builder(context);
                Log.i("jinlai","yes");
                dialog.setTitle("学生详情");
                dialog.setMessage("姓名："+listItems.get(position).get("first").toString()
                        +"\n帐号:"+listItems.get(position).get("forth").toString()
                        +"\n班级:" +listItems.get(position).get("third").toString()
                +"\n联系电话:"+listItems.get(position).get("second").toString());
                ;
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                } );
                dialog.show();



            }


        });
        return convertView;
    }
}/*存放控件*/
final class ViewHolder{
    public TextView title;
    public TextView text;
    public Button   bt;
}
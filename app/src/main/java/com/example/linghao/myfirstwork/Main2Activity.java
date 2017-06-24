package com.example.linghao.myfirstwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.linghao.myfirstwork.game1.StartActivity;
import com.example.linghao.myfirstwork.shujuku.Student;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
       View headerLayout = navigationView.getHeaderView(0);
        TextView textView1= (TextView) headerLayout. findViewById(R.id.zhanghao);
        final TextView textView2= (TextView) headerLayout. findViewById(R.id.stuname);
        Intent intent=getIntent();
        String value = intent.getStringExtra("zhanghao");
        Bmob.initialize(this, "c7f34b02339951edab89fa423df45013");
        BmobQuery<Student> query=new BmobQuery<Student>();
        query.addWhereEqualTo("zhanghao", value);
        query.findObjects(new FindListener<Student>() {

            public void done(List<Student> list, BmobException e) {
                if (e == null) {
                    String  a = list.get(0).getName().toString();
                textView2.setText(a);}
            }
        });
        textView1.setText(value);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(Main2Activity.this,loginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_classmate) {
            Intent intent1=new Intent(Main2Activity.this,tongxueluActivity.class);
            startActivity(intent1);

        } else if (id == R.id.nav_chakanchengji) {
            Intent intent4=new Intent(Main2Activity.this,ScoreCountActivity.class);
            startActivity(intent4);

        } else if (id == R.id.nav_kaoshianpai) {
            Intent intent3=new Intent(Main2Activity.this,kaoshianpaiActivity.class);
            startActivity(intent3);

        } else if (id == R.id.nav_liuyan) {
            Intent intent4=new Intent(Main2Activity.this,PostActivity.class);
            Intent intent=getIntent();
            String value = intent.getStringExtra("zhanghao");
           intent4.putExtra("zhanghao",value);
            startActivity(intent4);

        } else if (id == R.id.nav_call) {
            Intent intent2=new Intent(Main2Activity.this,SmsActivity.class);
            startActivity(intent2);

        } else if (id == R.id.nav_game) {
            Intent intent5=new Intent(Main2Activity.this, StartActivity.class);
            startActivity(intent5);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

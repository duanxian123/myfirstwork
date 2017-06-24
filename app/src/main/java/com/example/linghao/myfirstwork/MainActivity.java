package com.example.linghao.myfirstwork;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.linghao.myfirstwork.R;
import com.example.linghao.myfirstwork.fragment.MainFragment;
import com.example.linghao.myfirstwork.fragment.ScoreCountFragment;
import com.example.linghao.myfirstwork.fragment.StudentinFoFragment;
import com.example.linghao.myfirstwork.fragment.TestAnpaiFragment;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Fragment lastFragment;
    private ScoreCountFragment scoreCountFragment;
    private StudentinFoFragment studentinFoFragment;
    private TestAnpaiFragment testAnpaiFragment;
    private MainFragment mainFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("学生管理");
        setSupportActionBar(toolbar);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        init();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        changeFragment(mainFragment,R.id.fram_contianer);
        NavigationView navView= (NavigationView) findViewById(R.id.nav_view);
        navView.getChildAt(0).setVerticalScrollBarEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, 0, 0);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setCheckedItem(R.id.nav_main);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_main:
                        changeFragment(mainFragment,R.id.fram_contianer);
                        break;
                    case R.id.nav_info:
                        changeFragment(studentinFoFragment,R.id.fram_contianer);
                        break;
                    case R.id.nav_test:
                        changeFragment(testAnpaiFragment,R.id.fram_contianer);
                        break;
                    case R.id.nav_order:
                        changeFragment(scoreCountFragment,R.id.fram_contianer);
                        break;

                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } });


    }

    private void init() {
        scoreCountFragment = new ScoreCountFragment();
        studentinFoFragment = new StudentinFoFragment();
        testAnpaiFragment = new TestAnpaiFragment();
        mainFragment = new MainFragment();
    }

    public  boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return false;
    }
    public void changeFragment(Fragment fragment, @IdRes int id){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            if(lastFragment != null){
                fragmentTransaction.hide(lastFragment).add(id,fragment).commitAllowingStateLoss();
            }else{
                fragmentTransaction.add(id,fragment).commitAllowingStateLoss();
            }
        }else{
            fragmentTransaction.hide(lastFragment).show(fragment).commitAllowingStateLoss();
        }
        lastFragment = fragment;
    }
}

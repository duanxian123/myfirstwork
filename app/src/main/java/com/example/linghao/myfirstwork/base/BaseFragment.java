package com.example.linghao.myfirstwork.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/5/23.
 */

public abstract class BaseFragment extends Fragment{
    protected View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bmob.initialize(getActivity(), "c7f34b02339951edab89fa423df45013");
        mView = inflater.inflate(getLayoutId(), null);
        initInject();
        return mView;
    }

    protected abstract void initInject();
    protected abstract int getLayoutId();
}

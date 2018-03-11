package com.example.cui.phonesearch.mvp.impl;

import android.content.Context;
/**
 * Created by cui on 2018/3/11.
 */

public class BasePresenter
{
    Context mContext;
    public void attach(Context context){
        mContext = context;
    }
    public void onPause(){}
    public void onResume(){}
    public void onDestroy(){
        mContext = null;
    }
}

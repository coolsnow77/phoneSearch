package com.example.cui.phonesearch.mvp;

/**
 * Created by cui on 2018/3/11.
 */

public interface MvpMainView extends MvpLoadingView
{
   void showToast(String msg);
   void updateView();
}

package com.tb.tuihuobao.fragment;

import android.os.Bundle;
import android.view.View;

import com.tb.tuihuobao.R;
import com.tb.tuihuobao.login.LoginContext;
import com.tb.tuihuobao.presenter.IServiceView;
import com.tb.tuihuobao.presenter.ServiceP;

import butterknife.OnClick;
import comm.BaseFrag;

/**
 * Created by zxh on 2016/5/17.
 */
public class ServiceFragment extends BaseFrag<IServiceView, ServiceP> implements IServiceView {


  @Override
  public void initData(Bundle savedInstanceState) {

  }

  @Override
  protected int getLayoutId() {
    return R.layout.frag_services;
  }


  @OnClick({R.id.tv_daili_tousu, R.id.tv_tousu, R.id.tv_tucao})
   void submitServices(View v) {

    switch (v.getId()) {
      case R.id.tv_daili_tousu:

        break;
      case R.id.tv_tousu:

        break;
      case R.id.tv_tucao:

        break;
    }

    //投诉
    LoginContext.getInstance().tousu(getContext());

  }

  @Override
  protected ServiceP createPresenter() {
    return null;
  }
}

package com.tb.tuihuobao.fragment;

import android.os.Bundle;

import com.tb.tuihuobao.R;

import comm.BaseFrag;
import comm.BaseP;

/**
 * Created by zxh on 2016/5/17.
 */
public class HotNewsFragment extends BaseFrag {
  @Override
  public void initData(Bundle savedInstanceState) {

  }

  @Override
  protected int getLayoutId() {
    return R.layout.frag_hotnews;
  }

  @Override
  protected BaseP createPresenter() {
    return null;
  }
}

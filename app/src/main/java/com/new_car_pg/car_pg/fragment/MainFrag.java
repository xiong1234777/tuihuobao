package com.new_car_pg.car_pg.fragment;

import java.util.ArrayList;
import java.util.List;

import comm.BaseFrag;
import comm.BaseP;
import comm.BaseTabFrag;

/**
 * Created by zxh on 2016/4/19.
 */
public class MainFrag extends BaseTabFrag {

  @Override
  public List<String> getTitles() {
    mTitleLists = new ArrayList<>();
    mTitleLists.add("车新闻");
    mTitleLists.add("车投诉");
    mTitleLists.add("要吐槽");
    return mTitleLists;
  }

  @Override
  public List<BaseFrag> getFragments() {
    mFrgLists = new ArrayList<>();
    mFrgLists.add(new NewsListFrag());
    mFrgLists.add(new NewsListFrag());
    mFrgLists.add(new NewsListFrag());
    return mFrgLists;
  }

  @Override
  protected BaseP createPresenter() {
    return null;
  }
}

package com.tb.tuihuobao.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;

import com.tb.tuihuobao.R;

import java.util.Map;

import comm.utils.XutilsHelper;

/**
 * Created by zxh on 2016/5/17.
 */
public class FragmentFactory {

  private static Map<Integer, Fragment> mFragments = new ArrayMap<Integer, Fragment>();

  public static Fragment createFragment(int position) {
    Fragment fragment = null;
    fragment = mFragments.get(position);  //在集合中取出来Fragment
    if (fragment == null) {  //如果再集合中没有取出来 需要重新创建
      if (position == 0) {
        fragment = new ServiceFragment();
      } else if (position == 1) {
        String[] types = XutilsHelper.getResArr(R.array.news_type);
        fragment = NewsItemFrag.insatnce(types[0]);
      } else if (position == 2) {
        fragment = new UserInfoFragment();
      }
      if (fragment != null) {
        mFragments.put(position, fragment);// 把创建好的Fragment存放到集合中缓存起来
      }
    }
    return fragment;

  }
}

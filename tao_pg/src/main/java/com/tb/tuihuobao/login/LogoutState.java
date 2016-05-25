package com.tb.tuihuobao.login;

import android.content.Context;

import com.tb.tuihuobao.fragment.LoginFragment;

import comm.utils.UiTools;

/**
 * Created by zxh on 2016/5/24.
 * 未登录状态
 * 状态模式
 */
public class LogoutState implements UserState {

  @Override
  public void tousu(Context context) {
    //跳转到登录的的界面
    UiTools.jumpTActivity(context, LoginFragment.class,null,null);
  }

  @Override
  public void comment(Context context) {
    //跳转到登录的的界面
    UiTools.jumpTActivity(context, LoginFragment.class,null,null);
  }
}

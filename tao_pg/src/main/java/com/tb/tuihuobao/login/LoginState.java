package com.tb.tuihuobao.login;

import android.content.Context;

import com.tb.tuihuobao.TousuActivity;

import comm.utils.UiTools;

/**
 * Created by zxh on 2016/5/24.
 */
public class LoginState implements UserState{

  @Override
  public void tousu(Context context) {
    //跳转到投诉界面
    UiTools.jumpActivity(context, TousuActivity.class,null,null);
  }

  @Override
  public void comment(Context context) {
    //跳转到评论界面
    UiTools.showToast("跳转到评论");
  }
}

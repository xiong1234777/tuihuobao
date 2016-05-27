package com.tb.tuihuobao.login;

import android.content.Context;

/**
 * Created by zxh on 2016/5/24.
 * 用户的接口和状态的管理类
 */
public class LoginContext implements UserState{

  //单例的最佳写法
  private static volatile LoginContext sInst = null;  // <<< 这里添加了 volatile

  public static LoginContext getInstance() {
    LoginContext inst = sInst;  // <<< 在这里创建临时变量
    if (inst == null) {
      synchronized (LoginContext.class) {
        inst = sInst;
        if (inst == null) {
          inst = new LoginContext();
          sInst = inst;
        }
      }
    }
    return inst;
  }

  //默认是未登录的状态
  private UserState mUserSate = new LogoutState();

  //设置登录状态
  public void setLoginState(UserState userState,Context context){
      this.mUserSate = userState;
      //如果是未登录的状态就跳转到登录
      if(userState instanceof LogoutState){
        mUserSate.tousu(context);
      }
  }


  @Override
  public void tousu(Context context) {

    mUserSate.tousu(context);

  }

  @Override
  public void comment(Context context) {

    mUserSate.comment(context);

  }
}

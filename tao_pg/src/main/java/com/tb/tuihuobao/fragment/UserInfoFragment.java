package com.tb.tuihuobao.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tb.tuihuobao.R;
import com.tb.tuihuobao.bean.UserInfo;
import com.tb.tuihuobao.login.LoginContext;
import com.tb.tuihuobao.login.LogoutState;

import org.xutils.view.annotation.Event;

import comm.BaseFrag;
import comm.BaseP;
import comm.utils.SPFTools;
import comm.view.smartimageview.SmartImageView;

/**
 * Created by zxh on 2016/5/17.
 */
public class UserInfoFragment extends BaseFrag {


  private SmartImageView mIvHead;
  private TextView mTvUserName;


  @Event(R.id.btn_exit)
  private void exit(View v){
    LoginContext.getInstance().setLoginState(new LogoutState());
  }

  @Override
  public void initData(Bundle savedInstanceState) {
    mIvHead = (SmartImageView) mRootView.findViewById(R.id.iv_head);
    mTvUserName = (TextView) mRootView.findViewById(R.id.tv_user_name);
    UserInfo userInfo = SPFTools.SPHelper.getUserInfo();
    if(userInfo!=null){
//      XutilsHelper.showImage(mIvHead,userInfo.head_img);
      mIvHead.setImageUrl(userInfo.head_img);
      mTvUserName.setText(userInfo.user_name);
    }

  }

  @Override
  protected int getLayoutId() {
    return R.layout.frag_userinfo;
  }

  @Override
  protected BaseP createPresenter() {
    return null;
  }
}

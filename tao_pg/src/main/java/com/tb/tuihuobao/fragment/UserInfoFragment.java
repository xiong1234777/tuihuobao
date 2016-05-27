package com.tb.tuihuobao.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tb.tuihuobao.R;
import com.tb.tuihuobao.bean.UserInfo;
import com.tb.tuihuobao.login.LoginContext;
import com.tb.tuihuobao.login.LogoutState;

import butterknife.OnClick;
import comm.BaseFrag;
import comm.BaseP;
import comm.utils.SPFTools;
import comm.view.smartimageview.SmartImageView;

/**
 * Created by zxh on 2016/5/17.
 */
public class UserInfoFragment extends BaseFrag {


   SmartImageView mIvHead;
   TextView mTvUserName;

  @OnClick(R.id.btn_exit)
   void exit(View v){
    //结束当前的activity
    SPFTools.delAll();
    LoginContext.getInstance().setLoginState(new LogoutState(),getActivity());
    getActivity().finish();
  }

  @TargetApi(Build.VERSION_CODES.M)
  @Override
  public void initData(Bundle savedInstanceState) {
    mIvHead = (SmartImageView) mRootView.findViewById(R.id.iv_head);
    mTvUserName = (TextView) mRootView.findViewById(R.id.tv_user_name);
    UserInfo userInfo = SPFTools.SPHelper.getUserInfo();
    if(userInfo!=null){
      mIvHead.setBorderColor(R.color.bar_color);
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

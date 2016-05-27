package com.tb.tuihuobao.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tb.tuihuobao.FunctionActivity;
import com.tb.tuihuobao.R;
import com.tb.tuihuobao.global.UrlHelper;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comm.BaseFrag;
import comm.BaseP;
import comm.utils.DataTools;
import comm.utils.MyCallBack;
import comm.utils.SPFTools;
import comm.utils.UiTools;
import comm.utils.XutilsHelper;

/**
 * Created by zxh on 2016/5/19.
 */
public class RegFragment extends BaseFrag {

  @Bind(R.id.et_uname)
  TextInputLayout mTilName;
  @Bind(R.id.et_pwd)
  TextInputLayout mTilPwd;
  @Bind(R.id.et_phone_number)
  TextInputLayout mTilPhoneNum;
  @Bind(R.id.et_check_number)
  AppCompatEditText mEtCheckNum;
  @Bind(R.id.btn_check_number)
  Button btnCheckNumber;
  @Bind(R.id.btn_reg_submit)
  Button btnRegSubmit;

  @Override
  public void initData(Bundle savedInstanceState) {

  }

  @Override
  protected int getLayoutId() {
    return R.layout.frag_reg;
  }


  @OnClick({R.id.btn_reg_submit, R.id.btn_check_number})
  void regSubmit(View view) {

    switch (view.getId()) {

      case R.id.btn_reg_submit:

        //获取用户名
        String userName = mTilName.getEditText().getText().toString().trim();
        //获取密码
        String pwd = mTilPwd.getEditText().getText().toString().trim();
        //获取验证码
        String checkNum = mEtCheckNum.getText().toString().trim();

        //获取手机号
        String phoneNum = mTilPhoneNum.getEditText().getText().toString();

        if (TextUtils.isEmpty(phoneNum)) {
          mTilPhoneNum.setError("手机号不能为空");
        }

        if (!DataTools.isPhoneNum(phoneNum)) {
          mTilPhoneNum.setError("请输入正确的手机号");
          return;
        }

        if (TextUtils.isEmpty(userName)) {
          mTilName.setError("请输入用户名");
          return;
        }

        if (TextUtils.isEmpty(pwd)) {
          mTilPwd.setError("请输入密码");
          return;
        }

        if (TextUtils.isEmpty(checkNum)) {
          UiTools.showToast("请输入验证码");
          return;
        }

        if (TextUtils.isEmpty(phoneNum)) {
          UiTools.showToast("请输入手机号");
          return;
        }

        reg(userName, phoneNum, pwd, checkNum);

        break;
      case R.id.btn_check_number:

        //获取手机号
        String phoneNum1 = mTilPhoneNum.getEditText().getText().toString();

        if (TextUtils.isEmpty(phoneNum1)) {
          mTilPhoneNum.setError("手机号不能为空");
        }

        if (!DataTools.isPhoneNum(phoneNum1)) {
          mTilPhoneNum.setError("请输入正确的手机号");
          return;
        }

        RequestParams params = new RequestParams(UrlHelper.REG);
        params.addBodyParameter("request_type", "send_check_code");
        params.addBodyParameter("phone_number", phoneNum1);

        //获取验证码
        XutilsHelper.fetch(params, 1, new MyCallBack<String>() {
          @Override
          public void success(String result) throws JSONException {

          }

          @Override
          public void fail() {

          }

          @Override
          public void onSuccess(String result) {
            super.onSuccess(result);

            LogUtil.e(result);

            UiTools.showToast(result);
            try {

              JSONObject jsonObject = new JSONObject(result);

              int code = jsonObject.getInt("code");


              if (3 != code) {
                UiTools.showToast(jsonObject.getString("error_report"));
              } else {
                btnCheckNumber.setClickable(false);
                mTimeCount = new TimeCount(60000, 1000);
                mTimeCount.start();
                UiTools.showToast("验证码已发送");
              }

            } catch (JSONException e) {
              e.printStackTrace();

            }
          }
        });

        break;
    }

  }

  @Override
  protected BaseP createPresenter() {
    return null;
  }

  //注册的逻辑
  void reg(String userName, String userPhone, String pwd, String checkNum) {

    RequestParams params = new RequestParams(UrlHelper.REG);
    params.addBodyParameter("request_type", "send_register_data");
    params.addBodyParameter("user_name", userName);
    params.addBodyParameter("pwd", pwd);
    params.addBodyParameter("phone_number", userPhone);
    params.addBodyParameter("check_code", checkNum);

    XutilsHelper.fetch(params, 1, new MyCallBack<String>() {
      @Override
      public void success(String result) throws JSONException {

      }

      @Override
      public void fail() {

      }

      @Override
      public void onSuccess(String result) {
        super.onSuccess(result);
        try {

          LogUtil.e(result);
          JSONObject jsonObject = new JSONObject(result);
          int code = jsonObject.getInt("code");

          if (code != 1) {
            UiTools.showToast(jsonObject.getString("error_status"));
            return;
          }

          //注册成功 返回用户的信息
          SPFTools.insertData("user_info", jsonObject.getJSONObject("user_mes")
                  .toString());

          //跳转到主页面,标记为来自注册成功后
          UiTools.jumpActivity(getContext(), FunctionActivity.class, new
                  String[]{"from_id"}, new
                  String[]{"reg"});


        } catch (JSONException e) {
          e.printStackTrace();

        }

      }
    });
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
          savedInstanceState) {
    // TODO: inflate a fragment view
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  private TimeCount mTimeCount=null;
  class TimeCount extends CountDownTimer {

    public TimeCount(long millisInFuture, long countDownInterval) {
      super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
      btnCheckNumber.setText(millisUntilFinished / 1000 + "秒");
    }

    @Override
    public void onFinish() {
      btnCheckNumber.setText("重新验证");
      btnCheckNumber.setClickable(true);
    }
  }

}

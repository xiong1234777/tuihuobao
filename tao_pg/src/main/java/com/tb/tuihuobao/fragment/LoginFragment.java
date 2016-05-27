package com.tb.tuihuobao.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;

import com.tb.tuihuobao.FunctionActivity;
import com.tb.tuihuobao.R;
import com.tb.tuihuobao.global.UrlHelper;
import com.tb.tuihuobao.login.LoginContext;
import com.tb.tuihuobao.login.LoginState;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import butterknife.Bind;
import butterknife.OnClick;
import comm.BaseFrag;
import comm.BaseP;
import comm.utils.DataTools;
import comm.utils.MD5;
import comm.utils.MyCallBack;
import comm.utils.SPFTools;
import comm.utils.UiTools;
import comm.utils.XutilsHelper;

/**
 * Created by zxh on 2016/5/18.
 */
public class LoginFragment extends BaseFrag {


  @Bind(R.id.tiy_uname)
  TextInputLayout mName;

  @Bind(R.id.tiy_pwd)
  TextInputLayout mPwd;

  @OnClick({R.id.tv_reg_account, R.id.btn_login})
  void regAccount(View v) {
    switch (v.getId()) {
      case R.id.tv_reg_account:
        UiTools.jumpTActivity(getContext(), RegFragment.class, null, null);

        break;
      case R.id.btn_login:

        //登录
        final String name = mName.getEditText().getText().toString().trim();
        final String pwd = mPwd.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
          mName.setError("请输入用户名");
          return;
        }

        if (TextUtils.isEmpty(pwd)) {
          mPwd.setError("请输入密码");
          return;
        }

        if (!(DataTools.isFilter(name) && DataTools.isFilter(pwd))) {
          UiTools.showToast("请不要输入特殊字符");
          return;
        }

        //请求登录
        RequestParams params = new RequestParams(UrlHelper.LOGIN);
        params.addBodyParameter("account", name);
        params.addBodyParameter("pwd", MD5.GetMD5Code(pwd));

        mLoading = UiTools.UIHelper.getLoadDialog2(getActivity());

        XutilsHelper.fetch(params, 1, new MyCallBack<String>() {
                  @Override
                  public void success(String result) throws JSONException {

                    mLoading.dismiss();
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    String error_report = jsonObject.getString("error_report");

                    if (code == 1 || code == 4) {


                      //设置为状态为登录状态
                      LoginContext.getInstance().setLoginState(new LoginState(), getContext());
                      UiTools.showToast("登录成功");

                      //储存用户名和密码
                      SPFTools.insertData(new String[]{"user_name", "pwd"}, new String[]{name,
                              pwd});


                      //缓存用户信息到本地
                      SPFTools.insertData(XutilsHelper.getResStr(R.string.user_info), jsonObject.
                              getJSONObject("user_mes").toString());

                      if (getContext() != null) {


                        //跳转到主界面
                        UiTools.jumpActivity(getContext(), FunctionActivity.class, new
                                String[]{"from_id"}, new String[]{"login"});


                        //结束当前的活动
                        getActivity().finish();
                      }
                    } else {
                      //设置为状态为登录状态
                      LoginContext.getInstance().setLoginState(new LoginState(), getContext());
                      UiTools.showToast("验证失败" + error_report);

                    }
                  }

                  @Override
                  public void fail() {
                    mLoading.dismiss();
                  }
                }
        );
        break;
    }

  }


  @Override
  public void initData(Bundle savedInstanceState) {
  }

  @Override
  protected int getLayoutId() {
    return R.layout.frag_login;
  }

  @Override
  protected BaseP createPresenter() {
    return null;
  }
}

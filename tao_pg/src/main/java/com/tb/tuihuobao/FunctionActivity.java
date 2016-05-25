package com.tb.tuihuobao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.tb.tuihuobao.fragment.FragmentFactory;
import com.tb.tuihuobao.fragment.TouSuTelFragment;
import com.tb.tuihuobao.global.UrlHelper;
import com.tb.tuihuobao.login.LoginContext;
import com.tb.tuihuobao.login.LoginState;
import com.tb.tuihuobao.login.LogoutState;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import comm.BaseActivity;
import comm.NewTempActivity;
import comm.utils.MD5;
import comm.utils.MyCallBack;
import comm.utils.SPFTools;
import comm.utils.UiTools;
import comm.utils.XutilsHelper;

/**
 * Created by zxh on 2016/5/17.
 */
public class FunctionActivity extends BaseActivity {

  private TabLayout mTablayout = null;
  private ViewPager mVpcontent = null;
  private ImageView mImageTel = null;

  private String[] mTabTitles = new String[3];

  @Override
  protected void onCreate(Bundle arg0) {
    super.onCreate(arg0);
    this.setContentView(R.layout.activity_function);

//    String from_id = getIntent().getStringExtra("from_id");

//    if (from_id == null) {

    //用户名和密码请求一次登录
    final String user_name = SPFTools.queryStr("user_name");

    if (null != user_name) {
      //登录过,直接验证
      final String pwd = SPFTools.queryStr("pwd");

      RequestParams params = new RequestParams(UrlHelper.LOGIN);
      params.addBodyParameter("account", user_name);
      params.addBodyParameter("pwd", pwd);

      //发送请求
      XutilsHelper.fetch(params, 1, new MyCallBack<String>() {
        @Override
        public void onSuccess(String result) {
          super.onSuccess(result);

          try {

            JSONObject jsonObject = new JSONObject(result);
            int code = jsonObject.getInt("code");
            String error_report = jsonObject.getString("error_report");

            if (code == 1 || code == 4) {

              UiTools.showToast("登陆成功");
              LoginContext.getInstance().setLoginState(new LoginState());

              //储存用户名和密码
              SPFTools.insertData(new String[]{"user_name", "pwd"}, new String[]{user_name, MD5
                      .GetMD5Code(pwd)});

              //缓存用户信息到本地
              SPFTools.insertData(XutilsHelper.getResStr(R.string.user_info), jsonObject.
                      getJSONObject("user_mes").toString());

            } else {
              UiTools.showToast("验证失败" + error_report);
              LoginContext.getInstance().setLoginState(new LogoutState());
            }


          } catch (JSONException e) {
            UiTools.showToast("数据出现了点小问题");
            e.printStackTrace();
          }
        }
      });

    } else {
      LoginContext.getInstance().setLoginState(new LogoutState());
      finish();
    }


    //init views
    mTablayout = (TabLayout) findViewById(R.id.tab_layout);

    mVpcontent = (ViewPager) findViewById(R.id.vp_content);

    mImageTel = (ImageView) findViewById(R.id.iv_tel);

    mImageTel.setOnClickListener(new View.OnClickListener()

                                 {
                                   @Override
                                   public void onClick(View v) {

                                     Intent intent = new Intent(FunctionActivity.this,
                                             NewTempActivity.class);
                                     intent.putExtra("fragmentName", TouSuTelFragment.class
                                             .getName());

                                     startActivity(intent);
                                   }
                                 }

    );

    //init titles
    mTabTitles[0] = "服务";
    mTabTitles[1] = "资讯";
    mTabTitles[2] = "用户";

    //添加tab选项卡
    for (
            String title
            : mTabTitles)

    {
      mTablayout.addTab(mTablayout.newTab().setText(title));
    }

    //设置适配器
    FunctionFragAdapter adapter = new FunctionFragAdapter(getSupportFragmentManager());

    mVpcontent.setOffscreenPageLimit(1);

    //给ViewPager设置适配器
    mVpcontent.setAdapter(adapter);

    //将TabLayout和ViewPager关联起来。
    mTablayout.setupWithViewPager(mVpcontent);


  }

  class FunctionFragAdapter extends FragmentPagerAdapter {


    public FunctionFragAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
      return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mTabTitles[position];
    }
  }


}

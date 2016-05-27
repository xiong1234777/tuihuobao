package com.tb.tuihuobao;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.CircleProgress;
import com.tb.tuihuobao.global.UrlHelper;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import comm.BaseActivity;
import comm.utils.DataTools;
import comm.utils.MyCallBack;
import comm.utils.UiTools;
import comm.utils.XutilsHelper;

/**
 * Created by zxh on 2016/4/25.
 */
public class NewsDetailActivity extends BaseActivity {


  //private WebView mWeb = null;

  @ViewInject(R.id.web_content)
  private WebView mWeb;
  @ViewInject(R.id.circle_progress)
  private CircleProgress mProgress;
  private Toolbar toolbar = null;


  //
  @ViewInject(R.id.et_discussion_content)
  private EditText mEtDiscussionContent;
  @ViewInject(R.id.btn_discussion_submit)
  private Button mBtnDiscussionSubmit;
  @ViewInject(R.id.tv_discussion_counts)
  private TextView mTvDiscussionCounts;


  private int mDiscussionCounts;
  private long mNewsId;
  TextView mTitle;


  @TargetApi(Build.VERSION_CODES.M)
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_news_detail);
    x.view().inject(this);

    //初始化toolBar
    //initToolBar();
    mTitle = (TextView) findViewById(R.id.tv_news_title);


    Intent intent = getIntent();
    //获取网页的地址
    String url = intent.getStringExtra("url");
    //获取评论的数量
    mDiscussionCounts = intent.getIntExtra("discussion", -1);
    //获取新闻的id
    mNewsId = intent.getLongExtra("id", -1);
    if (mDiscussionCounts != -1) {
      //设置评论的数量
      mTvDiscussionCounts.setText(mDiscussionCounts);
    } else {
      mTvDiscussionCounts.setText("暂无评论");
    }


    if (null == url) {
      UiTools.showToast("加载失败");
      return;
    }


    //评论
    mBtnDiscussionSubmit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        RequestParams params = new RequestParams(UrlHelper.SUBMIT_DISCUSSION);

        String content = mEtDiscussionContent.getText().toString().trim();

        //过滤特殊字符
        if (TextUtils.isEmpty(content)) {
          UiTools.showToast("您没有输入内容.");
          return;
        }

        if (content.length() >= 300) {
          UiTools.showToast("您输入的内容超过最大限度。");
          return;
        }

        params.addBodyParameter("account", "taobpg");
        params.addBodyParameter("pwd", "123");
        params.addBodyParameter("news_id", mNewsId + "");
        params.addBodyParameter("content", DataTools.filter(content));
        params.addBodyParameter("uid", "17");

        //发表评论
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
              JSONObject jsonObject = new JSONObject(result);

              int status = jsonObject.getInt("status");

              if (1 != status) {
                UiTools.showToast("评论失败");
              } else {
                UiTools.showToast("成功");
              }


            } catch (JSONException e) {
              e.printStackTrace();
            }
          }

          @Override
          public void onError(Throwable ex, boolean isOnCallback) {
            super.onError(ex, isOnCallback);
            UiTools.showToast(ex.toString());
          }
        });
      }
    });

    //初始化webView
    initWebView(url);


  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    //先哦会webView
    if (mWeb != null) {
      mWeb.clearHistory();
      mWeb.clearCache(true);
      mWeb.destroy();
      mWeb = null;
    }
  }

  
  private void initWebView(String url) {


    //设置webView
    WebSettings settings = mWeb.getSettings();
    settings.setJavaScriptEnabled(true);// 表示支持js
    settings.setTextSize(WebSettings.TextSize.NORMAL);

    mWeb.setWebViewClient(new WebViewClient() {

      /**
       * 网页开始加载
       */
      @Override
      public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        mTitle.setText("新闻详情");
        mProgress.setVisibility(View.VISIBLE);
      }

      /**
       * 网页加载结束
       */
      @Override
      public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        mProgress.setVisibility(View.GONE);

      }

      /**
       * 所有跳转的链接都会在此方法中回调
       */
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // tel:110
        view.loadUrl(url);

        return true;
      }
    });

    mWeb.setWebChromeClient(new WebChromeClient() {

      /**
       * 进度发生变化
       */
      @Override
      public void onProgressChanged(WebView view, int newProgress) {
        mProgress.setProgress(newProgress);
        super.onProgressChanged(view, newProgress);
      }

      /**
       * 获取网页标题
       */
      @Override
      public void onReceivedTitle(WebView view, String title) {

        mTitle.setText("新闻详情");

        super.onReceivedTitle(view, title);
      }


    });

    // 加载网页
    mWeb.loadUrl(url);
  }
}

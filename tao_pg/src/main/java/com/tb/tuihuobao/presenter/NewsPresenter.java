package com.tb.tuihuobao.presenter;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.tb.tuihuobao.bean.Discussion;
import com.tb.tuihuobao.bean.NewsInfo;
import com.tb.tuihuobao.global.UrlHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

import java.util.List;

import comm.BaseP;
import comm.utils.DataTools;
import comm.utils.MyCallBack;
import comm.utils.UiTools;
import comm.utils.XutilsHelper;


/**
 * Created by SX on 2016/4/23.
 */
public class NewsPresenter extends BaseP<INewsView> {

  //请求参数
  private List<NewsInfo> mNewsInfos = null;
  private List<Discussion> mDiscussions = null;



  //获取评论的信息
  public void fetchDisccsions() {

//    XutilsHelper.fetch(new RequestParams(UrlHelper.getNesDiscussionUrl()),0, new
//            MyCallBack<String>(){
//
//              @Override
//              public void onSuccess(String result) {
//                  super.onSuccess(result);
//                  //判断相同的东西，我到要不要做封装呢
//
//              }
//            });
  }


  //做评论
  public void updateDisccsion(){

  }


  public void fetchNews(String type, int pn) {

    //打印记录
    LogUtil.e(UrlHelper.getNewsItemsUrl("taobpg", "123", type, pn).toString());

    //发送请求
    XutilsHelper.fetch(new RequestParams(UrlHelper.getNewsItemsUrl("taobpg", "123", type, pn)),
            0, new MyCallBack<String>() {
      
      @Override
      public void onSuccess(String result) {
        super.onSuccess(result);

        if (TextUtils.isEmpty(result)) {
          return;
        }

        try {
          JSONObject obj = new JSONObject(result);
          int status = obj.getInt("status");
          
          if (status == 1) {
            JSONArray array = obj.getJSONArray("list");

            if (mNewsInfos != null) {
              mNewsInfos.clear();
            }

            //获取json
            mNewsInfos = DataTools.getGosn().fromJson(array.toString(), new
                    TypeToken<List<NewsInfo>>() {
                    }.getType());

            //presenter 绑定后 执行
            if (isAttached()) {
              getView().onSuccess(mNewsInfos);
            }


          } else {
            if (status == 0) {
              UiTools.showToast("已经加载到底");

            } else {
              UiTools.showToast("错误码:" + status);
            }
            if (isAttached()) {
              getView().onFail();
            }
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onError(Throwable ex, boolean isOnCallback) {
        super.onError(ex, isOnCallback);
        if (isAttached()) {
          getView().onFail();
        }
      }
    });
  }
}

package com.new_car_pg.car_pg.fragment;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.new_car_pg.car_bg.R;
import com.new_car_pg.car_pg.bean.NewsInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.DensityUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;
import java.util.Map;

import comm.BaseListFrag;
import comm.BaseP;
import comm.CommonAdapter;
import comm.ViewHolder;
import comm.utils.MD5;
import comm.utils.MyCallBack;
import comm.utils.UiTools;
import comm.utils.XutilsHelper;

/**
 * Created by zxh on 2016/4/19.
 */
public class NewsListFrag extends BaseListFrag {


  private StringBuilder m_sb_tpg_host = new StringBuilder(XutilsHelper.getResStr(R.string
          .tpg_host));

  //获取数据填充适配器更新数据
  private void fetchData(String type, int page, String pwd, final boolean is_refresh) {
    //设置请求参数
    m_sb_tpg_host.append("/news/sharenews").
            append(XutilsHelper.getResStr(R.string.tpg_account)).append(type).
            append(XutilsHelper.getResStr(R.string.tpg_pwd)).append(MD5.GetMD5Code(pwd)).
            append(XutilsHelper.getResStr(R.string.tpg_news_page)).append(page);

    //开启刷新动画
    if (is_refresh) {
      mRefresh.setRefreshing(true);
    } else {
      mRefresh.setLoading(true);
    }


    //获取新闻数据
    XutilsHelper.fetch(new RequestParams(m_sb_tpg_host.toString()),
            0, new
                    MyCallBack() {
                      @Override
                      public void onSuccess(String result) {

                        synchronized (this) {

                          try {
                            //
                            JSONObject jsonObject = new JSONObject(result);

                            //如果返回的状态是true,代表数据准备完毕
                            if (jsonObject.getBoolean(XutilsHelper.getResStr(R.string
                                    .jsonkey_news_status))) {

                              //将新闻的数据集合取出来
                              JSONArray jsonArray = jsonObject.getJSONArray(XutilsHelper.getResStr
                                      (R.string
                                              .jsonkey_news_list));

                              //解析数据并赋值
                              List<NewsInfo> newsinfos = new Gson().fromJson(jsonArray.toString()
                                      , new
                                              TypeToken<List<NewsInfo>>() {
                                              }.getType());


                              mDatas = newsinfos;

                              //如果是刷新重新设置设配器
                              if (is_refresh) {
                                //停止刷新
                                mRefresh.setRefreshing(false);
                                updateAdapter();
                              } else {
                                //更新并创建建适配器
                                setAdapterOrUpDate();
                                mRefresh.setLoading(false);
                              }

                            } else {
                              UiTools.showToast("已经没有更多数据");
                              if (is_refresh) {
                                mRefresh.setRefreshing(false);
                              } else {
                                mRefresh.setLoading(false);
                              }
                            }


                          } catch (JSONException e) {
                            e.printStackTrace();
                            UiTools.showToast("数据获取失败");
                          }
                        }
                      }
                    });
  }

  @Override
  public void initData(Bundle savedInstanceState) {
    super.initData(savedInstanceState);
    //初始化完毕
    isPrepared = true;
    lazyLoad();
  }

  //加载的页数
  private int page = 1;

  @Override
  protected boolean lazyLoad() {
    if (super.lazyLoad()) {
      return true;
    }
    page = 1;
    //获取数据
    fetchData("carpg", page, "123456", true);
    return false;
  }

  @Override
  public CommonAdapter getAdapter() {
    //用来缓存配置的信息
    final Map<String, ImageOptions> cache = new ArrayMap();
    //加入缓存
    cache.put("cache", new ImageOptions.Builder().setRadius(DensityUtil.dip2px(10)).build());
    mAdapter = new CommonAdapter<NewsInfo>(getContext(), mDatas, R.layout.adapter_news) {
      @Override
      public void convert(ViewHolder helper, NewsInfo item) {
        helper.setText(R.id.tv_news_title, item.title);
        helper.setText(R.id.tv_news_digest, item.news_abstract);
        helper.setText(R.id.tv_news_time, item.time);
        helper.setText(R.id.tv_news_valuation, item.discussion_count + "");
        //获取image view
        ImageView iv = helper.getView(R.id.iv_news_image);

        //从缓存中获取
        if (cache.get("cache") != null) {

          //绑定图片到 image_view
          x.image().bind(iv, XutilsHelper.getResStr(R.string.tpg_host) + item.img_path, cache.get
                  ("cache"));

          LogUtil.e(cache.get("cache").toString());

        }
      }
    };

    return mAdapter;
  }

  @Override
  protected BaseP createPresenter() {
    return null;
  }

  @Override
  public void onRefresh() {
    super.onRefresh();
    page = 1;
    //如果是刷新的话 销毁之前的适配器，重新构建适配器
    fetchData("carpg", page, "123456", true);

  }

  @Override
  public void onLoad() {
    super.onLoad();
    fetchData("carpg", page, "123456", false);
    page++;
  }

  @Override
  public void onItemClick(AdapterView parent, View view, int position, long id) {
    super.onItemClick(parent, view, position, id);
  }

}

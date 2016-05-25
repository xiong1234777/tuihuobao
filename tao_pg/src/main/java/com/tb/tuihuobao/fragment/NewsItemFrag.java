package com.tb.tuihuobao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.tb.tuihuobao.NewsDetailActivity;
import com.tb.tuihuobao.R;
import com.tb.tuihuobao.bean.NewsInfo;
import com.tb.tuihuobao.presenter.INewsView;
import com.tb.tuihuobao.presenter.NewsPresenter;

import org.xutils.common.util.DensityUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;
import java.util.Map;

import comm.BaseListFrag;
import comm.CommonAdapter;
import comm.ViewHolder;

/**
 * Created by SX on 2016/4/23.
 */
public class NewsItemFrag extends BaseListFrag<INewsView, NewsPresenter> implements INewsView {

  private final static String NEWS_TYPE = "news_type";

  private int page = 1;
  private String m_type = null;
  private View networkErrorView = null;

  public static NewsItemFrag insatnce(String news_type) {

    NewsItemFrag newsItemFrag = new NewsItemFrag();

    Bundle b = new Bundle();
    b.putString(NEWS_TYPE, news_type);
    newsItemFrag.setArguments(b);

    return newsItemFrag;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public void initData(Bundle savedInstanceState) {
    super.initData(savedInstanceState);

    //获取参数
    m_type = getArguments().getString(NEWS_TYPE);

    //初始化监听事件
    initListener();

    if (m_type != null) {
//      //弹出进度条
      mRefresh.autoRefresh();
    }


  }


  private void initListener() {

    mRefresh.setLoadMore(true);
    mRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
      @Override
      public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {

        if (mDatas != null) {
          mDatas.clear();
        }

        page = 1;

        mPresenter.fetchNews(m_type, page);
      }

      @Override
      public void onfinish() {
      }

      @Override
      public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {

        mPresenter.fetchNews(m_type, ++page);

      }
    });
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
        helper.setText(R.id.tv_news_time, "本站发布时间:" + item.time);
        helper.setText(R.id.tv_news_valuation, "评论:" + item.discussion_count + "");
        //获取image view
        ImageView iv = helper.getView(R.id.iv_news_image);

        //从缓存中获取
        if (cache.get("cache") != null) {

          if (TextUtils.isEmpty(item.img_path)) {
            iv.setVisibility(View.GONE);
          } else {
            iv.setVisibility(View.VISIBLE);
            //绑定图片到 image_view
            x.image().bind(iv, "http://" + item.img_path, cache.get
                    ("cache"));
          }

        }
      }
    };

    return mAdapter;
  }

  @Override
  protected NewsPresenter createPresenter() {
    return new NewsPresenter();
  }


  @Override
  public void onFail() {

    mRefresh.finishRefresh();
    mRefresh.finishRefreshLoadMore();

    if (page < 2) {

      //显示加载失败的view
      ViewStub stub = (ViewStub) mRootView.findViewById(R.id.network_error_layout);
      networkErrorView = stub.inflate();
      TextView tv = (TextView) networkErrorView.findViewById(R.id.tv_net_error);

      tv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          page = 1;

          mPresenter.fetchNews(m_type, page);
        }
      });
    }
  }

  @Override
  public void onSuccess(List datas) {

    //显示加载下错误的页面
    showNormal();

    //添加说有的数据
    mDatas.addAll(datas);

    LogUtil.e(m_type + "页");

    //更新数据
    setAdapterOrUpDate();


    mRefresh.finishRefresh();
    mRefresh.finishRefreshLoadMore();
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    Intent intent = new Intent(getActivity(), NewsDetailActivity.class);

    //新闻的url
    intent.putExtra("url", ((NewsInfo) (mDatas.get(position))).url);
    //评论的数量
    intent.putExtra("discussion",((NewsInfo) (mDatas.get(position))).discussion_count);
    //新闻的id
    intent.putExtra("id",((NewsInfo) (mDatas.get(position))).id);
    startActivity(intent);

  }

  private void showNormal() {
    if (networkErrorView != null) {
      networkErrorView.setVisibility(View.GONE);
    }
  }
}

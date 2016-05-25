package com.tb.tuihuobao.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tb.tuihuobao.R;
import com.tb.tuihuobao.bean.Discussion;

import java.util.List;

import comm.CommonAdapter;
import comm.ViewHolder;
import comm.utils.XutilsHelper;

/**
 * Created by zxh on 2016/5/3.
 * 评论的adapter
 * 显示评论信息+以及评论的功能
 */
public class DiscussionAdapter extends CommonAdapter<Discussion>{

  //点击事件的回调
  private Callback mCallBack;

  public DiscussionAdapter(Context context, List<Discussion> mDatas,Callback
          mCallBack) {
    super(context, mDatas, R.layout.adapter_discussion);
    this.mCallBack = mCallBack;
  }

  @Override
  public void convert(final ViewHolder helper, Discussion item) {

    //评论者的头像
    XutilsHelper.showImage((ImageView) helper.getView(R.id.iv_user_image), item.user_mes.head_img);

    //评论者的昵称
    helper.setText(R.id.tv_user_nickname, item.user_mes.user_name);

    //评论的时间
    helper.setText(R.id.tv_discussion_time, item.time);

    //评论的内容
    helper.setText(R.id.tv_discussion_content, item.content);

    //赞的个数 --- -点赞的叠加工作
    final TextView tv_zan_count = helper.getView(R.id.tv_discussion_support);
    tv_zan_count.setText(item.zan_count + "赞");
    tv_zan_count.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mCallBack.onZan(helper.getmPosition(),tv_zan_count);
      }
    });

    //回复的个数----- 回复点击事件
    final TextView tv_reply_count = helper.getView(R.id.tv_discussion_counts);
    tv_reply_count.setText(item.reply_count + "评论");
    tv_reply_count.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mCallBack.onReply(helper.getmPosition(),tv_reply_count);
      }
    });

  }

  //这是局部点击事件的回掉
  public interface Callback{
    void onReply(int posion,View view);
    void onZan(int posion,View view);
  }
}

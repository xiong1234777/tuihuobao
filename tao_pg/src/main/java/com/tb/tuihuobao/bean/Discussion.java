package com.tb.tuihuobao.bean;

/**
 * Created by SX on 2016/4/23.
 */
public class Discussion {

  public long id;
  public long news_id;
  public long uid;
  public String content;
  public int reply_count;
  public int zan_count;
  public String time;
  public UserMes user_mes;

  public class UserMes{
    public String user_name ; //用户名
    public String head_img;   //用户的头像
  }
}

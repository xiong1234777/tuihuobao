package com.tb.tuihuobao.global;

/**
 * Created by zxh on 2016/5/9.
 */
public class UrlHelper {


  //访问的接口的地址
  private final static String NET_HOST = "http://www.taobpg.com/";

  //返回新闻条目的的接口
  public final static String getNewsItemsUrl(String account, String pwd, String type, int pn) {

    StringBuilder sb = new StringBuilder();

    sb.append(NET_HOST);
    sb.append("newsinterface/sharenewslist");
    sb.append("?account=").append(account);
    sb.append("&pwd=").append(pwd);
    sb.append("&type=").append(type);
    sb.append("&pn=").append(pn + "");


    return sb.toString();
  }


  public final static String getNesDiscussionUrl(String account, String pwd, String newsid, int
          pn) {
    StringBuilder sb = new StringBuilder();

    sb.append(NET_HOST);
    sb.append("sharenewslist");
    sb.append("?account=").append(account);
    sb.append("&pwd=").append(pwd);
    sb.append("&id=").append(newsid);
    sb.append("&pn=").append(pn + "");


    return sb.toString();
  }

  //评价的url
  public final static String SUBMIT_DISCUSSION = NET_HOST + "sharesenddiscussion";
  //登录的url
  public final static String LOGIN = NET_HOST +"indexinterface/login";
  //注册的url
  public final static String REG = NET_HOST+"indexinterface/register";
  //二维码登录接口
  public final static String ERWEIMA_LOGIN = NET_HOST+"indexinterface/qrcodelogin";
  public final static String test = NET_HOST+"indexinterface/test";
}

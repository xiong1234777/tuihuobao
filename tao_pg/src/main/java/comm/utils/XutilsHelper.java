package comm.utils;

import android.widget.ImageView;

import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by xiaohu on 2016/3/26.
 */
public class XutilsHelper {

  //图像的配置
  private static ImageOptions mImageOptions =new ImageOptions.Builder().setRadius(DensityUtil.dip2px(10)).build();

  //doget请求
  public static void fetch(RequestParams params, int type, final MyCallBack
          callBack) {
    params.setUseCookie(true);

    //获取系统的时间
    UiTools.current_time = System.currentTimeMillis();

    if (type == 0) {
      //get
      x.http().get(params, callBack);
      
    } else if (type == 1) {
      //post
      x.http().post(params, callBack);
    }
    
  }

  public static String getResStr(int strid) {
    return x.app().getString(strid);
  }

  public static String[] getResArr(int arr_id) {
    return x.app().getApplicationContext().getResources().getStringArray(arr_id);
  }


  public static void showImage(ImageView view, String head_img) {

    x.image().bind(view,head_img,mImageOptions);
  }
}

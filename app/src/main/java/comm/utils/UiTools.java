package comm.utils;

import android.widget.Toast;

import org.xutils.x;

/**
 * Created by zxh on 2016/4/19.
 */
public class UiTools {

  //显示toast信息
  public static void showToast(String content){
    Toast.makeText(x.app().getApplicationContext(),content,Toast.LENGTH_LONG).show();
  }
}

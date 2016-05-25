package comm.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.xutils.x;

import comm.NewTempActivity;

/**
 * Created by zxh on 2016/4/19.
 */
public class UiTools {

  //显示toast信息
  public static void showToast(String content){
    Toast.makeText(x.app().getApplicationContext(),content,Toast.LENGTH_LONG).show();
  }

  public static void jumpActivity(Context fragment, Class activity,String[]
          keys,
          String
          [] values){
    Intent intent = new Intent(fragment, activity);
    setIntent(keys,values,intent);
    fragment.startActivity(intent);
  }

  /**
   * 直接跳转到
   **/
  public static void jumpTActivity(Context context, Class fragmentclass, String[] keys, String
          [] values) {

    Intent intent = new Intent(context, NewTempActivity.class);
    intent.putExtra("fragmentName", fragmentclass.getName());

    setIntent(keys, values, intent);

    context.startActivity(intent);
  }

  private static void setIntent(String[] keys, String[] values, Intent intent) {
    if (keys != null) {
      if (values.length != keys.length) {
        intent.putExtra(keys[0], values);
      }
    }

    if (keys != null && values.length == keys.length) {
      for (int i = 0; i < keys.length; i++) {
        intent.putExtra(keys[i], values[i]);
      }
    }
  }


}

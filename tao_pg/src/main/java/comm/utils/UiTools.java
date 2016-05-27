package comm.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.xutils.x;

import comm.NewTempActivity;
import comm.view.dialog.Load2Dialog;

/**
 * Created by zxh on 2016/4/19.
 */
public class UiTools {

  public static long current_time = -1;
  //显示toast信息
  public static void showToast(String content) {
    Toast.makeText(x.app().getApplicationContext(), content, Toast.LENGTH_LONG).show();
  }
  public static void jumpActivity(Context fragment, Class activity, String[]
          keys,
                                  String
                                          [] values) {
    Intent intent = new Intent(fragment, activity);
    setIntent(keys, values, intent);
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

  public static class UIHelper {
    /**
     * 获取一个加载提示dialog
     *
     * @param context
     * @return
     */
    public static Dialog getLoadDialog2(Activity context) {
      Load2Dialog dialog = new Load2Dialog(context);
      Window window = dialog.getWindow();
      window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
      dialog.setCancelable(true);
      dialog.show();
      WindowManager m = context.getWindowManager();
      Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
      WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
      p.height = (int) (d.getHeight() * 0.25);
      p.width = (int) (d.getWidth() * 0.25);
      p.height = p.width = Math.min(p.height, p.width);
      dialog.onWindowAttributesChanged(p);
      p.dimAmount = 0f;// 设置背景不变暗
      window.setAttributes(p);
      return dialog;
    }

  }
}

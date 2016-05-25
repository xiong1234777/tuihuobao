package comm;
import android.app.Application;

import org.xutils.x;

/**
 * Created by zxh on 2016/4/14.
 */
public class BaseApplication extends Application{
  @Override
  public void onCreate() {
    super.onCreate();
    //xutils
    x.Ext.init(this);
  }
}

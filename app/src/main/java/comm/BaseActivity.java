package comm;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

public class BaseActivity extends FragmentActivity {

  protected void onCreate(Bundle arg0) {
    super.onCreate(arg0);
    //如果是大于4.4的系统
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
      localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
    }

    ActivityCollector.addActivity(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ActivityCollector.removeActivity(this);
  }
  @Override
  protected void onResume() {
    // TODO Auto-generated method stub
    super.onResume();
  }
  @Override
  protected void onPause() {
    // TODO Auto-generated method stub
    super.onPause();
  }
  @Override
  protected void onSaveInstanceState(Bundle outState) {
    // TODO Auto-generated method stub
    super.onSaveInstanceState(outState);
  }
}

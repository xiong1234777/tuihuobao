package comm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

  protected void onCreate(Bundle arg0) {
    super.onCreate(arg0);
    ActivityCollector.addActivity(this);
//    //如果是大于4.4的系统
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//      getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//      getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//    }
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

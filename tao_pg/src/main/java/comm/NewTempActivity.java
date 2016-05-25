package comm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.tb.tuihuobao.R;


/**
 * Created by zxh on 2016/4/28.
 */
public class NewTempActivity extends BaseActivity{


  @Override
  protected void onCreate(Bundle arg0) {
    super.onCreate(arg0);
    setContentView(R.layout.activity_temp);
    //根据传过来的
    createFragment(getIntent());
  }

  public void createFragment(Intent intent) {
    Bundle extras = intent.getExtras();
    String fragmentName = extras.getString("fragmentName");
    extras.remove("fragmentName");
    try {

      //反射实例化
      Fragment baseFragment = (Fragment) Class
              .forName(fragmentName).newInstance();

      baseFragment.setArguments(extras);

      FragmentManager fManager = getSupportFragmentManager();
      FragmentTransaction transaction = fManager.beginTransaction();

      transaction.add(R.id.fl_content, baseFragment);

      //设置动画效果
      transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

package comm.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.tb.tuihuobao.R;

import java.util.List;

import comm.FrgAdapter;

/**
 * Created by zxh on 2016/4/25.
 */
public class TabView<T> extends LinearLayout {

  private View mRootView = null;
  private TabLayout mTabLayout = null;
  private ViewPager mVpcontent =null;

  public TabView(Context context) {
    super(context);
    initView(context);
  }

  public TabView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initView(context);
  }

  public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView(context);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public TabView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    initView(context);
  }

  private void initView(Context context){

    if(mRootView == null){
        mRootView = View.inflate(context, R.layout.tab_view,this);
    }

    mTabLayout = (TabLayout) mRootView.findViewById(R.id.tab_layout);
    mVpcontent = (ViewPager) mRootView.findViewById(R.id.vp_content);

  }

  public TabView setTandC(List<String> titles, List<Fragment> fragments, FragmentManager fm,int
          tab_mode){

    mTabLayout.setTabMode(tab_mode);
    //添加tab选项卡
    for (String title : titles) {
      mTabLayout.addTab(mTabLayout.newTab().setText(title));
    }

    //设置适配器
    FrgAdapter adapter = new FrgAdapter(fm,fragments,titles);
    mVpcontent.setOffscreenPageLimit(2);
    //给ViewPager设置适配器
    mVpcontent.setAdapter(adapter);
    //将TabLayout和ViewPager关联起来。
    mTabLayout.setupWithViewPager(mVpcontent);

    return this;
  }

}

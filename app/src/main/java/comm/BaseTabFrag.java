package comm;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.new_car_pg.car_bg.R;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by zxh on 2016/4/14.
 * tab类型的fragment
 */
public abstract class BaseTabFrag extends BaseFrag {

  protected TabLayout mtab_layout = null;
  protected ViewPager mvp_content = null;
  //这是fragment容器
  protected List<BaseFrag> mFrgLists = null;
  //设置标题
  protected List<String> mTitleLists = null;

  @Override
  public void initData(Bundle savedInstanceState) {

    mtab_layout = (TabLayout) mRootView.findViewById(R.id.tab_layout);
    mvp_content = (ViewPager) mRootView.findViewById(R.id.vp_content);
    //初始化
    mFrgLists = getFragments();
    mTitleLists = getTitles();
    //添加tab选项卡
    for (String title : mTitleLists) {
      mtab_layout.addTab(mtab_layout.newTab().setText(title));
    }
    //设置frg适配器
    FrgAdapter mAdapter = new FrgAdapter(getChildFragmentManager());
    //给ViewPager设置适配器
    mvp_content.setAdapter(mAdapter);
    //取消预加载
    mvp_content.setOffscreenPageLimit(0);
    //将TabLayout和ViewPager关联起来。
    mtab_layout.setupWithViewPager(mvp_content);
    //给Tabs设置适配器
    mtab_layout.setTabsFromPagerAdapter(mAdapter);
  }

  //Fragment适配器
  private class FrgAdapter extends FragmentPagerAdapter {
    public FrgAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      return mFrgLists.get(position);
    }

    @Override
    public int getCount() {
      return mFrgLists.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      if (mTitleLists == null)
        return super.getPageTitle(position);
      else
        return mTitleLists.get(position);//页卡标题
    }
  }

  @Override
  public int getLayoutId() {
    return R.layout.frag_base_tab;
  }

  @Override
  public void onDetach() {
    super.onDetach();
    try {
      Field childFragmentManager = Fragment.class
              .getDeclaredField("mChildFragmentManager");
      childFragmentManager.setAccessible(true);
      childFragmentManager.set(this, null);

    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  //给tab设置标题
  public abstract List<String> getTitles();

  //给tab设置Fragment
  public abstract List<BaseFrag> getFragments();
}

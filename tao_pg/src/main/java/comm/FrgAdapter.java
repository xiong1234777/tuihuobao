package comm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import java.util.List;

/**
 * Created by xiaohu on 2016/4/24.
 */
//Fragment适配器
public class FrgAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFrgLists=null;
    private List<String> mTitleLists=null;

    public FrgAdapter(FragmentManager fm, List<Fragment> mFrgLists, List<String> mTitleLists) {
        super(fm);
        this.mFrgLists = mFrgLists;
        this.mTitleLists = mTitleLists;
    }

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

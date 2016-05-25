package comm;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.new_car_pg.car_bg.R;

import java.util.List;

import comm.view.RefreshLayout;

/**
 * Created by zxh on 2016/4/12.
 */
public abstract class BaseListFrag<I, C extends BaseP<I>> extends BaseFrag<I, C>
        implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, RefreshLayout.OnLoadListener {

  protected ListView mListView = null;
  protected RefreshLayout mRefresh = null;
  //适配器
  protected CommonAdapter mAdapter = null;
  //数据源
  protected List mDatas = null;
  /**
   * Fragment当前状态是否可见
   */
  protected boolean isVisible = false;
  /**
   * 标志位，标志已经初始化完成
   */
  protected boolean isPrepared = false;
  /**
   * 是否已被加载过一次，第二次就不再去请求数据了
   */
  protected boolean mHasLoadedOnce = false;
  /*设置适配器*/
  protected void setAdapterOrUpDate() {
    //生成adapter
    if (mAdapter == null) {
      mAdapter = getAdapter();
      //设置适配器
      mListView.setAdapter(mAdapter);
    } else {
      //更新数据
      mAdapter.upateData(mDatas);
    }
  }

  //更新适配器
  protected void updateAdapter(){

    if(mAdapter!=null){
      mAdapter = null;
    }

    mAdapter = getAdapter();

    mListView.setAdapter(mAdapter);
  }

  @Override
  public void initData(Bundle savedInstanceState) {
    mRefresh = (RefreshLayout) mRootView.findViewById(R.id.refresh);
    mListView = (ListView) mRootView.findViewById(R.id.lv_task_list);
    mRefresh.setOnRefreshListener(this);
    mRefresh.setOnLoadListener(this);
    mListView.setOnItemClickListener(this);
    mRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
  }

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (getUserVisibleHint()) {
      isVisible = true;
      onVisible();
    } else {
      isVisible = false;
      onInvisible();
    }
  }

  /**
   * 可见
   */
  protected void onVisible() {
    lazyLoad();
  }

  protected boolean lazyLoad() {
    //如果返回true就不继续执行了
    return!isPrepared || !isVisible||mHasLoadedOnce;
  }

  /**
   * 不可见
   */
  protected void onInvisible() {

  }
  /**
   * 设置适配器
   **/
  public abstract CommonAdapter getAdapter();

  @Override
  public int getLayoutId() {
    return R.layout.frag_base_list;
  }

  @Override
  public void onRefresh() {
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
  }

  @Override
  public void onLoad() {

  }
}

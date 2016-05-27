package comm;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cjj.MaterialRefreshLayout;
import com.tb.tuihuobao.R;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxh on 2016/4/12.
 */
public abstract class BaseListFrag<I, C extends BaseP<I>> extends BaseFrag<I, C>
        implements AdapterView.OnItemClickListener {

  protected ListView mListView = null;
  protected MaterialRefreshLayout mRefresh = null;
  //适配器
  protected CommonAdapter mAdapter = null;
  //数据源
  protected List mDatas = new ArrayList();


  /*设置适配器*/
  protected void setAdapterOrUpDate() {
    //生成adapter
    if (mAdapter == null) {
      mAdapter = getAdapter();

      //设置适配器
      mListView.setAdapter(mAdapter);
    }

    LogUtil.e("加载适配器");

    //更新数据
    mAdapter.upateData(mDatas);

  }

  @Override
  public void initData(Bundle savedInstanceState) {
    mRefresh = (MaterialRefreshLayout) mRootView.findViewById(R.id.refresh);
    mListView = (ListView) mRootView.findViewById(R.id.lv_task_list);
    mListView.setOnItemClickListener(this);
    if(null != mDatas) {
      setAdapterOrUpDate();
    }
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
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
  }

}

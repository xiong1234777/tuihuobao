package comm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFrag<V, T extends BaseP<V>> extends Fragment {
  protected View mRootView = null;
  protected T mPresenter = null;
  protected Context mContext = null;

  //初始化数据
  public abstract void initData(Bundle savedInstanceState);

  //获取布局的id
  protected abstract int getLayoutId();

  //创建presenter
  protected abstract T createPresenter();


  @Override
  public void onAttach(Context context) {
    // TODO Auto-generated method stub
    super.onAttach(context);

    mContext = context;

    //初始化presenter
    mPresenter = createPresenter();

    if (mPresenter != null) {
      //绑定presenter
      mPresenter.attachView((V) this);
    }

  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    //初始化根布局
    mRootView = inflater.inflate(getLayoutId(), null);

    //依赖注解xutils
    return mRootView;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {

    if(savedInstanceState == null) {
      //初始化数据
      initData(savedInstanceState);
    }
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public void onDetach() {
    super.onDetach();
    //解绑
    if (mPresenter != null) {
      mPresenter.detachView();
    }

    if (mContext != null) {
      mContext = null;
    }
  }

  /*设置frg对应的view是否显示的方法*/
  @Override
  public void setMenuVisibility(boolean menuVisible) {
    if (getView() != null) {
      getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }
    super.setMenuVisibility(menuVisible);
  }
}

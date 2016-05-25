package comm;

import java.lang.ref.WeakReference;

/**
 * Created by zxh on 2016/3/17.
 */
public abstract class BaseP<V> {
  //view接口的弱引用对象
  protected   WeakReference<V> mView=null;

  //绑定接口
  public void attachView(V v){
    mView = new WeakReference<V>(v);
  }

  //是否绑定
  public boolean isAttached(){
    return mView!=null&&mView.get()!=null;
  }

  //获取mView
  protected V getView(){
    return mView.get();
  }

  //解绑接扣
  public void detachView(){
    if(mView!=null){
      mView.clear();
      mView=null;
    }
  }
}

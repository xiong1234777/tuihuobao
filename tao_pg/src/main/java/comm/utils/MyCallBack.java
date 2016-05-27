package comm.utils;


import android.os.Handler;

import org.json.JSONException;
import org.xutils.common.Callback;

/**
 * Created by xiaohu on 2016/3/26.
 * 我的回调接口
 */
public abstract class MyCallBack<T> implements Callback.ProgressCallback<T>{

    //默认正在加载
    public boolean isLoading = true;

    public abstract void success(T result) throws JSONException;
    public abstract void fail();

    @Override
    public void onWaiting() {

    }
    @Override
    public void onStarted() {

    }

    @Override
    public void onLoading(long total, long current, boolean isDownloading) {

    }

    @Override
    public void onSuccess(final T result) {
        //获取系统当前的时间
        if(System.currentTimeMillis()-UiTools.current_time<2000){
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        success(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        UiTools.showToast("数据有点小问题");
                    }
                }
            }, 3000);
        }else{
            try {
                success(result);
            } catch (JSONException e) {
                e.printStackTrace();
                UiTools.showToast("数据有点小问题");
            }
        }
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        //获取系统当前的时间
        if(System.currentTimeMillis()-UiTools.current_time<2000){
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    //设置加载的状态
                    fail();
                }
            }, 3000);
        }else{
            fail();
        }
        UiTools.showToast("服务器有点忙。");
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}

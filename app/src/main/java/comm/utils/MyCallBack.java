package comm.utils;


/**
 * Created by xiaohu on 2016/3/26.
 * 我的回调接口
 */
public abstract class MyCallBack {

    /**成功时回调**/
    public abstract void onSuccess(String result);
    /**失败时回调**/
    public void onError(Throwable ex, boolean isOnCallback) {
        //失败时提示
        UiTools.showToast(ex.getMessage());
    }
    /**完成时回调**/
    public void onFinished() {
    }
    /**等待时时回调**/
    public void onWaiting() {
    }
    /**开始时回调**/
    public void onStarted() {
    }
    /**正在请求时回调**/
    public void onLoading(long total, long current, boolean isDownloading) {
    }
}

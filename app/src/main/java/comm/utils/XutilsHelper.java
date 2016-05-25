package comm.utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by xiaohu on 2016/3/26.
 */
public class XutilsHelper {

  //doget请求
  public static void fetch(RequestParams requestParams,int type,final MyCallBack callBack) {
    if (type == 0) {
      x.http().get(requestParams, new Callback.ProgressCallback<String>() {
        @Override
        public void onSuccess(String result) {
          callBack.onSuccess(result);
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
          callBack.onError(ex, isOnCallback);
        }

        @Override
        public void onCancelled(CancelledException cex) {

        }
        @Override
        public void onFinished() {
          callBack.onFinished();
        }

        @Override
        public void onWaiting() {
          callBack.onWaiting();
        }

        @Override
        public void onStarted() {
          callBack.onStarted();
        }
        @Override
        public void onLoading(long total, long current, boolean isDownloading) {
          callBack.onLoading(total, current, isDownloading);
        }
      });
    } else if (type == 1) {
      x.http().post(requestParams, new Callback.ProgressCallback<String>() {
        @Override
        public void onSuccess(String result) {
          callBack.onSuccess(result);
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
          callBack.onError(ex, isOnCallback);
        }

        @Override
        public void onCancelled(CancelledException cex) {

        }
        @Override
        public void onFinished() {
          callBack.onFinished();
        }

        @Override
        public void onWaiting() {
          callBack.onWaiting();
        }

        @Override
        public void onStarted() {
          callBack.onStarted();
        }

        @Override
        public void onLoading(long total, long current, boolean isDownloading) {
          callBack.onLoading(total, current, isDownloading);
        }
      });
    }
  }

  public static String getResStr(int strid){
    return x.app().getString(strid);
  }
}

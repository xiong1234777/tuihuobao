package comm.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.tb.tuihuobao.bean.UserInfo;

import org.xutils.x;

/**
 * SharedPreferences的数据处理类
 *
 * @author zxh
 */
public class SPFTools {
  protected static final SharedPreferences M_PREFERENCES;
  protected static final Editor M_EDITOR;

  static {
    M_PREFERENCES = PreferenceManager.getDefaultSharedPreferences(x.app().getApplicationContext());
    M_EDITOR = M_PREFERENCES.edit();
  }

  /**
   * 清除所有的文件信息
   */
  public static void delAll() {
    M_EDITOR.clear().commit();
  }

  /***
   * <a>插入单个值</a>
   *
   * @param k
   * @param content
   */
  public static void insertData(String k, String content) {
    M_EDITOR.putString(k, content);
    M_EDITOR.commit();
  }

  /**
   * <a>以数组的形式插入值<a/>
   *
   * @param key
   * @param content
   */
  public static void insertData(String[] key, String[] content) {
    for (int i = 0; i < key.length; i++) {
      M_EDITOR.putString(key[i], content[i]);
    }
    M_EDITOR.commit();
  }

  /**
   * @param key
   * @return
   */
  public static String queryStr(String key) {
    return M_PREFERENCES.getString(key, null);
  }


  public static void delStrByKey(String userPhoneNumOnLogin) {
    M_EDITOR.remove(userPhoneNumOnLogin);
    M_EDITOR.commit();
  }


  public static class SPHelper{

    public static UserInfo getUserInfo(){

      String string = M_PREFERENCES.getString("user_info", null);

      if(null == string){
        return null;
      }

      return DataTools.getGosn().fromJson(string,UserInfo.class);
    }
  }

}

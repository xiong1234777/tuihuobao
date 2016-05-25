package comm;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * ActivityCollector activity管理器
 *
 * @author zxh
 *         对activity进行统一的管理
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finshAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 清除主页以外的Activity
     **/
    public static void removeButHome() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                if (activity.getClass().getSimpleName()
                        .equals(Activity.class
                                .getSimpleName()))
                    continue;
                activity.finish();
            }

        }
    }
}

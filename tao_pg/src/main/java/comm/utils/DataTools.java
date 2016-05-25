package comm.utils;

import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by SX on 2016/4/23.
 */
public class DataTools {

    public static boolean isPhoneNum(String phoneNum) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(phoneNum);

        return m.matches();
    }

    public static class DataToolsHolder {
        public static final Gson mGosn = new Gson();
    }

    public static String filter(String str) throws PatternSyntaxException {
        // 只允许字母和数字
        // String   regEx  =  "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~@#$%^&*()+=|{}\\[\\]<>/?~！@#￥%……&*（）——|{}【】]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static Gson getGosn() {
        return DataToolsHolder.mGosn;
    }

    //判断有没有输入特殊字符
    public static boolean isFilter(String checked) {
        return checked.equals(filter(checked));
    }

}

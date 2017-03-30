package util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/26.
 */
public class RegexUtil {

    public static final String EMAIL_REGEX = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    public static final String PASSWORD_REGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$";

    public static boolean isEmail(String aEmail) {
        if (!TextUtils.isEmpty(aEmail)) {
            Pattern lPattern = Pattern.compile(EMAIL_REGEX);
            Matcher lMatcher = lPattern.matcher(aEmail);
            return lMatcher.matches();
        }
        return false;
    }

    /**
     * 返回是否为6到15位数字或密码
     * @param aPassword
     * @return
     */
    public static boolean checkPassword(String aPassword) {
        if (!TextUtils.isEmpty(aPassword)) {
            Pattern lPattern = Pattern.compile(PASSWORD_REGEX);
            Matcher lMatcher = lPattern.matcher(aPassword);
            return lMatcher.matches();
        }
        return false;
    }

    public static String getStartSubString(String aSourceString, String aEndString, boolean aContainsEndString){
        String lStartSubString = aSourceString;
        if (!TextUtils.isEmpty(aSourceString) && !TextUtils.isEmpty(aEndString)) {
            int lEndIndex = aSourceString.indexOf(aEndString);
            if (lEndIndex >= 0) {
                if (aContainsEndString) {
                    lEndIndex += aEndString.length();
                }
                lStartSubString = aSourceString.substring(0, lEndIndex);
            }
        }
        return lStartSubString;
    }
}

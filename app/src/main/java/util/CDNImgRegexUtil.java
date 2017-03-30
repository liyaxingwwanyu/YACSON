package util;

import android.text.TextUtils;

/**
 * Created by sheenly on 16/4/11.
 */
public class CDNImgRegexUtil {
    public static final String IMG_URL_PARAMS_BOTH = "!/both/";
    public static final String IMG_URL_PARAMS_FH = "!/fh/";
    public static final String IMG_URL_PARAMS_X = "x";
    public static final String IMG_URL_PARAMS_END = "/format/jpg";

    public static final String SCREEN_PATH_IGNORE_FIELD_REGEX[] = {IMG_URL_PARAMS_BOTH + "[0-9]+"+IMG_URL_PARAMS_X+"[0-9]+" + IMG_URL_PARAMS_END,
            IMG_URL_PARAMS_FH + "[0-9]+" + IMG_URL_PARAMS_END};

    public static String regexUrlForScreen(String aUrl) {
        if (!TextUtils.isEmpty(aUrl)) {
            for (String lRegex : SCREEN_PATH_IGNORE_FIELD_REGEX) {
                aUrl = aUrl.replaceAll(lRegex, "");
            }
        }
        return aUrl;
    }
}

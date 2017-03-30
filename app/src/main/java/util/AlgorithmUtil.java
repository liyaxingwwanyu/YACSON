package util;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by sheenly on 16/3/23.
 */
public class AlgorithmUtil {

    public static int getPosForTotal(int aRealCount, int aCurPosForReal, int aTotalCount) {
        return aCurPosForReal + aTotalCount / 2 - aTotalCount / 2 % aRealCount;
    }

    public static int dip2px(Context aContext, float aDpValue) {
        float lScale = aContext.getResources().getDisplayMetrics().density;
        return (int) (aDpValue * lScale + 0.5f);
    }

    public static String getIDFromUrl(String aUrl) {
        return FileUtil.getFileNameForUrl(aUrl, "/", ".");
    }

    //aCompareDate日期是否大于(可包含等于)aDate日期
    public static boolean dateCompare(String aDate, String aCompareDate, String aSeparator, boolean aContainsEquals) {
        if (!TextUtils.isEmpty(aDate) && !TextUtils.isEmpty(aCompareDate) && !TextUtils.isEmpty(aSeparator)) {
            try {
                String[] aTimeArr = aDate.split(aSeparator);
                String[] aCompareTimeArr = aCompareDate.split(aSeparator);
                if (aTimeArr.length == aCompareTimeArr.length) {
                    for (int i = 0; i < aTimeArr.length; i++) {
                        if (Integer.parseInt(aCompareTimeArr[i]) > Integer.parseInt(aTimeArr[i])) {
                            return true;
                        }
                    }
                }
                if (aContainsEquals) {
                    return aDate.equals(aCompareDate);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

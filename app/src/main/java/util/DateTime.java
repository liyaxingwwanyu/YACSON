package util;

import android.text.TextUtils;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sheenly on 16/3/10.
 */
public class DateTime {
    public static final String mTimeFormatDefault = "kk:mm";
    public static final String mDateFormatDefault = "EEEE dd MMM";
    public static final String mDateTimeFormatDefault = "yyyy-MM-dd HH:mm:ss";

    public static String getTime() {
        Calendar lCalendar = Calendar.getInstance();
        lCalendar.setTimeInMillis(System.currentTimeMillis());
        return DateFormat.format(mTimeFormatDefault, lCalendar).toString();
    }

    public static String getDate() {
        Date lCurrDate = new Date(System.currentTimeMillis());
        SimpleDateFormat lFormat = new SimpleDateFormat(mDateFormatDefault);
        return lFormat.format(lCurrDate);
    }

    public static String getDateTime(long aTimeMillis) {
        Date lCurrDate = new Date(aTimeMillis);
        SimpleDateFormat lFormat = new SimpleDateFormat(mDateTimeFormatDefault);
        return lFormat.format(lCurrDate);
    }

    public static String getCurrentTime(String aFormatStr) {
        String lFormatStr = "yyyy-MM-dd";
        if (!TextUtils.isEmpty(aFormatStr)) {
            lFormatStr = aFormatStr;
        }
        SimpleDateFormat lFormatter = new SimpleDateFormat(lFormatStr,
                Locale.getDefault());
        Date lDate = new Date(System.currentTimeMillis());
        return lFormatter.format(lDate);
    }

    public static String conversionDateToTimeMillis(String aDate) {
        if (!TextUtils.isEmpty(aDate)) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                Date date = formatter.parse(aDate);
                if (date != null) {
                    return date.getTime() + "";// date类型转成long类型
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

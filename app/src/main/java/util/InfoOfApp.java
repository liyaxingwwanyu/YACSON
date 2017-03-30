package util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sheenly on 16/3/30.
 */
public class InfoOfApp {

    public static final String KEYS[] = {"version", "packageName"};
    private Context mContext;
    private Map<String, Object> mDataMap;// ID Name
    private String mCurrPackageName;
    private PackageInfo mPackInfo = null;

    public InfoOfApp(Context aContext) {
        mContext = aContext;
        mDataMap = new HashMap<String, Object>();
    }

    public Map<String, Object> getData() {
        return mDataMap;
    }

    public void init() {
        mCurrPackageName = mContext.getPackageName();
        PackageManager lPackageManager = mContext.getPackageManager();
        try {
            mPackInfo = lPackageManager.getPackageInfo(mCurrPackageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        getVersionName();
        getPackName();
    }

    // App版本
    private void getVersionName() {
        String lVersionName = mPackInfo.versionName;
        mDataMap.put(KEYS[0], lVersionName);
    }

    // 当前App包名
    private void getPackName() {
        mDataMap.put(KEYS[1], mCurrPackageName);
    }
}

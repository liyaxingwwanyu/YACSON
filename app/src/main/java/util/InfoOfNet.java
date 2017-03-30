package util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sheenly on 16/4/11.
 */
public class InfoOfNet {

    public static final String KEYS[] = {"network"};
    private Context mContext;
    private Map<String, Object> mDataMap;
    private ConnectivityManager mConnManager;
    private NetworkInfo mNetworkInfo;
    private int mNetworkType = -1;

    public InfoOfNet(Context aIUIInterface) {
        mContext = aIUIInterface;
        mDataMap = new HashMap<String, Object>();
    }

    public void init() {
        mConnManager = (ConnectivityManager) mContext.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        mNetworkInfo = mConnManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            mNetworkType = mNetworkInfo.getType();
        }
    }

    public Map<String, Object> getData() {
        return mDataMap;
    }

    public void execute() {
        getNetwork();
    }

    // 网络类型Wifi或流量
    private String computeNetwork() {
        String lNetType = "null";
        switch (mNetworkType) {
            case ConnectivityManager.TYPE_WIFI:
                lNetType = computeWIFI();
                break;
            case ConnectivityManager.TYPE_MOBILE:
                lNetType = computeMOBILE();
                break;
        }
        return lNetType;
    }

    private String computeMOBILE() {
        String lNetType = mNetworkType + "";
        switch (mNetworkInfo.getSubtype()) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
                lNetType = compute2g();
                break;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                lNetType = compute3g();
                break;
            case TelephonyManager.NETWORK_TYPE_LTE:// LTE是3g到4g的过渡，是3.9G的全球标准
                lNetType = compute4g();
                break;
        }
        return lNetType;
    }

    private String computeWIFI() {
        return "wifi";
    }

    private String compute2g() {
        return "2g";
    }

    private String compute3g() {
        return "3g";
    }

    private String compute4g() {
        return "4g";
    }

    private void getNetwork() {
        mDataMap.put(KEYS[0], computeNetwork());
    }

}

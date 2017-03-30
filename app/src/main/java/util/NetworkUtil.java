package util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sheenly on 16/3/30.
 */
public class NetworkUtil {

    public static InputStream getStream(String aUrl) {
        try {
            URL lUrl = new URL(aUrl);
            URLConnection lConnection = lUrl.openConnection();
            lConnection.connect();
            return lConnection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Bitmap getBitmap(String aUrl) {
        Bitmap lBitmap = null;
        try {
            if (aUrl != null) {
                URL lImgUrl = new URL(aUrl);
                HttpURLConnection lConn = (HttpURLConnection) lImgUrl.openConnection();
                lConn.setDoInput(true);
                lConn.connect();
                lConn.setConnectTimeout(10000);
                InputStream lInputStream = lConn.getInputStream();
                lBitmap = BitmapFactory.decodeStream(lInputStream);
                lInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lBitmap;
    }

    // 是否可联互联网 aType=0:ConnectInternet;aType=1:ConnectWifi
    public static boolean getIsConnect(Context aContext, int aType) {
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) aContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                if (aType == 0) {
                    return mNetworkInfo.isAvailable();
                }
                if (aType == 1) {
                    int netType = mNetworkInfo.getType();
                    if (netType == ConnectivityManager.TYPE_WIFI) {
                        return mNetworkInfo.isConnected();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

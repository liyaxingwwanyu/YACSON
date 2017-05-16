package Z_MajorStructure.server;

import android.text.TextUtils;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/**
 * Created by sheenly on 16/4/11.
 */
public class HttpRequestDefault {

    private int mConnectTimeout = 10;
    private int mWriteTimeout = 10;
    private int mReadTimeout = 10;
    private OkHttpClient mOkHttpClient;

    public HttpRequestDefault() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(mConnectTimeout, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(mWriteTimeout, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(mReadTimeout, TimeUnit.SECONDS);
    }

    public String requestSync(String aUrl) {
        if (!TextUtils.isEmpty(aUrl)) {
            try {
                Request lRequest = new Request.Builder().url(aUrl).build();
                Response lResponse = mOkHttpClient.newCall(lRequest).execute();
                return lResponse.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String requestSyncForPostJson(String aUrl, String aJson) {
        if (!TextUtils.isEmpty(aUrl)) {
            try {
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody lBody = RequestBody.create(JSON, aJson);
                Request lRequest = new Request.Builder().url(aUrl).post(lBody).build();
                Response lResponse = mOkHttpClient.newCall(lRequest).execute();
                return lResponse.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void initHttps() {
        try {
//            mOkHttpClient.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            SSLContext lSSLContext = SSLContext.getInstance("SSL");
            lSSLContext.init(null, new TrustManager[]{new X509TrustManagerDefault()}, new SecureRandom());
            mOkHttpClient.setSslSocketFactory(lSSLContext.getSocketFactory());
            mOkHttpClient.setHostnameVerifier(new HostnameVerifierDefault());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String requestSyncForPost(String aUrl, Map<String, String> aParam) {
        if (!TextUtils.isEmpty(aUrl)) {
            try {
                FormEncodingBuilder lFormEncodingBuilder = new FormEncodingBuilder();
                if (aParam != null) {
                    for (Map.Entry<String, String> entry : aParam.entrySet()) {
                        if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                            lFormEncodingBuilder.add(entry.getKey(), entry.getValue());
                        }
                    }
                }
                RequestBody lBody = lFormEncodingBuilder.build();
                Request lRequest = new Request.Builder().url(aUrl).post(lBody).build();
                Response lResponse = mOkHttpClient.newCall(lRequest).execute();
                //System.out.println("requestSyncForPost lResponse = " + lResponse);
                //System.out.println("requestSyncForPost lResponse.body() = " + lResponse.body());
                return lResponse.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}

package util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.net.URLEncoder;

import login.BSModelForLogin;
import server.AgentVoData;
import server.HttpRequestDefault;
import top.coolook.gather.Gather;
import top.coolook.gather.utils.DES;
import top.coolook.vo.AgentVo;

//import top.coolook.gather.utils.DES;

/**
 * Created by wty on 2016/8/8.
 */
public class TokenToolkit {

    protected Context mContext;
    private SharedPreferences mSP;
    private HttpRequestDefault mHttpsRequest;

    public TokenToolkit(Context aContext, HttpRequestDefault aHttpsRequest) {
        mContext = aContext;
        mHttpsRequest = aHttpsRequest;
        mSP = mContext.getSharedPreferences(BSModelForLogin.SP_NAME, Context.MODE_PRIVATE);
    }

    public void checkToken() {
        if (TextUtils.isEmpty(mSP.getString(BSModelForLogin.SP_FILED[0], null))) {
            initToken();
        } else {
            updateToken(true);
        }
    }

    private void initToken() {
        String lAppID = "1";
        String lSecret = "aefewfrvgerb";
        long lCurTime = System.currentTimeMillis();
        String lSign = DES.desCrypto(lAppID + "|" + lSecret + "|" + lCurTime, DES.getPwd());
        lSign = URLEncoder.encode(lSign);
        String lUrl = "https://api-center.coolook.org/auth/gettoken" + "?sign=" + lSign + "&subtime=" + lCurTime;
        //System.out.println("TokenToolkit initToken lUrl = " + lUrl);

        String lJsonData = mHttpsRequest.requestSync(lUrl);
        //System.out.println("TokenToolkit initToken lJsonData = " + lJsonData);

        AgentVo lAgentVo = AgentVoData.resolve(lJsonData);
        if (lAgentVo != null) {
            String lSelfToken = lAgentVo.getAccess_token();
            //System.out.println("TokenToolkit initToken lSelfToken = " + lSelfToken);
            mSP.edit().putString(BSModelForLogin.SP_FILED[0], lSelfToken).commit();
        }
    }

    private void updateToken(boolean aWhile) {
        String lOldToken = mSP.getString(BSModelForLogin.SP_FILED[0], null);
        if (!TextUtils.isEmpty(lOldToken)) {
            String lUUID = TextUtils.isEmpty(mSP.getString(BSModelForLogin.SP_FILED[1], null)) ? "0" : Gather.getUUID(mContext);
            String lUrl = "https://api-center.coolook.org/authu/refreshtoken" + "?access_token=" + lOldToken + "&uid=" + lUUID;
            //System.out.println("TokenToolkit updateToken lUrl = " + lUrl);
            String lJsonData = mHttpsRequest.requestSync(lUrl);
            //System.out.println("TokenToolkit updateToken lJsonData = " + lJsonData);
            AgentVo lAgentVo = AgentVoData.resolveForOriginal(lJsonData);
            if (lAgentVo != null) {
                if (lAgentVo.getStatus() == 0) {
                    String lSelfToken = lAgentVo.getAccess_token();
                    //System.out.println("TokenToolkit updateToken lSelfToken = " + lSelfToken);
                    mSP.edit().putString(BSModelForLogin.SP_FILED[0], lSelfToken).commit();
                }
                if (aWhile && lAgentVo.getStatus() != 0) {
                    //System.out.println("TokenToolkit updateToken error");
                    initToken();
                    if (!TextUtils.isEmpty(mSP.getString(BSModelForLogin.SP_FILED[1], null))) {
                        updateToken(false);
                    }
                }
            }
        }
    }

    public String getToken(){
        return mSP.getString(BSModelForLogin.SP_FILED[0], "");
    }
}

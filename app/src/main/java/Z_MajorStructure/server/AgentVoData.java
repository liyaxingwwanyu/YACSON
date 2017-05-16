package Z_MajorStructure.server;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

import top.coolook.vo.AgentVo;

/**
 * Created by sheenly on 16/4/11.
 */
public class AgentVoData {
    public static AgentVo resolve(String aJson) {
        if (!TextUtils.isEmpty(aJson)) {
            try {
                JSONObject lJsonObject = new JSONObject(aJson);
                Object lStatusObj = lJsonObject.get("status");
                if (lStatusObj != null && 0 == (Integer) lStatusObj)
                    return JSON.parseObject(aJson, AgentVo.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static AgentVo resolveForOriginal(String aJson) {
        if (!TextUtils.isEmpty(aJson)) {
            try {
                return JSON.parseObject(aJson, AgentVo.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

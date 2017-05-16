package Z_MajorStructure.server;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/4/14.
 */
public class JsonOper {
    public static <T> T jsonToObject(String aJson, Class<T> aClass) {
        if (!TextUtils.isEmpty(aJson)) {
            try {
                Gson lGson = new Gson();
                return lGson.fromJson(aJson, aClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

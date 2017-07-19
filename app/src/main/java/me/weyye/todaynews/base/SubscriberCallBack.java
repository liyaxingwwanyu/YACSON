package me.weyye.todaynews.base;

import android.text.TextUtils;

import me.weyye.todaynews.utils.ToastUtils;

/**
 * Created by Administrator
 * on 2016/5/18.
 */
public abstract class SubscriberCallBack<T> extends BaseCallBack<ResultResponse<T>> {


    @Override
    public void onNext(ResultResponse response) {
        boolean isSuccess = (!TextUtils.isEmpty(response.message) && response.message.equals("success"))
                || !TextUtils.isEmpty(response.success) && response.success.equals("true");
        if (isSuccess) {
            onSuccess((T) response.data);
        } else {
            ToastUtils.showToast(response.message);
            onFailure(response);
        }
    }

    protected abstract void onSuccess(T response);
}

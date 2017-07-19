package me.weyye.todaynews.base;

/**
 * Created by Administrator on 2016/4/14.
 */
public class ResultResponse<T> {

    public String has_more;
    public String message;
    public String success;
    public T data;

    public ResultResponse(String more, String _message, T result) {
        has_more = more;
        message = _message;
        data = result;
    }
}

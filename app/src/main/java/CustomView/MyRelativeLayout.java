package customview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import static util.CtlUtil.hideKeyboard;

/**
 * Created by wy on 2017/3/10.
 *  点击EditText空白处关闭键盘
 */

public class MyRelativeLayout extends RelativeLayout {

    int rawx = 0;
    int rawy = 0;

    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                rawx = (int) event.getRawX();
                rawy = (int) event.getRawY();

                Rect rect = new Rect();
                getChildAt(0).getGlobalVisibleRect(rect);//,获取view的范围矩形
                if (!rect.contains(rawx, rawy)) {//该矩形是否包含这个点
                    hideKeyboard(getChildAt(0));
                }

                break;
        }
        return super.onTouchEvent(event);
    }

}

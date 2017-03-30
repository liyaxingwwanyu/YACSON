package util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sheenly on 16/3/11.
 */
public class CtlUtil {

    public static void hideKeyboard(View aView) {
        InputMethodManager lInputManager = (InputMethodManager) aView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (lInputManager.isActive()) {
            lInputManager.hideSoftInputFromWindow(aView.getApplicationWindowToken(), 0);
        }
    }

    public static void showKeyboard(final View aView) {
        Timer lTimer = new Timer();
        lTimer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager lInputMethodManager = (InputMethodManager) aView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                lInputMethodManager.showSoftInput(aView, InputMethodManager.SHOW_FORCED);
            }
        }, 300);
    }

    public static void moveView(ViewGroup aFrom, View aViewInFrom, ViewGroup aIn) {
        if (aFrom == null || aViewInFrom == null || aIn == null) {
            return;
        }
        int lBeforeCount = aFrom.getChildCount();
        aFrom.removeView(aViewInFrom);
        if (aFrom.getChildCount() != lBeforeCount) {
            aIn.addView(aViewInFrom);
        }
    }
}

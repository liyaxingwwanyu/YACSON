package util;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by sheenly on 16/3/17.
 */
public class AnimationTool {

    public static void execute(int aAnimationIndex, View aView) {
        switch (aAnimationIndex) {
            case 0:
                upAnimation(aView);
                break;
            case 1:
                downAnimation(aView);
                break;
            case 2:
                alphaAnimation(aView, 0.1f, 1.0f, 700);
                break;
            case 3:
                moveBounceAnimation(aView, 0.0f, 0.0f, 1.0f, 0.0f, 1000, false);
                break;
            case 4:
                moveBounceAnimation(aView, 0.0f, -0.8f, 0.0f, 0.0f, 1000, true);
                break;
            case 5:
                moveBounceAnimation(aView, 0.0f, 0.8f, 0.0f, 0.0f, 1000, true);
                break;
            case 6:
                moveBounceAnimation(aView, -0.8f, 0.0f, 0.0f, 0.0f, 1000, true);
                break;
            case 7:
                moveBounceAnimation(aView, 0.8f, 0.0f, 0.0f, 0.0f, 1000, true);
                break;
            case 8:
                alphaAnimation(aView, 1.0f, 0.0f, 300);
                break;
            case 9:
                alphaAnimation(aView, 0.0f, 1.0f, 300);
                break;
            case 10:
                translateAnimation(aView, -1.0f, 0.0f, 0.0f, 0.0f, 300);
                break;
            case 11:
                translateAnimation(aView, 0.0f, -1.0f, 0.0f, 0.0f, 300);
                break;
            case 12:
                translateAnimation(aView, 0.0f, 0.0f, 0.0f, -1.0f, 300);
                break;
            case 13:
                translateAnimation(aView, 0.0f, 0.0f, -1.0f, 0.0f, 300);
                break;
            default:
                break;
        }
    }

    public static void upDownAnimation(View aView, float aFromY, float aToY) {
        translateAnimation(aView, 0.0f, 0.0f, aFromY, aToY, 300);
    }


    public static void translateAnimation(View aView, float aFromX, float aToX, float aFromY, float aToY, long aDuration) {
        Animation lAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, aFromX,
                Animation.RELATIVE_TO_SELF, aToX,
                Animation.RELATIVE_TO_SELF, aFromY,
                Animation.RELATIVE_TO_SELF, aToY);
        lAnimation.setDuration(aDuration);
        lAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        aView.setAnimation(lAnimation);
        lAnimation.start();
    }

    public static void upAnimation(View aView) {
        upDownAnimation(aView, 1.0f, 0.0f);
    }

    public static void downAnimation(View aView) {
        upDownAnimation(aView, 0.0f, 1.0f);
    }

    public static void alphaAnimation(View aView, float aFromAlpha, float aToAlpha, long aDuration) {
        Animation lAnimation = new AlphaAnimation(aFromAlpha, aToAlpha);
        lAnimation.setDuration(aDuration);
        aView.startAnimation(lAnimation);
    }

    public static void moveBounceAnimation(View aView, float aFromX, float aToX, float aFromY, float aToY, int aDuration, boolean aFillAfter){
        Animation lAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, aFromX, Animation.RELATIVE_TO_SELF, aToX, Animation.RELATIVE_TO_SELF, aFromY, Animation.RELATIVE_TO_SELF, aToY);
        lAnimation.setInterpolator(new BounceInterpolator());
        lAnimation.setDuration(aDuration);
        lAnimation.setFillAfter(aFillAfter);
        aView.startAnimation(lAnimation);
    }

    public static void moveAnimationForAbsolute(View aView, float aFromX, float aToX, float aFromY, float aToY, int aDuration, boolean aFillAfter){
        Animation lAnimation = new TranslateAnimation(aFromX, aToX, aFromY,  aToY);
        lAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        lAnimation.setDuration(aDuration);
        lAnimation.setFillAfter(aFillAfter);
        aView.startAnimation(lAnimation);
    }

    public static void setScaleAnimation(View aView, float aFromX, float aToX, float aFromY, float aToY, long aDuration) {
        aView.setScaleX(aToX);
        aView.setScaleY(aToY);
        Animation lAnimation = new ScaleAnimation(aFromX / aToX, 1, aFromY / aToY, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        lAnimation.setDuration(aDuration);
        lAnimation.setFillAfter(true);
        aView.startAnimation(lAnimation);
    }
}

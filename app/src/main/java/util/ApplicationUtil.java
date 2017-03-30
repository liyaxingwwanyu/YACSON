package util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.newcoolook_0329.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/3/31.
 */
public class ApplicationUtil {

    public static void showInfo(Context aContext, String aInfo) {
//        showInfo(aContext, aInfo, null);
        Toast.makeText(aContext, aInfo, Toast.LENGTH_SHORT).show();
    }

    public static void showInfo(Context aContext, String aInfo, Bitmap aBitmap) {
        Toast lToast = new Toast(aContext);
        if (aBitmap != null) {
            lToast.setGravity(Gravity.CENTER, 0, 0);
            View view = LayoutInflater.from(aContext).inflate(R.layout.toast_layout, null);
            ((TextView) view.findViewById(R.id.toast_tv)).setText(aInfo);
            ((ImageView) view.findViewById(R.id.toast_iv)).setImageBitmap(aBitmap);
            lToast.setView(view);
        }
        lToast.show();
    }

    public static void sharedImg(Context aContext, String aImgPath) {
        try {
            if (!TextUtils.isEmpty(aImgPath)) {
                Uri lUri = Uri.fromFile(new File(aImgPath));
                Intent lIntent = new Intent(Intent.ACTION_SEND);
                lIntent.putExtra(Intent.EXTRA_STREAM, lUri);
                lIntent.setType("image/jpg");
                aContext.startActivity(lIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sharedText(Context aContext, String aText) {
        try {
            if (!TextUtils.isEmpty(aText)) {
                Intent lIntent = new Intent(Intent.ACTION_SEND);
                lIntent.putExtra(Intent.EXTRA_TEXT, aText);
                lIntent.setType("text/plain");
                aContext.startActivity(lIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cutImage(Activity aActivity, File aFromPath, int aAspectX, int aAspectY, File aSavePath, int aRequestCode) {
        try {
            if (aFromPath != null && aFromPath.exists()) {
                cutImage(aActivity, Uri.fromFile(aFromPath), aAspectX, aAspectY, aSavePath, aRequestCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cutImage(Activity aActivity, Uri aUri, int aAspectX, int aAspectY, File aSavePath, int aRequestCode) {
        try {
            if (aUri != null) {
                Intent lIntent = new Intent("com.android.camera.action.CROP", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                lIntent.setDataAndType(aUri, "image/*");
                // crop为true时表示显示的view可以剪裁
                lIntent.putExtra("crop", "true");
                // aspectX aspectY 是宽高的比例
                lIntent.putExtra("aspectX", aAspectX);
                lIntent.putExtra("aspectY", aAspectY);
                Uri uri = Uri.fromFile(aSavePath);
                lIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                lIntent.putExtra("outputFormat", "JPEG");
                aActivity.startActivityForResult(lIntent, aRequestCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readGallery(Activity aActivity, int aRequestCode) {
        try {
            Intent lIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            aActivity.startActivityForResult(lIntent, aRequestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void takingPictures(Activity aActivity, File aSavePath, int aRequestCode) {
        try {
            Intent lIntent = new Intent();//调用android自带的照相机
            lIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            lIntent.addCategory("android.intent.category.DEFAULT");
            Uri uri = Uri.fromFile(aSavePath);
            lIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            aActivity.startActivityForResult(lIntent, aRequestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean canOpenApp(Context aContext, String aPackageName) {
        boolean lIsStart = false;
        try {
            PackageManager lPackageManager = aContext.getPackageManager();
            if (lPackageManager.getLaunchIntentForPackage(aPackageName) != null) {
                lIsStart = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lIsStart;
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void lockScreenWindow(Activity aActivity) {
        aActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        aActivity.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        aActivity.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    }

    public static void closeService(Context aContent, Class<?> aClass) {
        Intent lIntent = new Intent();
        lIntent.setClass(aContent, aClass);
        aContent.stopService(lIntent);
    }

    public static void openService(Context aContent, Class<?> aClass) {
        Intent lIntent = new Intent(aContent, aClass);
        aContent.startService(lIntent);
    }

    public static String getChannelCode(Context aContext) {
        String lCode = "";
        try {
            ApplicationInfo lApplicationInfo = aContext.getPackageManager().getApplicationInfo(aContext.getPackageName(), PackageManager.GET_META_DATA);
            Object lValue = lApplicationInfo.metaData.get("CHANNEL");
            if (lValue != null) {
                lCode = lValue.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lCode;
    }

    public static List<Drawable> getIcons(Context aContext, List<String> aPackNames) {
        List<Drawable> lIcons = new ArrayList<Drawable>();
        PackageManager lPackageManager = aContext.getPackageManager();
        for (String lPackName : aPackNames) {
            Drawable lIcon = null;
            if (!TextUtils.isEmpty(lPackName)) {
                try {
                    lIcon = lPackageManager.getApplicationIcon(lPackName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lIcons.add(lIcon);
        }
        return lIcons;
    }

    public static void openActivity(Context aContent, String aWholeClassName, Map<String, String> aParam) {
        try {
            openActivity(aContent, Class.forName(aWholeClassName), aParam, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openActivity(Context aContent, Class<?> aClass, Map<String, String> aParam) {
        openActivity(aContent, aClass, aParam, null);
    }

    public static void openActivity(Context aContent, Class<?> aClass, Map<String, String> aParam, List<Integer> aFlags) {
        Intent lIntent = new Intent();
        if (aParam != null) {
            for (Map.Entry<String, String> lEntry : aParam.entrySet()) {
                lIntent.putExtra(lEntry.getKey(), lEntry.getValue());
            }
        }
        if (aFlags != null) {
            for (Integer lFlag : aFlags) {
                lIntent.addFlags(lFlag);
            }
        }
        lIntent.setClass(aContent, aClass);
        aContent.startActivity(lIntent);
    }

    public static void openApp(Context aContext, String aPackageName) {
        try {
            Intent lIntent = new Intent(Intent.ACTION_MAIN, null);
            lIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            lIntent.setPackage(aPackageName);
            PackageManager lPackageManager = aContext.getPackageManager();
            List<ResolveInfo> lApps = lPackageManager.queryIntentActivities(lIntent, 0);
            ResolveInfo lResolveInfo = lApps.iterator().next();
            if (lResolveInfo != null) {
                String lPackageName = lResolveInfo.activityInfo.packageName;
                String lName = lResolveInfo.activityInfo.name;
                ComponentName lComponentName = new ComponentName(lPackageName, lName);
                lIntent.setComponent(lComponentName);
                aContext.startActivity(lIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isIntentAvailableForSys(Context aContext, String aSysAction) {
        final PackageManager packageManager = aContext.getPackageManager();
        final Intent intent = new Intent(aSysAction);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo.size() > 0) {
            return true;
        }
        return false;
    }
}

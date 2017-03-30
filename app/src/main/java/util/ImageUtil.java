package util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by sheenly on 16/3/29.
 */
public class ImageUtil {

    public static Bitmap getScaleBitmap(String aPath, int aExpectWidth) {
        if (!TextUtils.isEmpty(aPath)) {
            try {
                BitmapFactory.Options lOptions = new BitmapFactory.Options();
                lOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(aPath, lOptions);
                int lSourceWidth = lOptions.outWidth;
                int lInSampleSize = 1;
                if (aExpectWidth > 0 && lSourceWidth > aExpectWidth) {
                    lInSampleSize = Math.round((float) lSourceWidth / (float) aExpectWidth);
                }
                lOptions.inSampleSize = lInSampleSize;
                lOptions.inJustDecodeBounds = false;
                return BitmapFactory.decodeFile(aPath, lOptions);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Bitmap getBitmapForNormalDegree(String aPath, int aExpectWidth) {
        Bitmap lBitmap = null;
        if (!TextUtils.isEmpty(aPath)){
            try {
                lBitmap = getScaleBitmap(aPath, aExpectWidth);
                int lDegree = readPictureDegree(aPath);
                if (lDegree != 0) {
                    lBitmap = rotateImageView(lDegree, lBitmap, true);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return lBitmap;
    }

    public static Bitmap getBitmapForNormalDegree(String aPath) {
        return getBitmapForNormalDegree(aPath, -1);
    }

    /**
     * 读取图片属性：旋转的角度
     * @param aPath 图片绝对路径
     * @return int 旋转的角度
     */
    public static int readPictureDegree(String aPath) {
        int lDegree = 0;
        if (!TextUtils.isEmpty(aPath)){
            try {
                ExifInterface lExifInterface = new ExifInterface(aPath);
                int lOrientation = lExifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                switch (lOrientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        lDegree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        lDegree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        lDegree = 270;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lDegree;
    }

    /**
     * 旋转图片
     * @param aAngle 旋转的角度
     * @param aSourceBitmap 旋转的源图片
     * @return Bitmap 旋转后的图片
     */
    public static Bitmap rotateImageView(int aAngle , Bitmap aSourceBitmap, boolean aRecycleSource) {
        Bitmap lBitmap = aSourceBitmap;
        if (lBitmap != null) {
            try {
                Matrix lMatrix = new Matrix();
                lMatrix.postRotate(aAngle);
                Bitmap lResizedBitmap = Bitmap.createBitmap(aSourceBitmap, 0, 0, aSourceBitmap.getWidth(), aSourceBitmap.getHeight(), lMatrix, true); // 创建新的图片
                if (lResizedBitmap != null) {
                    if (aRecycleSource) {
                        lBitmap.recycle();
                    }
                    lBitmap = lResizedBitmap;
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return lBitmap;
    }

    /**
     * bitmap转为base64
     * @param aBitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap aBitmap) {
        String lResult = null;
        ByteArrayOutputStream lOutputStream = null;
        try {
            if (aBitmap != null) {
                lOutputStream = new ByteArrayOutputStream();
                aBitmap.compress(Bitmap.CompressFormat.JPEG, 30, lOutputStream);
                lOutputStream.flush();
                lOutputStream.close();
                byte[] lBitmapBytes = lOutputStream.toByteArray();
                lResult = Base64.encodeToString(lBitmapBytes, Base64.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (lOutputStream != null) {
                    lOutputStream.flush();
                    lOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lResult;
    }

    /**
     * base64转为bitmap
     * @param aBase64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String aBase64Data) {
        try {
            if (!TextUtils.isEmpty(aBase64Data)) {
                byte[] lBytes = Base64.decode(aBase64Data, Base64.DEFAULT);
                return BitmapFactory.decodeByteArray(lBytes, 0, lBytes.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap getRotate(String aPath) {
        Bitmap lBitmap = null;
        try {
            lBitmap = BitmapFactory.decodeFile(aPath);
            int lDegree = readDegree(aPath);
            if (lDegree != 0) {
                Bitmap lNewBitmap = rotate(lDegree, lBitmap);
                lBitmap.recycle();
                lBitmap = lNewBitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return lBitmap;
    }

    /**
     * 旋转图片
     *
     * @param aAngle  旋转的角度
     * @param aBitmap 旋转的源图片
     * @return Bitmap 旋转后的图片
     */
    public static Bitmap rotate(int aAngle, Bitmap aBitmap) {
        Matrix lMatrix = new Matrix();
        lMatrix.postRotate(aAngle);
        return Bitmap.createBitmap(aBitmap, 0, 0, aBitmap.getWidth(), aBitmap.getHeight(), lMatrix, true); // 创建新的图片
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param aPath 图片绝对路径
     * @return int 旋转的角度
     */
    public static int readDegree(String aPath) {
        int lDegree = 0;
        try {
            ExifInterface lExifInterface = new ExifInterface(aPath);
            int lOrientation = lExifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (lOrientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    lDegree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    lDegree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    lDegree = 270;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lDegree;
    }

    public static StateListDrawable getStateListDrawable(Drawable aDefault, Drawable aClick) {
        StateListDrawable lStateListDrawable = new StateListDrawable();
        lStateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, aDefault);
        lStateListDrawable.addState(new int[]{android.R.attr.state_pressed}, aClick);
        return lStateListDrawable;
    }

    public static StateListDrawable getStateListDrawable(Bitmap aDefault, Bitmap aClick) {
        return getStateListDrawable(new BitmapDrawable(aDefault), new BitmapDrawable(aClick));
    }

    public static void saveImage(Context aContext, String aUrl, String aDir) {
        saveImage(aContext, aUrl,aDir, false);
    }

    public static void saveImage(Context aContext, final String aUrl, String aDir, boolean aIsCDNImgRegex) {
        final String lPath = FileUtil.getSDPackagePath(aContext, aDir);
        String lCDNImgUrl = aUrl;
        if (aIsCDNImgRegex) {
            lCDNImgUrl = CDNImgRegexUtil.regexUrlForScreen(aUrl);
        }
        final String lFileName = FileUtil.getFileNameForUrl(lCDNImgUrl);
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileUtil.writeFile(NetworkUtil.getStream(aUrl), FileUtil.createNewFile(lPath, lFileName));
            }
        }).start();
    }

    public static void saveImage(final String aUrl, final String aPath, final String aFileName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileUtil.writeFile(NetworkUtil.getStream(aUrl), FileUtil.createNewFile(aPath, aFileName));
            }
        }).start();
    }

    public static Bitmap getFuzzy(Bitmap aBitmap) {
        if (aBitmap == null) return null;
        Bitmap lNarrowBitmap = scale(aBitmap, aBitmap.getWidth() / 5, aBitmap.getHeight() / 5);
        Bitmap lFuzzyBitmap = fuzzy(lNarrowBitmap, 3);
        if (lNarrowBitmap != null) {
            lNarrowBitmap.recycle();
        }
        return lFuzzyBitmap;
    }

    public static Bitmap scale(Bitmap aBitmap, int aWidth, int aHeight) {
        if (aBitmap == null) return null;
        int lBitmapWidth = aBitmap.getWidth();
        int lBitmapHeight = aBitmap.getHeight();
        float lScaleWidth = ((float) aWidth) / lBitmapWidth;
        float lScaleHeight = ((float) aHeight) / lBitmapHeight;
        Matrix lMatrix = new Matrix();
        lMatrix.postScale(lScaleWidth, lScaleHeight);// 缩放
        return Bitmap.createBitmap(aBitmap, 0, 0, lBitmapWidth, lBitmapHeight, lMatrix, true);
    }

    public static Bitmap fuzzy(Bitmap aBitmap, int aRadius) {
        if (aBitmap == null) return null;
        Bitmap lBitmap = aBitmap.copy(aBitmap.getConfig(), true);

        if (aRadius < 1) {
            return (null);
        }

        int lWidth = lBitmap.getWidth();
        int lHeight = lBitmap.getHeight();

        int[] lPixs = new int[lWidth * lHeight];
        lBitmap.getPixels(lPixs, 0, lWidth, 0, 0, lWidth, lHeight);

        int wm = lWidth - 1;
        int hm = lHeight - 1;
        int wh = lWidth * lHeight;
        int div = aRadius + aRadius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(lWidth, lHeight)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = aRadius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < lHeight; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -aRadius; i <= aRadius; i++) {
                p = lPixs[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + aRadius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = aRadius;

            for (x = 0; x < lWidth; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - aRadius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + aRadius + 1, wm);
                }
                p = lPixs[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += lWidth;
        }
        for (x = 0; x < lWidth; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -aRadius * lWidth;
            for (i = -aRadius; i <= aRadius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + aRadius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += lWidth;
                }
            }
            yi = x;
            stackpointer = aRadius;
            for (y = 0; y < lHeight; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                lPixs[yi] = (0xff000000 & lPixs[yi]) | (dv[rsum] << 16)
                        | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - aRadius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * lWidth;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += lWidth;
            }
        }
        lBitmap.setPixels(lPixs, 0, lWidth, 0, 0, lWidth, lHeight);
        return lBitmap;
    }
}

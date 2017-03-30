package util;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by sheenly on 16/3/23.
 */
public class ImageLoaderUtil {

    public static DisplayImageOptions getDisplayImageOptions(int aLoadFailRID) {
        return new DisplayImageOptions.Builder().showImageForEmptyUri(aLoadFailRID).showImageOnFail(aLoadFailRID).resetViewBeforeLoading(true)
                .cacheInMemory(true).cacheOnDisk(false).bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true).build();
    }

    public static Bitmap getCache(String aPath){
        Bitmap lBitmap = null;
        try {
            String lPath = aPath;
            if(!lPath.startsWith("file://")){
                lPath = "file://" + aPath;
            }
            String lCollectionKeys = ImageLoader.getInstance().getMemoryCache().keys().toString();
            int lStartIndex = lCollectionKeys.indexOf(lPath);
            if (lStartIndex > -1) {
                int lEndIndex = lCollectionKeys.indexOf(",", lStartIndex);
                if (lEndIndex < 0) {
                    lEndIndex = lCollectionKeys.indexOf("]", lStartIndex);
                }
                if (lEndIndex > lStartIndex) {
                    lPath = lCollectionKeys.substring(lStartIndex, lEndIndex);
                    lBitmap = ImageLoader.getInstance().getMemoryCache().get(lPath);
                }
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return lBitmap;
    }

    public static String getCachePath(String aUrl) {
        String lPath = null;
        if (!TextUtils.isEmpty(aUrl)) {
            try {
                File lFile = ImageLoader.getInstance().getDiskCache().get(aUrl);
                if (lFile != null) {
                    lPath = lFile.getPath();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lPath;
    }
    public static String getCacheSize() {
        File lImageLoaderFile = ImageLoader.getInstance().getDiskCache().getDirectory();
        double lTotalSize = 0;
        File lFiles[] = new File[]{lImageLoaderFile};
        for (int i = 0; i < lFiles.length; i++) {
            lTotalSize = lTotalSize + getFolderSize(lFiles[i]);
        }
        double total = lTotalSize / (1024 * 1024);
        DecimalFormat df = new DecimalFormat("0.00");
        String lResult = df.format(total);
        return "0.00".equals(lResult) ? "0M" : lResult + "M";
    }

    public static double getFolderSize(File aFile) {
        double lFileSize = 0;
        try {
            File[] lFileList = aFile.listFiles();
            for (int i = 0; i < lFileList.length; i++) {
                if (lFileList[i].isDirectory()) {
                    lFileSize = lFileSize + getFolderSize(lFileList[i]);
                } else {
                    lFileSize = lFileSize + lFileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lFileSize;
    }

    public static void clearCache() {
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiskCache();
    }
}

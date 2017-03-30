package util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by sheenly on 16/3/30.
 */
public class FileUtil {

    public static String getPath(Context aContext, Uri aUri) {
        String lPath = null;
        if (aUri != null) {
            try {
                String[] lFilePathColumn = {MediaStore.Images.Media.DATA};
                Cursor lCursor = aContext.getContentResolver().query(aUri, lFilePathColumn, null, null, null);
                if (lCursor != null) {
                    lCursor.moveToFirst();
                    lPath = lCursor.getString(lCursor.getColumnIndex(lFilePathColumn[0]));
                    lCursor.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lPath;
    }

    public static File makeJPGFile(Context aContext, String aFolderName) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String lFileName = System.currentTimeMillis() + ".jpg";
            InfoOfApp lInfoOfApp = new InfoOfApp(aContext);
            lInfoOfApp.init();
            lInfoOfApp.execute();
            String lPackName = lInfoOfApp.getData().get(InfoOfApp.KEYS[1]).toString();
            String lFilePath = Environment.getExternalStorageDirectory() + "/" + lPackName + "/" + aFolderName;
            FileUtil.mkDirs(lFilePath);
            return new File(lFilePath, lFileName);
        }
        return null;
    }

    public static String getSDPackagePath(Context aContext, String aFoldName) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) return null;
        InfoOfApp lInfoOfApp = new InfoOfApp(aContext);
        lInfoOfApp.init();
        lInfoOfApp.execute();
        String lPackageName = lInfoOfApp.getData().get(InfoOfApp.KEYS[1]).toString();
        return Environment.getExternalStorageDirectory() + "/" + lPackageName + "/" + aFoldName;
    }

    public static String getFileNameForUrl(String aUrl) {
        int lIndex = aUrl.lastIndexOf("/");
        return aUrl.substring(lIndex + 1, aUrl.length());
    }

    public static String getFileNameForUrl(String aUrl, String aStartSymbol, String aEndSymbol) {
        String lString = null;
        if (!TextUtils.isEmpty(aUrl)) {
            int lStartIndex = 0;
            if (!TextUtils.isEmpty(aStartSymbol)) {
                lStartIndex = aUrl.lastIndexOf(aStartSymbol) + 1;
            }
            int lEndIndex = aUrl.length();
            if (!TextUtils.isEmpty(aEndSymbol)) {
                int lEndIndexTmp = aUrl.lastIndexOf(aEndSymbol);
                if (lEndIndexTmp != -1) {
                    lEndIndex = lEndIndexTmp;
                }
            }
            if (lStartIndex >= 0 && lStartIndex < lEndIndex) {
                lString = aUrl.substring(lStartIndex, lEndIndex);
            }
        }
        return lString;
    }

    public static void mkDirs(String aPath) {
        File lFile = new File(aPath);
        if (!lFile.exists()) {
            lFile.mkdirs();
        }
    }

    public static boolean isExistsFile(String aPathFileName) {
        try {
            File lFile = new File(aPathFileName);
            return lFile.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static File createNewFile(String aPath, String aFileName) {
        mkDirs(aPath);
        File lFile = new File(aPath, aFileName);
        if (lFile.exists()) {
            lFile.delete();
        }
        return lFile;
    }

    public static void writeFile(InputStream aInputStream, File aFile) {
        try {
            FileOutputStream lFileOutputStream = new FileOutputStream(aFile);
            byte lBuffer[] = new byte[1024];
            int lNumRead;
            while ((lNumRead = aInputStream.read(lBuffer)) != -1) {
                lFileOutputStream.write(lBuffer, 0, lNumRead);
            }
            aInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            aFile.delete();
        }
    }

    public static void deleteFile(String aPath) {
        try {
            File lFile = new File(aPath);
            deleteFile(lFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(File aFile) {
        if (aFile.exists()) { // 判断文件是否存在
            if (aFile.isFile()) { // 判断是否是文件
                aFile.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (aFile.isDirectory()) { // 否则如果它是一个目录
                File files[] = aFile.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            aFile.delete();
        }
    }
}

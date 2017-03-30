package util;

import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class ImageLoaderUtill {

	public static void initDefault(Context aContext) {
		if (!ImageLoader.getInstance().isInited()) {
			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
					aContext.getApplicationContext()).build();
			ImageLoader.getInstance().init(config);
		}
	}

	public static void init(Context aContext) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				aContext.getApplicationContext()).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024)// 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		ImageLoader.getInstance().init(config);
	}

	public static void clearCache() {
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.clearDiscCache();
		imageLoader.clearDiskCache();
		imageLoader.clearMemoryCache();
	}
}

package here.wait.photo.share.utils;

import java.text.SimpleDateFormat;
import java.util.Random;


public class Utilities
{
	private static final SimpleDateFormat photoNameDataFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
	private static final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final Random random = new Random();
	public static SimpleDateFormat getDataFormat()
	{
		return dataFormat;
	}
	public static SimpleDateFormat getPhotoNameDataFormat()
	{
		return photoNameDataFormat;
	}
	public static Random getRandom()
	{
		return random;
	}
}

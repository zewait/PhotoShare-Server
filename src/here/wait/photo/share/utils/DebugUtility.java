package here.wait.photo.share.utils;

public class DebugUtility
{
	private static boolean DEBUG = true;

	public static void p(String str)
	{
		if (DEBUG)
			System.out.println(str);
	}
}

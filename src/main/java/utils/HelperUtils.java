package utils;

import java.text.SimpleDateFormat;

public class HelperUtils {
	private HelperUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static Integer parseInteger(String str ) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	public static String changeString(String s) {
		try {
			return new String(s.getBytes("ISO8859_1"), "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
}
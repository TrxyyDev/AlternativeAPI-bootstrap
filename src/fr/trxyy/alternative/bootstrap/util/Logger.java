package fr.trxyy.alternative.bootstrap.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {

	public static void log(String s) {
		System.out.println(getTime() + s);
	}

	public static void err(String s) {
		System.err.println(getTime() + s);
	}

	public static String getTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return "[" + sdf.format(cal.getTime()) + "]";
	}

}

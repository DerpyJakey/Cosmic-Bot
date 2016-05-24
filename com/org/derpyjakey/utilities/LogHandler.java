package com.org.derpyjakey.utilities;

public class LogHandler {
	static boolean debug = true;
	public static void report(int level, Object report) {
		switch(level) {
		  case 1:
		    infoReport(report);
		    break;
		  case 2:
		    debugReport(report);
		    break;
		  case 3:
		    warningReport(report);
		    break;
		  case 4:
		    errorReport(report);
		    break;
		  case 5:
		    fatalReport(report);
		    break;
		}
	}
	
	static void infoReport(Object message) {
		System.out.println(message.toString());
	}
	
	static void debugReport(Object message) {
		if (debug) {
			System.out.println(message.toString());
		}
	}
	
	static void warningReport(Object message) {
		System.out.println(message.toString());
	}
	
	static void errorReport(Object message) {
		System.out.println(message.toString());
	}
	
	static void fatalReport(Object message) {
		System.out.println(message.toString());
	}
}

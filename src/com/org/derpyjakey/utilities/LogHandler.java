package com.org.derpyjakey.utilities;

import com.org.derpyjakey.references.Directories;

public class LogHandler {
    public static void infoReport(String message) {
        System.out.println("INFO: " + message);
    }

    public static void debugReport(String message) {
        if (IOHandler.getValue(Directories.Files.configurationFile, "Debug Mode").equals("True")) {
            System.out.println("DEBUG: " + message);
        }
    }

    public static void warningReport(String message) {
        System.out.println("WARNING: " + message);
    }

    public static void errorReport(String message) {
        System.out.println("ERROR: " + message);
    }

    public static void fatalErrorReport(String message) {
        System.out.println("FATAL ERROR: " + message);
    }
}

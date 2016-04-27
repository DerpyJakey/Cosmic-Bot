package com.org.derpyjakey.utilities;

public class LogHandler {
    static boolean debug = true;
    public static void Report(int level, Object report) {
        switch (level) {
            case 1:
                InfoReport(report);
                break;
            case 2:
                DebugReport(report);
                break;
            case 3:
                WarningReport(report);
                break;
            case 4:
                ErrorReport(report);
                break;
            case 5:
                FatalReport(report);
                break;
        }
    }

    static void InfoReport(Object infoMessage) {
        System.out.println("--INFO--\n" + infoMessage.toString());
    }

    static void DebugReport(Object debugMessage) {
        if (debug) {
            System.out.println("--DEBUG--\n" + debugMessage.toString());
        }
    }

    static void WarningReport(Object warningMessage) {
        System.out.println("--WARNING--\n" + warningMessage.toString());
    }

    static void ErrorReport(Object errorMessage) {
        System.out.println("--ERROR--\n" + errorMessage.toString());
    }

    static void FatalReport(Object fatalMessage) {
        System.out.println("--FATAL--\n" + fatalMessage.toString());
    }
}

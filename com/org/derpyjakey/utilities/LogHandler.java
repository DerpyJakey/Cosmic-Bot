package com.org.derpyjakey.utilities;

public class LogHandler {

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

    private static void InfoReport(Object infoMessage) {
        System.out.println("--INFO--\n" + infoMessage.toString());
    }

    private static void DebugReport(Object debugMessage) {
        boolean debug = true;
        if (debug) {
            System.out.println("--DEBUG--\n" + debugMessage.toString());
        }
    }

    private static void WarningReport(Object warningMessage) {
        System.out.println("--WARNING--\n" + warningMessage.toString());
    }

    private static void ErrorReport(Object errorMessage) {
        System.out.println("--ERROR--\n" + errorMessage.toString());
    }

    private static void FatalReport(Object fatalMessage) {
        System.out.println("--FATAL--\n" + fatalMessage.toString());
    }
}

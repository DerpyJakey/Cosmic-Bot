package com.org.derpyjakey.utilities;

public class LogHandler {
  private static boolean debug = true;
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
    System.out.println(infoMessage.toString());
  }

  private static void DebugReport(Object debugMessage) {
    if (debug) {
      System.out.println(debugMessage.toString());
    }
  }

  private static void WarningReport(Object warningMessage) {
    System.out.println(warningMessage.toString());
  }

  private static void ErrorReport(Object errorMessage) {
    System.out.println(errorMessage.toString());
  }

  private static void FatalReport(Object fatalMessage) {
    System.out.println(fatalMessage.toString());
  }
}

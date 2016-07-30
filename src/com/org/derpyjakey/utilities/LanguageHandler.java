package com.org.derpyjakey.utilities;

import com.org.derpyjakey.references.Directories;

public class LanguageHandler {
    public static void setLanguage(String language) {
        IOHandler.setKey(Directories.Files.configurationFile, "Language", language);
    }

    public static String getLanguage() {
        return IOHandler.getValue(Directories.Files.configurationFile, "Language");
    }

    public static String[] listLanguages() {
        return IOHandler.getValue(Directories.Files.languageFile, "Available Languages").split(", ");
    }

    public static String getText(String text) {
        if (IOHandler.checkKey(Directories.Files.languageFile, getLanguage() + "." + text)) {
            return IOHandler.getValue(Directories.Files.languageFile, getLanguage() + "." + text);
        } else {
            LogHandler.errorReport("Missing " + '"' + text + '"' + " for " + getLanguage());
            return null;
        }
    }

    public static String getText(String language, String text) {
        if (IOHandler.checkKey(Directories.Files.languageFile, language + "." + text)) {
            return IOHandler.getValue(Directories.Files.languageFile, language + "." + text);
        } else {
            LogHandler.errorReport("Missing " + '"' + text + '"' + " for " + language);
            return null;
        }
    }

    public static String convertTextFromEnglish(String text) {
        if (IOHandler.checkKey(Directories.Files.languageFile, getLanguage() + "." + text)) {
            return getText(getLanguage(), text);
        } else {
            LogHandler.errorReport("Missing " + '"' + text + '"' + " for " + getLanguage());
            return null;
        }
    }
}

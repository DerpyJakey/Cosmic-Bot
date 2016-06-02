package com.org.derpyjakey.utilities;

import com.org.derpyjakey.references.Directories;

public class Language {
    public static void setLanguage(String language) {
        IOHandler.setValue(Directories.Files.ConfigurationFile, "Language", language);
    }

    public static String getLanguage() {
        return IOHandler.getValue(Directories.Files.ConfigurationFile, "Language");
    }

    public static String[] listLanguages() {
        if (IOHandler.getValue(Directories.Files.LanguageFile, "Available Languages").isEmpty()) {
            return null;
        } else {
            return IOHandler.getValue(Directories.Files.LanguageFile, "Available Languages").split(", ");
        }
    }

    public static String getText(String language, String text) {
        if (IOHandler.containsKey(Directories.Files.LanguageFile, language + "." + text)) {
            return (IOHandler.getValue(Directories.Files.LanguageFile, language + "." + text));
        } else {
            return getText(text);
        }
    }

    public static String getText(String text) {
        if (IOHandler.containsKey(Directories.Files.LanguageFile, getLanguage() + "." + text)) {
            return (IOHandler.getValue(Directories.Files.LanguageFile, getLanguage() + "." + text));
        } else {
            LogHandler.report(2, "Requesting Missing Text: " + text);
            return null;
        }
    }

    public static String convertTextToEnglish(String text) {
        if (IOHandler.containsKey(Directories.Files.LanguageFile, getLanguage() + "." + text)) {
            return getText("English", text);
        } else {
            LogHandler.report(2, "Missing " + '"' + text + '"' + " for " + '"' + getLanguage() + "'");
            return null;
        }
    }
    
    public static String convertTextFromEnglish(String text) {
        if (IOHandler.containsKey(Directories.Files.LanguageFile, Language.getLanguage() + "." + text)) {
            return getText(Language.getLanguage(), text);
        } else {
            LogHandler.report(2, "Missing " + '"' + text + '"' + " for " + '"' + Language.getLanguage() + '"');
            return null;
        }
    }
    
    public static String convertTextFromEnglish(String language, String text) {
        if (IOHandler.containsKey(Directories.Files.LanguageFile, language + "." + text)) {
            return getText(language, text);
        } else {
            LogHandler.report(2, "Missing " + '"' + text + '"' + " for " + '"' + language + '"');
            return null;
        }
    }
}
package com.org.derpyjakey.reference;

import com.org.derpyjakey.utilities.IOHandler;

public class Language {
    public static void SetLanguage(String language) {
        IOHandler.SetConfig(Directories.Files.ConfigurationFile, "Language", language);
    }

    public static String GetLanguage() {
        return IOHandler.GetValue(Directories.Files.ConfigurationFile, "Language");
    }

    public static String[] ListLanguages() {
        if (IOHandler.GetValue(Directories.Files.LanguageFile, "Available Languages").isEmpty()) {
            return null;
        } else {
            return IOHandler.GetValue(Directories.Files.LanguageFile, "Available Languages").split(", ");
        }
    }

    public static String GetText(String language, String text) {
        if (IOHandler.ContainsKey(Directories.Files.LanguageFile, language + "_" + text)) {
            return (IOHandler.GetValue(Directories.Files.LanguageFile, language + "_" + text));
        } else {
            return GetText(text);
        }
    }

    public static String GetText(String text) {
        if (IOHandler.ContainsKey(Directories.Files.LanguageFile, GetLanguage() + "_" + text)) {
            return (IOHandler.GetValue(Directories.Files.LanguageFile, GetLanguage() + "_" + text));
        } else {
            return null;
        }
    }
}

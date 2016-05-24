package com.org.derpyjakey.utilities;

import com.org.derpyjakey.utilities.Language;
import com.org.derpyjakey.utilities.IOHandler;
import com.org.derpyjakey.references.Directories;

public class Language {
	public static void setLanguage(String language) {
		IOHandler.setValue(Directories.Files.ConfigurationFile, "Language", language);
	}
	
	public static String getLanguage() {
		return IOHandler.getValue(Directories.Files.ConfigurationFile, "Language");
	}
	
	public static String[] listLanguages() {
		if (IOHandler.getValue(Directories.Files.LanguageFile, "Available Languages").equals(null)) {
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
}

package com.org.derpyjakey.utilities;

import com.org.derpyjakey.reference.Directories;

public class DefaultConfig {
	public static void createDefaultConfig() {
		IOHandler.setValue(Directories.Files.ConfigurationFile, "Language", "English");
		IOHandler.setValue(Directories.Files.ConfigurationFile, "Host", "irc.twitch.tv");
		IOHandler.setValue(Directories.Files.ConfigurationFile, "Port", "6667");
		IOHandler.setValue(Directories.Files.ConfigurationFile, "Username", "");
		IOHandler.setValue(Directories.Files.ConfigurationFile, "Password", "");
		IOHandler.setValue(Directories.Files.ConfigurationFile, "Channel", "");
	}
	
	public static void createDefaultLanguage() {
		IOHandler.setValue(Directories.Files.LanguageFile, "Available Languages", "English");
		IOHandler.setValue(Directories.Files.LanguageFile, "English.Title.Client", "Cosmic-Bot");
		IOHandler.setValue(Directories.Files.LanguageFile, "English.Title.Account", "Account");
		IOHandler.setValue(Directories.Files.LanguageFile, "English.Title.Language", "Language");
		IOHandler.setValue(Directories.Files.LanguageFile, "English.Label.Language", "Language");
		IOHandler.setValue(Directories.Files.LanguageFile, "English.Labal.Username", "Username");
		IOHandler.setValue(Directories.Files.LanguageFile, "English.Label.Password", "Password");
		IOHandler.setValue(Directories.Files.LanguageFile, "English.Button.Save", "Save");
		IOHandler.setValue(Directories.Files.LanguageFile, "English.Button.Close", "Close");
		IOHandler.setValue(Directories.Files.LanguageFile, "English.Button.OAuth", "OAuth");
		IOHandler.setValue(Directories.Files.LanguageFile, "Available Languages", "English");
	}
	
	public static void createDefaultChannelConfig(String channel) {
		IOHandler.setValue(Directories.Files.ChannelFile.replace("%CHANNEL%", channel), "Mode", "Chat");
		IOHandler.setValue(Directories.Files.ChannelFile.replace("%CHANNEL%", channel), "Command Key", "!");
	}
	
	public static void createDefaultCommandConfig(String channel) {
		IOHandler.setValue(Directories.Files.CommandFile.replace("%CHANNEL%", channel), "Test.Enable", "False");
		IOHandler.setValue(Directories.Files.CommandFile.replace("%CHANNEL%", channel), "Test.Message", "Test Message. To get content of a text file, use %FileDirectory%");
		IOHandler.setValue(Directories.Files.CommandFile.replace("%CHANNEL%", channel), "Test.FileDirectory", "C:/Test.txt");
		IOHandler.setValue(Directories.Files.CommandFile.replace("%CHANNEL%", channel), "Test.Permission", "Moderator");
	}
}

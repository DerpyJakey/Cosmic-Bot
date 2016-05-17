package com.org.derpyjakey.utilities;

import com.org.derpyjakey.reference.Directories;

public class DefaultConfig {
	public static void createDefaultConfig() {
		IOHandler.setConfig(Directories.Files.ConfigurationFile, "Language", "English");
		IOHandler.setConfig(Directories.Files.ConfigurationFile, "Host", "irc.twitch.tv");
		IOHandler.setConfig(Directories.Files.ConfigurationFile, "Port", "6667");
		IOHandler.setConfig(Directories.Files.ConfigurationFile, "Username", "");
		IOHandler.setConfig(Directories.Files.ConfigurationFile, "Password", "");
		IOHandler.setConfig(Directories.Files.ConfigurationFile, "Channel", "");
	}
	
	public static void createDefaultLanguage() {
		IOHandler.setConfig(Directories.Files.LanguageFile, "Available Languages", "English");
		IOHandler.setConfig(Directories.Files.LanguageFile, "English.Title.Client", "Cosmic-Bot");
		IOHandler.setConfig(Directories.Files.LanguageFile, "English.Title.Account", "Account");
		IOHandler.setConfig(Directories.Files.LanguageFile, "English.Title.Language", "Language");
		IOHandler.setConfig(Directories.Files.LanguageFile, "English.Label.Language", "Language");
		IOHandler.setConfig(Directories.Files.LanguageFile, "English.Labal.Username", "Username");
		IOHandler.setConfig(Directories.Files.LanguageFile, "English.Label.Password", "Password");
		IOHandler.setConfig(Directories.Files.LanguageFile, "English.Button.Save", "Save");
		IOHandler.setConfig(Directories.Files.LanguageFile, "English.Button.Close", "Close");
		IOHandler.setConfig(Directories.Files.LanguageFile, "English.Button.OAuth", "OAuth");
		IOHandler.setConfig(Directories.Files.LanguageFile, "Available Languages", "English");
	}
	
	public static void createDefaultChannelConfig(String channel) {
		IOHandler.setConfig(Directories.Files.ChannelFile.replace("%CHANNEL%", channel), "Mode", "Chat");
		IOHandler.setConfig(Directories.Files.ChannelFile.replace("%CHANNEL%", channel), "Command Key", "!");
	}
	
	public static void createDefaultCommandConfig(String channel) {
		IOHandler.setConfig(Directories.Files.CommandFile.replace("%CHANNEL%", channel), "Test.Enable", "False");
		IOHandler.setConfig(Directories.Files.CommandFile.replace("%CHANNEL%", channel), "Test.Message", "Test Message. To get content of a text file, use %FileDirectory%");
		IOHandler.setConfig(Directories.Files.CommandFile.replace("%CHANNEL%", channel), "Test.FileDirectory", "C:/Test.txt");
		IOHandler.setConfig(Directories.Files.CommandFile.replace("%CHANNEL%", channel), "Test.Permission", "Moderator");
	}
}

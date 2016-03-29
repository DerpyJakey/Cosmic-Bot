package utilities;

import reference.Directories;

public class DefaultConfig {
	public static void CreateDefaultConfig() {
		IOHandler.SetConfig(Directories.Files.ConfigurationFile, "Language", "English");
		IOHandler.SetConfig(Directories.Files.ConfigurationFile, "Host", "irc.twitch.tv");
		IOHandler.SetConfig(Directories.Files.ConfigurationFile, "Port", "6667");
		IOHandler.SetConfig(Directories.Files.ConfigurationFile, "Username", "");
		IOHandler.SetConfig(Directories.Files.ConfigurationFile, "Password", "");
		IOHandler.SetConfig(Directories.Files.ConfigurationFile, "Channel", "");
	}

	public static void CreateDefaultLanguage() {
		IOHandler.SetConfig(Directories.Files.LanguageFile, "Available Languages", "English");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Title_Client", "Cosmic-Bot");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Title_Account", "Account");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Title_Channel", "Channel");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Title_Language_Selection", "Language Selection");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Label_Username", "Username");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Label_Password", "Password");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Label_Channel", "Channel");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Label_Channel_Selection", "Select Channel");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Label_Channel_Mode", "Channel Mode");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Label_Available_Commands", "Commands");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Label_Enable", "Enable");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Label_Permission", "Permission");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Label_Command", "Commands");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Label_Message", "Message");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Label_Directory", "File Directory");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Label_Language", "Language");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Button_Save", "Save");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Button_Close", "Close");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Button_Delete", "Delete");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Button_OAuth", "OAuth"); 
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Button_Update", "Update"); 
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Button_Send", "Send"); 
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Option_Chat", "Chat"); 
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Option_Add_New_Command", "Add new command"); 
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Option_Bot", "Bot"); 
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Option_True", "True"); 
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Option_False", "False");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Option_Viewer", "Viewer"); 
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Option_Moderator", "Moderator");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Item_Account", "Account");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Item_Channel", "Channel");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Item_Language", "Language");
		IOHandler.SetConfig(Directories.Files.LanguageFile, "English_Menu_Settings", "Settings");
	}

	public static void CreateDefaultChannelConfig(String channel) {
		IOHandler.SetConfig(Directories.Files.ChannelFile.replace("%CHANNEL%", channel), "Mode", "Chat");
		IOHandler.SetConfig(Directories.Files.ChannelFile.replace("%CHANNEL%", channel), "Command Key", "!");
	}

	public static void CreateDefaultCommandConfig(String channel) {
		IOHandler.SetConfig(Directories.Files.CommandFile.replace("%CHANNEL%", channel), "Test.Enable", "False");
		IOHandler.SetConfig(Directories.Files.CommandFile.replace("%CHANNEL%", channel), "Test.Message", "Test Message. To get content of txt file. Use %FileDirectory%");
		IOHandler.SetConfig(Directories.Files.CommandFile.replace("%CHANNEL%", channel), "Test.FileDirectory", "C:/Test.txt");
		IOHandler.SetConfig(Directories.Files.CommandFile.replace("%CHANNEL%", channel), "Test.Permission", "Moderator");
	}
}
package com.org.derpyjakey.utilities;

import com.org.derpyjakey.references.Directories;

public class DefaultConfig {
    public static void createDefaultConfig() {
        IOHandler.createDirectory(Directories.Folders.configurationFolder);
        String[] keys = {"Language", "Host", "Port", "Username", "Password", "Channel"};
        String[] values = {"English", "irc.twitch.tv", "6667", "", "", ""};
        IOHandler.setKey(Directories.Files.configurationFile, keys, values);
    }

    public static void createDefaultLanguage() {
        IOHandler.createDirectory(Directories.Folders.configurationFolder);
        IOHandler.setKey(Directories.Files.languageFile, "Available Languages", "English");
        String[] englishFrames = {"English.Frame.Client", "English.Frame.Account", "English.Frame.Language", "English.Frame.Channel", "English.Frame.About"};
        String[] englishFramesText = {"Cosmic-Bot", "Account", "Language", "Channel", "About Cosmic-Bot"};
        IOHandler.setKey(Directories.Files.languageFile, englishFrames, englishFramesText);
        String[] englishMenus = {"English.Menu.Client", "English.Menu.Server", "English.Menu.Settings"};
        String[] englishMenusText = {"Client", "Server", "Settings"};
        IOHandler.setKey(Directories.Files.languageFile, englishMenus, englishMenusText);
        String[] englishMenuItems = {"English.MenuItem.Connect", "English.MenuItem.Disconnect", "English.MenuItem.About", "English.MenuItem.Exit", "English.MenuItem.Account", "English.MenuItem.Channel", "English.MenuItem.Language"};
        String[] englishMenuItemsText = {"Connect", "Disconnect", "About", "Exit", "Account", "Channel", "Language"};
        IOHandler.setKey(Directories.Files.languageFile, englishMenuItems, englishMenuItemsText);
        String[] englishLabels = {"English.Label.CommandKey", "English.Label.SelectChannel", "English.Label.Channels", "English.Label.Language", "English.Label.Username", "English.Label.Password", "English.Label.Mode", "English.Label.Enable", "English.Label.Permission", "English.Label.SelectCommand", "English.Label.Command", "English.Label.Message", "English.Label.FileDirectory", "English.Label.Developer", "English.Label.Github", "English.Label.Twitch"};
        String[] englishLabelsText = {"Command Key", "Select Channel", "Channels", "Language", "Username", "Password", "Mode", "Enable", "Permission", "Select Command", "Command", "Message", "File Directory", "Developer", "Github", "Twitch"};
        IOHandler.setKey(Directories.Files.languageFile, englishLabels, englishLabelsText);
        String[] englishButtons = {"English.Button.Update", "English.Button.Send", "English.Button.Save", "English.Button.Close", "English.Button.OAuth", "English.Button.Delete"};
        String[] englishButtonsText = {"Update", "Send", "Save", "Close", "OAuth", "Delete"};
        IOHandler.setKey(Directories.Files.languageFile, englishButtons, englishButtonsText);
        String[] englishOptions = {"English.Option.True", "English.Option.False", "English.Option.Chat", "English.Option.Bot", "English.Option.Moderator", "English.Option.Everyone", "English.Option.AddNewCommand"};
        String[] englishOptionsText = {"True", "False", "Chat", "Bot", "Moderator", "Everyone", "Add New Command"};
        IOHandler.setKey(Directories.Files.languageFile, englishOptions, englishOptionsText);
        String[] englishCheckBox = {"English.CheckBox.Enable", "English.CheckBox.Bot", "English.CheckBox.Timer"};
        String[] englishCheckBoxText = {"Enable", "Bot", "Timer"};
        IOHandler.setKey(Directories.Files.languageFile, englishCheckBox, englishCheckBoxText);
    }

    public static void createDefaultChannelConfig(String channel) {
        IOHandler.createDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channel));
        String[] keys = {"Bot", "Command Key", "Enable Blacklist Words", "Blacklisted Words", "Enable Caps Limit", "Caps Limit", "Enable Text Limit", "Text Limit", "Enable Word Limit", "Word Limit", "Enable Spam Protection", "Spam Amount", "Spam Timer", "1 Warning", "2 Warning", "3 Warning", "4 Warning", "5 Warning"};
        String[] values = {"False", "!", "False", "", "False", "", "False", "", "False", "", "False", "", "", "30s", "1m", "5m", "10m", "ban"};
        IOHandler.setKey(Directories.Files.channelFile.replace("%CHANNEL%", channel), keys, values);
    }

    public static void createDefaultChannelCommandConfig(String channel) {
        IOHandler.createDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channel));
        String[] keys = {"Test.Enable", "Test.Message", "Test.FileDirectory", "Test.Permission", "Test.EnableTimer", "Test.Timer"};
        String[] values = {"False", "Test Message. To get content of a text file. Use %FileDirectory%", "C:/Test.txt", "Moderator", "False", "0"};
        IOHandler.setKey(Directories.Files.commandFile.replace("%CHANNEL%", channel), keys, values);
    }
}

package com.org.derpyjakey.utilities;

import com.org.derpyjakey.references.Directories;

public class DefaultConfig {
    public static void createDefaultConfig() {
        IOHandler.createDirectory(Directories.Folders.configurationFolder);
        String[] keys = {"Language", "Host", "Port", "Username", "Password", "Channel"};
        String[] values = {"English", "irc.twitch.tv", "6667", "", "", ""};
        IOHandler.compareKey(Directories.Files.configurationFile, keys, values);
    }

    public static void createDefaultLanguage() {
        IOHandler.createDirectory(Directories.Folders.configurationFolder);
        String[] frames = {"Client", "About", "Account", "Channel", "Language", "Bot"};
        String[] englishFramesTranslation = {"Client", "About", "Account", "Channel", "Language", "Editing"};
        IOHandler.compareKey(Directories.Files.languageFile, "English", "Frame", frames, englishFramesTranslation);
        String[] menus = {"Client", "Server", "Settings"};
        String[] englishMenusTranslation = {"Client", "Server", "Settings"};
        IOHandler.compareKey(Directories.Files.languageFile, "English", "Menu", menus, englishMenusTranslation);
        String[] menuItems = {"Connect", "DarkUI", "LightUI", "About", "Exit", "Account", "Channel", "Language"};
        String[] englishMenuItemsTranslation = {"Connect", "DarkUI", "LightUI", "About", "Exit", "Account", "Channel", "Language"};
        IOHandler.compareKey(Directories.Files.languageFile, "English", "MenuItem", menuItems, englishMenuItemsTranslation);
        String[] labels = {"CommandKey", "BotFeatureEnable", "Blacklist", "BlacklistEnable", "BlacklistRules", "BlacklistResponse", "CapLimit", "CapLimitEnable", "CapLimitRules", "CapLimitResponse", "CharLimit", "CharLimitEnable", "CharLimitRules", "CharLimitResponse", "WordLimit", "WordLimitEnable", "WordLimitRules", "WordLimitResponse", "SpamProtection", "SpamProtectionEnable", "SpamProtectionRule", "SpamProtectionResponse", "Developer", "Github", "Twitch", "Username", "Password", "Channels", "SelectChannel", "SelectCommand", "Command", "Message", "FileDirectory", "Permission", "CommandKey", "Language"};
        String[] englishLabelsTranslation = {"Command Key", "Enable Bot", "Blacklist", "Enable", "Rules", "Response", "Cap Limit", "Enable", "Rules", "Response", "Text Limit", "Enable", "Rules", "Response", "Word Limit", "Enable", "Rules", "Response", "Spam Protection", "Enable", "Rules", "Response", "Developer", "Github", "Twitch", "Username", "Password", "Channels", "Select Channel", "Select Command", "Command", "Message", "File Directory", "Permission", "Command Key ", "Language"};
        IOHandler.compareKey(Directories.Files.languageFile, "English", "Label", labels, englishLabelsTranslation);
        String[] buttons = {"Save", "Close", "Send", "OAuth", "Update", "Delete"};
        String[] englishButtonsTranslation = {"Save", "Close", "Send", "OAuth", "Update", "Delete"};
        IOHandler.compareKey(Directories.Files.languageFile, "English", "Button", buttons, englishButtonsTranslation);
        String[] options = {"Moderator", "Everyone"};
        String[] englishOptionsTranslation = {"Moderator", "Everyone"};
        IOHandler.compareKey(Directories.Files.languageFile, "English", "Options", options, englishOptionsTranslation);
        String[] checkbox = {"Enable", "Bot", "Timer"};
        String[] englishCheckboxTranslation = {"Enable", "Bot", "Timer"};
        IOHandler.compareKey(Directories.Files.languageFile, "English", "Checkbox", checkbox, englishCheckboxTranslation);
        IOHandler.setKey(Directories.Files.languageFile, "Available Languages", "English");
    }

    public static void createDefaultChannelConfig(String[] channel) {
        for(int i = 0; i <= channel.length - 1; i++) {
            if (channel[i].contains("#")) {
                createDefaultChannelConfig(channel[i].replace("#", ""));
            } else {
                createDefaultChannelConfig(channel[i]);
            }
        }
    }

    public static void createDefaultChannelConfig(String channel) {
        IOHandler.createDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channel));
        String[] keys = {"Enable Bot", "Command Key", "Enable Blacklist", "Blacklist Rules", "Blacklist Response", "Enable Cap Limit", "Cap Limit Rules", "Cap Limit Response", "Enable Char Limit", "Char Limit Rules", "Char Limit Response", "Enable Word Limit", "Word Limit Rules", "Word Limit Response", "Enable Spam Protection", "Spam Protection Rules", "Spam Protection Response", "1 Warning", "2 Warning", "3 Warning", "4 Warning", "5 Warning"};
        String[] values = {"False", "!", "False", "Bitch, Fuck", "You've said a blacklisted word!", "False", "10", "Too many caps!", "False", "100", "Too many text in a message!", "False", "50", "Too many words in a message!", "False", "10/5", "Too many messages in timeframe", "30s", "1m", "5m", "10m", "ban"};
        IOHandler.compareKey(Directories.Files.channelFile.replace("%CHANNEL%", channel), keys, values);
    }

    public static void createDefaultChannelCommandConfig(String[] channel) {
        for(int i = 0; i <= channel.length - 1; i++) {
            if (channel[i].contains("#")) {
                createDefaultChannelCommandConfig(channel[i].replace("#", ""));
            } else {
                createDefaultChannelCommandConfig(channel[i]);
            }
        }
    }

    public static void createDefaultChannelCommandConfig(String channel) {
        IOHandler.createDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channel));
        String[] keys = {"Test.Enable", "Test.Message", "Test.FileDirectory", "Test.Permission", "Test.EnableTimer", "Test.Timer"};
        String[] values = {"False", "Test Message. To get content of a text file. Use %FileDirectory%", "C:/Test.txt", "Moderator", "False", "0"};
        IOHandler.compareKey(Directories.Files.commandFile.replace("%CHANNEL%", channel), keys, values);
    }
}

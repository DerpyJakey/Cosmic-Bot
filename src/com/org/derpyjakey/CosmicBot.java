package com.org.derpyjakey;

import com.org.derpyjakey.frames.*;
import com.org.derpyjakey.references.Directories;
import com.org.derpyjakey.utilities.DefaultConfig;
import com.org.derpyjakey.utilities.IOHandler;

public class CosmicBot {
    public static void main(String[] args) {
        boolean firstBoot = false;
        boolean triggered = false;
        if (!IOHandler.checkDirectory(Directories.Folders.rootFolder)) {
            IOHandler.createDirectory(Directories.Folders.rootFolder);
            firstBoot = true;
        }
        if (!IOHandler.checkDirectory(Directories.Folders.configurationFolder)) {
            IOHandler.createDirectory(Directories.Folders.configurationFolder);
            firstBoot = true;
        }
        if (!IOHandler.checkDirectory(Directories.Folders.channelRootFolder)) {
            IOHandler.createDirectory(Directories.Folders.channelRootFolder);
            firstBoot = true;
        }
        DefaultConfig.createDefaultConfig();
        DefaultConfig.createDefaultLanguage();
        if (firstBoot) {
            if (IOHandler.getValue(Directories.Files.languageFile, "Available Languages").contains(", ")) {
                Language language = new Language();
            }
        }
        IOHandler.createDirectory(Directories.Folders.channelRootFolder);
        if (IOHandler.getValue(Directories.Files.configurationFile, "Channel") != null) {
            String channel = IOHandler.getValue(Directories.Files.configurationFile, "Channel");
            if (channel.contains(", ")) {
                String[] channelArray = channel.replace("#", "").split(", ");
                for (int i = 0; i <= channelArray.length - 1; i++) {
                    IOHandler.createDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channelArray[i]));
                    DefaultConfig.createDefaultChannelConfig(channelArray[i]);
                    DefaultConfig.createDefaultChannelCommandConfig(channelArray[i]);
                }
            } else {
                IOHandler.createDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channel));
                DefaultConfig.createDefaultChannelConfig(channel);
                DefaultConfig.createDefaultChannelCommandConfig(channel);
            }
        }
        Client client = new Client();
    }
}

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
        for (int i = 0; i <= args.length - 1; i++) {
            if (args[i].toLowerCase().equals("debug")) {
                IOHandler.setKey(Directories.Files.configurationFile, "Debug Mode", "True");
                triggered = true;
            } else {
                if (!triggered) {
                    IOHandler.setKey(Directories.Files.configurationFile, "Debug Mode", "False");
                }
            }
        }
        Client client = new Client();
    }
}

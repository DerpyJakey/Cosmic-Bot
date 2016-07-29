package com.org.derpyjakey;

import com.org.derpyjakey.frames.Client;
import com.org.derpyjakey.frames.Language;
import com.org.derpyjakey.references.Directories;
import com.org.derpyjakey.utilities.DefaultConfig;
import com.org.derpyjakey.utilities.IOHandler;

public class CosmicBot {
    public static void main(String[] args) {
        if (!IOHandler.checkDirectory(Directories.Folders.rootFolder)) {
            IOHandler.createDirectory(Directories.Folders.rootFolder);
        }
        if (!IOHandler.checkDirectory(Directories.Folders.configurationFolder)) {
            IOHandler.createDirectory(Directories.Folders.configurationFolder);
        }
        if (!IOHandler.checkDirectory(Directories.Folders.channelRootFolder)) {
            IOHandler.createDirectory(Directories.Folders.channelRootFolder);
        }
        if (!IOHandler.checkDirectory(Directories.Files.configurationFile) || !(IOHandler.checkDirectory(Directories.Files.languageFile))) {
            if (!IOHandler.checkDirectory(Directories.Files.configurationFile)) {
                DefaultConfig.createDefaultConfig();
            }
            if (!IOHandler.checkDirectory(Directories.Files.languageFile)) {
                DefaultConfig.createDefaultLanguage();
            }
            if (IOHandler.getValue(Directories.Files.languageFile, "Available Languages").contains(", ")) {
                Language language = new Language();
            }
        }
        Client client = new Client();
    }
}

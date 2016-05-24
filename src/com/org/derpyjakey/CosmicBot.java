package com.org.derpyjakey;

import com.org.derpyjakey.frames.ClientFrame;
import com.org.derpyjakey.frames.LanguageFrame;
import com.org.derpyjakey.utilities.IOHandler;
import com.org.derpyjakey.references.Directories;
import com.org.derpyjakey.utilities.DefaultConfig;

public class CosmicBot {
    public static void main(String[] args) {
        if (!IOHandler.checkDirectory(Directories.Folders.RootFolder)) {
            IOHandler.createDirectory(Directories.Folders.RootFolder);
        }
        if (!IOHandler.checkDirectory(Directories.Folders.ConfigurationFolder)) {
            IOHandler.createDirectory(Directories.Folders.ConfigurationFolder);
        }
        if (!IOHandler.checkDirectory(Directories.Folders.ChannelRootFolder)) {
            IOHandler.createDirectory(Directories.Folders.ChannelRootFolder);
        }
        if (!IOHandler.checkDirectory(Directories.Files.ConfigurationFile) || !IOHandler.checkDirectory(Directories.Files.LanguageFile)) {
            if (!IOHandler.checkDirectory(Directories.Files.ConfigurationFile)) {
                DefaultConfig.createDefaultConfig();
            }
            if (!IOHandler.checkDirectory(Directories.Files.LanguageFile)) {
                DefaultConfig.createDefaultLanguage();
            }
            LanguageFrame languageFrame = new LanguageFrame();
        }
        ClientFrame clientFrame = new ClientFrame();
    }
}

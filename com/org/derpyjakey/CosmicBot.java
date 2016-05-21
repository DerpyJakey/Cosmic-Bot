package com.org.derpyjakey;

import com.org.derpyjakey.frames.ClientFrame;
import com.org.derpyjakey.utilities.IOHandler;
import com.org.derpyjakey.references.Directories;
import com.org.derpyjakey.utilities.DefaultConfig;

public class CosmicBot {
	public static void main(String[] args) {
		if (!IOHandler.CheckDirectory(Directories.Folders.RootFolder)) {
			IOHandler.CreateDirectory(Directories.Folders.RootFolder);
		}
		if (!IOHandler.CheckDirectory(Directories.Folders.ConfigurationFolder)) {
			IOHandler.CreateDirectory(Directories.Folders.ConfigurationFolder);
		}
		if (!IOHandler.CheckDirectory(Directories.Files.ConfigurationFile) ||
		IOHandler.CheckDirectory(Directories.Files.LanguageFile)) {
			if (!IOHandler.CheckDirectory(Directories.Files.ConfigurationFile)) {
				DefaultConfig.CreateDefaultConfig();
			}
			if (!IOHandler.CheckDirectory(Directories.Files.LanguageFile)) {
				DefaultConfig.CreateDefaultLanguage();
			}
			new LanguageFrame();
		}
		new ClientFrame();
	}
}

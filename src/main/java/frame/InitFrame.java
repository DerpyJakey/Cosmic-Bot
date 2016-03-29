package frame;

import reference.Directories;
import utilities.DefaultConfig;
import utilities.IOHandler;
import javax.swing.*;

public class InitFrame extends JFrame {
	public InitFrame() {
		if (!IOHandler.CheckDirectory(Directories.Folders.RootFolder)) {
			IOHandler.CreateDirectory(Directories.Folders.RootFolder);
		}
		if (!IOHandler.CheckDirectory(Directories.Folders.ConfigurationFolder)) {
			IOHandler.CreateDirectory(Directories.Folders.ConfigurationFolder);
		}
		if (!IOHandler.CheckDirectory(Directories.Folders.ChannelRootFolder)) {
			IOHandler.CreateDirectory(Directories.Folders.ChannelRootFolder);
		}
		if (!IOHandler.CheckDirectory(Directories.Files.ConfigurationFile) || !IOHandler.CheckDirectory(Directories.Files.LanguageFile)) {
			if (!IOHandler.CheckDirectory(Directories.Files.ConfigurationFile)) {
				DefaultConfig.CreateDefaultConfig();
			}
			if (!IOHandler.CheckDirectory(Directories.Files.LanguageFile)) {
				DefaultConfig.CreateDefaultLanguage();
			}
		LanguageSelectionFrame languageSelectionFrame = new LanguageSelectionFrame();
		}
		DefaultConfig.CreateDefaultLanguage();
		dispose();
	}
}
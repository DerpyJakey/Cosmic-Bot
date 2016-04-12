package com.org.derpyjakey.frame;

import com.org.derpyjakey.reference.Directories;
import com.org.derpyjakey.utilities.DefaultConfig;
import com.org.derpyjakey.utilities.IOHandler;
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

package com.org.derpyjakey.references;

import com.org.derpyjakey.utilities.IOHandler;

public class Directories {
	public static final class Folders {
		public final static String RootFolder = IOHandler.getRoot();
		public final static String ConfigurationFolder = RootFolder + "/Config";
		public final static String ChannelRootFolder = RootFolder + "/Channels";
		public final static String ChannelFolder = ChannelRootFolder + "/%CHANNEL%";
	}
	
	public static final class Files {
		public final static String ConfigurationFile = Folders.ConfigurationFolder + "/Settings.cfg";
		public final static String LanguageFile = Folders.ConfigurationFolder + "/Languages.cfg";
		public final static String ChannelFile = Folders.ChannelFolder + "/Settings.cfg";
		public final static String CommandFile = Folders.ChannelFolder + "/Commands.cfg";
		public final static String ViewerFiles = Folders.ChannelFolder + "/Viewers.cfg";
	}
}

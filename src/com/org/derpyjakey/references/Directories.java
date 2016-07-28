package com.org.derpyjakey.references;

import com.org.derpyjakey.utilities.IOHandler;

public class Directories {
    public static final class Folders {
        public final static String rootFolder = IOHandler.getRoot();
        public final static String configurationFolder = rootFolder + "/Config";
        public final static String channelRootFolder = rootFolder + "/Channels";
        public final static String channelFolder = channelRootFolder + "/%CHANNEL%";
    }

    public static final class Files {
        public final static String configurationFile = Folders.configurationFolder + "/Settings.cfg";
        public final static String languageFile = Folders.configurationFolder + "/Languages.cfg";
        public final static String channelFile = Folders.channelFolder + "/Settings.cfg";
        public final static String commandFile = Folders.channelFolder + "/Commands.cfg";
        public final static String viewerFile = Folders.channelFolder + "/Viewers.cfg";
    }
}

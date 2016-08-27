package com.org.derpyjakey.utilities;

import com.org.derpyjakey.references.Directories;

import java.io.*;
import java.util.*;

public class IOHandler {
    public static String getRoot() {
        String osID = System.getProperty("os.name").toLowerCase();
        if(osID.contains("lin")) {
            return System.getProperty("user.home") + "/.config/Cosmic-Bot";
        } else if(osID.contains("win")) {
            return System.getenv("AppData") + "/Cosmic-Bot";
        } else if(osID.contains("mac")) {
            return System.getProperty("user.dir") + "/Library/Application Support/Cosmic-Bot";
        } else {
            LogHandler.warningReport("This OS has not been added into the application. Please provide OS ID when reporting this error to the developer\nOS ID: " + osID);
            return System.getProperty("user.dir") + "/Cosmic-Bot";
        }
    }

    public static boolean checkDirectory(String directory) {
        File file = new File(directory);
        return file.exists();
    }

    public static void createDirectory(String[] directory) {
        for (int i = 0; i<= directory.length - 1; i++) {
            createDirectory(directory[i]);
        }
    }

    public static void createDirectory(String directory) {
        File file = new File(directory);
        file.mkdirs();
    }

    public static boolean checkKey(String directory, String key) {
        PropertySorter propertySorter = new PropertySorter();
        try {
            propertySorter.load(new FileReader(directory));
            return propertySorter.containsKey(key);
        } catch (FileNotFoundException eof) {
            return false;
        } catch (IOException ioe) {
            return false;
        }
    }

    public static boolean[] checkKey(String directory, String[] keys) {
        boolean[] keyExist = new boolean[keys.length];
        PropertySorter propertySorter = new PropertySorter();
        try {
            propertySorter.load(new FileReader(directory));
            for (int i = 0; i <= keys.length - 1; i++) {
                keyExist[i] = propertySorter.containsKey(keys[i]);
            }
            return keyExist;
        } catch (FileNotFoundException eof) {
            return null;
        } catch (IOException ioe) {
            return null;
        }
    }

    public static void setKey(String directory, String key, String value) {
        PropertySorter propertySorter = new PropertySorter();
        try {
            if(checkDirectory(directory)) {
                propertySorter.load(new FileReader(directory));
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(directory)) {
                propertySorter.setProperty(key, value);
                propertySorter.store(fileOutputStream, null);
            }
        } catch (IOException ioe) {
            LogHandler.errorReport("Could not write to file.\n" + ioe);
        }
    }

    public static void setKey(String directory, String[] keys, String[] values) {
        PropertySorter propertySorter = new PropertySorter();
        try {
            if(checkDirectory(directory)) {
                propertySorter.load(new FileReader(directory));
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(directory)) {
                for(int i = 0; i <= keys.length - 1 && i <= values.length - 1; i++) {
                    propertySorter.setProperty(keys[i], values[i]);
                }
                propertySorter.store(fileOutputStream, null);
            }
        } catch (IOException ioe) {
            LogHandler.errorReport("Could not write to file.\n" + ioe);
        }
    }

    public static String getValue(String directory, String key) {
        try {
            Properties property = new Properties();
            property.load(new FileReader(directory));
            if (property.containsKey(key)) {
                return property.getProperty(key);
            } else {
                return null;
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ioe) {
            return null;
        }
    }

    public static String[] getValue(String directory, String[] keys) {
        try {
            Properties property = new Properties();
            String[] values = new String[keys.length];
            property.load(new FileReader(directory));
            for(int i = 0; i <= keys.length - 1; i++) {
                if (property.containsKey(keys[i])) {
                    values[i] = property.getProperty(keys[i]);
                } else {
                    values[i] = null;
                }
            }
            return values;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ioe) {
            return null;
        }
    }
    
    public static void compareKey(String directory, String language, String item, String key, String value) {
        if (getValue(directory, key) == null) {
            setKey(directory, language + "." + item + "." + key, value);
        }
    }
    
    public static void compareKey(String directory, String language, String item, String[] key, String[] value) {
        for (int i = 0; i <= key.length - 1; i++) {
            if (getValue(directory, key[i]) == null) {
                setKey(directory, language + "." + item + "." + key[i], value[i]);
            }
        }
    }

    
    public static void compareKey(String directory, String key, String value) {
        if (getValue(directory, key) == null) {
            setKey(directory, key, value);
        }
    }
    
    public static void compareKey(String directory, String[] key, String[] value) {
        for (int i = 0; i <= key.length - 1; i++) {
            if (getValue(directory, key[i]) == null) {
                setKey(directory, key[i], value[i]);
            }
        }
    }

    public static void deleteKey(String directory, String key) {
        PropertySorter propertySorter = new PropertySorter();
        try {
            if(checkDirectory(directory)) {
                propertySorter.load(new FileReader(directory));
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(directory)) {
                propertySorter.remove(key);
                propertySorter.store(fileOutputStream, null);
            }
        } catch (IOException ioe) {
            LogHandler.errorReport("Could not write to file.\n" + ioe);
        }
    }

    public static void deleteKey(String directory, String[] keys) {
        PropertySorter propertySorter = new PropertySorter();
        try {
            if(checkDirectory(directory)) {
                propertySorter.load(new FileReader(directory));
            }
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            for (int i = 0; i <= keys.length - 1; i++) {
                propertySorter.remove(keys[i]);
            }
            propertySorter.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException ioe) {
            LogHandler.errorReport("Could not write to file.\n" + ioe);
        }
    }

    public static String[] listCommands(String channel) {
        try {
            int i = 0;
            List<String> stringList = new ArrayList<>();
            Properties property = new Properties();
            property.load(new FileReader(Directories.Files.commandFile.replace("%CHANNEL%", channel)));
            Enumeration enuKeys = property.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                if (key.equals(key.replace(".Enable", "") + ".Enable")) {
                    stringList.add(i++, key.replace(".Enable", ""));
                }
            }
            String[] output = stringList.toArray(new String[stringList.size()]);
            return output;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ioe) {
            return null;
        }
    }
}

class PropertySorter extends Properties {
    @Override
    public Enumeration keys() {
        Enumeration enumeration = super.keys();
        Vector<String> keyList = new Vector<String>();
        while(enumeration.hasMoreElements()) {
            keyList.add((String)enumeration.nextElement());
        }
        Collections.sort(keyList);
        return keyList.elements();
    }
}

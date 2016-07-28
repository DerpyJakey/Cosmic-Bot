package com.org.derpyjakey.utilities;

import java.io.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

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

    public static void setValue(String directory, String key, String value) {
        PropertySorter propertySorter = new PropertySorter();
        try {
            if(checkDirectory(directory)) {
                propertySorter.load(new FileReader(directory));
            }
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            propertySorter.setProperty(key, value);
            propertySorter.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException ioe) {
            LogHandler.errorReport("Could not write to file.\n" + ioe);
        }
    }

    public static void setValue(String directory, String[] keys, String[] values) {
        PropertySorter propertySorter = new PropertySorter();
        try {
            if(checkDirectory(directory)) {
                propertySorter.load(new FileReader(directory));
            }
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            for(int i = 0; i <= keys.length - 1 && i <= values.length - 1; i++) {
                propertySorter.setProperty(keys[i], values[i]);
            }
            propertySorter.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException ioe) {
            LogHandler.errorReport("Could not write to file.\n" + ioe);
        }
    }

    public static String getValue(String directory, String key) {
        try {
            PropertySorter propertySorter = new PropertySorter();
            propertySorter.load(new FileReader(directory));
            if (propertySorter.containsKey(key)) {
                return propertySorter.getProperty(key);
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
            PropertySorter propertySorter = new PropertySorter();
            String[] values = new String[keys.length];
            propertySorter.load(new FileReader(directory));
            for(int i = 0; i <= keys.length - 1; i++) {
                if (propertySorter.containsKey(keys[i])) {
                    values[i] = propertySorter.getProperty(keys[i]);
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

    public static void deleteValue(String directory, String key) {
        PropertySorter propertySorter = new PropertySorter();
        try {
            if(checkDirectory(directory)) {
                propertySorter.load(new FileReader(directory));
            }
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            propertySorter.remove(key);
            propertySorter.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException ioe) {
            LogHandler.errorReport("Could not write to file.\n" + ioe);
        }
    }

    public static void deleteValue(String directory, String[] keys) {
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
}

class PropertySorter extends Properties {
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

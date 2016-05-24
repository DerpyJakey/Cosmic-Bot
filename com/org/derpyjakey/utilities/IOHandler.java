package com.org.derpyjakey.utilities;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Enumeration;
import com.org.derpyjakey.utilities.Language;

public class IOHandler {	
	public static String getRoot() {
		String osID  = System.getProperty(("os.name").toLowerCase());
		if (osID.contains("nux")) {
			return System.getProperty("user.home") + "/.Cosmic-Bot";
		} else if (osID.contains("win")) {
			return System.getenv("AppData") + "/Cosmic-Bot";
		} else if (osID.contains("mac")) {
			return System.getProperty("user.dir") + "/Library/Application Support/Cosmic-Bot";
		} else {
			LogHandler.report(1, "This OS has not been added into script. Please provide OS ID when reporting to the developer\n");
			LogHandler.report(1, "OS ID = " + osID);
			return System.getProperty("user.dir") + "/Cosmic-Bot";
		}
	}
	
	public static boolean checkDirectory(String directory) {
		File fileDirectory = new File(directory);
		return fileDirectory.exists();
	}
	
	public static void createDirectory(String directory) {
		File fileDirectory = new File(directory);
		fileDirectory.mkdir();
	}
	
	public static void setValue(String directory, String key, String value) {
		FileInputStream fileInput = null;
		FileOutputStream fileOutput = null;
		Properties properties = new Properties();
		if (checkDirectory(directory)) {
			try {
				fileInput = new FileInputStream(directory);
				properties.load(fileInput);
				fileInput.close();
			} catch (IOException ioe) {
				LogHandler.report(2, "Could not read file.\n" + ioe);
			}
		}
		try {
			fileOutput = new FileOutputStream(directory);
			properties.setProperty(key, value);
			properties.store(fileOutput, null);
			fileOutput.close();
		} catch (IOException ioe) {
			LogHandler.report(2, "Could not write to file.\n" + ioe);
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException ioe) {
					LogHandler.report(2, "Could not close FileInputStream\n" + ioe);
				}
			}
			if (fileOutput != null) {
				try {
					fileOutput.close();
				} catch (IOException ioe) {
					LogHandler.report(2, "Could not close FileOutputStream\n" + ioe);
				}
			}
		}
	}
	
	public static void deleteKey(String directory, String key) {
		FileInputStream fileInput = null;
		FileOutputStream fileOutput = null;
		Properties properties = new Properties();
		if (checkDirectory(directory)) {
			try {
				fileInput = new FileInputStream(directory);
				properties.load(fileInput);
				fileInput.close();
			} catch (IOException ioe) {
				LogHandler.report(2, "Could not read file\n" + ioe);
			}
		}
		try {
			fileOutput = new FileOutputStream(directory);
			properties.remove(key);
			properties.store(fileOutput, null);
			fileOutput.close();
		} catch (IOException ioe) {
			LogHandler.report(2, "Could not write to file\n" + ioe);
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException ioe) {
					LogHandler.report(2, "Could not close FileOutputStream\n" + ioe);
				}
			}
		}
	}
	
	public static String getValue(String directory, String key) {
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(directory);
			Properties properties = new Properties();
			properties.load(fileInput);
			return properties.getProperty(key);
		} catch (IOException ioe) {
			LogHandler.report(2, "Could not read file\n" + directory + "\n" + ioe);
			return null;
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException ioe) {
					LogHandler.report(2, "Could not close FileInputStream\n" + ioe);
				}
			}
		}
	}
	
	public static boolean containsKey(String directory, String key) {
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(directory);
			Properties properties = new Properties();
			properties.load(fileInput);
			return properties.containsKey(key);
		} catch (IOException ioe) {
			LogHandler.report(2, "Could not read file\n" + directory + "\n" + ioe);
			return false;
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException ioe) {
					LogHandler.report(2, "Could not close FileInputStream\n" + ioe);
				}
			}
		}
	}
	
	public static String[] listCommands(String directory) {
		FileInputStream fileInput = null;
		List<String> stringList = new ArrayList<String>();
		int i = 0;
		try {
			fileInput = new FileInputStream(directory);
			Properties properties = new Properties();
			properties.load(fileInput);
			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				if (key.contains(".Enable")) {
					stringList.add(i++, key.replace(".Enable", ""));
				}
			}
			stringList.add(i++, Language.getText("Options.Add.New.Command"));
			String[] output = stringList.toArray(new String[stringList.size()]);
			return output;
		} catch (IOException ioe) {
			LogHandler.report(2, "Could not list commands\n" + ioe);
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException ioe) {
					LogHandler.report(2, "Could not close FileInputStream\n" + ioe);
				}
			}
		}
		return null;
	}
}

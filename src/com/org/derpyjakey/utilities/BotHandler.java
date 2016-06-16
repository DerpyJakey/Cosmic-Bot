package com.org.derpyjakey.utilities;

import com.org.derpyjakey.references.Directories;


//INCOMPLETE // NOT YET IMPLEMENTED
public class BotHandler {
    static String botControl(String channel, String username, String message) {
        if (enableBotControl(channel)) {
            if(!bypassBotControl(channel, username)) {
                if(enableForbiddenWords(channel)) {
                    if (findForbiddenWord(channel, message)) {
                        return hammerControl(channel, username);
                    }
                }
                if(enableTooManyCaps(channel)) {
                    if(tooManyCaps(channel, message)) {
                        return hammerControl(channel, username);
                    }
                }
                if(enableTooManyLetters(channel)) {
                    if(tooManyLetters(channel, message)) {
                        return hammerControl(channel, username);
                    }
                }
                if(enableTooManyWords(channel)) {
                    if(tooManyWords(channel, message)) {
                        return hammerControl(channel, username);
                    }
                }
                if(enableSpamProtection(channel)) {
                    if (tooManySpam(channel, message)) {
                        return hammerControl(channel, username);
                    }
                }
            }
        }
        return null;
    }
    
    static private boolean enableBotControl(String channel) {
        return IOHandler.getValue(Directories.Files.ChannelFile.replace("%CHANNEL%", channel), "Mode").equals("Bot");
    }
    
    static private boolean bypassBotControl(String channel, String username) {
        return false;
    }
    
    static private boolean enableForbiddenWords(String channel) {
        return IOHandler.getValue(Directories.Files.ChannelFile.replace("%CHANNEL%", channel), "Enable Forbidden Word Protection").equals("true");
    }
    
    static private boolean findForbiddenWord(String channel, String message) {
        return false;
    }
    
    static private boolean enableTooManyCaps(String channel) {
        return IOHandler.getValue(Directories.Files.ChannelFile.replace("%CHANNEL%", channel), "Enable Caps Limit Protection").equals("true");
    }
    
    static private boolean tooManyCaps(String channel, String message) {
        return false;
    }
    
    static private boolean enableTooManyLetters(String channel) {
        return IOHandler.getValue(Directories.Files.ChannelFile.replace("%CHANNEL%", channel), "Enable Letter Limit Protection").equals("true");
    }
    
    static private boolean tooManyLetters(String channel, String message) {
        return false;
    }
    
    static private boolean enableTooManyWords(String channel) {
        return IOHandler.getValue(Directories.Files.ChannelFile.replace("%CHANNEL%", channel), "Enable Word Limit Protection").equals("true");
    }
    
    static private boolean tooManyWords(String channel, String message) {
        return false;
    }
    
    static private boolean enableSpamProtection(String channel) {
        return IOHandler.getValue(Directories.Files.ChannelFile.replace("%CHANNEL%", channel), "Enable Spam Protection").equals("true");
    }
    
    static private boolean tooManySpam(String channel, String username) {
        return false;
    }
    
    static private String hammerControl(String channel, String username) {
        String warnings = IOHandler.getValue(Directories.Files.ViewerFiles.replace("%CHANNEL%", channel), username + ".Warnings");
        String timeout = IOHandler.getValue(Directories.Files.ChannelFile.replace("%CHANNEL%", channel), warnings + " warning");
        IOHandler.setValue(Directories.Files.ViewerFiles.replace("%CHANNEL%", channel), username + ".Warnings", String.valueOf(Integer.parseInt(warnings) + 1));
        if (timeout.equals("ban")) {
            return banUser(channel, username);
        } else {
            return timeoutUser(channel, username, timeout);
        }
    }
    
    static String timeoutUser(String channel, String username, String time) {
        if (time.contains("s")) {
            return "/timeout " + username + " " + time.replace("s", "");
        } else if (time.contains("m")) {
            return "/timeout " + username + " " + Integer.getInteger(time.replace("m", ""))*60;
        } else if (time.contains("h")) {
            return "/timeout " + username + " " + Integer.getInteger(time.replace("h", ""))*3600;
        } else if (time.contains("d")) {
            return "/timeout " + username + " " + Integer.getInteger(time.replace("d", ""))*86400;
        } else {
            return "null";
        }
    }
    
    static String banUser(String channel, String username) {
        return "/ban " + username; 
    }
}

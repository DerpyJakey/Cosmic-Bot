package com.org.derpyjakey.utilities;

import com.org.derpyjakey.references.Directories;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotHandler {
    static boolean BotControl(String channel, String username, String message) {
        if (enableBotControl(channel)) {
            if (bypassBotControl(channel, username)) {
                if (enableBlacklistWords(channel)) {
                    if (checkBlacklistWord(channel, message)) {
                        return true;
                    }
                } else if (enableCapLimit(channel)) {
                    if (checkCapLimit(channel, message)) {
                        return true;
                    }
                } else if (enableWordLimit(channel)) {
                    if (checkWordLimit(channel, message)) {
                        return true;
                    }
                } else if (enableSpamProtection(channel)) {
                    if (checkSpamProtection(channel, username)) {
                        return true;
                    }
                } else {
                    LogHandler.infoReport("None of the protection features are enabled. Please double check this or disable it.");
                }
            }
        }
        return false;
    }

    static private boolean enableBotControl(String channel) {
        return IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", channel), "Mode").equals("Bot");
    }

    static private boolean bypassBotControl(String channel, String username) {
        if (IOHandler.getValue(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".Moderator").equals("True")) {
            return true;
        } else {
            return false;
        }
    }

    static private boolean enableBlacklistWords(String channel) {
        if (IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", channel), "Enable Blacklist Words").equals("True")) {
            return true;
        } else {
            return false;
        }
    }

    static private boolean checkBlacklistWord(String channel, String message) {
        String[] blacklistWords = IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", channel), "Blacklisted Words").split(", ");
        for (int i = 0; i <= blacklistWords.length - 1; i++) {
            if (message.toLowerCase().contains(blacklistWords[i].toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    static private boolean enableCapLimit(String channel) {
        if (IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", channel), "Enable Caps Limit").equals("True")) {
            return true;
        } else {
            return false;
        }
    }

    static private boolean checkCapLimit(String channel, String message) {
        int capCount = 0;
        int capLimit = Integer.parseInt(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", channel), "Caps Limit"));
        for (int i = 0; i < message.length(); i++) {
            if (Character.isUpperCase(message.charAt(i))) {
                capCount++;
            }
        }
        if (capCount <= capLimit) {
            return true;
        }
        return false;
    }

    static private boolean enableWordLimit(String channel) {
        if (IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", channel), "Enable Word Limit").equals("True")) {
            return true;
        } else {
            return false;
        }
    }

    static private boolean checkWordLimit(String channel, String message) {
        int wordLimit = Integer.parseInt(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", channel), "Word Limit"));
        String[] words = message.split("\\w\\s+");
        int totalWords = words.length;
        if (totalWords <= wordLimit) {
            return true;
        }
        return false;
    }

    static private boolean enableSpamProtection(String channel) {
        if (IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", channel), "Enable Spam Protection").equals("True")) {
            return true;
        } else {
            return false;
        }
    }

    static public boolean checkSpamProtection(String channel, String username) {
        int spamTotal;
        int spamExpiredDate;
        int spamExpiredTime;
        int spamTimer = Integer.parseInt(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", channel), "Spam Timer"));
        int spamAmount = Integer.parseInt(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", channel), "Spam Amount"));
        Calendar spamCalendar = Calendar.getInstance();
        spamCalendar.add(Calendar.SECOND, spamTimer);
        if (IOHandler.checkKey(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamExpiredDate")) {
            spamExpiredDate = Integer.parseInt(IOHandler.getValue(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamExpiredDate").replace("/", ""));
        } else {
            IOHandler.setKey(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamExpiredDate", new SimpleDateFormat("MM/dd/yyyy").format(spamCalendar.getInstance().getTime()));
            spamExpiredDate = Integer.parseInt(new SimpleDateFormat("MMddyyyy").format(spamCalendar.getInstance().getTime()));
        }
        if (IOHandler.checkKey(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamExpiredTime")) {
            spamExpiredTime = Integer.parseInt(IOHandler.getValue(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamExpiredTime").replace(":", ""));
        } else {
            IOHandler.setKey(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamExpiredTime", new SimpleDateFormat("HH:mm:ss").format(spamCalendar.getInstance().getTime()));
            spamExpiredTime = Integer.parseInt(new SimpleDateFormat("HHmmss").format(spamCalendar.getInstance().getTime()));
        }
        if (IOHandler.checkKey(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamCount")) {
            spamTotal = Integer.parseInt(IOHandler.getValue(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamCount"));
        } else {
            IOHandler.setKey(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamCount", "0");
            spamTotal = 0;
        }
        if (spamExpiredTime - Integer.parseInt(new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime())) >= spamTimer) {
            IOHandler.setKey(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamExpiredTime", new SimpleDateFormat("HH:mm:ss").format(spamCalendar.getTime()));
            IOHandler.setKey(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamCount", "0");
        }
        if (spamExpiredDate != Integer.parseInt(new SimpleDateFormat("MMddyyyy").format(spamCalendar.getTime()))) {
            IOHandler.setKey(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamExpiredDate", new SimpleDateFormat("MM/dd/yyyy").format(spamCalendar.getTime()));
            IOHandler.setKey(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamCount", "0");
        }
        spamTotal++;
        if (spamTotal >= spamAmount) {
            IOHandler.setKey(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamCount", Integer.toString(spamTotal));
            return true;
        } else {
            IOHandler.setKey(Directories.Files.viewerFile.replace("%CHANNEL%", channel), username + ".SpamCount", Integer.toString(spamTotal));
        }
        return false;
    }
}

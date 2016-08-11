package com.org.derpyjakey.utilities;

import com.org.derpyjakey.references.Directories;

import java.io.*;
import java.net.Socket;

public class IRCHandler {
    boolean connectedServer = false;
    Socket cosmicSocket;
    BufferedWriter cosmicWriter;
    BufferedReader cosmicReader;
    String connectedChannels;

    public void connect() {
        String[] server = {"Host", "Port"};
        connectToServer(IOHandler.getValue(Directories.Files.configurationFile, server));
        String[] account = {"Username", "Password"};
        loginToServer(IOHandler.getValue(Directories.Files.configurationFile, account));
        connectToChannel(IOHandler.getValue(Directories.Files.configurationFile, "Channels").split(", "));
        connectedServer = true;
    }

    void connectToServer(String[] server) {
        try {
            cosmicSocket = new Socket(server[0], Integer.parseInt(server[1]));
            cosmicWriter = new BufferedWriter(new OutputStreamWriter(cosmicSocket.getOutputStream()));
            cosmicReader = new BufferedReader(new InputStreamReader(cosmicSocket.getInputStream()));
        } catch (IOException ioe) {
            LogHandler.errorReport("Could not connect to Server!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }

    void loginToServer(String[] account) {
        sendRawMessage("PASS " + account[1]);
        sendRawMessage("NICK " + account[0]);
    }

    void connectToChannel(String[] channels) {
        for (String channel:channels) {
            if (channel.startsWith("#")) {
                if (connectedChannels == null) {
                    connectedChannels = channel;
                } else {
                    connectedChannels = connectedChannels + ", " + channel;
                }
                sendRawMessage("JOIN " + channel.toLowerCase());
            } else {
                if (connectedChannels == null) {
                    connectedChannels = "#" + channel;
                } else {
                    connectedChannels = connectedChannels + ", #" + channel;
                }
                sendRawMessage("JOIN #" + channel.toLowerCase());
            }
        }
    }

    void disconnectFromChannel(String[] channels) {
        for (String channel:channels) {
            if (channel.startsWith("#")) {
                connectedChannels = connectedChannels.replace(", " + channel, "");
                sendRawMessage("PART " + channel.toLowerCase());
            } else {
                connectedChannels = connectedChannels.replace(", #" + channel, "");
                sendRawMessage("PART #" + channel.toLowerCase());
            }
        }
    }

    public void sendMessage(String channel, String message) {
        sendRawMessage("PRIVMSG " + channel.toLowerCase() + " :" + message);
    }

    void sendMessage(String[] channel, String[] message) {
        for (int i = 0; i <= channel.length; i++) {
            sendRawMessage("PRIVMSG " + channel[i].toLowerCase() + " :" + message[i]);
        }
    }

    public void sendRawMessage(String message) {
        try {
            cosmicWriter.write(message + "\r\n");
            cosmicWriter.flush();
        } catch (IOException ioe) {
            if (!cosmicSocket.isConnected()) {
                connect();
            }
        }
    }

    public String recieveMessage() {
        try {
            return cosmicReader.readLine().replace("\n", "").replace("\r", "");
        } catch (IOException | NullPointerException ioe) {
            return null;
        }
    }

    public String getConnectedChannels() {
        return connectedChannels;
    }

    public int getConnectedChannelAmount() {
        if (getConnectedChannels().contains(", ")) {
            return getConnectedChannels().split(", ").length;
        } else {
            return 1;
        }
    }

    public void disconnectServer() {
        connectedChannels = null;
        try {
            cosmicSocket.close();
            cosmicReader.close();
            cosmicWriter.close();
            connectedServer = false;
        } catch (IOException ioe) {
            LogHandler.warningReport(ioe.toString());
        }
    }
}

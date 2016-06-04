package com.org.derpyjakey.utilities;

import com.org.derpyjakey.references.Directories;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class IRCHandler {
    Socket cosmic_Socket;
    BufferedWriter cosmic_Writer;
    BufferedReader cosmic_Reader;
    String connected_Channels;

    public void connect() {
        connectToServer(IOHandler.getValue(Directories.Files.ConfigurationFile, "Host"), Integer.parseInt(IOHandler.getValue(Directories.Files.ConfigurationFile, "Port")));
        loginToServer(IOHandler.getValue(Directories.Files.ConfigurationFile, "Username"), IOHandler.getValue(Directories.Files.ConfigurationFile, "Password"));
        connectToChannel(IOHandler.getValue(Directories.Files.ConfigurationFile, "Channels"));
    }

    void connectToServer(String host, int port) {
        try {
            cosmic_Socket = new Socket(host, port);
            cosmic_Writer = new BufferedWriter(new OutputStreamWriter(cosmic_Socket.getOutputStream()));
            cosmic_Reader = new BufferedReader(new InputStreamReader(cosmic_Socket.getInputStream()));
        } catch (IOException ioe) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }
	
    void loginToServer(String username, String password) {
        sendRawMessage("PASS " + password);
        sendRawMessage("NICK " + username);
    }

    void connectToChannel(String channels) {
        String[] channelList = channels.toLowerCase().split(", ");
        for (String channel:channelList) {
            if (channel.startsWith("#")) {
                if (connected_Channels == null) {
                    connected_Channels = channel;
                } else {
                    connected_Channels = connected_Channels + ", " + channel;
                }
                sendRawMessage("JOIN " + channel);
            } else {
                if (connected_Channels == null) {
                    connected_Channels = "#" + channel;
                } else {
                    connected_Channels = connected_Channels + ", #" + channel;
                }
                sendRawMessage("JOIN #" + channel);
            }
        }
    }

    void disconnectFromChannel(String channels) {
        
    }

    public void sendRawMessage(String message) {
        try {
            cosmic_Writer.write(message + "\r\n");
            cosmic_Writer.flush();
        } catch (IOException ioe) {
            if (!cosmic_Socket.isConnected()) {
                connect();
            }
        }
    }

    public void sendMessage(String channel, String message) {
        sendRawMessage("PRIVMSG " + channel + " :" + message);
    }

    public String getConnectedChannels() {
        if (connected_Channels == null) {
            return null;
        } else {
            return connected_Channels;
        }
    }

    public String recieveMessage() {
        try {
            return cosmic_Reader.readLine().replace("\n", "").replace("\r", "");
        } catch (IOException ioe) {
            return null;
        }
    }

    public void disconnect() {
        connected_Channels = null;
        try {
            cosmic_Socket.close();
        } catch (IOException ioe) {
            LogHandler.report(4, ioe);
        }
    }
}
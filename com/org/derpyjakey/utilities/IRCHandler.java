package com.org.derpyjakey.utilities;


import com.org.derpyjakey.reference.Directories;
import java.io.*;
import java.net.Socket;

public class IRCHandler {
    private Socket twitchSocket = null;
    private BufferedWriter twitchWriter = null;
    private BufferedReader twitchReader = null;
    private String connectedChannels = null;

    public void Connect() {
        ConnectToServer(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Host"), Integer.parseInt(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Port")));
        LoginToServer(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Username"), IOHandler.GetValue(Directories.Files.ConfigurationFile, "Password"));
        ConnectToChannel(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Channel"));
    }

    private void ConnectToServer(String host, int port) {
        try {
            twitchSocket = new Socket(host, port);
            twitchWriter = new BufferedWriter(new OutputStreamWriter(twitchSocket.getOutputStream()));
            twitchReader = new BufferedReader(new InputStreamReader(twitchSocket.getInputStream()));
        } catch (IOException ioe) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void LoginToServer(String username, String password) {
        SendRawMessage("PASS " + password);
        SendRawMessage("NICK " + username);
    }

    private void ConnectToChannel(String channels) {
        String[] channelList = channels.toLowerCase().split(", ");
        for (String channel:channelList) {
            if (channel.startsWith("#")) {
                SendRawMessage("JOIN " + channel);
                if (connectedChannels == null) {
                    connectedChannels = channel;
                } else {
                    connectedChannels = connectedChannels + ", " + channel;
                }
            } else {
                SendRawMessage("JOIN #" + channel);
                if (connectedChannels == null) {
                    connectedChannels = "#" + channel;
                } else {
                    connectedChannels = connectedChannels + ", #" + channel;
                }
            }
        }
    }

    void DisconnectFromChannel(String channels) {
        String[] channelList = channels.toLowerCase().split(", ");
        for (String channel:channelList) {
            if (channel.startsWith("#")) {
                SendRawMessage("PART " + channel);
                connectedChannels = connectedChannels.replace(", " + channel, "");
            } else {
                SendRawMessage("PART #" + channel);
                connectedChannels = connectedChannels.replace(", #" + channel, "");
            }
        }
    }

    private void SendRawMessage(String rawMessage) {
        LogHandler.Report(2, "MESSAGE: " + rawMessage);
        try {
            twitchWriter.write(rawMessage + "\r\n");
            twitchWriter.flush();
        } catch (IOException ioe) {
            if (!twitchSocket.isConnected()) {
                LogHandler.Report(4, "Socket has lost connection!\n" + ioe);
                Connect();
                SendRawMessage(rawMessage);
            } else {
                LogHandler.Report(4, "Could not send message!\n" + ioe);
            }
        }
    }

    void SendMessage(String channel, String message) {
        SendRawMessage("PRIVMSG " + channel + " :" + message);
    }

    public String GetConnectedChannels() {
        if (connectedChannels == null) {
            return null;
        } else {
            return connectedChannels;
        }
    }

    public String ReceiveMessage() {
        if (!twitchSocket.isClosed()) {
            String message;
            try {
                if (!twitchReader.readLine().isEmpty()) {
                    message = twitchReader.readLine();
                } else {
                    message = null;
                }
            } catch (IOException ioe) {
                message = null;
            }
            return message;
        } else {
            return "Kill Cosmic Thread";
        }
    }

    public void Disconnect() {
        connectedChannels = null;
        try {
            twitchSocket.close();
        } catch (IOException ioe) {
            LogHandler.Report(4, "Could not close socket?\n" + ioe);
        }
    }
}

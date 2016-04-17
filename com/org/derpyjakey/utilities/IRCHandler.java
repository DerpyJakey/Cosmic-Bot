package com.org.derpyjakey.utilities;

import java.io.*;
import java.net.Socket;
import com.org.derpyjakey.utilities.LogHandler;
import com.org.derpyjakey.reference.Directories;

public class IRCHandler {
  Socket twitchSocket = null;
  BufferedWriter twitchWriter = null;
  BufferedReader twitchReader = null;
  String connectedChannels = null;

  public void Connect() {
    ConnectToServer(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Host"), Integer.parseInt(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Port")));
    LoginToServer(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Username"), IOHandler.GetValue(Directories.Files.ConfigurationFile, "Password"));
    ConnectToChannel(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Channel"));
  }

  void ConnectToServer(String host, int port) {
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

  void LoginToServer(String username, String password) {
    SendRawMessage("PASS " + password);
    SendRawMessage("NICK " + username);
  }

  void ConnectToChannel(String channels) {
    String[] channelList = channels.toLowerCase().split(", ");
    for (String channel:channelList) {
      if (channel.startsWith("#")) {
        if (connectedChannels == null) {
          connectedChannels = (channel);
        } else {
          connectedChannels = (connectedChannels + ", " + channel);
        }
        SendRawMessage("JOIN " + channel);
      } else {
        if (connectedChannels == null) {
          connectedChannels = ("#" + channel);
        } else {
          connectedChannels = (connectedChannels + ", #" + channel);
        }
        SendRawMessage("JOIN #" + channel);
      }
    }
    LogHandler.Report(2, "CC New Channels: " + connectedChannels);
  }

  void DisconnectFromChannel(String channels) {
    String[] channelList = channels.toLowerCase().split(", ");
    for (String channel:channelList) {
      if (channel.startsWith("#")) {
        connectedChannels = connectedChannels.replace(", " + channel, "");
        SendRawMessage("PART " + channel);
      } else {
        connectedChannels = connectedChannels.replace(", #" + channel, "");
        SendRawMessage("PART #" + channel);
      }
    }
    LogHandler.Report(2, "PC New Channels: " + connectedChannels);
  }

  public void SendRawMessage(String rawMessage) {
    LogHandler.Report(2, "Message: " + rawMessage);
    try {
      twitchWriter.write(rawMessage + "\r\n");
      twitchWriter.flush();
    } catch (IOException ioe) {
      if (!twitchSocket.isConnected()) {
        Connect();
      }
    }
  }

  public void SendMessage(String channel, String message) {
    SendRawMessage("PRIVMSG " + channel + " :" + message);
  }
  
  public String GetConnectedChannels() {
    LogHandler.Report(2, "TEST: " + connectedChannels);
    if (connectedChannels == null) {
      return "null";
    } else {
      return connectedChannels;
    }
  }
  
  public String RecieveMessage() {
    String message = null;
    try {
      message = twitchReader.readLine();
      LogHandler.Report(1, message);
      return message;
    } catch (IOException ioe) {
      return "Null";
    }
  }
  
  public void Disconnect() {
    connectedChannels = null;
    try {
      twitchSocket.close();
    } catch (IOException ioe) {
      LogHandler.Report(4, ioe);
    }
  }
}

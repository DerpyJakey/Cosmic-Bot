package com.org.derpyjakey.utilities;

import java.io.*;
import java.net.Socket;
import com.org.derpyjakey.reference.Directories;

public class IRCHandler {
  Socket twitchSocket = null;
  BufferedWriter twitchWriter = null;
  BufferedReader twitchReader = null;
  String connectedChannels = "";

  public void Connect() {
    ConnectToServer(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Host"), Integer.parseInt(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Port")));
    LoginToServer(IOHandler.GetValue(Directories.Files.ConfigurationFile, "User"), IOHandler.GetValue(Directories.Files.ConfigurationFile, "Password"));
    ConnectToChannel(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Channel"));
  }

  void ConnectToServer(String host, int port) {
    try {
      twitchSocket = new Socket(host, port);
      twitchReader = new BufferedReader(new InputStreamReader(twitchSocket.getInputStream()));
      twitchWriter = new BufferedWriter(new OutputStreamWriter(twitchSocket.getOutputStream()));
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
        connectedChannels = (connectedChannels + ", " + channel);
        SendRawMessage("JOIN " + channel);
      } else {
        connectedChannels = (connectedChannels + ", #" + channel);
        SendRawMessage("JOIN #" + channel);
      }
    }
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
  }

  public void SendRawMessage(String rawMessage) {
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
    return connectedChannels;
  }
  
  public void CloseSocket() {
    //Not Yet Implemented
  }
}

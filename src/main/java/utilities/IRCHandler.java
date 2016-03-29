package utilities;

import reference.Directories;
import java.io.*;
import java.net.Socket;

public class IRCHandler {
	Socket twitchSocket = null;
	BufferedWriter twitchWriter = null;
	BufferedReader twitchReader = null;

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
				SendRawMessage("JOIN " + channel);
			} else {
				SendRawMessage("JOIN #" + channel);
			}
		}
	}

	void DisconnectFromChannel(String channels) {
		String[] channelList = channels.toLowerCase().split(", ");
		for (String channel:channelList) {
			if (channel.startsWith("#")) {
				SendRawMessage("PART " + channel);
			} else {
				SendRawMessage("PART #" + channel);
			}
		}
	}

	void SendRawMessage(String rawMessage) {
		try {
			twitchWriter.write(rawMessage + "\r\n");
			twitchWriter.flush();
		} catch (IOException ioe) {
			if (!twitchSocket.isConnected()) {
				Connect();
			}
		}
	}

	void SendMessage(String channel, String message) {
		SendRawMessage("PRIVMSG " + channel + " :" + message);
	}
}
package com.org.derpyjakey.frames;

import com.org.derpyjakey.utilities.IRCHandler;
import com.org.derpyjakey.utilities.LanguageHandler;
import com.org.derpyjakey.utilities.LogHandler;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Client {
    private String activeLanguage = "";
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel(new BorderLayout());
    private JPanel inputPanel = new JPanel(new BorderLayout());
    private JMenuBar menuBar = new JMenuBar();
    private JMenu clientMenu = new JMenu();
    private JMenu serverMenu = new JMenu();
    private JMenu settingsMenu = new JMenu();
    private JMenuItem connectItem = new JMenuItem();
    private JMenuItem aboutItem = new JMenuItem();
    private JMenuItem exitItem = new JMenuItem();
    private JMenuItem accountItem = new JMenuItem();
    private JMenuItem channelItem = new JMenuItem();
    private JMenuItem languageItem = new JMenuItem();
    private JComboBox channelSelectionComboBox = new JComboBox();
    private JTextPane chatTextPane = new JTextPane();
    private JTextField chatInputField = new JTextField();
    private JButton sendButton = new JButton();
    private JScrollPane chatScrollPane = new JScrollPane();
    private StyledDocument styledDocument = chatTextPane.getStyledDocument();
    private boolean connectionStatus = false;
    private boolean isThreadInitialized = false;
    Thread chatThread;
    private IRCHandler ircHandler = new IRCHandler();
    private String clientMessage;

    public Client() {
        updateLanguage();
        addComponents();
        setFrameProperties();
        aboutItem.addActionListener((ActionEvent actionEvent) -> {
            About about = new About();
        });
        exitItem.addActionListener((ActionEvent actionEvent) -> {
            connectionStatus = false;
            try {
                chatThread.interrupt();
            } catch (NullPointerException ignored) {
            }
            frame.dispose();
        });
        accountItem.addActionListener((ActionEvent actionEvent) -> {
            Account account = new Account();
        });
        channelItem.addActionListener((ActionEvent actionEvent) -> {
            Channel channel = new Channel();
        });
        languageItem.addActionListener((ActionEvent actionEvent) -> {
            Language language = new Language();
        });
        connectItem.addActionListener((ActionEvent actionEvent) -> {
            if (connectItem.getText().equals(LanguageHandler.convertTextFromEnglish("MenuItem.Connect"))) {
                connectItem.setText(LanguageHandler.getText("MenuItem.Disconnect"));
                connectionStatus = true;
                ircHandler.connect();
                if (!isThreadInitialized) {
                    updateChat();
                }
                for (String channel : ircHandler.getConnectedChannels().replace("#", "").split(", ")) {
                    channelSelectionComboBox.addItem(channel);
                }
            } else {
                connectItem.setText(LanguageHandler.getText("MenuItem.Connect"));
                updateChatBox("Disconnected");
                channelSelectionComboBox.removeAllItems();
                ircHandler.disconnectServer();
            }
        });
        chatInputField.addActionListener((ActionEvent actionEvent) -> {
            ircHandler.sendMessage("#" + channelSelectionComboBox.getSelectedItem().toString(), chatInputField.getText());
            updateChatBox(chatInputField.getText(), Color.GREEN);
            chatInputField.setText("");
        });
        sendButton.addActionListener((ActionEvent actionEvent) -> {
            ircHandler.sendMessage("#" + channelSelectionComboBox.getSelectedItem().toString(), chatInputField.getText());
            updateChatBox(chatInputField.getText(), Color.GREEN);
            chatInputField.setText("");
        });
    }

    private void updateLanguage() {
        if (!activeLanguage.equals(LanguageHandler.getLanguage())) {
            frame.setTitle(LanguageHandler.getText("Frame.Client"));
            clientMenu.setText(LanguageHandler.getText("Menu.Client"));
            serverMenu.setText(LanguageHandler.getText("Menu.Server"));
            settingsMenu.setText(LanguageHandler.getText("Menu.Settings"));
            connectItem.setText(LanguageHandler.getText("MenuItem.Connect"));
            aboutItem.setText(LanguageHandler.getText("MenuItem.About"));
            exitItem.setText(LanguageHandler.getText("MenuItem.Exit"));
            accountItem.setText(LanguageHandler.getText("MenuItem.Account"));
            channelItem.setText(LanguageHandler.getText("MenuItem.Channel"));
            languageItem.setText(LanguageHandler.getText("MenuItem.Language"));
            sendButton.setText(LanguageHandler.getText("Button.Send"));
            activeLanguage = LanguageHandler.getLanguage();
        }
    }

    private void addComponents() {
        menuBar.add(clientMenu);
        menuBar.add(serverMenu);
        menuBar.add(settingsMenu);
        clientMenu.add(aboutItem);
        clientMenu.add(exitItem);
        serverMenu.add(connectItem);
        serverMenu.add(accountItem);
        serverMenu.add(channelItem);
        settingsMenu.add(languageItem);
        inputPanel.add(channelSelectionComboBox, BorderLayout.WEST);
        inputPanel.add(chatInputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        chatScrollPane.setViewportView(chatTextPane);
        panel.add(chatScrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);
        frame.setJMenuBar(menuBar);
    }

    private void setFrameProperties() {
        chatTextPane.setEditable(false);
        chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        chatScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void updateChat() {
        Thread chatThread = new Thread(() -> {
            while (connectionStatus) {
                clientMessage = ircHandler.recieveMessage();
                try {
                    if (!clientMessage.isEmpty()) {
                        try {
                            if (clientMessage.startsWith(":tmi.twitch.tv")) {
                                if (clientMessage.contains("001")) {
                                    updateChatBox("Joined Twitch.TV");
                                } else if (clientMessage.equals("PING :tmi.twitch.tv")) {
                                    ircHandler.sendRawMessage("PONG :tmi.twitch.tv");
                                }
                            } else {
                                if (clientMessage.contains(".tmi.twitch.tv JOIN #")) {
                                    updateChatBox("Joined #" + clientMessage.substring(clientMessage.lastIndexOf("#") + 1));
                                } else if (clientMessage.contains(".tmi.twitch.tv 353") || clientMessage.contains(".tmi.twitch.tv 366")) {
                                } else {
                                    try {
                                        String usernameOne = clientMessage.substring(clientMessage.indexOf(":") + 1, clientMessage.indexOf("!"));
                                        String usernameTwo = clientMessage.substring(clientMessage.indexOf("!") + 1, clientMessage.indexOf("@"));
                                        String usernameThree = clientMessage.substring(clientMessage.indexOf("@") + 1, clientMessage.indexOf(".tmi.twitch.tv"));
                                        String chatSplitChannel = clientMessage.substring(clientMessage.indexOf("PRIVMSG #") + 9, clientMessage.indexOf(" :"));
                                        String chatSplitMessage = clientMessage.substring(clientMessage.indexOf("PRIVMSG #" + chatSplitChannel + " :") + ("PRIVMSG #" + chatSplitChannel + " :").length());
                                        if (usernameOne.equals(usernameTwo) && usernameOne.equals(usernameThree)) {
                                            if (ircHandler.getConnectedChannelAmount() > 1) {
                                                updateChatBox("#" + chatSplitChannel, usernameOne, chatSplitMessage);
                                            } else {
                                                updateChatBox(usernameOne, chatSplitMessage);
                                            }
                                        }
                                    } catch (StringIndexOutOfBoundsException ignored) {
                                    }
                                }
                            }
                        } catch (StringIndexOutOfBoundsException ignored) {
                        }
                    }
                } catch (NullPointerException ignored) {
                }
            }
        });
        if (connectionStatus && !isThreadInitialized) {
            chatThread.start();
            isThreadInitialized = true;
        }
    }

    private void updateChatBox(String message) {
        try {
            Style clientChatStyle = chatTextPane.addStyle("Style", null);
            StyleConstants.setForeground(clientChatStyle, Color.BLACK);
            styledDocument.insertString(styledDocument.getStartPosition().getOffset(), message + "\n", clientChatStyle);
        } catch (BadLocationException ble) {
            LogHandler.warningReport(ble.toString());
        }
    }

    private void updateChatBox(String message, Color messageColor) {
        try {
            Style clientChatStyle = chatTextPane.addStyle("Style", null);
            StyleConstants.setForeground(clientChatStyle, messageColor);
            styledDocument.insertString(styledDocument.getStartPosition().getOffset(), message + "\n", clientChatStyle);
        } catch (BadLocationException ignored) {
        }
    }

    private void updateChatBox(String chatUsername, String chatMessage) {
        try {
            Style clientChatStyle = chatTextPane.addStyle("Style", null);
            StyleConstants.setForeground(clientChatStyle, Color.BLACK);
            styledDocument.insertString(styledDocument.getStartPosition().getOffset(), ": " + chatMessage + "\n", clientChatStyle);
            StyleConstants.setForeground(clientChatStyle, Color.RED);
            styledDocument.insertString(styledDocument.getStartPosition().getOffset(), chatUsername, clientChatStyle);
        } catch (BadLocationException ignored) {
        }
    }

    private void updateChatBox(String chatChannel, String chatUsername, String chatMessage) {
        try {
            Style clientChatStyle = chatTextPane.addStyle("Style", null);
            StyleConstants.setForeground(clientChatStyle, Color.BLACK);
            styledDocument.insertString(styledDocument.getStartPosition().getOffset(), ": " + chatMessage + "\n", clientChatStyle);
            StyleConstants.setForeground(clientChatStyle, Color.RED);
            styledDocument.insertString(styledDocument.getStartPosition().getOffset(), chatUsername, clientChatStyle);
            StyleConstants.setForeground(clientChatStyle, Color.GRAY);
            styledDocument.insertString(styledDocument.getStartPosition().getOffset(), chatChannel + " ", clientChatStyle);
        } catch (BadLocationException ignored) {
        }
    }
}

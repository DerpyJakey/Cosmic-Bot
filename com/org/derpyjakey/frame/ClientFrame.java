package com.org.derpyjakey.frame;

import com.org.derpyjakey.reference.Directories;
import com.org.derpyjakey.reference.Language;
import com.org.derpyjakey.utilities.IOHandler;
import com.org.derpyjakey.utilities.IRCHandler;
import com.org.derpyjakey.utilities.LogHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientFrame extends JFrame {
    String current_Language = "";
    boolean initialized = false;
    JMenuBar menuBar;
    JMenu clientMenu;
    JMenu settingMenu;
    JMenuItem accountItem;
    JMenuItem channelItem;
    JMenuItem languageItem;
    JMenuItem connectItem;
    JMenuItem disconnectItem;
    JMenuItem exitItem;
    JTextArea chatBox;
    JComboBox channelSelectionBox;
    JTextField messageInput;
    JButton sendBTN;
    JPanel inputPanel;
    IRCHandler ircHandler = new IRCHandler();

    public ClientFrame() {
        CreateUI();
        UpdateLanguage();
        accountItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AccountFrame accountFrame = new AccountFrame();
            }
        });
        channelItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChannelFrame channelFrame = new ChannelFrame();
            }
        });
        languageItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LanguageSelectionFrame languageSelectionFrame = new LanguageSelectionFrame();
            }
        });
        sendBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        connectItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (initialized == false && !IOHandler.GetValue(Directories.Files.ConfigurationFile, "Channel").isEmpty()) {
                    LogHandler.Report(2, "Connecting");
                    ircHandler.Connect();
                    initialized = true;
                    UpdateInterface(0);
                    UpdateChat();
                }
            }
        });
        disconnectItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (initialized == true) {
                    LogHandler.Report(2, "Disconnecting");
                    ircHandler.Disconnect();
                    UpdateInterface(1);
                    initialized = false;
                }
            }
        });
        addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                if (!current_Language.equals(Language.GetLanguage())) {
                    LogHandler.Report(2, "Language Update");
                    UpdateLanguage();
                }
            }
        });
    }

    void CreateUI() {
        setTitle(Language.GetText("Title_Client"));
        menuBar = new JMenuBar();
        settingMenu = new JMenu();
        accountItem = new JMenuItem();
        channelItem = new JMenuItem();
        languageItem = new JMenuItem();
        clientMenu = new JMenu();
        connectItem = new JMenuItem();
        disconnectItem = new JMenuItem();
        exitItem = new JMenuItem();
        menuBar.add(clientMenu);
        menuBar.add(settingMenu);
        clientMenu.add(connectItem);
        clientMenu.add(disconnectItem);
        clientMenu.add(exitItem);
        settingMenu.add(accountItem);
        settingMenu.add(channelItem);
        settingMenu.add(languageItem);
        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setLineWrap(true);
        chatBox.setAutoscrolls(true);
        JScrollPane chatScrollPane = new JScrollPane(chatBox);
        chatScrollPane.setAutoscrolls(true);
        chatScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        channelSelectionBox = new JComboBox();
        messageInput = new JTextField();
        sendBTN = new JButton();
        inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(channelSelectionBox, BorderLayout.WEST);
        inputPanel.add(messageInput, BorderLayout.CENTER);
        inputPanel.add(sendBTN, BorderLayout.EAST);
        JPanel clientPanel = new JPanel(new BorderLayout());
        clientPanel.add(chatScrollPane, BorderLayout.CENTER);
        clientPanel.add(inputPanel, BorderLayout.SOUTH);
        getContentPane().add(clientPanel);
        setJMenuBar(menuBar);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void UpdateLanguage() {
        if (!current_Language.equals(Language.GetLanguage())) {
            setTitle(Language.GetText("Title_Client"));
            settingMenu.setText(Language.GetText("Menu_Settings"));
            accountItem.setText(Language.GetText("Item_Account"));
            channelItem.setText(Language.GetText("Item_Channel"));
            clientMenu.setText(Language.GetText("Menu_Client"));
            connectItem.setText(Language.GetText("Item_Connect"));
            disconnectItem.setText(Language.GetText("Item_Disconnect"));
            exitItem.setText(Language.GetText("Item_Exit"));
            languageItem.setText(Language.GetText("Item_Language"));
            sendBTN.setText(Language.GetText("Button_Send"));
            current_Language = Language.GetLanguage();
        }
    }

    void UpdateInterface(int updateObject) {
        if (updateObject == 0) {
            try {
                String[] channelSelections = (ircHandler.GetConnectedChannels().replace("#", "").split(", "));
                channelSelectionBox.setModel(new DefaultComboBoxModel(channelSelections));
            } catch (NullPointerException npe) {
                LogHandler.Report(2, "Could not get list of connected channels");
            }
        } else if (updateObject == 1) {
            channelSelectionBox.removeAllItems();
        }
    }

    void UpdateChat() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (initialized) {
                    if (!ircHandler.ReceiveMessage().isEmpty()) {
                        if (ircHandler.ReceiveMessage().equals("Kill Cosmic Thread")) {
                            LogHandler.Report(2, "Stopped Thread");
                            break;
                        } else {
                            chatBox.append(ircHandler.ReceiveMessage() + "\r\n");
                        }
                    }
                }
            }
        }).start();
    }
}

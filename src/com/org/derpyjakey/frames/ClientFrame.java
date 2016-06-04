package com.org.derpyjakey.frames;

import com.org.derpyjakey.utilities.IRCHandler;
import com.org.derpyjakey.utilities.Language;
import com.org.derpyjakey.utilities.LogHandler;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class ClientFrame {
    String client_Message;
    String current_Language;
    boolean status_Connected;
    boolean chat_Thread_Init;
    JFrame client_Frame;
    JPanel client_Panel;
    JPanel client_Input_Panel;
    JMenuBar client_Menu_Bar;
    JMenu client_Menu;
    JMenu client_Server_Menu;
    JMenu client_Settings_Menu;
    JMenuItem client_Connect_Item;
    JMenuItem client_About_Item;
    JMenuItem client_Exit_Item;
    JMenuItem client_Account_Item;
    JMenuItem client_Channel_Item;
    JMenuItem client_Language_Item;
    JComboBox client_Channel_Selection_Box;
    JTextArea client_Chat_Text_Area;
    JTextField client_Input_Text_Field;
    JButton client_Send_Button;
    JScrollPane client_Chat_Scroll;
    IRCHandler ircHandler = new IRCHandler();

    public ClientFrame() {
    	initialize();
    	updateLanguage();
    	addComponents();
    	setFrameProperties();
    	client_About_Item.addActionListener((ActionEvent actionEvent) -> {
            AboutFrame aboutFrame = new AboutFrame();
        });
        client_Exit_Item.addActionListener((ActionEvent actionEvent) -> {
            status_Connected = false;
            client_Frame.dispose();
        });
        client_Account_Item.addActionListener((ActionEvent actionEvent) -> {
            AccountFrame accountFrame = new AccountFrame();
        });
        client_Channel_Item.addActionListener((ActionEvent actionEvent) -> {
            ChannelFrame channelFrame = new ChannelFrame();
        });
        client_Language_Item.addActionListener((ActionEvent actionEvent) -> {
            LanguageFrame languageFrame = new LanguageFrame();
        });
        client_Connect_Item.addActionListener((ActionEvent actionEvent) -> {
            if (client_Connect_Item.getText().equals(Language.convertTextFromEnglish("MenuItem.Connect"))) {
                client_Connect_Item.setText(Language.getText("MenuItem.Disconnect"));
                status_Connected = true;
                ircHandler.connect();
                updateChat();
            } else {
                client_Connect_Item.setText(Language.getText("MenuItem.Connect"));
                status_Connected = false;
                updateChat();
                ircHandler.disconnect();
            }
        });
    }

    private void initialize() {
        current_Language = "";
        client_Frame = new JFrame();
        client_Panel = new JPanel(new BorderLayout());
        client_Input_Panel = new JPanel(new BorderLayout());
        client_Menu_Bar = new JMenuBar();
        client_Menu = new JMenu();
        client_Server_Menu = new JMenu();
        client_Settings_Menu = new JMenu();
        client_Connect_Item = new JMenuItem();
        client_About_Item = new JMenuItem();
        client_Exit_Item = new JMenuItem();
        client_Account_Item = new JMenuItem();
        client_Channel_Item = new JMenuItem();
        client_Language_Item = new JMenuItem();
        client_Channel_Selection_Box = new JComboBox();
        client_Chat_Text_Area = new JTextArea();
        client_Input_Text_Field = new JTextField();
        client_Send_Button = new JButton();
        client_Chat_Scroll = new JScrollPane();
        status_Connected = false;
        chat_Thread_Init = false;
    }

    private void updateLanguage() {
        if (!current_Language.equals(Language.getLanguage())) {
            client_Frame.setTitle(Language.getText("Frame.Client"));
            client_Menu.setText(Language.getText("Menu.Client"));
            client_Server_Menu.setText(Language.getText("Menu.Server"));
            client_Settings_Menu.setText(Language.getText("Menu.Settings"));
            client_Connect_Item.setText(Language.getText("MenuItem.Connect"));
            client_About_Item.setText(Language.getText("MenuItem.About"));
            client_Exit_Item.setText(Language.getText("MenuItem.Exit"));
            client_Account_Item.setText(Language.getText("MenuItem.Account"));
            client_Channel_Item.setText(Language.getText("MenuItem.Channel"));
            client_Language_Item.setText(Language.getText("MenuItem.Language"));
            client_Send_Button.setText(Language.getText("Button.Send"));
            current_Language = Language.getLanguage();
        }
    }

    private void addComponents() {
        client_Menu_Bar.add(client_Menu);
        client_Menu_Bar.add(client_Server_Menu);
        client_Menu_Bar.add(client_Settings_Menu);
        client_Menu.add(client_About_Item);
        client_Menu.add(client_Exit_Item);
        client_Server_Menu.add(client_Connect_Item);
        client_Server_Menu.add(client_Account_Item);
        client_Server_Menu.add(client_Channel_Item);
        client_Settings_Menu.add(client_Language_Item);
        client_Input_Panel.add(client_Channel_Selection_Box, BorderLayout.WEST);
        client_Input_Panel.add(client_Input_Text_Field, BorderLayout.CENTER);
        client_Input_Panel.add(client_Send_Button, BorderLayout.EAST);
        client_Chat_Scroll.setViewportView(client_Chat_Text_Area);
        client_Panel.add(client_Chat_Scroll, BorderLayout.CENTER);
        client_Panel.add(client_Input_Panel, BorderLayout.SOUTH);
        client_Frame.getContentPane().add(client_Panel);
        client_Frame.setJMenuBar(client_Menu_Bar);
    }

    private void setFrameProperties() {
        client_Chat_Text_Area.setEditable(false);
        client_Chat_Text_Area.setWrapStyleWord(true);
        client_Chat_Text_Area.setLineWrap(true);
        client_Chat_Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        client_Chat_Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        client_Frame.setSize(500, 500);
        client_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client_Frame.setVisible(true);
    }

    void updateChat() {
        Thread chat_Thread = new Thread(() -> {
            OUTER: 
            while (status_Connected) {
                client_Message = ircHandler.recieveMessage();
                switch (client_Message) {
                    case "Disconnecting Cosmic-Bot":
                        status_Connected = false;
                        break OUTER;
                    default:
                        LogHandler.report(2, client_Message);
                        if (client_Message.startsWith(":tmi.twitch.tv")) {
                            if (client_Message.contains("001")) {
                                updateChat("Joined Twitch.TV");
                            } else if (client_Message.equals("PING :tmi.twitch.tv")) {
                                ircHandler.sendRawMessage("PONG :tmi.twitch.tv");
                            }
                        } else {
                            updateChat(client_Message);
                        }
                        break;
                }
            }
        });
        if (status_Connected && !chat_Thread_Init) {
            chat_Thread.start();
            chat_Thread_Init = true;
        }
    }
    
    private void updateChat(String chat_Message) {
        try {
            client_Chat_Text_Area.getDocument().insertString(0, chat_Message + "\n", null);
        } catch (BadLocationException ble) {
            LogHandler.report(3, ble);
        }
    }
}

package com.org.derpyjakey.frames;

import com.org.derpyjakey.utilities.IRCHandler;
import com.org.derpyjakey.utilities.Language;
import com.org.derpyjakey.utilities.LogHandler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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
    JTextPane client_Chat_Text;
    JTextField client_Input_Text_Field;
    JButton client_Send_Button;
    JScrollPane client_Chat_Scroll;
    StyledDocument client_Document;
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
                if (!chat_Thread_Init) {
                    updateChat();
                }
                for (String channel : ircHandler.getConnectedChannels().replace("#", "").split(", ")) {
                    client_Channel_Selection_Box.addItem(channel);
                }
            } else {
                client_Connect_Item.setText(Language.getText("MenuItem.Connect"));
                updateChat("Disconnecting");
                ircHandler.disconnect();
            }
        });
        client_Input_Text_Field.addActionListener((ActionEvent actionEvent) -> {
            ircHandler.sendMessage("#" + client_Channel_Selection_Box.getSelectedItem().toString(), client_Input_Text_Field.getText());
            updateChat(client_Input_Text_Field.getText(), Color.GREEN);
            client_Input_Text_Field.setText("");
        });
        client_Send_Button.addActionListener((ActionEvent actionEvent) -> {
            ircHandler.sendMessage("#" + client_Channel_Selection_Box.getSelectedItem().toString(), client_Input_Text_Field.getText());
            updateChat(client_Input_Text_Field.getText(), Color.GREEN);
            client_Input_Text_Field.setText("");
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
        client_Chat_Text = new JTextPane();
        client_Input_Text_Field = new JTextField();
        client_Send_Button = new JButton();
        client_Chat_Scroll = new JScrollPane();
        client_Document = client_Chat_Text.getStyledDocument();
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
        client_Chat_Scroll.setViewportView(client_Chat_Text);
        client_Panel.add(client_Chat_Scroll, BorderLayout.CENTER);
        client_Panel.add(client_Input_Panel, BorderLayout.SOUTH);
        client_Frame.getContentPane().add(client_Panel);
        client_Frame.setJMenuBar(client_Menu_Bar);
    }

    private void setFrameProperties() {
        client_Chat_Text.setEditable(false);
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
                try {
                    if (client_Message.startsWith(":tmi.twitch.tv")) {
                        if (client_Message.contains("001")) {
                            updateChat("Joined Twitch.TV");
                        } else if (client_Message.equals("PING :tmi.twitch.tv")) {
                            ircHandler.sendRawMessage("PONG :tmi.twitch.tv");
                        }
                    } else {
                        if (client_Message.contains(".tmi.twitch.tv JOIN #")) {
                            updateChat("Joined #" + client_Message.substring(client_Message.lastIndexOf("#") + 1));
                        } else if (client_Message.contains(".tmi.twitch.tv 353") || client_Message.contains(".tmi.twitch.tv 366")) {
                        } else {
                            try {
                                String username_One = client_Message.substring(client_Message.indexOf(":") + 1, client_Message.indexOf("!"));
                                String username_Two = client_Message.substring(client_Message.indexOf("!") + 1, client_Message.indexOf("@"));
                                String username_Three = client_Message.substring(client_Message.indexOf("@") + 1, client_Message.indexOf(".tmi.twitch.tv"));
                                String chat_Split_Channel = client_Message.substring(client_Message.indexOf("PRIVMSG #") + 9, client_Message.indexOf(" :"));
                                String chat_Split_Message = client_Message.substring(client_Message.indexOf("PRIVMSG #" + chat_Split_Channel + " :") + ("PRIVMSG #" + chat_Split_Channel + " :").length());
                                if (username_One.equals(username_Two) && username_One.equals(username_Three)) {
                                    if (ircHandler.getConnectedChannelAmount() > 1) {
                                        updateChat("#" + chat_Split_Channel, username_One, chat_Split_Message);
                                    } else {
                                    updateChat(username_One, chat_Split_Message);
                                    }
                                }
                            } catch (StringIndexOutOfBoundsException ignored) {
                            }
                        }
                    }
                } catch (NullPointerException ignored) {
                }
            }
        });
        if (status_Connected && !chat_Thread_Init) {
            chat_Thread.start();
            chat_Thread_Init = true;
        }
    }
 
    private void updateChat(String message) {
        try {
            Style client_Chat_Style = client_Chat_Text.addStyle("Style", null);
            StyleConstants.setForeground(client_Chat_Style, Color.BLACK);
            client_Document.insertString(client_Document.getStartPosition().getOffset(), message + "\n", client_Chat_Style);
        } catch (BadLocationException ble) {
            LogHandler.report(3, ble);
        }
    }
    
    private void updateChat(String message, Color message_Color) {
        try {
            Style client_Chat_Style = client_Chat_Text.addStyle("Style", null);
            StyleConstants.setForeground(client_Chat_Style, message_Color);
            client_Document.insertString(client_Document.getStartPosition().getOffset(), message + "\n", client_Chat_Style);
        } catch (BadLocationException ble) {
            LogHandler.report(3, ble);
        }
    }

    private void updateChat(String chat_Username, String chat_Message) {
        try {
            Style client_Chat_Style = client_Chat_Text.addStyle("Style", null);
            StyleConstants.setForeground(client_Chat_Style, Color.BLACK);
            client_Document.insertString(client_Document.getStartPosition().getOffset(), ": " + chat_Message + "\n", client_Chat_Style);
            StyleConstants.setForeground(client_Chat_Style, Color.RED);
            client_Document.insertString(client_Document.getStartPosition().getOffset(), chat_Username, client_Chat_Style);
        } catch (BadLocationException ble) {
            LogHandler.report(3, ble);
        }
    }

    private void updateChat(String chat_Channel, String chat_Username, String chat_Message) {
        try {
            Style client_Chat_Style = client_Chat_Text.addStyle("Style", null);
            StyleConstants.setForeground(client_Chat_Style, Color.BLACK);
            client_Document.insertString(client_Document.getStartPosition().getOffset(), ": " + chat_Message + "\n", client_Chat_Style);
            StyleConstants.setForeground(client_Chat_Style, Color.RED);
            client_Document.insertString(client_Document.getStartPosition().getOffset(), chat_Username, client_Chat_Style);
            StyleConstants.setForeground(client_Chat_Style, Color.GRAY);
            client_Document.insertString(client_Document.getStartPosition().getOffset(), chat_Channel + " ", client_Chat_Style);
        } catch (BadLocationException ble) {
            LogHandler.report(3, ble);
        }
    }
}

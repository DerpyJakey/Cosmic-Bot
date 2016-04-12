package com.org.derpyjakey.frame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.org.derpyjakey.frame.AccountFrame;
import com.org.derpyjakey.frame.ChannelFrame;
import com.org.derpyjakey.reference.Language;
import com.org.derpyjakey.utilities.IRCHandler;
import com.org.derpyjakey.frame.LanguageSelectionFrame;

public class ClientFrame extends JFrame {
  String current_Language = "";
  String current_Channel = "";
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
  IRCHandler ircHandler;

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
        if (initialized == false) {
          //IRCHandler ircHandler = new IRCHandler();
          //ircHandler.Connect();
          initialized = true;
        }
      }
    });
    disconnectItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        if (initialized == true) {
          //ircHandler.CloseSocket();
          initialized = false;
        }
      }
    });
    addWindowFocusListener(new WindowAdapter() {
      public void windowGainedFocus(WindowEvent e) {
        if (!current_Language.equals(Language.GetLanguage())) {
          System.out.println("UP");
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
    channelSelectionBox = new JComboBox();
    messageInput = new JTextField();
    sendBTN = new JButton();
    inputPanel = new JPanel(new BorderLayout());
    inputPanel.add(channelSelectionBox, BorderLayout.WEST);
    inputPanel.add(messageInput, BorderLayout.CENTER);
    inputPanel.add(sendBTN, BorderLayout.EAST);
    JPanel clientPanel = new JPanel(new BorderLayout());
    clientPanel.add(chatBox, BorderLayout.CENTER);
    clientPanel.add(inputPanel, BorderLayout.SOUTH);
    getContentPane().add(clientPanel);
    setJMenuBar(menuBar);
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
    if (initialized) {
      String[] connectedChannels = (ircHandler.GetConnectedChannels().replace("#", "").split(", "));
      channelSelectionBox.setModel(new DefaultComboBoxModel(connectedChannels));
    }
  }
}

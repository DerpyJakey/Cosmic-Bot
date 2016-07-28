package com.org.derpyjakey.frames;

import com.org.derpyjakey.utilities.IRCHandler;
import com.org.derpyjakey.utilities.Language;

import javax.swing.*;
import javax.swing.text.StyledDocument;

public class Client {
    private String activeLanguage;
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JPanel inputPanel = new JPanel();
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
    private StyledDocument styledDocument;
    private boolean connectionStatus = false;
    private boolean isThreadInitialized = false;
    private IRCHandler ircHandler = new IRCHandler();

    public Client() {
        updateLanguage();
        addComponents();
        setFrameProperties();
    }

    private void updateLanguage() {
        if (!activeLanguage.equals(Language.getLanguage())) {
            frame.setTitle(Language.getText("Frame.Client"));
            clientMenu.setText(Language.getText("Menu.Client"));
            serverMenu.setText(Language.getText("Menu.Server"));
            settingsMenu.setText(Language.getText("Menu.Settings"));
            connectItem.setText(Language.getText("MenuItem.Connect"));
            aboutItem.setText(Language.getText("MenuItem.About"));
            exitItem.setText(Language.getText("MenuItem.Exit"));
            accountItem.setText(Language.getText("MenuItem.Account"));
            channelItem.setText(Language.getText("MenuItem.Channel"));
            languageItem.setText(Language.getText("MenuItem.Language"));
            sendButton.setText(Language.getText("Button.Send"));
            activeLanguage = Language.getLanguage();
        }
    }
}

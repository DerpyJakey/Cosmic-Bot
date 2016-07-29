package com.org.derpyjakey.frames;

import com.org.derpyjakey.references.Directories;
import com.org.derpyjakey.utilities.DefaultConfig;
import com.org.derpyjakey.utilities.IOHandler;
import com.org.derpyjakey.utilities.LanguageHandler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Channel {
    private String activeLanguage = "";
    private JFrame frame = new JFrame();
    private JLabel channelLabel = new JLabel();
    private JLabel enableLabel = new JLabel();
    private JLabel permissionLabel = new JLabel();
    private JLabel fileDirectoryLabel = new JLabel();
    private JLabel channelSelectionLabel = new JLabel();
    private JLabel modeLabel = new JLabel();
    private JLabel availableCommandsLabel = new JLabel();
    private JLabel commandLabel = new JLabel();
    private JLabel messageLabel = new JLabel();
    private JTextField channelInputBox = new JTextField(20);
    private JTextField commandInputBox = new JTextField();
    private JTextField messageInputBox = new JTextField();
    private JTextField fileDirectoryInputBox = new JTextField();
    private JComboBox channelSelectionComboBox = new JComboBox();
    private JComboBox modeComboBox = new JComboBox();
    private JComboBox permissionComboBox = new JComboBox();
    private JComboBox enableComboBox = new JComboBox();
    private JComboBox availableCommandsComboBox = new JComboBox();
    private JButton updateButton = new JButton();
    private JButton saveButton = new JButton();
    private JButton deleteButton = new JButton();
    private JButton closeButton = new JButton();
    private JPanel panelContainer1 = new JPanel(new BorderLayout());
    private JPanel panelContainer2 = new JPanel(new BorderLayout());
    private JPanel panelContainer3 = new JPanel(new BorderLayout());
    private JPanel channelOptionPanel = new JPanel(new GridLayout(1, 4));

    public Channel() {
        updateLanguage();
        addComponents();
        setFrameProperties();
        updateButton.addActionListener((ActionEvent actionEvent) -> {
            if (!channelInputBox.getText().isEmpty()) {
                if (channelInputBox.getText().contains("#")) {
                    if (channelInputBox.getText().contains(", ")) {
                        String[] listChannels = channelInputBox.getText().replace("#", "").split(", ");
                        for(String channel : listChannels) {
                            if(!IOHandler.checkDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channel))) {
                                IOHandler.createDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channel));
                                DefaultConfig.createDefaultChannelConfig(channel);
                                DefaultConfig.createDefaultChannelCommandConfig(channel);
                            }
                            channelSelectionComboBox.addItem(channel);
                        }
                    } else {
                        if (!IOHandler.checkDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channelInputBox.getText().replace("#", "")))) {
                            IOHandler.createDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channelInputBox.getText().replace("#", "")));
                            DefaultConfig.createDefaultChannelConfig(channelInputBox.getText().replace("#", ""));
                            DefaultConfig.createDefaultChannelCommandConfig(channelInputBox.getText().replace("#", ""));
                        }
                        channelSelectionComboBox.addItem(channelInputBox.getText().replace("#", ""));
                    }
                    IOHandler.setKey(Directories.Files.configurationFile, "Channels", channelInputBox.getText());
                    channelSelectionLabel.setVisible(true);
                    channelSelectionComboBox.setVisible(true);
                    channelOptionPanel.setVisible(true);
                }
            } else {
                channelOptionPanel.setVisible(false);
            }
        });
    }

    private void updateLanguage() {
        if (!activeLanguage.equals(LanguageHandler.getLanguage())) {
            enableComboBox.removeAllItems();
            enableComboBox.addItem(LanguageHandler.getText("Option.True"));
            enableComboBox.addItem(LanguageHandler.getText("Option.False"));
            modeComboBox.removeAllItems();
            modeComboBox.addItem(LanguageHandler.getText("Option.Chat"));
            modeComboBox.addItem(LanguageHandler.getText("Option.Bot"));
            permissionComboBox.removeAllItems();
            permissionComboBox.addItem(LanguageHandler.getText("Option.Moderator"));
            permissionComboBox.addItem(LanguageHandler.getText("Option.Everyone"));
            frame.setTitle(LanguageHandler.getText("Frame.Channel"));
            channelLabel.setText(LanguageHandler.getText("Label.Channels"));
            channelSelectionLabel.setText(LanguageHandler.getText("Label.ChannelSelection"));
            modeLabel.setText(LanguageHandler.getText("Label.Mode"));
            enableLabel.setText(LanguageHandler.getText("Label.Enable"));
            permissionLabel.setText(LanguageHandler.getText("Label.Permission"));
            availableCommandsLabel.setText(LanguageHandler.getText("Label.AvailableCommands"));
            commandLabel.setText(LanguageHandler.getText("Label.Command"));
            messageLabel.setText(LanguageHandler.getText("Label.Message"));
            fileDirectoryLabel.setText(LanguageHandler.getText("Label.FileDirectory"));
            updateButton.setText(LanguageHandler.getText("Button.Update"));
            saveButton.setText(LanguageHandler.getText("Button.Save"));
            deleteButton.setText(LanguageHandler.getText("Button.Delete"));
            closeButton.setText(LanguageHandler.getText("Button.Close"));
            activeLanguage = LanguageHandler.getLanguage();
        }
    }

    private void addComponents() {
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(channelLabel, BorderLayout.WEST);
        inputPanel.add(channelInputBox, BorderLayout.CENTER);
        inputPanel.add(updateButton, BorderLayout.EAST);
        channelOptionPanel.add(channelSelectionLabel);
        channelOptionPanel.add(channelSelectionComboBox);
        channelOptionPanel.add(modeLabel);
        channelOptionPanel.add(modeComboBox);
        JPanel availableCommandsPanel = new JPanel(new BorderLayout());
        availableCommandsPanel.add(availableCommandsLabel, BorderLayout.WEST);
        availableCommandsPanel.add(availableCommandsLabel, BorderLayout.CENTER);
        JPanel comboPanel = new JPanel(new GridLayout(1, 4));
        comboPanel.add(enableLabel);
        comboPanel.add(enableComboBox);
        comboPanel.add(permissionLabel);
        comboPanel.add(permissionComboBox);
        JPanel commandPanel = new JPanel(new BorderLayout());
        commandPanel.add(commandLabel, BorderLayout.WEST);
        commandPanel.add(commandInputBox, BorderLayout.CENTER);
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(messageLabel, BorderLayout.WEST);
        messagePanel.add(messageInputBox, BorderLayout.CENTER);
        JPanel directoryPanel = new JPanel(new BorderLayout());
        directoryPanel.add(fileDirectoryLabel, BorderLayout.WEST);
        directoryPanel.add(fileDirectoryInputBox, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(closeButton);
        panelContainer1.add(inputPanel, BorderLayout.NORTH);
        panelContainer1.add(channelOptionPanel, BorderLayout.SOUTH);
        panelContainer2.add(availableCommandsPanel, BorderLayout.NORTH);
        panelContainer2.add(comboPanel, BorderLayout.CENTER);
        panelContainer2.add(commandPanel, BorderLayout.SOUTH);
        panelContainer3.add(messagePanel);
        panelContainer3.add(directoryPanel);
        JPanel panelContainer = new JPanel(new BorderLayout());
        panelContainer.add(panelContainer1, BorderLayout.NORTH);
        panelContainer.add(panelContainer2, BorderLayout.CENTER);
        panelContainer.add(panelContainer3, BorderLayout.SOUTH);
        frame.getContentPane().add(panelContainer);
    }

    private void setFrameProperties() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        modeLabel.setVisible(false);
        modeComboBox.setVisible(false);
        panelContainer1.setVisible(true);
        panelContainer2.setVisible(false);
        panelContainer3.setVisible(false);
        frame.setVisible(true);
    }

    private void updateChannelList() {
        channelInputBox.setText(IOHandler.getValue(Directories.Files.configurationFile, "Channels"));
        if (!channelInputBox.getText().isEmpty()) {
            channelSelectionComboBox.removeAllItems();
            if (channelInputBox.getText().contains(", ")) {
                String[] listChannels = channelInputBox.getText().replace("#", "").split(", ");
                for (String channel : listChannels) {
                    if (!IOHandler.checkDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channel))) {
                        IOHandler.createDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channel));
                        DefaultConfig.createDefaultChannelConfig(channel);
                        DefaultConfig.createDefaultChannelCommandConfig(channel);
                    }
                    channelSelectionComboBox.addItem(channel);
                }
            } else {
                if (!IOHandler.checkDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channelInputBox.getText().replace("#", "")))) {
                    IOHandler.createDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channelInputBox.getText().replace("#", "")));
                    DefaultConfig.createDefaultChannelConfig(channelInputBox.getText().replace("#", ""));
                    DefaultConfig.createDefaultChannelCommandConfig(channelInputBox.getText().replace("#", ""));
                }
                channelSelectionComboBox.addItem(channelInputBox.getText().replace("#", ""));
            }
            if (!channelSelectionComboBox.getSelectedItem().toString().isEmpty()) {
                modeComboBox.setSelectedItem(LanguageHandler.convertTextFromEnglish("Option." + IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", channelSelectionComboBox.getSelectedItem().toString()), "Mode")));
                modeLabel.setVisible(true);
                modeComboBox.setVisible(true);
            }
        }
    }

    private void repopulateCommandList() {
        availableCommandsComboBox.removeAllItems();
        for (String command : IOHandler.listCommands(channelSelectionComboBox.getSelectedItem().toString())) {
            availableCommandsComboBox.addItem(command);
        }
        availableCommandsComboBox.addItem(LanguageHandler.getText("Option.AddNewCommand"));
        availableCommandsComboBox.setSelectedIndex(0);
    }

    private void clearCommandInput() {
        enableComboBox.setSelectedIndex(0);
        permissionComboBox.setSelectedIndex(0);
        commandInputBox.setText("");
        messageInputBox.setText("");
        fileDirectoryInputBox.setText("");
        commandLabel.setVisible(true);
        commandInputBox.setVisible(true);
    }
}

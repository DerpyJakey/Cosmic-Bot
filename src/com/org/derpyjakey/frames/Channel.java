package com.org.derpyjakey.frames;

import com.org.derpyjakey.references.Directories;
import com.org.derpyjakey.utilities.DefaultConfig;
import com.org.derpyjakey.utilities.IOHandler;
import com.org.derpyjakey.utilities.LanguageHandler;

import javax.swing.*;
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
        updateChannelList();
        if(getChannelMode(channelSelectionComboBox.getSelectedItem().toString()).equals(LanguageHandler.getText("English", "Option.Bot"))) {
            modeComboBox.setSelectedItem(LanguageHandler.getText("Option.Bot"));
            modeUpdate();
        } else {
            modeComboBox.setSelectedItem(LanguageHandler.getText("Option.Chat"));
        }
        updateButton.addActionListener((ActionEvent actionEvent) -> {
            IOHandler.setKey(Directories.Files.configurationFile, "Channels", channelInputBox.getText());
            updateChannelList();
            modeComboBox.setSelectedItem(getChannelMode(channelSelectionComboBox.getSelectedItem().toString()));
        });
        channelSelectionComboBox.addActionListener((ActionEvent actionEvent) -> {
            try {
                modeComboBox.setSelectedItem(getChannelMode(channelSelectionComboBox.getSelectedItem().toString()));
            } catch (NullPointerException ignored) {
            }
        });
        modeComboBox.addActionListener((ActionEvent actionEvent) -> {
            modeUpdate();
        });
        availableCommandsComboBox.addActionListener((ActionEvent actionEvent) -> {
            try {
                if (availableCommandsComboBox.getSelectedItem().toString().equals(LanguageHandler.convertTextFromEnglish("Option.AddNewCommand"))) {
                    clearCommandInput();
                    addNewCommandSelected();
                } else {
                    updateCommandSelection(channelSelectionComboBox.getSelectedItem().toString(), availableCommandsComboBox.getSelectedItem().toString());
                    commandSelected();
                }
            } catch (NullPointerException ignored) {
            }
        });
        saveButton.addActionListener((ActionEvent actionEvent) -> {
            if (availableCommandsComboBox.getSelectedItem().toString().equals(LanguageHandler.convertTextFromEnglish("Option.AddNewCommand"))) {
                String[] keys = {commandInputBox.getText() + ".Enable", commandInputBox.getText() + ".FileDirectory", commandInputBox.getText() + ".Message", commandInputBox.getText() + ".Permission"};
                String tmp1;
                if (LanguageHandler.getText("Option.True").equals(enableComboBox.getSelectedItem().toString())) {
                    tmp1 = LanguageHandler.getText("English", "Option.True");
                } else {
                    tmp1 = LanguageHandler.getText("English", "Option.False");
                }
                String tmp2;
                if (LanguageHandler.getText("Option.Moderator").equals(permissionComboBox.getSelectedItem().toString())) {
                    tmp2 = LanguageHandler.getText("English", "Option.Moderator");
                } else {
                    tmp2 = LanguageHandler.getText("English", "Option.Everyone");
                }
                String[] values = {tmp1, fileDirectoryInputBox.getText(), messageInputBox.getText(), tmp2};
                IOHandler.setKey(Directories.Files.commandFile.replace("%CHANNEL%", channelSelectionComboBox.getSelectedItem().toString()), keys, values);
            } else {
                String[] keys = {availableCommandsComboBox.getSelectedItem().toString() + ".Enable", availableCommandsComboBox.getSelectedItem().toString() + ".FileDirectory", availableCommandsComboBox.getSelectedItem().toString() + ".Message", availableCommandsComboBox.getSelectedItem().toString() + ".Permission"};
                String tmp1;
                if (LanguageHandler.getText("Option.True").equals(enableComboBox.getSelectedItem().toString())) {
                    tmp1 = LanguageHandler.getText("English", "Option.True");
                } else {
                    tmp1 = LanguageHandler.getText("English", "Option.False");
                }
                String tmp2;
                if (LanguageHandler.getText("Option.Moderator").equals(permissionComboBox.getSelectedItem().toString())) {
                    tmp2 = LanguageHandler.getText("English", "Option.Moderator");
                } else {
                    tmp2 = LanguageHandler.getText("English", "Option.Everyone");
                }
                String[] values = {tmp1, fileDirectoryInputBox.getText(), messageInputBox.getText(), tmp2};
                IOHandler.setKey(Directories.Files.commandFile.replace("%CHANNEL%", channelSelectionComboBox.getSelectedItem().toString()), keys, values);
            }
            clearCommandInput();
            repopulateCommandList();
        });
        deleteButton.addActionListener((ActionEvent actionEvent) -> {
            removeCommand();
            clearCommandInput();
            repopulateCommandList();
        });
        closeButton.addActionListener((ActionEvent actionEvent) -> {
            frame.dispose();
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
        availableCommandsPanel.add(availableCommandsComboBox, BorderLayout.CENTER);
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
        panelContainer3.add(messagePanel, BorderLayout.NORTH);
        panelContainer3.add(directoryPanel, BorderLayout.CENTER);
        panelContainer3.add(buttonPanel, BorderLayout.SOUTH);
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
            } else {
                modeLabel.setVisible(false);
                modeComboBox.setVisible(false);
            }
        } else {
            channelSelectionComboBox.removeAllItems();
            modeLabel.setVisible(false);
            modeComboBox.setVisible(false);
        }
    }

    private String getChannelMode(String channel) {
        return LanguageHandler.convertTextFromEnglish("Option." + IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", channel), "Mode"));
    }

    private void modeUpdate() {
        if(modeComboBox.getSelectedItem().toString().equals(LanguageHandler.convertTextFromEnglish("Option.Bot"))) {
            clearCommandInput();
            repopulateCommandList();
            if (availableCommandsComboBox.getSelectedItem().toString().equals(LanguageHandler.convertTextFromEnglish("Option.AddNewCommand"))) {
                clearCommandInput();
                addNewCommandSelected();
            } else {
                updateCommandSelection(channelSelectionComboBox.getSelectedItem().toString(), availableCommandsComboBox.getSelectedItem().toString());
                commandSelected();
            }
        } else {
            clearFrame();
        }
        String tmp;
        if (LanguageHandler.getText("Option.Bot").equals(modeComboBox.getSelectedItem().toString())) {
            tmp = LanguageHandler.getText("English", "Option.Bot");
        } else {
            tmp = LanguageHandler.getText("English", "Option.Chat");
        }
        IOHandler.setKey(Directories.Files.channelFile.replace("%CHANNEL%", channelSelectionComboBox.getSelectedItem().toString()), "Mode", tmp);
    }

    private void removeCommand() {
        String[] keys = {availableCommandsComboBox.getSelectedItem().toString() + ".Enable", availableCommandsComboBox.getSelectedItem().toString() + ".FileDirectory", availableCommandsComboBox.getSelectedItem().toString() + ".Message", availableCommandsComboBox.getSelectedItem().toString() + ".Permission"};
        IOHandler.deleteKey(Directories.Files.commandFile.replace("%CHANNEL%", channelSelectionComboBox.getSelectedItem().toString()), keys);
    }

    private void repopulateCommandList() {
        availableCommandsComboBox.removeAllItems();
        try {
            for (String command : IOHandler.listCommands(channelSelectionComboBox.getSelectedItem().toString())) {
                availableCommandsComboBox.addItem(command);
            }
        } catch (NullPointerException ignored) {
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

    private void updateCommandSelection(String channel, String command) {
        enableComboBox.setSelectedItem(LanguageHandler.getText("Option." + IOHandler.getValue(Directories.Files.commandFile.replace("%CHANNEL%", channel), command + ".Enable")));
        permissionComboBox.setSelectedItem(LanguageHandler.getText("Option." + IOHandler.getValue(Directories.Files.commandFile.replace("%CHANNEL%", channel), command + ".Permission")));
        commandInputBox.setText(command);
        messageInputBox.setText(IOHandler.getValue(Directories.Files.commandFile.replace("%CHANNEL%", channel), channel + ".Message"));
        fileDirectoryInputBox.setText(IOHandler.getValue(Directories.Files.commandFile.replace("%CHANNEL%", channel), channel + ".FileDirectory"));
    }

    private void addNewCommandSelected() {
        panelContainer2.setVisible(true);
        panelContainer3.setVisible(true);
        panelContainer2.setVisible(true);
        panelContainer3.setVisible(true);
        commandLabel.setVisible(true);
        commandInputBox.setVisible(true);
        availableCommandsLabel.setVisible(true);
        availableCommandsComboBox.setVisible(true);
        messageLabel.setVisible(true);
        messageInputBox.setVisible(true);
        fileDirectoryLabel.setVisible(true);
        fileDirectoryInputBox.setVisible(true);
        permissionLabel.setVisible(true);
        permissionComboBox.setVisible(true);
        enableLabel.setVisible(true);
        enableComboBox.setVisible(true);
        saveButton.setVisible(true);
        deleteButton.setVisible(false);
        closeButton.setVisible(true);
        frame.pack();
    }

    private void commandSelected() {
        panelContainer2.setVisible(true);
        panelContainer3.setVisible(true);
        commandLabel.setVisible(false);
        commandInputBox.setVisible(false);
        availableCommandsLabel.setVisible(true);
        availableCommandsComboBox.setVisible(true);
        messageLabel.setVisible(true);
        messageInputBox.setVisible(true);
        fileDirectoryLabel.setVisible(true);
        fileDirectoryInputBox.setVisible(true);
        permissionLabel.setVisible(true);
        permissionComboBox.setVisible(true);
        enableLabel.setVisible(true);
        enableComboBox.setVisible(true);
        saveButton.setVisible(true);
        deleteButton.setVisible(true);
        closeButton.setVisible(true);
        frame.pack();
    }

    private void clearFrame() {
        commandLabel.setVisible(false);
        commandInputBox.setVisible(false);
        messageLabel.setVisible(false);
        messageInputBox.setVisible(false);
        fileDirectoryLabel.setVisible(false);
        fileDirectoryInputBox.setVisible(false);
        permissionLabel.setVisible(false);
        permissionComboBox.setVisible(false);
        enableLabel.setVisible(false);
        enableComboBox.setVisible(false);
        availableCommandsLabel.setVisible(false);
        availableCommandsComboBox.setVisible(false);
        saveButton.setVisible(false);
        deleteButton.setVisible(false);
        closeButton.setVisible(true);
        frame.pack();
    }
}

package com.org.derpyjakey.frame;

import com.org.derpyjakey.reference.Directories;
import com.org.derpyjakey.reference.Language;
import com.org.derpyjakey.utilities.DefaultConfig;
import com.org.derpyjakey.utilities.IOHandler;
import com.org.derpyjakey.utilities.LogHandler;
import javax.swing.*;
import java.awt.*;

class ChannelFrame extends JFrame {
    private String selectedChannel;
    private String selectedCommand;
    private JLabel channelLabel;
    private JTextField channelInputBox;
    private JButton updateBTN;
    private JLabel channelSelectionLabel;
    private JComboBox channelSelectionBox;
    private JLabel modeLabel;
    private JComboBox modeBox;
    private JLabel enableLabel;
    private JComboBox enableBox;
    private JLabel permissionLabel;
    private JComboBox permissionBox;
    private JLabel availableCommandsLabel;
    private JComboBox availableCommandsBox;
    private JLabel commandLabel;
    private JTextField commandInput;
    private JLabel messageLabel;
    private JTextField messageInput;
    private JLabel fileDirectoryLabel;
    private JTextField fileDirectoryInput;
    private JButton saveBTN;
    private JButton deleteBTN;
    private JButton closeBTN;
    private JPanel modePanel;
    private JPanel availableCommandsPanel;
    private JPanel comboPanel;
    private JPanel commandPanel;
    private JPanel messagePanel;
    private JPanel directoryPanel;

    ChannelFrame() {
        try {
            UpdateInterface(9);
        } catch (NullPointerException npe) {
            LogHandler.Report(2, "No Channels found\n" + npe);
        }
        CreateUI();
        UpdateLanguage();
        InitializeInterface();
        UpdateInterface(1);
        updateBTN.addActionListener(actionEvent -> {
            if (!channelInputBox.getText().isEmpty()) {
                IOHandler.SetConfig(Directories.Files.ConfigurationFile, "Channel", channelInputBox.getText());
                UpdateInterface(9);
                UpdateInterface(2);
                UpdateInterface(4);
                UpdateInterface(1);
            }
        });
        channelSelectionBox.addActionListener(actionEvent -> {
            if (!channelSelectionBox.getSelectedItem().toString().isEmpty()) {
                UpdateInterface(9);
                UpdateInterface(3);
            }
        });
        modeBox.addActionListener(actionEvent -> {
            IOHandler.SetConfig(Directories.Files.ChannelFile.replace("%CHANNEL%", selectedChannel), "Mode", modeBox.getSelectedItem().toString());
            if (modeBox.getSelectedItem().toString().equals(Language.GetText("Option_Bot"))) {
                UpdateInterface(2);
                UpdateInterface(5);
                UpdateInterface(4);
                if (modeBox.getSelectedItem().toString().equals(Language.GetText("Option_Add_New_Command"))) {
                    UpdateInterface(6);
                } else {
                    UpdateInterface(7);
                }
            } else {
                UpdateInterface(8);
            }
        });
        availableCommandsBox.addActionListener(actionEvent -> {
            UpdateInterface(4);
            if (availableCommandsBox.getSelectedItem().toString().equals(Language.GetText("Option_Add_New_Command"))) {
                UpdateInterface(6);
            } else {
                UpdateInterface(7);
            }
        });
        saveBTN.addActionListener(actionEvent -> {
            if (availableCommandsBox.getSelectedItem().toString().equals(Language.GetText("Option_Add_New_Command"))) {
                if (!commandInput.getText().isEmpty()) {
                    AddCommand();
                }
            } else {
                SaveCommand();
            }
        });
        deleteBTN.addActionListener(actionEvent -> DeleteCommand());
        closeBTN.addActionListener(actionEvent -> dispose());
        setVisible(true);
    }

    private void CreateUI() {
        channelLabel = new JLabel();
        channelInputBox = new JTextField(10);
        updateBTN = new JButton();
        channelSelectionLabel = new JLabel();
        channelSelectionBox = new JComboBox();
        modeLabel = new JLabel();
        modeBox = new JComboBox();
        availableCommandsLabel = new JLabel();
        availableCommandsBox = new JComboBox();
        enableLabel = new JLabel();
        enableBox = new JComboBox();
        permissionLabel = new JLabel();
        permissionBox = new JComboBox();
        commandLabel = new JLabel();
        commandInput = new JTextField();
        messageLabel = new JLabel();
        messageInput = new JTextField();
        fileDirectoryLabel = new JLabel();
        fileDirectoryInput = new JTextField();
        saveBTN = new JButton();
        deleteBTN = new JButton();
        closeBTN = new JButton();
        JPanel channelInputPanel = new JPanel(new BorderLayout());
        channelInputPanel.add(channelLabel, BorderLayout.WEST);
        channelInputPanel.add(channelInputBox, BorderLayout.CENTER);
        channelInputPanel.add(updateBTN, BorderLayout.EAST);
        JPanel channelSelectionPanel = new JPanel(new BorderLayout());
        channelSelectionPanel.add(channelSelectionLabel, BorderLayout.WEST);
        channelSelectionPanel.add(channelSelectionBox, BorderLayout.CENTER);
        modePanel = new JPanel(new BorderLayout());
        modePanel.add(modeLabel, BorderLayout.WEST);
        modePanel.add(modeBox, BorderLayout.CENTER);
        availableCommandsPanel = new JPanel(new BorderLayout());
        availableCommandsPanel.add(availableCommandsLabel, BorderLayout.WEST);
        availableCommandsPanel.add(availableCommandsBox, BorderLayout.CENTER);
        comboPanel = new JPanel(new GridLayout(1, 4));
        comboPanel.add(enableLabel);
        comboPanel.add(enableBox);
        comboPanel.add(permissionLabel);
        comboPanel.add(permissionBox);
        commandPanel = new JPanel(new BorderLayout());
        commandPanel.add(commandLabel, BorderLayout.WEST);
        commandPanel.add(commandInput, BorderLayout.CENTER);
        messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(messageLabel, BorderLayout.WEST);
        messagePanel.add(messageInput, BorderLayout.CENTER);
        directoryPanel = new JPanel(new BorderLayout());
        directoryPanel.add(fileDirectoryLabel, BorderLayout.WEST);
        directoryPanel.add(fileDirectoryInput, BorderLayout.CENTER);
        JPanel btnPanel = new JPanel(new GridLayout(1, 3));
        btnPanel.add(saveBTN);
        btnPanel.add(deleteBTN);
        btnPanel.add(closeBTN);
        JPanel container1 = new JPanel(new BorderLayout());
        container1.add(channelInputPanel, BorderLayout.NORTH);
        container1.add(channelSelectionPanel, BorderLayout.CENTER);
        container1.add(modePanel, BorderLayout.SOUTH);
        JPanel container2 = new JPanel(new BorderLayout());
        container2.add(availableCommandsPanel, BorderLayout.NORTH);
        container2.add(comboPanel, BorderLayout.CENTER);
        container2.add(commandPanel, BorderLayout.SOUTH);
        JPanel container3 = new JPanel(new BorderLayout());
        container3.add(messagePanel, BorderLayout.NORTH);
        container3.add(directoryPanel, BorderLayout.CENTER);
        container3.add(btnPanel, BorderLayout.SOUTH);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(container1, BorderLayout.NORTH);
        mainPanel.add(container2, BorderLayout.CENTER);
        mainPanel.add(container3, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    private void UpdateLanguage() {
        setTitle(Language.GetText("Title_Channel"));
        channelLabel.setText(Language.GetText("Label_Channel"));
        updateBTN.setText(Language.GetText("Button_Update"));
        channelSelectionLabel.setText(Language.GetText("Label_Channel_Selection"));
        modeLabel.setText(Language.GetText("Label_Channel_Mode"));
        availableCommandsLabel.setText(Language.GetText("Label_Available_Commands"));
        enableLabel.setText(Language.GetText("Label_Enable"));
        permissionLabel.setText(Language.GetText("Label_Permission"));
        commandLabel.setText(Language.GetText("Label_Command"));
        messageLabel.setText(Language.GetText("Label_Message"));
        fileDirectoryLabel.setText(Language.GetText("Label_Directory"));
        saveBTN.setText(Language.GetText("Button_Save"));
        closeBTN.setText(Language.GetText("Button_Close"));
        deleteBTN.setText(Language.GetText("Button_Delete"));
        String[] availableModes = (Language.GetText("Option_Chat") + ", " + Language.GetText("Option_Bot")).split(", ");
        String[] enableOptions = (Language.GetText("Option_True") + ", " + Language.GetText("Option_False")).split(", ");
        String[] permissionOptions = (Language.GetText("Option_Viewer") + ", " + Language.GetText("Option_Moderator")).split(", ");
        modeBox.setModel(new DefaultComboBoxModel(availableModes));
        enableBox.setModel(new DefaultComboBoxModel(enableOptions));
        permissionBox.setModel(new DefaultComboBoxModel(permissionOptions));
        channelInputBox.setText(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Channel"));
    }

    private void InitializeInterface() {
        modePanel.setVisible(false);
        availableCommandsPanel.setVisible(false);
        comboPanel.setVisible(false);
        commandPanel.setVisible(false);
        messagePanel.setVisible(false);
        directoryPanel.setVisible(false);
        saveBTN.setVisible(false);
        deleteBTN.setVisible(false);
    }

    private void UpdateInterface(int updateObject) {
        if (updateObject == 1) {
            channelSelectionBox.setModel(new DefaultComboBoxModel(channelInputBox.getText().replace("#", "").split(", ")));
        } else if (updateObject == 2) {
            selectedChannel = channelSelectionBox.getSelectedItem().toString();
        } else if (updateObject == 3) {
            UpdateInterface(2);
            modePanel.setVisible(true);
            modeBox.setSelectedItem(IOHandler.GetValue(Directories.Files.ChannelFile.replace("%CHANNEL%", selectedChannel), "Mode"));
        } else if (updateObject == 4) {
            try {
                selectedCommand = availableCommandsBox.getSelectedItem().toString();
            } catch (NullPointerException npe) {
                LogHandler.Report(2, "Could not update SelectedCommand.\n" + npe);
            }
        } else if (updateObject == 5) {
            availableCommandsBox.setModel(new DefaultComboBoxModel(IOHandler.ListCommands(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel))));
            availableCommandsPanel.setVisible(true);
        } else if (updateObject == 6) {
            comboPanel.setVisible(true);
            commandPanel.setVisible(true);
            messagePanel.setVisible(true);
            directoryPanel.setVisible(true);
            saveBTN.setVisible(true);
            deleteBTN.setVisible(false);
            enableBox.setSelectedItem(Language.GetText("Option_False"));
            permissionBox.setSelectedItem(Language.GetText("Option_Moderator"));
            commandInput.setText("");
            messageInput.setText("");
            fileDirectoryInput.setText("");
        } else if (updateObject == 7) {
            comboPanel.setVisible(true);
            commandPanel.setVisible(false);
            messagePanel.setVisible(true);
            directoryPanel.setVisible(true);
            saveBTN.setVisible(true);
            deleteBTN.setVisible(true);
            enableBox.setSelectedItem(IOHandler.GetValue(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), selectedCommand + ".Enable"));
            messageInput.setText(IOHandler.GetValue(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), selectedCommand + ".Message"));
            fileDirectoryInput.setText(IOHandler.GetValue(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), selectedCommand + ".FileDirectory"));
            permissionBox.setSelectedItem(IOHandler.GetValue(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), selectedCommand + ".Permission"));
        } else if (updateObject == 8) {
            availableCommandsPanel.setVisible(false);
            comboPanel.setVisible(false);
            commandPanel.setVisible(false);
            messagePanel.setVisible(false);
            directoryPanel.setVisible(false);
            saveBTN.setVisible(false);
            deleteBTN.setVisible(false);
        } else if (updateObject == 9) {
            if (!channelInputBox.getText().isEmpty()) {
                String[] channels = channelInputBox.getText().replace("#", "").split(", ");
                for (String channel:channels) {
                    channel = channel.toLowerCase();
                    if (!IOHandler.CheckDirectory(Directories.Folders.ChannelFolder.replace("%CHANNEL%", channel))) {
                        IOHandler.CreateDirectory(Directories.Folders.ChannelFolder.replace("%CHANNEL%", channel));
                    }
                    if (!IOHandler.CheckDirectory(Directories.Files.ChannelFile.replace("%CHANNEL%", channel))) {
                        DefaultConfig.CreateDefaultChannelConfig(channel);
                    }
                    if (!IOHandler.CheckDirectory(Directories.Files.CommandFile.replace("%CHANNEL%", channel))) {
                        DefaultConfig.CreateDefaultCommandConfig(channel);
                    }
                }
            }
        }
        pack();
    }

    private void AddCommand() {
        IOHandler.SetConfig(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), commandInput.getText() + ".Enable", enableBox.getSelectedItem().toString());
        IOHandler.SetConfig(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), commandInput.getText() + ".Permission", permissionBox.getSelectedItem().toString());
        IOHandler.SetConfig(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), commandInput.getText() + ".Message", messageInput.getText());
        IOHandler.SetConfig(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), commandInput.getText() + ".FileDirectory", fileDirectoryInput.getText());
        UpdateInterface(5);
        UpdateInterface(4);
        UpdateInterface(1);
        availableCommandsBox.setSelectedItem(commandInput.getText());
    }

    private void SaveCommand() {
        IOHandler.SetConfig(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), availableCommandsBox.getSelectedItem().toString() + ".Enable", enableBox.getSelectedItem().toString());
        IOHandler.SetConfig(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), availableCommandsBox.getSelectedItem().toString() + ".Permission", permissionBox.getSelectedItem().toString());
        IOHandler.SetConfig(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), availableCommandsBox.getSelectedItem().toString() + ".Message", messageInput.getText());
        IOHandler.SetConfig(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), availableCommandsBox.getSelectedItem().toString() + ".FileDirectory", fileDirectoryInput.getText());
        UpdateInterface(5);
        UpdateInterface(4);
        UpdateInterface(1);
        availableCommandsBox.setSelectedItem(commandInput.getText());
    }

    private void DeleteCommand() {
        IOHandler.DeleteKey(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), availableCommandsBox.getSelectedItem().toString() + ".Enable");
        IOHandler.DeleteKey(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), availableCommandsBox.getSelectedItem().toString() + ".Permission");
        IOHandler.DeleteKey(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), availableCommandsBox.getSelectedItem().toString() + ".Message");
        IOHandler.DeleteKey(Directories.Files.CommandFile.replace("%CHANNEL%", selectedChannel), availableCommandsBox.getSelectedItem().toString() + ".FileDirectory");
        UpdateInterface(5);
        UpdateInterface(4);
        UpdateInterface(1);
        availableCommandsBox.setSelectedIndex(0);
    }
}
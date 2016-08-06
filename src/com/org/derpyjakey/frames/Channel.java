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
    private JLabel selectChannelLabel = new JLabel();
    private JLabel selectCommandLabel = new JLabel();
    private JLabel commandLabel = new JLabel();
    private JLabel messageLabel = new JLabel();
    private JLabel fileDirectoryLabel = new JLabel();
    private JLabel permissionLabel = new JLabel();
    private JLabel commandKeyLabel = new JLabel();
    private JTextField channelTextField = new JTextField(7);
    private JTextField commandTextField = new JTextField(10);
    private JTextField messageTextField = new JTextField(10);
    private JTextField fileDirectoryTextField = new JTextField(10);
    private JTextField timerTextField = new JTextField(4);
    private JTextField commandKeyTextField = new JTextField(4);
    private JButton updateButton = new JButton();
    private JButton saveButton = new JButton();
    private JButton deleteButton = new JButton();
    private JButton closeButton = new JButton();
    private JComboBox selectChannelComboBox = new JComboBox();
    private JComboBox selectCommandComboBox = new JComboBox();
    private JComboBox permissionComboBox = new JComboBox();
    private JCheckBox botCheckBox = new JCheckBox();
    private JCheckBox enableCheckBox = new JCheckBox();
    private JCheckBox timerCheckBox = new JCheckBox();
    private JPanel panel1 = new JPanel(new GridLayout(3, 3));
    private JPanel panel2 = new JPanel(new GridLayout(3, 2));
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel(new BorderLayout());


    public Channel() {
        updateLanguage();
        addComponents();
        setFrameProperties();
        updateChannelList();
        checkBotCheckBox();
        botCheckUpdate();
        updateButton.addActionListener((ActionEvent actionEvent) -> {
            IOHandler.setKey(Directories.Files.configurationFile, "Channels", channelTextField.getText());
            updateChannelList();
            checkBotCheckBox();
        });
        botCheckBox.addActionListener((ActionEvent actionEvent) -> {
            botCheckUpdate();
            botSaveSetting();
        });
        selectChannelComboBox.addActionListener((ActionEvent actionEvent) -> {
            try {
                checkBotCheckBox();
                botCheckUpdate();
            } catch (NullPointerException ignored) {
            }
        });
        selectCommandComboBox.addActionListener((ActionEvent actionEvent) -> {
            try {
                if (selectCommandComboBox.getSelectedItem().toString().equals(LanguageHandler.getText("Option.AddNewCommand"))) {
                    clearCommandInputs();
                    addNewCommandSelected();
                } else {
                    updateCommandSelection(selectCommandComboBox.getSelectedItem().toString());
                    commandSelected();
                }
            } catch (NullPointerException ignored) {
            }
        });
        saveButton.addActionListener((ActionEvent actionEvent) -> {
            if (selectCommandComboBox.getSelectedItem().toString().equals(LanguageHandler.getText("Option.AddNewCommand"))) {
                String[] keys = {commandTextField.getText() + ".Enable", commandTextField.getText() + ".EnableTimer", commandTextField.getText() + ".FileDirectory", commandTextField.getText() + ".Message", commandTextField.getText() + ".Permission", commandTextField.getText() + ".Timer"};
                String[] values = new String[6];
                if (enableCheckBox.isSelected()) {
                    values[0] = "True";
                } else {
                    values[0] = "False";
                }
                if (timerCheckBox.isSelected()) {
                    values[1] = "True";
                } else {
                    values[1] = "False";
                }
                values[2] = fileDirectoryTextField.getText();
                values[3] = messageTextField.getText();
                if (permissionComboBox.getSelectedItem().equals(LanguageHandler.getText("Option.Moderator"))) {
                    values[4] = LanguageHandler.getText("English", "Option.Moderator");
                } else {
                    values[4] = LanguageHandler.getText("English", "Option.Everyone");
                }
                values[5] = timerTextField.getText();
                IOHandler.setKey(Directories.Files.commandFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), keys, values);
            } else {
                String[] keys = {selectCommandComboBox.getSelectedItem().toString() + ".Enable", selectCommandComboBox.getSelectedItem().toString() + ".EnableTimer", selectCommandComboBox.getSelectedItem().toString() + ".FileDirectory", selectCommandComboBox.getSelectedItem().toString() + ".Message", selectCommandComboBox.getSelectedItem().toString() + ".Permission", selectCommandComboBox.getSelectedItem().toString() + ".Timer"};
                String[] values = new String[6];
                if (enableCheckBox.isSelected()) {
                    values[0] = "True";
                } else {
                    values[0] = "False";
                }
                if (timerCheckBox.isSelected()) {
                    values[1] = "True";
                } else {
                    values[1] = "False";
                }
                values[2] = fileDirectoryTextField.getText();
                values[3] = messageTextField.getText();
                if (permissionComboBox.getSelectedItem().equals(LanguageHandler.getText("Option.Moderator"))) {
                    values[4] = LanguageHandler.getText("English", "Option.Moderator");
                } else {
                    values[4] = LanguageHandler.getText("English", "Option.Everyone");
                }
                values[5] = timerTextField.getText();
                IOHandler.setKey(Directories.Files.commandFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), keys, values);
            }
            IOHandler.setKey(Directories.Files.channelFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), "Command Key", commandKeyTextField.getText());
            clearCommandInputs();
            repopulateCommandList();
        });
        deleteButton.addActionListener((ActionEvent actionEvent) -> {
            removeCommand();
            clearCommandInputs();
            repopulateCommandList();
        });
        closeButton.addActionListener((ActionEvent actionEvent) -> {
            frame.dispose();
        });
    }

    private void updateLanguage() {
        if (!activeLanguage.equals(LanguageHandler.getLanguage())) {
            frame.setTitle(LanguageHandler.getText("Frame.Channel"));
            enableCheckBox.setText(LanguageHandler.getText("CheckBox.Enable"));
            botCheckBox.setText(LanguageHandler.getText("CheckBox.Bot"));
            timerCheckBox.setText(LanguageHandler.getText("CheckBox.Timer"));
            permissionComboBox.addItem(LanguageHandler.getText("Option.Moderator"));
            permissionComboBox.addItem(LanguageHandler.getText("Option.Everyone"));
            channelLabel.setText(LanguageHandler.getText("Label.Channels"));
            selectChannelLabel.setText(LanguageHandler.getText("Label.SelectChannel"));
            selectCommandLabel.setText(LanguageHandler.getText("Label.SelectCommand"));
            commandLabel.setText(LanguageHandler.getText("Label.Command"));
            messageLabel.setText(LanguageHandler.getText("Label.Message"));
            fileDirectoryLabel.setText(LanguageHandler.getText("Label.FileDirectory"));
            permissionLabel.setText(LanguageHandler.getText("Label.Permission"));
            commandKeyLabel.setText(LanguageHandler.getText("Label.CommandKey"));
            updateButton.setText(LanguageHandler.getText("Button.Update"));
            saveButton.setText(LanguageHandler.getText("Button.Save"));
            deleteButton.setText(LanguageHandler.getText("Button.Delete"));
            closeButton.setText(LanguageHandler.getText("Button.Close"));
        }
    }

    private void addComponents() {
        panel1.add(channelLabel);
        panel1.add(channelTextField);
        panel1.add(updateButton);
        panel1.add(selectChannelLabel);
        panel1.add(selectChannelComboBox);
        panel1.add(botCheckBox);
        panel1.add(selectCommandLabel);
        panel1.add(selectCommandComboBox);
        panel1.add(enableCheckBox);
        panel2.add(commandLabel);
        panel2.add(commandTextField);
        panel2.add(messageLabel);
        panel2.add(messageTextField);
        panel2.add(fileDirectoryLabel);
        panel2.add(fileDirectoryTextField);
        panel3.add(permissionLabel);
        panel3.add(permissionComboBox);
        panel3.add(commandKeyLabel);
        panel3.add(commandKeyTextField);
        panel3.add(timerCheckBox);
        panel3.add(timerTextField);
        panel4.add(saveButton);
        panel4.add(deleteButton);
        panel4.add(closeButton);
        panel5.add(panel2, BorderLayout.NORTH);
        panel5.add(panel3, BorderLayout.SOUTH);
        JPanel container = new JPanel(new BorderLayout());
        container.add(panel1, BorderLayout.NORTH);
        container.add(panel5, BorderLayout.CENTER);
        container.add(panel4, BorderLayout.SOUTH);
        frame.getContentPane().add(container);
    }

    private void setFrameProperties() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        selectCommandLabel.setVisible(false);
        selectCommandComboBox.setVisible(false);
        enableCheckBox.setVisible(false);
        panel2.setVisible(false);
        panel3.setVisible(false);
        saveButton.setVisible(false);
        deleteButton.setVisible(false);
        frame.pack();
        frame.setVisible(true);
    }

    private void updateChannelList() {
        channelTextField.setText(IOHandler.getValue(Directories.Files.configurationFile, "Channels"));
        if (!channelTextField.getText().isEmpty()) {
            selectChannelComboBox.removeAllItems();
            if (channelTextField.getText().contains(", ")) {
                String[] listChannels = channelTextField.getText().replace("#", "").split(", ");
                for (String channel : listChannels) {
                    if (!IOHandler.checkDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channel))) {
                        IOHandler.createDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channel));
                    }
                    if (!IOHandler.checkDirectory(Directories.Files.channelFile.replace("%CHANNEL%", channel))) {
                        DefaultConfig.createDefaultChannelConfig(channel);
                    }
                    if (!IOHandler.checkDirectory(Directories.Files.commandFile.replace("%CHANNEL%", channel))) {
                        DefaultConfig.createDefaultChannelCommandConfig(channel);
                    }
                    selectChannelComboBox.addItem(channel);
                }
            } else {
                if (!IOHandler.checkDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channelTextField.getText().replace("#", "")))) {
                    IOHandler.createDirectory(Directories.Folders.channelFolder.replace("%CHANNEL%", channelTextField.getText().replace("#", "")));
                }
                if (!IOHandler.checkDirectory(Directories.Files.channelFile.replace("%CHANNEL%", channelTextField.getText().replace("#", "")))) {
                    DefaultConfig.createDefaultChannelConfig(channelTextField.getText().replace("#", ""));
                }
                if (!IOHandler.checkDirectory(Directories.Files.commandFile.replace("%CHANNEL%", channelTextField.getText().replace("#", "")))) {
                    DefaultConfig.createDefaultChannelCommandConfig(channelTextField.getText().replace("#", ""));
                }
                selectChannelComboBox.addItem(channelTextField.getText().replace("#", ""));
            }
            if (!selectChannelComboBox.getSelectedItem().toString().isEmpty()) {
                botCheckUpdate();
                botCheckBox.setVisible(true);
            } else {
                botCheckBox.setSelected(false);
                botCheckBox.setVisible(false);
            }
        } else {
            clearCommandInputs();
            botCheckBox.setVisible(false);
        }
    }

    private void clearCommandInputs() {
        commandTextField.setText("");
        messageTextField.setText("");
        fileDirectoryTextField.setText("");
        commandKeyTextField.setText("");
        timerTextField.setText("");
        permissionComboBox.setSelectedIndex(0);
        timerCheckBox.setSelected(false);
    }

    private void checkBotCheckBox() {
        if (IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), "Bot").equals("True")) {
            botCheckBox.setSelected(true);
        } else {
            botCheckBox.setSelected(false);
        }
    }

    private void botCheckUpdate() {
        if (botCheckBox.isSelected()) {
            clearCommandInputs();
            repopulateCommandList();
            if (selectCommandComboBox.getSelectedItem().toString().equals(LanguageHandler.getText("Option.AddNewCommand"))) {
                clearCommandInputs();
                addNewCommandSelected();
            } else {
                updateCommandSelection(selectCommandComboBox.getSelectedItem().toString());
                commandSelected();
            }
        } else {
            clearFrame();
        }
    }

    private void botSaveSetting() {
        if (botCheckBox.isSelected()) {
            IOHandler.setKey(Directories.Files.channelFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), "Bot", "True");
        } else {
            IOHandler.setKey(Directories.Files.channelFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), "Bot", "False");
        }
    }

    private void updateCommandSelection(String command) {
        if (IOHandler.getValue(Directories.Files.commandFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), command + ".Enable").equals("True")) {
            enableCheckBox.setSelected(true);
        } else {
            enableCheckBox.setSelected(false);
        }
        if (IOHandler.getValue(Directories.Files.commandFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), command + ".EnableTimer").equals("True")) {
            timerCheckBox.setSelected(true);
        } else {
            timerCheckBox.setSelected(false);
        }
        messageTextField.setText(IOHandler.getValue(Directories.Files.commandFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), command + ".Message"));
        fileDirectoryTextField.setText(IOHandler.getValue(Directories.Files.commandFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), command + ".FileDirectory"));
        timerTextField.setText(IOHandler.getValue(Directories.Files.commandFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), command + ".Timer"));
        commandKeyTextField.setText(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), "Command Key"));
    }

    private void repopulateCommandList() {
        selectCommandComboBox.removeAllItems();
        selectCommandComboBox.addItem(LanguageHandler.getText("Option.AddNewCommand"));
        try {
            for (String command : IOHandler.listCommands(selectChannelComboBox.getSelectedItem().toString())) {
                selectCommandComboBox.addItem(command);
            }
        } catch (NullPointerException ignored) {
        }
        selectCommandComboBox.setSelectedIndex(0);
    }

    private void addNewCommandSelected() {
        selectCommandLabel.setVisible(true);
        selectCommandComboBox.setVisible(true);
        enableCheckBox.setVisible(true);
        panel2.setVisible(true);
        panel3.setVisible(true);
        panel4.setVisible(true);
        panel5.setVisible(true);
        commandLabel.setVisible(true);
        commandTextField.setVisible(true);
        saveButton.setVisible(true);
        deleteButton.setVisible(false);
        closeButton.setVisible(true);
        frame.pack();
        commandKeyTextField.setText(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), "Command Key"));
    }

    private void commandSelected() {
        selectCommandLabel.setVisible(true);
        selectCommandComboBox.setVisible(true);
        enableCheckBox.setVisible(true);
        panel2.setVisible(true);
        panel3.setVisible(true);
        panel4.setVisible(true);
        panel5.setVisible(true);
        commandLabel.setVisible(false);
        commandTextField.setVisible(false);
        saveButton.setVisible(true);
        deleteButton.setVisible(true);
        closeButton.setVisible(true);
        frame.pack();
    }

    private void clearFrame() {
        selectCommandLabel.setVisible(false);
        selectCommandComboBox.setVisible(false);
        enableCheckBox.setVisible(false);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(true);
        panel5.setVisible(false);
        commandLabel.setVisible(false);
        commandTextField.setVisible(false);
        saveButton.setVisible(false);
        deleteButton.setVisible(false);
        closeButton.setVisible(true);
        frame.pack();
    }

    private void removeCommand() {
        String[] keys = {selectCommandComboBox.getSelectedItem().toString() + ".Enable", selectCommandComboBox.getSelectedItem().toString() + ".EnableTimer", selectCommandComboBox.getSelectedItem().toString() + ".FileDirectory", selectCommandComboBox.getSelectedItem().toString() + ".Message", selectCommandComboBox.getSelectedItem().toString() + ".Permission", selectCommandComboBox.getSelectedItem().toString() + ".Timer"};
        IOHandler.deleteKey(Directories.Files.commandFile.replace("%CHANNEL%", selectChannelComboBox.getSelectedItem().toString()), keys);
    }
}
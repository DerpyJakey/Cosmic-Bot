package com.org.derpyjakey.frames;

/**
 * Placeholder for Bot Setting Frame. (Will be added into Channel Frame)
 * To Enable, Disable, and Modify Bot settings.
 */
import com.org.derpyjakey.utilities.LanguageHandler;
import com.org.derpyjakey.references.Directories;
import com.org.derpyjakey.utilities.LogHandler;
import com.org.derpyjakey.utilities.IOHandler;

import java.awt.event.ActionEvent;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
 
public class Bot {
    private String activeLanguage = "";
    private String activeChannel;
    private JFrame frame = new JFrame();
    private JLabel botLabel = new JLabel();
    private JLabel commandKeyLabel = new JLabel();
    private JLabel blacklistLabel = new JLabel();
    private JLabel blacklistEnableLabel = new JLabel();
    private JLabel blacklistRulesLabel = new JLabel();
    private JLabel blacklistResponseLabel = new JLabel();
    private JLabel capLimitLabel = new JLabel();
    private JLabel capLimitEnableLabel = new JLabel();
    private JLabel capLimitRulesLabel = new JLabel();
    private JLabel capLimitResponseLabel = new JLabel();
    private JLabel charLimitLabel = new JLabel();
    private JLabel charLimitEnableLabel = new JLabel();
    private JLabel charLimitRulesLabel = new JLabel();
    private JLabel charLimitResponseLabel = new JLabel();
    private JLabel wordLimitLabel = new JLabel();
    private JLabel wordLimitEnableLabel = new JLabel();
    private JLabel wordLimitRulesLabel = new JLabel();
    private JLabel wordLimitResponseLabel = new JLabel();
    private JLabel spamProtectionLabel = new JLabel();
    private JLabel spamProtectionEnableLabel = new JLabel();
    private JLabel spamProtectionRuleLabel = new JLabel();
    private JLabel spamProtectionResponseLabel = new JLabel();
    private JCheckBox botEnableCheckbox = new JCheckBox();
    private JCheckBox blacklistEnableCheckbox = new JCheckBox();
    private JCheckBox capLimitEnableCheckbox = new JCheckBox();
    private JCheckBox charLimitEnableCheckbox = new JCheckBox();
    private JCheckBox wordLimitEnableCheckbox = new JCheckBox();
    private JCheckBox spamProtectionEnableCheckbox = new JCheckBox();
    private JTextField commandKeyTextfield = new JTextField(8);
    private JTextField blacklistRulesTextfield = new JTextField(10);
    private JTextField blacklistResponseTextfield = new JTextField(10);
    private JTextField capLimitRulesTextfield = new JTextField(10);
    private JTextField capLimitResponseTextfield = new JTextField(10);
    private JTextField charLimitRulesTextfield = new JTextField(10);
    private JTextField charLimitResponseTextfield = new JTextField(10);
    private JTextField wordLimitRulesTextfield = new JTextField(10);
    private JTextField wordLimitResponseTextfield = new JTextField(10);
    private JTextField spamProtectionRuleTextfield = new JTextField(10);
    private JTextField spamProtectionResponseTextfield = new JTextField(10);
    private JButton saveButton = new JButton();
    private JButton closeButton = new JButton();
    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints gridLayout = new GridBagConstraints();

    public Bot(String channel) {
        activeChannel = channel;
        updateLanguage();
        addComponents();
        setFrameProperties();
        updateSettings();
        saveButton.addActionListener((ActionEvent actionEvent) -> {
            
        });
        closeButton.addActionListener((ActionEvent actionEvent) -> {
            frame.dispose();
        });
    }
    
    private void updateLanguage() {
        if (!activeLanguage.equals(LanguageHandler.getLanguage())) {
            frame.setTitle(LanguageHandler.getText("Frame.Bot") + " " + activeChannel);
            commandKeyLabel.setText(LanguageHandler.getText("Label.CommandKey"));
            botLabel.setText(LanguageHandler.getText("Label.BotFeatureEnable"));
            blacklistLabel.setText(LanguageHandler.getText("Label.Blacklist"));
            blacklistEnableLabel.setText(LanguageHandler.getText("Label.BlacklistEnable"));
            blacklistRulesLabel.setText(LanguageHandler.getText("Label.BlacklistRules"));
            blacklistResponseLabel.setText(LanguageHandler.getText("Label.BlacklistResponse"));
            capLimitLabel.setText(LanguageHandler.getText("Label.CapLimit"));
            capLimitEnableLabel.setText(LanguageHandler.getText("Label.CapLimitEnable"));
            capLimitRulesLabel.setText(LanguageHandler.getText("Label.CapLimitRules"));
            capLimitResponseLabel.setText(LanguageHandler.getText("Label.CapLimitResponse"));
            charLimitLabel.setText(LanguageHandler.getText("Label.CharLimit"));
            charLimitEnableLabel.setText(LanguageHandler.getText("Label.CharLimitEnable"));
            charLimitRulesLabel.setText(LanguageHandler.getText("Label.CharLimitRules"));
            charLimitResponseLabel.setText(LanguageHandler.getText("Label.CharLimitResponse"));
            wordLimitLabel.setText(LanguageHandler.getText("Label.WordLimit"));
            wordLimitEnableLabel.setText(LanguageHandler.getText("Label.WordLimitEnable"));
            wordLimitRulesLabel.setText(LanguageHandler.getText("Label.WordLimitRules"));
            wordLimitResponseLabel.setText(LanguageHandler.getText("Label.WordLimitResponse"));
            spamProtectionLabel.setText(LanguageHandler.getText("Label.SpamProtection"));
            spamProtectionEnableLabel.setText(LanguageHandler.getText("Label.SpamProtectionEnable"));
            spamProtectionRuleLabel.setText(LanguageHandler.getText("Label.SpamProtectionRule"));
            spamProtectionResponseLabel.setText(LanguageHandler.getText("Label.SpamProtectionResponse"));
            saveButton.setText(LanguageHandler.getText("Button.Save"));
            closeButton.setText(LanguageHandler.getText("Button.Close"));
            activeLanguage = LanguageHandler.getLanguage();
        }
    }
    
    private void addComponents() {
        gridLayout.weightx = 0.5;
        int[] tempX = {1, 2, 4, 5, 0, 1, 2, 3, 4, 5, 6, 0, 1, 2, 3, 4, 5, 6, 0, 1, 2, 3, 4, 5, 6, 0, 1, 2, 3, 4, 5, 6, 0, 1, 2, 3, 4, 5, 6, 2, 4};
        int[] tempY = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6};
        Component[] tempComp = {botLabel, botEnableCheckbox, commandKeyLabel, commandKeyTextfield, blacklistLabel, blacklistEnableLabel, blacklistEnableCheckbox, blacklistRulesLabel, blacklistRulesTextfield, blacklistResponseLabel, blacklistResponseTextfield, capLimitLabel, capLimitEnableLabel, capLimitEnableCheckbox, capLimitRulesLabel, capLimitRulesTextfield, capLimitResponseLabel, capLimitResponseTextfield, charLimitLabel, charLimitEnableLabel, charLimitEnableCheckbox, charLimitRulesLabel, charLimitRulesTextfield, charLimitResponseLabel, charLimitResponseTextfield, wordLimitLabel, wordLimitEnableLabel, wordLimitEnableCheckbox, wordLimitRulesLabel, wordLimitRulesTextfield, wordLimitResponseLabel, wordLimitResponseTextfield, spamProtectionLabel, spamProtectionEnableLabel, spamProtectionEnableCheckbox, spamProtectionRuleLabel, spamProtectionRuleTextfield, spamProtectionResponseLabel, spamProtectionResponseTextfield, saveButton, closeButton};
        setGrid(mainPanel, tempX, tempY, tempComp, gridLayout);
        frame.getContentPane().add(mainPanel);
    }

    private void setGrid(JPanel panel, int[] x, int[] y, Component[] comp, GridBagConstraints gridBagLayout) {
        for (int i = 0; i <= comp.length - 1; i++) {
            gridBagLayout.gridx = x[i];
            gridBagLayout.gridy = y[i];
            panel.add(comp[i], gridBagLayout);
        }
    }

    private void setFrameProperties() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private void updateSettings() {
        botEnableCheckbox.setSelected(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Enable Bot").equals("True"));
        blacklistEnableCheckbox.setSelected(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Enable Blacklist").equals("True"));
        capLimitEnableCheckbox.setSelected(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Enable Cap Limit").equals("True"));
        charLimitEnableCheckbox.setSelected(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Enable Char Limit").equals("True"));
        wordLimitEnableCheckbox.setSelected(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Enable Word Limit").equals("True"));
        spamProtectionEnableCheckbox.setSelected(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Enable Spam Protection").equals("True"));
        commandKeyTextfield.setText(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Command Key"));
        blacklistRulesTextfield.setText(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Blacklist Rules"));
        capLimitRulesTextfield.setText(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Cap Limit Rules"));
        charLimitRulesTextfield.setText(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Char Limit Rules"));
        wordLimitRulesTextfield.setText(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Word Limit Rules"));
        spamProtectionRuleTextfield.setText(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Spam Protection Rules"));
        blacklistResponseTextfield.setText(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Blacklist Response"));
        capLimitResponseTextfield.setText(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Cap Limit Response"));
        charLimitResponseTextfield.setText(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Char Limit Response"));
        wordLimitResponseTextfield.setText(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Word Limit Response"));
        spamProtectionResponseTextfield.setText(IOHandler.getValue(Directories.Files.channelFile.replace("%CHANNEL%", activeChannel), "Spam Protection Response"));
    }
}

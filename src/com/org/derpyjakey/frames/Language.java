package com.org.derpyjakey.frames;

import com.org.derpyjakey.utilities.LanguageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Language {
    private String activeLanguage = "";
    JFrame frame = new JFrame();
    JLabel languageLabel = new JLabel();
    JComboBox languageComboBox = new JComboBox();
    JButton saveButton = new JButton();

    public Language() {
        updateLanguage();
        addComponents();
        setFrameProperties();
        languageComboBox.addActionListener((ActionEvent actionEvent) -> {
            changeLanguage(languageComboBox.getSelectedItem().toString());
        });
        saveButton.addActionListener((ActionEvent actionEvent) -> {
            LanguageHandler.setLanguage(languageComboBox.getSelectedItem().toString());
            frame.dispose();
        });
    }

    private void updateLanguage() {
        if (!activeLanguage.equals(LanguageHandler.getLanguage())) {
            frame.setTitle(LanguageHandler.getText("Frame.Language"));
            languageLabel.setText(LanguageHandler.getText("Label.Language"));
            saveButton.setText(LanguageHandler.getText("Button.Save"));
            activeLanguage = LanguageHandler.getLanguage();
        }
    }

    private void changeLanguage(String newLanguage) {
        if (!activeLanguage.equals(newLanguage)) {
            frame.setTitle(LanguageHandler.getText(newLanguage, "Frame.Language"));
            languageLabel.setText(LanguageHandler.getText(newLanguage, "Label.Language"));
            saveButton.setText(LanguageHandler.getText(newLanguage, "Button.Save"));
            activeLanguage = newLanguage;
        }
    }

    private void addComponents() {
        String[] availableLanguages = LanguageHandler.listLanguages();
        for (String listLanguage : availableLanguages) {
            languageComboBox.addItem(listLanguage);
        }
        frame.add(languageLabel, BorderLayout.WEST);
        frame.add(languageComboBox, BorderLayout.CENTER);
        frame.add(saveButton, BorderLayout.SOUTH);
    }

    private void setFrameProperties() {
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
    }
}

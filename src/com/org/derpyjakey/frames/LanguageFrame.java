package com.org.derpyjakey.frames;

import com.org.derpyjakey.utilities.Language;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LanguageFrame {
    String current_Language;
    JFrame language_Frame;
    JLabel language_Label;
    JComboBox language_ComboBox;
    JButton language_Save_Button;

    public LanguageFrame() {
        initialize();
        updateLanguage();
        addComponents();
        setFrameProperties();
        language_ComboBox.addActionListener((ActionEvent actionEvent) -> {
            updateLanguage(language_ComboBox.getSelectedItem().toString());
            setFrameProperties();
        });
        language_Save_Button.addActionListener((ActionEvent actionEvent) -> {
            Language.setLanguage(language_ComboBox.getSelectedItem().toString());
            language_Frame.dispose();
        });
    }

    private void initialize() {
        current_Language = "";
        language_Frame = new JFrame();
        language_Label = new JLabel();
        language_ComboBox = new JComboBox();
        language_ComboBox.setModel(new DefaultComboBoxModel(Language.listLanguages()));
        language_Save_Button = new JButton();
    }

    private void updateLanguage() {
        if (!current_Language.equals(Language.getLanguage())) {
            language_Frame.setTitle(Language.getText("Frame.Language"));
            language_Label.setText(Language.getText("Label.Language"));
            language_Save_Button.setText(Language.getText("Button.Save"));
            current_Language = Language.getLanguage();
        }
    }

    private void updateLanguage(String language) {
        if (!current_Language.equals(Language.getLanguage())) {
            language_Frame.setTitle(Language.getText(language, "Frame.Language"));
            language_Label.setText(Language.getText(language, "Label.Language"));
            language_Save_Button.setText(Language.getText(language, "Button.Save"));
            current_Language = language;
        }
    }

    private void addComponents() {
        language_Frame.add(language_ComboBox, BorderLayout.CENTER);
        language_Frame.add(language_Save_Button, BorderLayout.SOUTH);
    }

    private void setFrameProperties() {
        language_Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        language_Frame.setResizable(false);
        language_Frame.pack();
        language_Frame.setSize(language_Frame.getWidth() + 165, language_Frame.getHeight() + 25);
        language_Frame.setVisible(true);
    }
}
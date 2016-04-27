package com.org.derpyjakey.frame;

import com.org.derpyjakey.reference.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguageSelectionFrame extends JFrame {
    String currentLanguage = "";
    JLabel languageLabel;
    JComboBox languageBox;
    JButton saveBTN;

    public LanguageSelectionFrame() {
        CreateUI();
        UpdateLanguage();
        languageBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setTitle(Language.GetText(languageBox.getSelectedItem().toString(), "Title_Language_Selection"));
                languageLabel.setText(Language.GetText(languageBox.getSelectedItem().toString(), "Label_Language"));
                saveBTN.setText(Language.GetText(languageBox.getSelectedItem().toString(), "Button_Save"));
            }
        });
        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Language.SetLanguage(languageBox.getSelectedItem().toString());
                dispose();
            }
        });
    }

    void CreateUI() {
        languageLabel = new JLabel(Language.GetText("Label_Language"));
        languageBox = new JComboBox();
        languageBox.setModel(new DefaultComboBoxModel(Language.ListLanguages()));
        saveBTN = new JButton();
        add(languageBox, BorderLayout.CENTER);
        add(saveBTN, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        pack();
        setSize(getWidth() + 160, getHeight() + 20);
    }

    void UpdateLanguage() {
        if (!currentLanguage.equals(Language.GetLanguage())) {
            setTitle(Language.GetText("Title_Language_Selection"));
            languageLabel.setText(Language.GetText("Label_Language"));
            saveBTN.setText(Language.GetText("Button_Save"));
            currentLanguage = Language.GetLanguage();
        }
    }
}

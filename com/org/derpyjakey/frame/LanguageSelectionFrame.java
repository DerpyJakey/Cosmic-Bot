package com.org.derpyjakey.frame;

import com.org.derpyjakey.reference.Language;
import javax.swing.*;
import java.awt.*;

public class LanguageSelectionFrame extends JFrame {
    private String currentLanguage = "";
    private JLabel languageLabel;
    private JComboBox languageBox;
    private JButton saveBTN;

    public LanguageSelectionFrame() {
        CreateUI();
        UpdateLanguage();
        languageBox.addActionListener(actionEvent -> {
            setTitle(Language.GetText(languageBox.getSelectedItem().toString(), "Title_Language_Selection"));
            languageLabel.setText(Language.GetText(languageBox.getSelectedItem().toString(), "Label_Language"));
            saveBTN.setText(Language.GetText(languageBox.getSelectedItem().toString(), "Button_Save"));
        });
        saveBTN.addActionListener(actionEvent -> {
            Language.SetLanguage(languageBox.getSelectedItem().toString());
            dispose();
        });
    }

    private void CreateUI() {
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

    private void UpdateLanguage() {
        if (!currentLanguage.equals(Language.GetLanguage())) {
            setTitle(Language.GetText("Title_Language_Selection"));
            languageLabel.setText(Language.GetText("Label_Language"));
            saveBTN.setText(Language.GetText("Button_Save"));
            currentLanguage = Language.GetLanguage();
        }
    }
}

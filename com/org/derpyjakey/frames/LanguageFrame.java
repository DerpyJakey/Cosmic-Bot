package com.org.derpyjakey.frames;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.org.derpyjakey.references.Language;

public class LanguageFrame {
	String current_Language;
	JFrame language_Frame;
	JLabel language_Label;
	JComboBox language_ComboBox;
	JButton language_Save_Button;
	
	LanguageFrame() {
		initialize();
		updateLanguage();
		addComponents();
		setFrameProperties();
		language_ComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				updateLanguage(language_ComboBox.getSelectedItem().toString());
				setFrameProperties();
			}
		});
		language_Save_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				Language.setLanguage(language_ComboBox.getSelectedItem().toString());
				dispose();
			}
		});
	}
	
	void initialize() {
		current_Language = "";
		language_Frame = new JFrame();
		language_Label = new JLabel();
		language_ComboBox = new JComboBox();
		language_ComboBox.setModel(new DefaultComboBoxModel(Language.ListLanguages()));
		language_Save_Button = new JButton();
	}
	
	void updateLanguage() {
		if (!current_Language.equals(Language.getLanguage())) {
			language_Frame.setTitle(Language.getText("Title.Language"));
			language_Label.setText(Language.getText("Label.Language"));
			language_Save_Button.setText("Button.Save"));
			current_Language = Language.getLanguage();
		}
	}
	
	void updateLanguage(String language) {
		if (!current_Language.equals(Language.getLanguage())) {
			language_Frame.setTitle(Language.getText(language, "Title.Language.Selection"));
			language_Label.setText(Language.getText(language, "Label.Language"));
			language_Save_Button.setText(language, "Button.Save"));
			current_Language = Language.getLanguage();
		}
	}
	
	void addComponents() {
		add(language_ComboBox, BorderLayout.CENTER);
		add(language_Save_Button, BorderLayout.SOUTH);
	}
	
	void setFrameProperties() {
		language_Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		language_Frame.setResizable(false);
		language_Frame.pack();
		language_Frame.setSize(language_Frame.getWidth() + 165, language_Frame.getHeight() + 25);
	}
}

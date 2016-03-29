package frame;

import reference.Language;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguageSelectionFrame extends JFrame {
	String current_Language = "";
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
		add(languageBox, BorderLayout.NORTH);
		add(saveBTN, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		pack();
	}

	void UpdateLanguage() {
		if (!current_Language.equals(Language.GetLanguage())) {
			setTitle(Language.GetText("Title_Language_Selection"));
			languageLabel.setText(Language.GetText("Label_Language"));
			saveBTN.setText(Language.GetText("Button_Save"));
			current_Language = Language.GetLanguage();
		}
	}
}
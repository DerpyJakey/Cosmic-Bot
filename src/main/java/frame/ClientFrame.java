package frame;

import java.awt.*;
import javax.swing.*;
import frame.AccountFrame;
import frame.ChannelFrame;
import reference.Language;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import frame.LanguageSelectionFrame;

public class ClientFrame extends JFrame {
	String current_Language = Language.GetLanguage();
	String current_Channel;
	JMenuBar menuBar;
	JMenu settingMenu;
	JMenuItem accountItem;
	JMenuItem channelItem;
	JMenuItem languageItem;
	JTextArea chatBox;
	JComboBox channelSelectionBox;
	JTextField messageInput;
	JButton sendBTN;
	JPanel inputPanel;
	
	public ClientFrame() {
		CreateUI();
		UpdateLanguage();
		accountItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AccountFrame accountFrame = new AccountFrame();
			}
		});
		channelItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				ChannelFrame channelFrame = new ChannelFrame();
			}
		});
		languageItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				LanguageSelectionFrame languageSelectionFrame = new LanguageSelectionFrame();
			}
		});
	}
	
	void CreateUI() {
		menuBar = new JMenuBar();
		settingMenu = new JMenu();
		accountItem = new JMenuItem();
		channelItem = new JMenuItem();
		languageItem = new JMenuItem();
		menuBar.add(settingMenu);
		settingMenu.add(accountItem);
		settingMenu.add(channelItem);
		settingMenu.add(languageItem);
		chatBox = new JTextArea();
		chatBox.setEditable(false);
		channelSelectionBox = new JComboBox();
		messageInput = new JTextField();
		sendBTN = new JButton();
		inputPanel = new JPanel(new BorderLayout());
		inputPanel.add(channelSelectionBox, BorderLayout.WEST);
		inputPanel.add(messageInput, BorderLayout.CENTER);
		inputPanel.add(sendBTN, BorderLayout.EAST);
		JPanel clientPanel = new JPanel(new BorderLayout());
		clientPanel.add(chatBox, BorderLayout.CENTER);
		clientPanel.add(inputPanel, BorderLayout.SOUTH);
		getContentPane().add(clientPanel);
		setJMenuBar(menuBar);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	void UpdateLanguage() {
		settingMenu.setText(Language.GetText("Menu_Settings"));
		accountItem.setText(Language.GetText("Item_Account"));
		channelItem.setText(Language.GetText("Item_Channel"));
		languageItem.setText(Language.GetText("Item_Language"));
		sendBTN.setText(Language.GetText("Button_Send"));
	}
	
	void UpdateInterface(int updateObject) {
		
	}
}
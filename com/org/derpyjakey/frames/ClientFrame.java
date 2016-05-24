package com.org.derpyjakey.frames;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.org.derpyjakey.utilities.Language;
import com.org.derpyjakey.utilities.IRCHandler;

public class ClientFrame {
	String current_Language;
	boolean status_Connected;
	boolean chat_Thread_Init;
	JFrame client_Frame;
	JPanel client_Panel;
	JPanel client_Input_Panel;
	JMenuBar client_Menu_Bar;
	JMenu client_Menu;
	JMenu client_Server_Menu;
	JMenu client_Settings_Menu;
	JMenuItem client_Connect_Item;
	JMenuItem client_About_Item;
	JMenuItem client_Exit_Item;
	JMenuItem client_Account_Item;
	JMenuItem client_Channel_Item;
	JMenuItem client_Language_Item;
	JComboBox client_Channel_Selection_Box;
	JTextArea client_Chat_Text_Area;
	JTextField client_Input_Text_Field;
	JButton client_Send_Button;
	IRCHandler ircHandler = new IRCHandler();
	
	public ClientFrame() {
		initialize();
		updateLanguage();
		addComponents();
		setFrameProperties();
	}
	
	void initialize() {
		current_Language = "";
		client_Frame = new JFrame();
		client_Panel = new JPanel(new BorderLayout());
		client_Input_Panel = new JPanel(new BorderLayout());
		client_Menu_Bar = new JMenuBar();
		client_Menu = new JMenu();
		client_Server_Menu = new JMenu();
		client_Settings_Menu = new JMenu();
		client_Connect_Item = new JMenuItem();
		client_About_Item = new JMenuItem();
		client_Exit_Item = new JMenuItem();
		client_Account_Item = new JMenuItem();
		client_Channel_Item = new JMenuItem();
		client_Language_Item = new JMenuItem();
		client_Channel_Selection_Box = new JComboBox();
		client_Chat_Text_Area = new JTextArea();
		client_Input_Text_Field = new JTextField();
		client_Send_Button = new JButton();
		status_Connected = false;
		chat_Thread_Init = false;
	}
	
	void updateLanguage() {
		if (!current_Language.equals(Language.getLanguage())) {
			client_Frame.setTitle(Language.getText("Frame.Client"));
			client_Menu.setText(Language.getText("Menu.Client"));
			client_Server_Menu.setText(Language.getText("Menu.Server"));
			client_Settings_Menu.setText(Language.getText("Menu.Settings"));
			client_Connect_Item.setText(Language.getText("MenuItem.Connect"));
			client_About_Item.setText(Language.getText("MenuItem.About"));
			client_Exit_Item.setText(Language.getText("MenuItem.Exit"));
			client_Account_Item.setText(Language.getText("MenuItem.Account"));
			client_Channel_Item.setText(Language.getText("MenuItem.Channel"));
			client_Language_Item.setText(Language.getText("MenuItem.Language"));
			client_Send_Button.setText(Language.getText("Button.Send"));
			current_Language = Language.getLanguage();
		}
	}
	
	void addComponents() {
		client_Menu_Bar.add(client_Menu);
		client_Menu_Bar.add(client_Server_Menu);
		client_Menu_Bar.add(client_Settings_Menu);
		client_Menu.add(client_About_Item);
		client_Menu.add(client_Exit_Item);
		client_Server_Menu.add(client_Connect_Item);
		client_Server_Menu.add(client_Account_Item);
		client_Server_Menu.add(client_Channel_Item);
		client_Settings_Menu.add(client_Language_Item);
		client_Input_Panel.add(client_Channel_Selection_Box, BorderLayout.WEST);
		client_Input_Panel.add(client_Input_Text_Field, BorderLayout.CENTER);
		client_Input_Panel.add(client_Send_Button, BorderLayout.EAST);
		client_Panel.add(client_Chat_Text_Area, BorderLayout.CENTER);
		client_Panel.add(client_Input_Panel, BorderLayout.SOUTH);
		client_Frame.getContentPane().add(client_Panel);
		client_Frame.setJMenuBar(client_Menu_Bar);
	}
	
	void setFrameProperties() {
		client_Frame.setSize(500, 500);
		client_Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		client_Frame.setVisible(true);
	}
	
	void updateChat() {
		Thread chat_Thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (status_Connected) {
					if (ircHandler.recieveMessage().equals("Disconnect Cosmic-Bot")) {
						status_Connected = false;
						break;
					} else {
						client_Chat_Text_Area.append(ircHandler.recieveMessage());
					}
				}
			}
		});
		if (status_Connected && !chat_Thread_Init) {
			chat_Thread.start();
			chat_Thread_Init = true;
		}
	}
}

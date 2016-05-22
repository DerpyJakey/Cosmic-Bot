package com.org.derpyjakey.frames;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.org.derpyjakey.utilities.Language;

public class ClientFrame {
	String current_Language;
	boolean status_Connected;
	boolean chat_Thread_Init;
	JFrame client_Frame;
	JMenuBar client_Menu_Bar;
	JMenu client_Menu;
	JMenu client_Server_Menu;
	JTextField client_Chat_Text_Field;
	JTextField client_Input_Text_Field;
	JButton client_Send_Button;
	
	public ClientFrame() {
		initialize();
		updateLanguage();
		addComponents();
		setFrameProperties();
	}
	
	void initialize() {
		current_Language = "";
		client_Frame = new JFrame();
		client_Menu_Bar = new JMenuBar();
		client_Menu = new JMenu();
		client_Server_Menu = new JMenu();
		client_Chat_Text_Field = new JTextField();
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
			client_Send_Button.setText(Language.getText("Button.Send"));
			current_Language = Language.getLanguage();
		}
	}
	
	void addComponents() {
		
	}
	
	void setFrameProperties() {
	
	}
	
	void updateChat() {
		Thread chat_Thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (status_Connected) {
					if (IRCHandler.recieveMessage().equals("Disconnect Cosmic-Bot")) {
						status_Connected = false;
						break;
					} else {
						client_Chat_Text_Field.append(IRCHandler.recieveMessage());
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

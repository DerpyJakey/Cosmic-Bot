package com.org.derpyjakey.frames;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.org.derpyjakey.utilities.Language;

public class ClientFrame {
	String current_Language;
	JFrame client_Frame;
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
		client_Chat_Text_Field = new JTextField();
		client_Input_Text_Field = new JTextField();
		client_Send_Button = new JButton();s
	}
	
	void updateLanguage() {
		client_Frame.setTitle(Language.getText("Frame.Client"));
		client_Send_Button.setText(Language.getText("Button.Send"));
	}
	
	void addComponents() {
	
	}
	
	void setFrameProperties() {
	
	}
}

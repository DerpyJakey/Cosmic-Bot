package com.org.derpyjakey.frames;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.org.derpyjakey.utilities.Language;

public class ClientFrame {
	String current_Language;
	JFrame client_Frame;
	
	public ClientFrame() {
		initialize();
		updateLanguage();
		addComponents();
		setFrameProperties();
	}
	
	void initialize() {
		current_Language = "";
		client_Frame = new JFrame();
	}
	
	void updateLanguage() {
	
	}
	
	void addComponents() {
	
	}
	
	void setFrameProperties() {
	
	}
}

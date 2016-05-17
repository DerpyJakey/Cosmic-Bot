package com.org.derpyjakey.frames;

import com.org.derpyjakey.utilities.IOHandler;
import com.org.derpyjakey.utilities.LogHandler;
import com.org.derpyjakey.references.Directories;

public class AccountFrame {
	String current_Language;
	JFrame account_Frame;
	JLabel account_Username_Label;
	JLabel account_Password_Label;
	JTextField account_Username_Input_Box;
	JPasswordField account_Password_Input_Box;
	JButton account_Save_Button;
	JButton account_Close_Button;
	JButton account_OAuth_Button;
	
	public AccountFrame() {
		initialize();
		updateLanguage();
		addComponents();
		setFrameProperties();
		account_Save_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (!account_Username_Input_Box.getText().equals(IOHandler.getValue(Directories.Files.ConfigurationFile, "Username"))) {
					IOHandler.setConfig(Directories.Files.ConfigurationFile, "Username", account_Username_Input_Box.getText());
				}
				String account_tmp_Password = new String(account_Password_Input_Box.getPassword());
				if (!account_tmp_Password.equals(IOHandler.getValue(Directories.Files.ConfigurationFile, "Password"))) {
					IOHandler.setConfig(Directories.Files.ConfigurationFile, "Password", account_tmp_Password);
				}
				dispose();
			}
		});
		account_Close_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				dispose();
			}
		});
		account_OAuth_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI("https://twitchapps.com/tmi/"));
				} catch (URISyntaxException URISE) {
					JOptionPane.showMessageDialog(null, Language.getText("Error.OAUTH"));
				} catch (IOException IOE) {
					LogHandler.Report(4, IOE);
				}
			}
		});
	}
	
	void initialize() {
		current_Language = "";
		account_Frame = new JFrame();
		account_Username_Label = new JLabel();
		account_Password_Label = new JLabel();
		account_Username_Input_Box = new JTextField();
		account_Password_Input_Box = new JPasswordField();
		account_Save_Button = new JButton();
		account_Close_Button = new JButton();
		account_OAuth_Button = new JButton();
	}
	
	void updateLanguage() {
		if (!current_Language.equals(Language.getLanguage())) {
			account_Frame.setTitle(Language.getText("Title.Account"));
			account_Username_Label.setText(Language.getText("Label.Username"));
			account_Password_Label.setText(Language.getText("Label.Password"));
			account_Save_Button.setText("Button.Save"));
			account_Close_Button.setText("Button.Close"));
			account_OAuth_Button.setText("Button.OAuth"));
			current_Language = Language.getLanguage();
		}
	}
	
	void addComponents() {
		JPanel account_Input_Panel = new JPanel(new GridLayout(2, 2));
		account_Input_Panel.add(account_Username_Label);
		account_Input_Panel.add(account_Username_Input_Box);
		account_Input_Panel.add(account_Password_Label);
		account_Input_Panel.add(account_Password_Input_Box);
		JPanel account_Button_Panel = new JPanel(new GridLayout(1, 3));
		account_Button_Panel.add(account_Save_Button);
		account_Button_Panel.add(account_Close_Button);
		account_Button_Panel.add(account_OAuth_Button);
		JPanel account_Frame_Panel = new JPanel(new BorderLayout());
		account_Frame_Panel.add(account_Input_Panel);
		account_Frame_Panel.add(account_Button_Panel, BorderLayout.SOUTH);
		account_Frame.getContentPane().add(account_Frame_Panel);
	}
	
	void setFrameProperties() {
		account_Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		account_Frame.setResizable(false);
		account_Frame.pack();
		account_Frame.setSize(account_Frame.getWidth(), account_Frame.getHeight() + 10);
	}
}

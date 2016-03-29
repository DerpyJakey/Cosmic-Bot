package frame;

import java.awt.*;
import java.net.URI;
import javax.swing.*;
import reference.Language;
import java.io.IOException;
import utilities.IOHandler;
import utilities.LogHandler;
import reference.Directories;
import java.awt.event.ActionEvent;
import java.net.URISyntaxException;
import java.awt.event.ActionListener;

public class AccountFrame extends JFrame {
	String current_Language = "";
	JLabel usernameLabel;
	JLabel passwordLabel;
	JTextField usernameInputBox;
	JPasswordField passwordInputBox;
	JButton saveBTN;
	JButton closeBTN;
	JButton oAuthBTN;

	public AccountFrame() {
		CreateUI();
		UpdateLanguage();
		saveBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (!usernameInputBox.getText().equals(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Username"))) {
					IOHandler.SetConfig(Directories.Files.ConfigurationFile, "Username", usernameInputBox.getText());
				}
				String passwordString = new String(passwordInputBox.getPassword());
				if (!passwordString.equals(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Password"))) {
					IOHandler.SetConfig(Directories.Files.ConfigurationFile, "Password", passwordString);
				}
				dispose();
			}
		});
		closeBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				dispose();
			}
		});
		oAuthBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				Desktop desktop = Desktop.getDesktop();
				try {
		  			desktop.browse(new URI("https://twitchapps.com/tmi/"));
				} catch (URISyntaxException URISE) {
					JOptionPane.showMessageDialog(null, Language.GetText("OAUTH_ERROR_MESSAGE"));
				} catch (IOException IOE) {
					LogHandler.Report(4, IOE);
				}
			}
		});
	}
  
	void CreateUI() {
		usernameLabel = new JLabel();
		usernameInputBox = new JTextField(20);
		passwordLabel = new JLabel();
		passwordInputBox = new JPasswordField(20);
		saveBTN = new JButton();
		closeBTN = new JButton();
		oAuthBTN = new JButton();
		JPanel usernamePanel = new JPanel(new BorderLayout());
		usernamePanel.add(usernameLabel, BorderLayout.WEST);
		usernamePanel.add(usernameInputBox, BorderLayout.CENTER);
		JPanel passwordPanel = new JPanel(new BorderLayout());
		passwordPanel.add(passwordLabel, BorderLayout.WEST);
		passwordPanel.add(passwordInputBox, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.add(saveBTN, BorderLayout.WEST);
		buttonPanel.add(closeBTN, BorderLayout.CENTER);
		buttonPanel.add(oAuthBTN, BorderLayout.EAST);
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(usernamePanel, BorderLayout.NORTH);
		mainPanel.add(passwordPanel);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		pack();
		setSize(getWidth() + 10, getHeight() + 20);
	}
  
	void UpdateLanguage() {
		setTitle(Language.GetText("Title_Account"));
		usernameLabel.setText(Language.GetText("Label_Username"));
		passwordLabel.setText(Language.GetText("Label_Password"));
		saveBTN.setText(Language.GetText("Button_Save"));
		closeBTN.setText(Language.GetText("Button_Close"));
		oAuthBTN.setText(Language.GetText("Button_OAuth"));
	}
}
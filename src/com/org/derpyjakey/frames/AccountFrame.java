package com.org.derpyjakey.frames;

import java.awt.*;
import java.net.URI;
import javax.swing.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.net.URISyntaxException;
import com.org.derpyjakey.utilities.Language;
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
	account_Save_Button.addActionListener((ActionEvent actionEvent) -> {
            if (!account_Username_Input_Box.getText().equals(IOHandler.getValue(Directories.Files.ConfigurationFile, "Username"))) {
                IOHandler.setValue(Directories.Files.ConfigurationFile, "Username", account_Username_Input_Box.getText());
            }
            String account_tmp_Password = new String(account_Password_Input_Box.getPassword());
            if (!account_tmp_Password.equals(IOHandler.getValue(Directories.Files.ConfigurationFile, "Password"))) {
                IOHandler.setValue(Directories.Files.ConfigurationFile, "Password", account_tmp_Password);
            }
            account_Frame.dispose();
        });
        account_Close_Button.addActionListener((ActionEvent actionEvent) -> {
            account_Frame.dispose();
        });
        account_OAuth_Button.addActionListener((ActionEvent actionEvent) -> {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI("https://twitchapps.com/tmi/"));
            } catch (URISyntaxException URISE) {
                JOptionPane.showMessageDialog(null, Language.getText("Error.OAUTH"));
            } catch (IOException IOE) {
                LogHandler.report(4, IOE);
            }
        });
    }

    private void initialize() {
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

    private void updateLanguage() {
        if (!current_Language.equals(Language.getLanguage())) {
            account_Frame.setTitle(Language.getText("Frame.Account"));
            account_Username_Label.setText(Language.getText("Label.Username"));
            account_Password_Label.setText(Language.getText("Label.Password"));
            account_Save_Button.setText(Language.getText("Button.Save"));
            account_Close_Button.setText(Language.getText("Button.Close"));
            account_OAuth_Button.setText(Language.getText("Button.OAuth"));
            current_Language = Language.getLanguage();
        }
    }

    private void addComponents() {
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

    private void setFrameProperties() {
        account_Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	account_Frame.setResizable(false);
        account_Frame.pack();
	account_Frame.setSize(account_Frame.getWidth(), account_Frame.getHeight() + 10);
        account_Frame.setVisible(true);
    }
}
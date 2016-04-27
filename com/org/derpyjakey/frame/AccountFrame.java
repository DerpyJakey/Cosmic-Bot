package com.org.derpyjakey.frame;

import com.org.derpyjakey.reference.Directories;
import com.org.derpyjakey.reference.Language;
import com.org.derpyjakey.utilities.IOHandler;
import com.org.derpyjakey.utilities.LogHandler;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

class AccountFrame extends JFrame {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameInputBox;
    private JPasswordField passwordInputBox;
    private JButton saveBTN;
    private JButton closeBTN;
    private JButton oAuthBTN;

    AccountFrame() {
        CreateUI();
        UpdateLanguage();
        saveBTN.addActionListener(actionEvent -> {
            if (!usernameInputBox.getText().equals(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Username"))) {
                IOHandler.SetConfig(Directories.Files.ConfigurationFile, "Username", usernameInputBox.getText());
            }
            String passwordString = new String(passwordInputBox.getPassword());
            if (!passwordString.equals(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Password"))) {
                IOHandler.SetConfig(Directories.Files.ConfigurationFile, "Password", passwordString);
            }
            dispose();
        });
        closeBTN.addActionListener(actionEvent -> dispose());
        oAuthBTN.addActionListener(actionEvent -> {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI("https://twitchapps.com/tmi/"));
            } catch (URISyntaxException URISE){
                JOptionPane.showMessageDialog(null, Language.GetText("OAUTH_ERROR_MESSAGE"));
            } catch (IOException ioe) {
                LogHandler.Report(4, ioe);
            }
        });
    }

    private void CreateUI() {
        usernameLabel = new JLabel();
        usernameInputBox = new JTextField(20);
        passwordLabel = new JLabel();
        passwordInputBox = new JPasswordField(20);
        saveBTN = new JButton();
        closeBTN = new JButton();
        oAuthBTN = new JButton();
        JPanel accountPanel = new JPanel(new GridLayout(2, 2));
        accountPanel.add(usernameLabel);
        accountPanel.add(usernameInputBox);
        accountPanel.add(passwordLabel);
        accountPanel.add(passwordInputBox);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(saveBTN);
        buttonPanel.add(closeBTN);
        buttonPanel.add(oAuthBTN);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(accountPanel);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        pack();
        setSize(getWidth() + 20, getHeight() + 10);
    }

    private void UpdateLanguage() {
        setTitle(Language.GetText("Title_Account"));
        usernameLabel.setText(Language.GetText("Label_Username"));
        passwordLabel.setText(Language.GetText("Label_Password"));
        saveBTN.setText(Language.GetText("Button_Save"));
        closeBTN.setText(Language.GetText("Button_Close"));
        oAuthBTN.setText(Language.GetText("Button_OAuth"));
        usernameInputBox.setText(IOHandler.GetValue(Directories.Files.ConfigurationFile, "Username"));
    }
}

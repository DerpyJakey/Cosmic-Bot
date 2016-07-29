package com.org.derpyjakey.frames;

import com.org.derpyjakey.references.Directories;
import com.org.derpyjakey.utilities.IOHandler;
import com.org.derpyjakey.utilities.LanguageHandler;
import com.org.derpyjakey.utilities.LogHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Account {
    private String activeLanguage = "";
    private JFrame frame = new JFrame();
    private JLabel usernameLabel = new JLabel();
    private JLabel passwordLabel = new JLabel();
    private JTextField usernameInputBox = new JTextField();
    private JPasswordField passwordInputBox = new JPasswordField();
    private JButton saveButton = new JButton();
    private JButton closeButton = new JButton();
    private JButton oAuthButton = new JButton();

    public Account() {
        updateLanguage();
        addComponents();
        setFrameProperties();
        saveButton.addActionListener((ActionEvent actionEvent) -> {
            if (!usernameInputBox.getText().equals(IOHandler.getValue(Directories.Files.configurationFile, "Username"))) {
                IOHandler.setKey(Directories.Files.configurationFile, "Username", usernameInputBox.getText());
            }
            String tmpPassword = new String(passwordInputBox.getPassword());
            if (!tmpPassword.equals(IOHandler.getValue(Directories.Files.configurationFile, "Password"))) {
                LogHandler.infoReport(tmpPassword);
                IOHandler.setKey(Directories.Files.configurationFile, "Password", tmpPassword);
            }
            frame.dispose();
        });
        closeButton.addActionListener((ActionEvent actionEvent) -> {
            frame.dispose();
        });
        oAuthButton.addActionListener((ActionEvent actionEvent) -> {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI("https://twitchapps.com/tmi/"));
            } catch (URISyntaxException URISE) {
                JOptionPane.showMessageDialog(null, LanguageHandler.getText("Error.OAuth"));
            } catch (IOException ioe) {
                LogHandler.warningReport(ioe.toString());
            }
        });
    }

    private void updateLanguage() {
        if (!activeLanguage.equals(LanguageHandler.getLanguage())) {
            frame.setTitle(LanguageHandler.getText("Frame.Account"));
            usernameLabel.setText(LanguageHandler.getText("Label.Username"));
            passwordLabel.setText(LanguageHandler.getText("Label.Password"));
            saveButton.setText(LanguageHandler.getText("Button.Save"));
            closeButton.setText(LanguageHandler.getText("Button.Close"));
            oAuthButton.setText(LanguageHandler.getText("Button.OAuth"));
            activeLanguage  = LanguageHandler.getLanguage();
        }
    }

    private void addComponents() {
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameInputBox);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordInputBox);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(saveButton);
        buttonPanel.add(closeButton);
        buttonPanel.add(oAuthButton);
        JPanel framePanel = new JPanel(new BorderLayout());
        framePanel.add(inputPanel);
        framePanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(framePanel);
    }

    private void setFrameProperties() {
        usernameInputBox.setText(IOHandler.getValue(Directories.Files.configurationFile, "Username"));
        passwordInputBox.setText(IOHandler.getValue(Directories.Files.configurationFile, "Password"));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setSize(frame.getWidth(), frame.getHeight() + 10);
        frame.setVisible(true);
    }
}

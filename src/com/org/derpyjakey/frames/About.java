package com.org.derpyjakey.frames;

import com.org.derpyjakey.references.Information;
import com.org.derpyjakey.utilities.LanguageHandler;
import com.org.derpyjakey.utilities.LogHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class About {
    private String activeLanguage = "";
    final private JFrame frame = new JFrame();
    final private JLabel developerLabel = new JLabel();
    final private JLabel githubLabel = new JLabel();
    final private JLabel twitchLabel = new JLabel();

    public About() {
        updateLanguage();
        addComponents();
        setFrameProperties();
        githubLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(Information.GithubLink));
                } catch (URISyntaxException | IOException ex) {
                    LogHandler.errorReport(ex.toString());
                }
            }
        });
        twitchLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                try {
                    Desktop.getDesktop().browse(new URI(Information.TwitchLink));
                } catch (URISyntaxException | IOException ex) {
                    LogHandler.errorReport(ex.toString());
                }
            }
        });
    }

    private void updateLanguage() {
        if (!activeLanguage.equals(LanguageHandler.getLanguage())) {
            frame.setTitle(LanguageHandler.getText("Frame.About"));
            developerLabel.setText(LanguageHandler.getText("Label.Developer") + ": " + Information.Developer);
            githubLabel.setText(LanguageHandler.getText("Label.Github"));
            twitchLabel.setText(LanguageHandler.getText("Label.Twitch"));
            activeLanguage = LanguageHandler.getLanguage();
        }
    }

    private void addComponents() {
        frame.add(developerLabel, BorderLayout.NORTH);
        frame.add(githubLabel, BorderLayout.CENTER);
        frame.add(twitchLabel, BorderLayout.SOUTH);
    }

    private void setFrameProperties() {
        developerLabel.setHorizontalAlignment(JLabel.CENTER);
        githubLabel.setHorizontalAlignment(JLabel.CENTER);
        twitchLabel.setHorizontalAlignment(JLabel.CENTER);
        githubLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        twitchLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setSize(frame.getWidth() + 20, frame.getHeight() + 10);
        frame.setVisible(true);
    }
}
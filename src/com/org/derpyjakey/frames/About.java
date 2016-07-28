package com.org.derpyjakey.frames;

import com.org.derpyjakey.references.Information;
import com.org.derpyjakey.utilities.Language;

import javax.swing.*;
import java.awt.*;

public class About {
    private String activeLanguage;
    private JFrame frame = new JFrame();
    private JLabel developerLabel = new JLabel();
    private JLabel githubLabel = new JLabel();
    private JLabel twitchLabel = new JLabel();

    public About() {
        updateLanguage();
        addComponents();
        setFrameProperties();
    }

    private void updateLanguage() {
        if (!activeLanguage.equals(Language.getLanguage())) {
            frame.setTitle(Language.getText("Frame.About"));
            developerLabel.setText(Language.getText("Label.Developer") + ": " + Information.Developer);
            githubLabel.setText(Language.getText("Label.Github"));
            twitchLabel.setText(Language.getText("Label.Twitch"));
            activeLanguage = Language.getLanguage();
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
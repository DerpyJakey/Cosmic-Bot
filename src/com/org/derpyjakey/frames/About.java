package com.org.derpyjakey.frames;

import com.org.derpyjakey.references.Information;
import com.org.derpyjakey.utilities.Language;

import javax.swing.*;
import java.awt.*;

public class About {
    private String active_Language = "";
    private JFrame frame = new JFrame();
    private JLabel developer_Label = new JLabel();
    private JLabel github_Label = new JLabel();
    private JLabel twitch_Label = new JLabel();

    public About() {
        updateLanguage();
        addComponents();
        setFrameProperties();
    }

    private void updateLanguage() {
        if (!active_Language.equals(Language.getLanguage())) {
            frame.setTitle(Language.getText("Frame.About"));
            developer_Label.setText(Language.getText("Label.Developer") + ": " + Information.Developer);
            github_Label.setText(Language.getText("Label.Github"));
            twitch_Label.setText(Language.getText("Label.Twitch"));
            active_Language = Language.getLanguage();
        }
    }

    private void addComponents() {
        frame.add(developer_Label, BorderLayout.NORTH);
        frame.add(github_Label, BorderLayout.CENTER);
        frame.add(twitch_Label, BorderLayout.SOUTH);
    }

    private void setFrameProperties() {
        developer_Label.setHorizontalAlignment(JLabel.CENTER);
        github_Label.setHorizontalAlignment(JLabel.CENTER);
        twitch_Label.setHorizontalAlignment(JLabel.CENTER);
        github_Label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        twitch_Label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setSize(frame.getWidth() + 20, frame.getHeight() + 10);
        frame.setVisible(true);
    }
}
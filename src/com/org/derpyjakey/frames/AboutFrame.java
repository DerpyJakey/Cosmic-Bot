package com.org.derpyjakey.frames;

import com.org.derpyjakey.references.BotInfo;
import com.org.derpyjakey.utilities.Language;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AboutFrame {
    String current_Language = "";
    JFrame about_Frame;
    JLabel about_Developer_Label;
    JLabel about_Github_Label;
    JLabel about_Twitch_Label;
    
    public AboutFrame() {
        initialize();
        updateLanguage();
        addComponents();
        setFrameProperties();
    }
    
    private void initialize() {
        about_Frame = new JFrame();
        about_Developer_Label = new JLabel();
        about_Github_Label = new JLabel();
        about_Twitch_Label = new JLabel();
    }
    
    private void updateLanguage() {
        if (!current_Language.equals(Language.getLanguage())) {
            about_Frame.setTitle(Language.getText("Frame.About"));
            about_Developer_Label.setText(Language.getText("Label.Developer") + ": " + BotInfo.Information.Developer);
            about_Github_Label.setText(Language.getText("Label.Github"));
            about_Twitch_Label.setText(Language.getText("Label.Twitch"));
            current_Language = Language.getLanguage();
        }
    }
    
    private void addComponents() {
        about_Frame.add(about_Developer_Label, BorderLayout.NORTH);
        about_Frame.add(about_Github_Label, BorderLayout.CENTER);
        about_Frame.add(about_Twitch_Label, BorderLayout.SOUTH);
        about_Developer_Label.setHorizontalAlignment(JLabel.CENTER);
        about_Github_Label.setHorizontalAlignment(JLabel.CENTER);
        about_Twitch_Label.setHorizontalAlignment(JLabel.CENTER);
    }
    
    private void setFrameProperties() {
        about_Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        about_Frame.setResizable(false);
        about_Frame.pack();
        about_Frame.setSize(about_Frame.getWidth() + 20, about_Frame.getHeight() + 10);
        about_Frame.setVisible(true);
    }
}

package com.org.derpyjakey.frames;

/**
 * Placeholder for Bot Setting Frame. (Will be added into Channel Frame)
 * To Enable, Disable, and Modify Bot settings.
 */
import com.org.derpyjakey.utilities.LanguageHandler;
 
import javax.swing.*;
 
public class Bot {
    private String activeLanguage = "";
    private JFrame frame = new JFrame();
    private JLabel commandKeyLabel = new JLabel();
    private JLabel enableBotFeatures = new JLabel();
    private JLabel enableBlacklistWordsLabel = new JLabel();
    private JLabel blackListWordsLabel = new JLabel();
    private JLabel enableCapLimitLabel = new JLabel();
    private JLabel capLimitLabel = new JLabel();
    private JLabel enableTextLimitLabel = new JLabel();
    private JLabel textLimitLabel = new JLabel();
    private JLabel enableWordLimitLabel = new JLabel();
    private JLabel wordLimitLabel = new JLabel();
    private JLabel enableSpamFeatureLabel = new JLabel();
    private JLabel timeFrameLabel = new JLabel();
    private JLabel messagePerTimeFrameLabel = new JLabel();
    private JCheckBox enableBotFeaturesCheckBox = new JCheckBox();
    private JCheckBox enableBlacklistWordsCheckBox = new JCheckBox();
    private JCheckBox enableCapLimitCheckBox = new JCheckBox();
    private JCheckBox enableTextLimitCheckBox = new JCheckBox();
    private JCheckBox enableWordLimitCheckBox = new JCheckBox();
    private JCheckBox enableSpamFeatureCheckBox = new JCheckBox();
    private JTextField commandKeyTextField = new JTextField();
    private JTextField blackListWordsTextField = new JTextField();
    private JTextField capLimitTextField = new JTextField();
    private JTextField textLimitTextField = new JTextField();
    private JTextField wordLimitTextField = new JTextField();
    private JTextField spamTimerTextField = new JTextField();
    private JTextField spamMessageTextField = new JTextField();
    private JButton saveButton = new JButton();
    private JButton closeButton = new JButton();
    
    public Bot() {
        updateLanguage();
        addComponents();
        setFrameProperties();
    }
    
    private void updateLanguage() {
        if (!activeLanguage.equals(LanguageHandler.getLanguage())) {
            frame.setTitle(LanguageHandler.getText("Frame.Bot"));
            commandKeyLabel.setText(LanguageHandler.getText("Label.CommandKey"));
            enableBotFeatures.setText(LanguageHandler.getText("Label.EnableBotFeatures"));
            enableBlacklistWordsLabel.setText(LanguageHandler.getText("Label.EnableBlacklistFeature"));
            blackListWordsLabel.setText(LanguageHandler.getText("Label.BlacklistWords"));
            enableCapLimitLabel.setText(LanguageHandler.getText("Label.EnableCapLimitFeature"));
            capLimitLabel.setText(LanguageHandler.getText("Label.CapLimit"));
            enableTextLimitLabel.setText(LanguageHandler.getText("Label.EnableTextLimitFeature"));
            wordLimitLabel.setText(LanguageHandler.getText("Label.TextLimit"));
            enableWordLimitLabel.setText(LanguageHandler.getText("Label.EnableWordLimitFeature"));
            wordLimitLabel.setText(LanguageHandler.getText("Label.WordLimit"));
            enableSpamFeatureLabel.setText(LanguageHandler.getText("Label.EnableSpamFeature"));
            timeFrameLabel.setText(LanguageHandler.getText("Label.SpamTimeFrame"));
            messagePerTimeFrameLabel.setText(LanguageHandler.getText("Label.MessagePerTimeFrame"));
            saveButton.setText(LanguageHandler.getText("Button.Save"));
            closeButton.setText(LanguageHandler.getText("Button.Close"));
        }
    }
    
    private void addComponents() {
    }
    
    private void setFrameProperties() {
    }
}

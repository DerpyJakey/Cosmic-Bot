package com.org.derpyjakey.frames;

/**
 * Placeholder for Bot Setting Frame. (Will be added into Channel Frame)
 * To Enable, Disable, and Modify Bot settings.
 */
import com.org.derpyjakey.utilities.LanguageHandler;
 
import javax.swing.*;
import java.awt.*;
 
public class Bot {
    private String activeLanguage = "";
    private JFrame frame = new JFrame();
    private JLabel commandKeyLabel = new JLabel();
    private JLabel enableBotFeatures = new JLabel();
    private JLabel enableBlacklistWordsLabel = new JLabel();
    private JLabel blackListWordsLabel = new JLabel();
    private JLabel enableCapLimitLabel = new JLabel();
    private JLabel capLimitLabel = new JLabel();
    private JLabel enableWordLimitLabel = new JLabel();
    private JLabel wordLimitLabel = new JLabel();
    private JLabel enableSpamFeatureLabel = new JLabel();
    private JLabel timeFrameLabel = new JLabel();
    private JLabel messagePerTimeFrameLabel = new JLabel();
    private JCheckBox enableBotFeaturesCheckBox = new JCheckBox();
    private JCheckBox enableBlacklistWordsCheckBox = new JCheckBox();
    private JCheckBox enableCapLimitCheckBox = new JCheckBox();
    private JCheckBox enableWordLimitCheckBox = new JCheckBox();
    private JCheckBox enableSpamFeatureCheckBox = new JCheckBox();
    private JTextField commandKeyTextField = new JTextField();
    private JTextField blackListWordsTextField = new JTextField();
    private JTextField capLimitTextField = new JTextField();
    private JTextField wordLimitTextField = new JTextField();
    private JTextField spamTimerTextField = new JTextField();
    private JTextField spamMessageTextField = new JTextField();
    private JButton saveButton = new JButton();
    private JButton closeButton = new JButton();
    
    public Bot {
        updateLanguage();
        addComponents();
        setFrameProperties();
    }
    
    private void updateLanguage() {
        if (!activeLanguage.equals(LanguageHandler.getLanguage()) {
        }
    }
    
    private void addComponents() {
    
    }
    
    private void setFrameProperties() {
    
    }
}

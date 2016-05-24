package com.org.derpyjakey.frames;

import com.org.derpyjakey.references.Directories;
import com.org.derpyjakey.utilities.DefaultConfig;
import com.org.derpyjakey.utilities.IOHandler;
import com.org.derpyjakey.utilities.Language;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChannelFrame {
    String current_Language;
    JFrame channel_Frame;
    JLabel channel_Label;
    JLabel channel_Enable_Label;
    JLabel channel_Permission_Label;
    JLabel channel_File_Directory_Label;
    JLabel channel_Selection_Label;
    JLabel channel_Mode_Label;
    JLabel channel_Available_Commands_Label;
    JLabel channel_Command_Label;
    JLabel channel_Message_Label;
    JTextField channel_Input_Box;
    JTextField channel_Command_Input;
    JTextField channel_Message_Input;
    JTextField channel_File_Directory_Input;
    JComboBox channel_Selection_Box;
    JComboBox channel_Mode_Combo_Box;
    JComboBox channel_Permission_Combo_Box;
    JComboBox channel_Enable_Box;
    JComboBox channel_Available_Commands_Combo_Box;
    JButton channel_Update_Button;
    JButton channel_Save_Button;
    JButton channel_Delete_Button;
    JButton channel_Close_Button;
    JPanel channel_Panel_Container_1;
    JPanel channel_Panel_Container_2;
    JPanel channel_Panel_Container_3;

    public ChannelFrame() {
        initialize();
	updateLanguage();
        addComponents();
        setFrameProperties();
        channel_Update_Button.addActionListener((ActionEvent actionEvent) -> {
            if (!channel_Input_Box.getText().isEmpty()) {
                channel_Selection_Box.removeAllItems();
                if (channel_Input_Box.getText().contains(", ")) {
                    String[] listChannels = channel_Input_Box.getText().toLowerCase().replace("#", "").split(", ");
                    for(String channel : listChannels) {
                        if (!IOHandler.checkDirectory(Directories.Folders.ChannelFolder.replace("%CHANNEL%", channel))) {
                            IOHandler.createDirectory(Directories.Folders.ChannelFolder.replace("%CHANNEL%", channel));
                            DefaultConfig.createDefaultChannelConfig(channel);
                            DefaultConfig.createDefaultCommandConfig(channel);
                        }
                        channel_Selection_Box.addItem(channel);
                    }
                }
                channel_Selection_Label.setVisible(true);
                channel_Selection_Box.setVisible(true);
            }
        });
        channel_Selection_Box.addActionListener((ActionEvent actionEvent) -> {
            if (!channel_Selection_Box.getSelectedItem().toString().isEmpty()) {
                channel_Mode_Combo_Box.setSelectedItem(Language.convertTextFromEnglish(Language.getLanguage(), "Option." + IOHandler.getValue(Directories.Files.ChannelFile.replace("%CHANNEL%", channel_Selection_Box.getSelectedItem().toString()), "Mode")));
                channel_Mode_Label.setVisible(true);
                channel_Mode_Combo_Box.setVisible(true);
            }
        });
        channel_Mode_Combo_Box.addActionListener((ActionEvent actionEvent) -> {
            if (channel_Mode_Combo_Box.getSelectedItem().toString().equals(Language.getText("Option.Bot"))) {
                channel_Panel_Container_2.setVisible(true);
                channel_Panel_Container_3.setVisible(true);
            } else {
                channel_Panel_Container_2.setVisible(false);
                channel_Panel_Container_3.setVisible(false);
            }
        });
    }

    private void initialize() {
        current_Language = "";
        channel_Frame = new JFrame();
        channel_Label = new JLabel();
        channel_Input_Box = new JTextField();
        channel_Update_Button = new JButton();
        channel_Selection_Label = new JLabel();
        channel_Selection_Box = new JComboBox();
        channel_Mode_Label = new JLabel();
        channel_Enable_Label = new JLabel();
        channel_Enable_Box = new JComboBox();
        channel_Mode_Combo_Box = new JComboBox();
        channel_Permission_Label = new JLabel();
        channel_Permission_Combo_Box = new JComboBox();
        channel_Available_Commands_Label = new JLabel();
        channel_Available_Commands_Combo_Box = new JComboBox();
        channel_Command_Label = new JLabel();
        channel_Command_Input = new JTextField();
        channel_Message_Label = new JLabel();
        channel_Message_Input = new JTextField();
        channel_File_Directory_Label = new JLabel();
        channel_File_Directory_Input = new JTextField();
        channel_Save_Button = new JButton();
        channel_Delete_Button = new JButton();
        channel_Close_Button = new JButton();
    }

    private void updateLanguage() {
        if (!current_Language.equals(Language.getLanguage())) {
            channel_Enable_Box.removeAllItems();
            channel_Enable_Box.addItem(Language.getText("Option.True"));
            channel_Enable_Box.addItem(Language.getText("Option.False"));
            channel_Mode_Combo_Box.removeAllItems();
            channel_Mode_Combo_Box.addItem(Language.getText("Option.Chat"));
            channel_Mode_Combo_Box.addItem(Language.getText("Option.Bot"));
            channel_Permission_Combo_Box.removeAllItems();
            channel_Permission_Combo_Box.addItem(Language.getText("Option.Moderator"));
            channel_Permission_Combo_Box.addItem(Language.getText("Option.Everyone"));
            channel_Frame.setTitle(Language.getText("Frame.Channel"));
            channel_Label.setText(Language.getText("Label.Channels"));
            channel_Update_Button.setText(Language.getText("Button.Update"));
            channel_Selection_Label.setText(Language.getText("Label.Channel.Selection"));
            channel_Mode_Label.setText(Language.getText("Label.Mode"));
            channel_Permission_Label.setText(Language.getText("Label.Permission"));
            channel_Available_Commands_Label.setText(Language.getText("Label.Available.Commands"));
            channel_Command_Label.setText(Language.getText("Label.Command"));
            channel_Message_Label.setText(Language.getText("Label.Message"));
            channel_File_Directory_Label.setText(Language.getText("Label.File.Directory"));
            channel_Save_Button.setText(Language.getText("Button.Save"));
            channel_Delete_Button.setText(Language.getText("Button.Delete"));
            channel_Close_Button.setText(Language.getText("Button.Close"));
            current_Language = Language.getLanguage();
        }
    }

    private void addComponents() {
        JPanel channel_Input_Panel = new JPanel(new BorderLayout());
        channel_Input_Panel.add(channel_Label, BorderLayout.WEST);
        channel_Input_Panel.add(channel_Input_Box, BorderLayout.CENTER);
        channel_Input_Panel.add(channel_Update_Button, BorderLayout.EAST);
        JPanel channel_Channel_Selection_Panel = new JPanel(new BorderLayout());
        channel_Channel_Selection_Panel.add(channel_Selection_Label, BorderLayout.WEST);
        channel_Channel_Selection_Panel.add(channel_Selection_Box, BorderLayout.EAST);
        JPanel channel_Mode_Panel = new JPanel();
        channel_Mode_Panel.add(channel_Mode_Label, BorderLayout.WEST);
        channel_Mode_Panel.add(channel_Mode_Combo_Box, BorderLayout.CENTER);
        JPanel channel_Available_Commands_Panel = new JPanel(new BorderLayout());
        channel_Available_Commands_Panel.add(channel_Available_Commands_Label, BorderLayout.WEST);
        channel_Available_Commands_Panel.add(channel_Available_Commands_Combo_Box, BorderLayout.CENTER);
        JPanel channel_Combo_Panel = new JPanel(new GridLayout(1, 4));
        channel_Combo_Panel.add(channel_Enable_Label);
        channel_Combo_Panel.add(channel_Enable_Box);
        channel_Combo_Panel.add(channel_Permission_Label);
        channel_Combo_Panel.add(channel_Permission_Combo_Box);
        JPanel channel_Command_Panel = new JPanel(new BorderLayout());
        channel_Command_Panel.add(channel_Command_Label, BorderLayout.WEST);
        channel_Command_Panel.add(channel_Command_Input, BorderLayout.CENTER);
        JPanel channel_Message_Panel = new JPanel(new BorderLayout());
        channel_Message_Panel.add(channel_Message_Label, BorderLayout.WEST);
        channel_Message_Panel.add(channel_Message_Input, BorderLayout.CENTER);
        JPanel channel_Directory_Panel = new JPanel(new BorderLayout());
        channel_Directory_Panel.add(channel_File_Directory_Label, BorderLayout.WEST);
        channel_Directory_Panel.add(channel_File_Directory_Input, BorderLayout.CENTER);
        JPanel channel_Button_Panel = new JPanel(new GridLayout(1, 3));
        channel_Button_Panel.add(channel_Save_Button);
        channel_Button_Panel.add(channel_Delete_Button);
        channel_Button_Panel.add(channel_Close_Button);
        channel_Panel_Container_1 = new JPanel(new BorderLayout());
        channel_Panel_Container_1.add(channel_Input_Panel, BorderLayout.NORTH);
        channel_Panel_Container_1.add(channel_Channel_Selection_Panel, BorderLayout.CENTER);
        channel_Panel_Container_1.add(channel_Mode_Panel, BorderLayout.SOUTH);
        channel_Panel_Container_2 = new JPanel(new BorderLayout());
        channel_Panel_Container_2.add(channel_Available_Commands_Panel, BorderLayout.NORTH);
        channel_Panel_Container_2.add(channel_Combo_Panel, BorderLayout.CENTER);
        channel_Panel_Container_2.add(channel_Command_Panel, BorderLayout.SOUTH);
        channel_Panel_Container_3 = new JPanel(new BorderLayout());
        channel_Panel_Container_3.add(channel_Message_Panel, BorderLayout.NORTH);
        channel_Panel_Container_3.add(channel_Directory_Panel, BorderLayout.CENTER);
        channel_Panel_Container_3.add(channel_Button_Panel, BorderLayout.SOUTH);
        JPanel channel_Frame_Panel = new JPanel(new BorderLayout());
        channel_Frame_Panel.add(channel_Panel_Container_1, BorderLayout.NORTH);
        channel_Frame_Panel.add(channel_Panel_Container_2, BorderLayout.CENTER);
        channel_Frame_Panel.add(channel_Panel_Container_3, BorderLayout.SOUTH);
        channel_Frame.getContentPane().add(channel_Frame_Panel);
    }

    private void setFrameProperties() {
        channel_Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        channel_Frame.pack();
        channel_Mode_Label.setVisible(false);
        channel_Mode_Combo_Box.setVisible(false);
        channel_Panel_Container_2.setVisible(false);
        channel_Panel_Container_3.setVisible(false);
        channel_Frame.setVisible(true);
    }
}

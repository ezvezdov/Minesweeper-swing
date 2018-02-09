package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_StatusBar extends JPanel{

    View_StatusBar(View window){
        this.setSize(2*32, 4*32);
        this.setLayout(new BorderLayout());

        JButton New_Game_Button = new JButton("New Game");
        New_Game_Button.setSize(32,64);
        New_Game_Button.setSize(2*32,2*32);
        New_Game_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.Restart();
            }
        });

        this.add(New_Game_Button);
    }
}
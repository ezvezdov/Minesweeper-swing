package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusBar extends JPanel{
    StatusBar(View window){
        this.setSize(32*16,32*2);
        JButton NGButton = new JButton("New Game");
        NGButton.setSize(32 * 5,32*2);
        this.add(NGButton);
        NGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.Restart();
            }
        });
    }
}

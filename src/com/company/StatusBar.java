package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StatusBar extends JPanel {


    int PANEL_LENGTH;

    JLabel Timer = new JLabel();
    JLabel Flags = new JLabel();

    StatusBar(View window, int SQUARE_PX, int BOMBS_COUNT) {
        this.PANEL_LENGTH = SQUARE_PX * 2;
        this.setBackground(Color.YELLOW);

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBounds(0, 0, SQUARE_PX * 16, SQUARE_PX * 2);

        JButton NGButton = new JButton("New Game");
        NGButton.setFont(NGButton.getFont().deriveFont(25f));
        //NGButton.setPreferredSize(new Dimension(SQUARE_PX * 5, SQUARE_PX * 2));
        NGButton.setBounds(0,0,SQUARE_PX*5,SQUARE_PX*2);
        //NGButton.setBorderPainted(false);
        NGButton.setFocusPainted(false);

        Flags.setText(Integer.toString(BOMBS_COUNT));
        Flags.setPreferredSize(new Dimension(SQUARE_PX * 5, SQUARE_PX * 2));
        Flags.setFont(Flags.getFont().deriveFont(40f));

        JLabel Timer = new JLabel("Time");

        Timer.setPreferredSize(new Dimension(SQUARE_PX * 5, SQUARE_PX * 2));
        Timer.setFont(Flags.getFont().deriveFont(40f));

        this.add(NGButton);
        this.add(Timer);
        this.add(Flags);

        NGButton.addActionListener(e -> window.Restart());
    }

    public void ChangeFlagsCounter(int flags) {
        Flags.setText(Integer.toString(flags));
    }

    public void setTimer(){
        final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    }

}

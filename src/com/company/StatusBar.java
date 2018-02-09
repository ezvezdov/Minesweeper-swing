package com.company;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {


    int PANEL_LENGTH;

    //JLabel Time = new JLabel();
    JLabel Flags = new JLabel();

    StatusBar(View window, int SQUARE_PX, int BOMBS_COUNT) {
        this.PANEL_LENGTH = SQUARE_PX * 2;

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBounds(0, 0, SQUARE_PX * 16, SQUARE_PX * 2);

        JButton NGButton = new JButton("New Game");
        NGButton.setFont(NGButton.getFont().deriveFont(25f));
        NGButton.setPreferredSize(new Dimension(SQUARE_PX * 5, SQUARE_PX * 2));

        Flags.setText(Integer.toString(BOMBS_COUNT));
        Flags.setPreferredSize(new Dimension(SQUARE_PX * 5, SQUARE_PX * 2));
        Flags.setFont(Flags.getFont().deriveFont(40f));

        //JLabel Time = new JLabel("Time");

        this.add(NGButton);
        //this.add(Time);
        this.add(Flags);

        NGButton.addActionListener(e -> window.Restart());
    }

    public void ChangeFlagsCounter(int flags) {
        Flags.setText(Integer.toString(flags));
    }

}

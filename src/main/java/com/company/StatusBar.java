package main.java.com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusBar extends JPanel {


    int PANEL_HEIGHT;

    JLabel Time;
    JLabel Flags = new JLabel();

    StatusBar(View window, int SQUARE_PX, int BOMBS_COUNT) {
        this.PANEL_HEIGHT = SQUARE_PX * 2;
        this.setBackground(Color.YELLOW);

        this.setLayout(null);
        this.setBounds(0, 0, SQUARE_PX * 16, PANEL_HEIGHT);

        JButton NGButton = new JButton("New Game");
        NGButton.setFont(NGButton.getFont().deriveFont(25f));
        NGButton.setBounds(0,0,SQUARE_PX*5,PANEL_HEIGHT);
        NGButton.setFocusPainted(false);

        Flags.setText(Integer.toString(BOMBS_COUNT));
        Flags.setBounds(SQUARE_PX*10,0,SQUARE_PX*5,PANEL_HEIGHT);
        Flags.setFont(Flags.getFont().deriveFont(40f));

        Time = new JLabel("00:00");
        Time.setBounds(SQUARE_PX*5,0,SQUARE_PX*5,PANEL_HEIGHT);
        Time.setFont(Flags.getFont().deriveFont(40f));

        this.add(NGButton);
        this.add(Time);
        this.add(Flags);

        NGButton.addActionListener(e -> window.Restart());
    }

    public void ChangeFlagsCounter(int flags) {
        Flags.setText(Integer.toString(flags));
    }

    Timer timer;
    public void setTimer(){
        ActionListener actionListener = new ActionListener() {
            int mins = 0;
            int secs = 0;
            String str_mins, str_secs;
            @Override
            public void actionPerformed(ActionEvent e) {
                secs++;

                if(secs == 60){
                    secs = 0;
                    mins++;
                }

                str_mins = Integer.toString(mins);
                if(mins < 10) str_mins = "0" + str_mins;
                str_secs = Integer.toString(secs);
                if(secs < 10) str_secs = "0" + str_secs;

                Time.setText(str_mins + ":" + str_secs);
            }
        };
        timer = new Timer(1000,actionListener);
        timer.setRepeats(true);
        timer.start();
    }
    public void StopTimer() {timer.stop();}

}

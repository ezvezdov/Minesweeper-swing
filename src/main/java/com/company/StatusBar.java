package main.java.com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusBar extends JPanel {


    int PANEL_HEIGHT;
    int BOADRD_WIDTH;

    JLabel Time;
    JLabel Flags = new JLabel();

    StatusBar(View window,int BOARD_WIDTH, int SQUARE_PX, int BOMBS_COUNT) {
        this.PANEL_HEIGHT = SQUARE_PX * 2;
        this.BOADRD_WIDTH = BOARD_WIDTH;

        this.setBackground(Color.YELLOW);

        this.setLayout(null);
        this.setBounds(0, 0, SQUARE_PX * BOARD_WIDTH, PANEL_HEIGHT);

        JButton NGButton = new JButton("New");
        NGButton.setBounds(0,0,(SQUARE_PX*BOADRD_WIDTH)/3,PANEL_HEIGHT);
        NGButton.setFont(NGButton.getFont().deriveFont(SQUARE_PX*BOADRD_WIDTH/3  / 3.5f));
        NGButton.setFocusPainted(false);

        Time = new JLabel("00:00");
        Time.setBounds((SQUARE_PX*BOADRD_WIDTH)/3,0,(SQUARE_PX*BOADRD_WIDTH)/3,PANEL_HEIGHT);
        Time.setFont(Flags.getFont().deriveFont(40f));

        Flags.setText(Integer.toString(BOMBS_COUNT));
        Flags.setBounds((SQUARE_PX*BOADRD_WIDTH)/3 * 2,0,(SQUARE_PX*BOADRD_WIDTH)/3,PANEL_HEIGHT);
        Flags.setFont(Flags.getFont().deriveFont(40f));


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

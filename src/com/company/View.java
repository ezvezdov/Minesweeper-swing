package com.company;

import javax.swing.*;

public class View extends JFrame {

    final private String WINDOW_TITLE = "Minesweeper";

    int WINDOW_LENGTH;
    int WINDOW_WIDTH;

    final private int SQUARE_PX = 32; //side length of icons (px)
    //  if you wont to change icons to 64x64 or 16x16, you should and change SQUARE_PX to 64 or 16;

    View(Fields fields) {

        AddNewComponents(fields);

        final Boolean RESIZABLE = false;
        final Boolean visible = true;

        //this.setLayout(new BorderLayout());
        this.setTitle(WINDOW_TITLE);
        this.setSize(WINDOW_WIDTH, WINDOW_LENGTH);
        this.setResizable(RESIZABLE);

        this.setVisible(visible);
    }

    private void AddNewComponents(Fields fields) {
        StatusBar statusBar = new StatusBar(this, SQUARE_PX, fields.BOMBS_COUNT);
        View_field game_field = new View_field(fields, statusBar, fields.BOMBS_COUNT, fields.FIELD_LENGTH, fields.FIELD_WIDTH, SQUARE_PX);
        this.WINDOW_LENGTH = game_field.PANEL_LENGTH + statusBar.PANEL_LENGTH;
        this.WINDOW_WIDTH = game_field.PANEL_WIDTH;


        this.add(game_field);//, BorderLayout.BEFORE_FIRST_LINE);
        this.add(statusBar);//,BorderLayout.AFTER_LAST_LINE);
    }

    public void Restart() {
        this.setVisible(false);
        Main.NewGame();
        Fields fields = new Fields();
        AddNewComponents(fields);
    }

}
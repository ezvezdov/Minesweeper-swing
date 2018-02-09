package com.company;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    final private String WINDOW_TITLE = "Minesweeper";

    int WINDOW_LENGTH;
    int WINDOW_WIDTH;

    View(Fields fields) {

        AddNewComponents(fields);

        final Boolean RESIZABLE = false;
        final Boolean visible= true;

        this.setLayout(new BorderLayout());
        this.setTitle(WINDOW_TITLE);
        this.setSize(WINDOW_WIDTH, WINDOW_LENGTH);
        this.setResizable(RESIZABLE);

        this.setVisible(visible);
    }

    private void AddNewComponents(Fields fields){
        View_field game_field = new View_field(fields, fields.BOMBS_COUNT, fields.FIELD_LENGTH, fields.FIELD_WIDTH);
        StatusBar StatusBar= new StatusBar(this);
        this.WINDOW_LENGTH = game_field.PANEL_LENGTH + 32*2;
        this.WINDOW_WIDTH = game_field.PANEL_WIDTH ;


        this.add(game_field, BorderLayout.BEFORE_FIRST_LINE);
        this.add(StatusBar,BorderLayout.AFTER_LAST_LINE);
    }

    public void Restart(){
        Fields fields = new Fields();
        AddNewComponents(fields);
    }

}
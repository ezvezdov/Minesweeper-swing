package com.company;

import javax.swing.*;

public class View {

    View(Fields fields) {
        /*
        Making window,graphics
         */
        View_field game_field = new View_field(fields, fields.BOMBS_COUNT, fields.FIELD_LENGTH, fields.FIELD_WIDTH);

        final int WINDOW_LENGTH = game_field.PANEL_LENGTH;
        final int WINDOW_WIDTH = game_field.PANEL_WIDTH;
        final String WINDOW_TITLE = "Minesweeper";
        final Boolean RESIZABLE = false;

        JFrame window = new JFrame(WINDOW_TITLE);
        window.setSize(WINDOW_WIDTH, WINDOW_LENGTH);
        window.setResizable(RESIZABLE);

        window.add(game_field);

        window.setVisible(true);
    }

}
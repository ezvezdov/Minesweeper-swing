package com.company;

import javax.swing.*;
import java.awt.*;

public class View {

    View(Fields fields) {

        View_field game_field = new View_field(fields, fields.BOMBS_COUNT,fields.FIELD_LENGTH,fields.FIELD_WIDTH);

        //game_field.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        game_field.setBackground(Color.red);

        final int WINDOW_LENGTH = game_field.PANEL_LENGTH;
        final int WINDOW_WIDTH = game_field.PANEL_WIDTH;
        final String WINDOW_TITLE = "Minesweeper";
        final Boolean RESIZABLE = false;

        JFrame window = new JFrame(WINDOW_TITLE);
        window.setSize(WINDOW_WIDTH, WINDOW_LENGTH);
        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(true);
        window.setResizable(RESIZABLE);

        window.add(game_field);

        window.setVisible(true);
    }

    public static void main(String args[]) {

        SwingUtilities.invokeLater(() -> new View(new Fields()));
    }

}
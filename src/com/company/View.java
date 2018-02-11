package com.company;

import javax.swing.*;

public class View extends JFrame {

    final private String WINDOW_TITLE = "Minesweeper";

    int WINDOW_LENGTH;
    int WINDOW_WIDTH;

    final private int SQUARE_PX = 32; //side length of icons (px)
    //  if you wont to change icons to 64x64 or 16x16, you should and change SQUARE_PX to 64 or 16;

    View(Board board) {
        AddNewComponents(board);

        final Boolean visible = true;

        this.setTitle(WINDOW_TITLE);
        this.setSize(WINDOW_WIDTH, WINDOW_LENGTH);
        this.setResizable(false);

        this.setVisible(visible);
    }

    StatusBar statusBar;
    View_field game_field;
    private void AddNewComponents(Board board) {
        statusBar = new StatusBar(this, SQUARE_PX, board.BOMBS_COUNT);
        game_field = new View_field(board,this,board.BOMBS_COUNT, board.FIELD_LENGTH, board.FIELD_WIDTH, SQUARE_PX);

        this.WINDOW_LENGTH = game_field.PANEL_LENGTH + statusBar.PANEL_LENGTH;
        this.WINDOW_WIDTH = game_field.PANEL_WIDTH;

        this.add(game_field);
        this.add(statusBar);
        revalidate();
        repaint();
    }

    public void Restart() {
        Board board = new Board();

        this.remove(statusBar);
        this.remove(game_field);

        AddNewComponents(board);
    }

    public void StatusBarUpdate(int flags){
        statusBar.ChangeFlagsCounter(flags);
    };

    public void setTimer(){

    }

}
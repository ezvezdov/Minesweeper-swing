package main.java.com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class View extends JFrame {

    final private String WINDOW_TITLE = "Minesweeper";

    int WINDOW_LENGTH;
    int WINDOW_WIDTH;

    final private int SQUARE_PX = 32; //side length of icons (px)
    //  if you wont to change icons to 64x64 or 16x16, you should and change SQUARE_PX to 64 or 16;

    Board board;

    View(Board board) {
        this.board = board;
        AddNewComponents(board);

        this.setTitle(WINDOW_TITLE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/images/other/icon2.jpg")));


        this.setSize(WINDOW_WIDTH, WINDOW_LENGTH);
        this.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (this.getWidth() / 2), middle.y - (this.getHeight() / 2));
        this.setLocation(newLocation);

        this.setVisible(true);
    }

    Logic logic;
    StatusBar statusBar;
    Field game_field;
    private void AddNewComponents(Board board) {
        logic = new Logic(board,this,board.BOARD_LENGTH,board.BOARD_WIDTH,SQUARE_PX);
        statusBar = new StatusBar(this,board.BOARD_WIDTH, SQUARE_PX, board.BOMBS_COUNT);
        game_field = new Field(board,this,board.BOARD_LENGTH, board.BOARD_WIDTH, SQUARE_PX);


        this.WINDOW_LENGTH = game_field.PANEL_LENGTH + statusBar.PANEL_HEIGHT;
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


    public void InitBoard(BufferedImage[][] closed_img_field){
        logic.InitClosed_img_field(board,closed_img_field);
    }
    public void BoardHandler(Board board,BufferedImage[][] closed_img_field,int MouseButton,int i, int j){
        logic.MouseButton(board,this,closed_img_field,MouseButton,i,j);
    }

    public void StatusBarUpdate(int flags){
        statusBar.ChangeFlagsCounter(flags);
    };
    public void BoardUpdate(){
        game_field.Update();
    }

    public void setTimer(){
        statusBar.setTimer();
    }

    public void StopTimer(){
        statusBar.StopTimer();
    }

}
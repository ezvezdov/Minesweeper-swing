package main.java.com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

class Field extends JPanel {
    private int SQUARE_PX;
    private boolean Game_over = false; //if Game_over == true, clicks will not work

    private BufferedImage[][] closed_img_field;

    private int BOARD_LENGTH, BOARD_WIDTH;
    int PANEL_LENGTH, PANEL_WIDTH;


    Field(Board board, View window,int BOARD_LENGTH, int BOARD_WIDTH, int SQUARE_PX) {
        //this.flags_count = BOMBS_COUNT;
        this.BOARD_LENGTH = BOARD_LENGTH;
        this.BOARD_WIDTH = BOARD_WIDTH;
        this.SQUARE_PX = SQUARE_PX;

        closed_img_field = new BufferedImage[BOARD_LENGTH][BOARD_WIDTH];
        window.InitBoard(closed_img_field);
        this.repaint();

        this.PANEL_LENGTH = SQUARE_PX * BOARD_LENGTH + 29; // I don't know, why without 29 and 6 not working
        this.PANEL_WIDTH = SQUARE_PX * BOARD_WIDTH + 6;

        this.setBounds(0, SQUARE_PX * 2, SQUARE_PX * board.BOARD_WIDTH, SQUARE_PX * board.BOARD_LENGTH);

        final boolean[] first_click = {false};
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!first_click[0]){
                    first_click[0] = true;
                    window.setTimer();
                }

                int x = e.getX();
                int y = e.getY();

                int j = x / SQUARE_PX;
                int i = y / SQUARE_PX;

                System.out.print("x = ");
                System.out.print(x);
                System.out.print(" ; y = ");
                System.out.print(y);
                System.out.println();
                System.out.print("j = ");
                System.out.print(j);
                System.out.print(" ; i = ");
                System.out.println(i);
                System.out.println();

                if (i >= BOARD_LENGTH || i >= BOARD_WIDTH) return;

                if (SwingUtilities.isLeftMouseButton(e)) {
                    window.BoardHandler(board,closed_img_field,0,i,j);
                }
                if (SwingUtilities.isRightMouseButton(e) && !Game_over) {
                    window.BoardHandler(board,closed_img_field,1,i,j);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    public void Update(){
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                g.drawImage(closed_img_field[i][j], j * SQUARE_PX, i * SQUARE_PX, this);
            }
        }
    }
}


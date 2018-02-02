package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class View_field extends JPanel {

    final private int SQUARE_PX = 32; //side length of a square (px)
    private boolean Game_over = false;

    /*
    if you wont to change icons to 64x64, you should change folder "32x32 images" to "64x64 images" and
    change SQUARE_PX to 64;
     */
    final private ImageIcon CLOSED_SQUARE_ICON = new ImageIcon("images/32x32 images/covered_square.png");
    final private ImageIcon BOMB_ICON = new ImageIcon("images/32x32 images/bomb.png");
    final private ImageIcon WRONG_BOMB_ICON = new ImageIcon("images/32x32 images/wrong_bomb.png");
    final private ImageIcon FLAG_ICON = new ImageIcon("images/32x32 images/flag.png");
    final private ImageIcon FAIL_BOMB_ICON = new ImageIcon("images/32x32 images/fail_bomb.png");
    final private ImageIcon ZERO_BOMBS_ICON = new ImageIcon("images/32x32 images/0_bombs.png");
    final private ImageIcon ONE_BOMBS_ICON = new ImageIcon("images/32x32 images/1_bombs.png");
    final private ImageIcon TWO_BOMBS_ICON = new ImageIcon("images/32x32 images/2_bombs.png");
    final private ImageIcon THREE_BOMBS_ICON = new ImageIcon("images/32x32 images/3_bombs.png");
    final private ImageIcon FOUR_BOMBS_ICON = new ImageIcon("images/32x32 images/4_bombs.png");
    final private ImageIcon FIVE_BOMBS_ICON = new ImageIcon("images/32x32 images/5_bombs.png");
    final private ImageIcon SIX_BOMBS_ICON = new ImageIcon("images/32x32 images/6_bombs.png");
    final private ImageIcon SEVEN_BOMBS_ICON = new ImageIcon("images/32x32 images/7_bombs.png");
    final private ImageIcon EIGHT_BOMBS_ICON = new ImageIcon("images/32x32 images/8_bombs.png");

    private int flags_count;
    int PANEL_LENGTH, PANEL_WIDTH;
    private int FIELD_LENGTH, FIELD_WIDTH;

    View_field(Fields fields, int BOMBS_COUNT,int FIELD_LENGTH, int FIELD_WIDTH) {
        this.flags_count = BOMBS_COUNT;
        this.FIELD_LENGTH = FIELD_LENGTH;
        this.FIELD_WIDTH = FIELD_WIDTH;

        JLabel[][] closed_img_field = new JLabel[FIELD_LENGTH][FIELD_WIDTH];
        InitClosed_img_field(closed_img_field);


        this.PANEL_LENGTH = SQUARE_PX * FIELD_LENGTH;
        this.PANEL_WIDTH = SQUARE_PX * FIELD_WIDTH;

        this.setSize(PANEL_WIDTH, PANEL_LENGTH);
        this.setLayout(new GridLayout(FIELD_LENGTH, FIELD_WIDTH));
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x, y;
                x = e.getX();
                y = e.getY();
                int i, j;
                j = Math.round(x) / SQUARE_PX;
                i = Math.round(y) / SQUARE_PX;

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

                if (SwingUtilities.isLeftMouseButton(e)) {
                    LeftMouseButtonHandler(i, j,closed_img_field, fields);
                }
                if (SwingUtilities.isRightMouseButton(e) && !Game_over) {
                    RightMouseButtonHandler(i, j, closed_img_field, fields);
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

    private void InitClosed_img_field(JLabel[][] closed_img_field) {
        for (int i = 0; i < FIELD_LENGTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                closed_img_field[i][j] = new JLabel(CLOSED_SQUARE_ICON);
                this.add(closed_img_field[i][j]);
            }
        }
    }

    private void SetIcons(int i, int j, JLabel[][] closed_img_field, boolean IsZeroSetIcon, boolean RightMouseButton, Fields fields) {
        if (fields.closed_txt_field_is_FLAG(i, j) && RightMouseButton) {
            closed_img_field[i][j].setIcon(CLOSED_SQUARE_ICON);
            fields.closed_txt_field_set_CLOSED_SQUARE(i, j);
        } else if (fields.closed_txt_field_is_CLOSED_SQUARE(i, j) && RightMouseButton) {
            closed_img_field[i][j].setIcon(FLAG_ICON);
            fields.closed_txt_field_set_FLAG(i, j);
        }
        if (fields.opened_txt_field_is_WRONG_BOMB(i, j)) {
            closed_img_field[i][j].setIcon(WRONG_BOMB_ICON);
            //fields.closed_txt_field_set_ZERO_BOMBS(i,j);
        }
        if (IsZeroSetIcon && !RightMouseButton) {
            closed_img_field[i][j].setIcon(ZERO_BOMBS_ICON);
            fields.closed_txt_field_set_ZERO_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_ZERO_BOMBS(i, j) && !RightMouseButton) {
            OpenZeroBlocks(i, j, closed_img_field, fields);
        } else if (fields.opened_txt_field_is_ONE_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j].setIcon(ONE_BOMBS_ICON);
            fields.closed_txt_field_set_ONE_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_TWO_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j].setIcon(TWO_BOMBS_ICON);
            fields.closed_txt_field_set_TWO_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_THREE_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j].setIcon(THREE_BOMBS_ICON);
            fields.closed_txt_field_set_THREE_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_FOUR_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j].setIcon(FOUR_BOMBS_ICON);
            fields.closed_txt_field_set_FOUR_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_FIVE_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j].setIcon(FIVE_BOMBS_ICON);
            fields.closed_txt_field_set_FIVE_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_SIX_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j].setIcon(SIX_BOMBS_ICON);
            fields.closed_txt_field_set_SIX_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_SEVEN_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j].setIcon(SEVEN_BOMBS_ICON);
            fields.closed_txt_field_set_SEVEN_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_EIGHT_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j].setIcon(EIGHT_BOMBS_ICON);
            fields.closed_txt_field_set_EIGHT_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_BOMB(i, j) && !RightMouseButton) {
            closed_img_field[i][j].setIcon(BOMB_ICON);
        } else if (fields.opened_txt_field_is_FAIL_BOMB(i, j) && !RightMouseButton) {
            closed_img_field[i][j].setIcon(FAIL_BOMB_ICON);
        }
    }

    private void OpenZeroBlocks(int i, int j, JLabel[][] closed_img_field, Fields fields) {
        /*
        If you clicked at block, where 0 bomb around. It's fill algorithm.
         */
        if (i == -1 || i == FIELD_LENGTH || j == -1 || j == FIELD_WIDTH || fields.closed_txt_field_is_FLAG(i, j))
            return;
        if (!fields.opened_txt_field_is_ZERO_BOMBS(i, j)) {
            SetIcons(i, j, closed_img_field, false, false, fields);
        }
        if (!fields.opened_txt_field_is_ZERO_BOMBS(i, j) || fields.closed_txt_field_is_ZERO_BOMBS(i, j)) return;
        if (fields.opened_txt_field_is_ZERO_BOMBS(i, j)) {
            SetIcons(i, j, closed_img_field, true, false, fields);
        }
        OpenZeroBlocks(i, j - 1, closed_img_field, fields);
        OpenZeroBlocks(i, j + 1, closed_img_field, fields);
        OpenZeroBlocks(i - 1, j, closed_img_field, fields);
        OpenZeroBlocks(i + 1, j, closed_img_field, fields);
        OpenZeroBlocks(i + 1, j - 1, closed_img_field, fields);
        OpenZeroBlocks(i + 1, j + 1, closed_img_field, fields);
        OpenZeroBlocks(i - 1, j - 1, closed_img_field, fields);
        OpenZeroBlocks(i - 1, j + 1, closed_img_field, fields);
        return;
    }
    private void OpenBlocksAround(int i, int j, JLabel[][] closed_img_field, Fields fields) {
        /*
        if you will click at number(which shows the number of bombs around),
        and flags_count around number == number,
        blocks around number will open. If flags will not
        install at bombs, you will lose.
         */
        int flags_around = fields.opened_txt_field_get_information(i,j);

        int kmax = i+1 < FIELD_LENGTH ? 2:1;
        int lmax = j+1 < FIELD_LENGTH ? 2:1;

        //kmax, lmax, k and l are calculated to prevent ArrayIndexOutOfBoundsException.

        for(int k = i-1 >= 0 ? -1:0; k < kmax; k++){
            for(int l = j-1 >= 0 ? -1:0; l < lmax;l++){
                if (k != 0 || l != 0) {
                    if(fields.closed_txt_field_is_FLAG(i + k,j + l)) flags_around--;
                }
            }
        }

        if(flags_around <= 0){ // if flags_around < 0, the user made a mistake
            for(int k = i-1 >= 0 ? -1:0; k < kmax; k++){
                for(int l = j-1 >= 0 ? -1:0; l < lmax;l++){
                    if(k != 0 || l != 0){
                        if(! fields.closed_txt_field_is_FLAG(i + k,j + l)){
                            if (fields.opened_txt_field_is_BOMB(i+k, j+l) && !Game_over) {
                                fields.opened_txt_field_set_FAIL_BOMB(i+k, j+l);
                                CheckWrongBombs(fields);
                                OpenAllBlocks(closed_img_field, fields);
                            }
                            SetIcons(i+k,j+l,closed_img_field,false,false,fields);
                        }
                    }
                }
            }
        }
    }
    private void OpenAllBlocks(JLabel[][] closed_img_field, Fields fields) {
        /*
        Open all blocks if you lose
         */
        Game_over = true;
        for (int i = 0; i < FIELD_LENGTH; i++) {
            for (int j = 0; j < FIELD_LENGTH; j++) {
                if (fields.opened_txt_field_is_ZERO_BOMBS(i, j)) {
                    SetIcons(i, j, closed_img_field, true, false, fields);
                } else SetIcons(i, j, closed_img_field, false, false, fields);

            }
        }
    }

    private void CheckWrongBombs(Fields fields) {
        for (int i = 0; i < FIELD_LENGTH; i++) {
            for (int j = 0; j < FIELD_LENGTH; j++) {
                if (fields.closed_txt_field_is_FLAG(i, j)) {
                    if (!fields.opened_txt_field_is_BOMB(i, j)) {
                        fields.opened_txt_field_set_WRONG_BOMB(i, j);
                    }
                }
            }
        }
    }

    private void RightMouseButtonHandler(int i, int j, JLabel[][] closed_img_field, Fields fields){
        boolean RightMouseButton = true;

        if (!fields.closed_txt_field_is_FLAG(i, j) && fields.closed_txt_field_is_CLOSED_SQUARE(i, j) && flags_count != 0) {
            SetIcons(i, j, closed_img_field, false, RightMouseButton, fields);
            flags_count--;
        } else if (fields.closed_txt_field_is_FLAG(i, j)) {
            SetIcons(i, j, closed_img_field, false, RightMouseButton, fields);
            flags_count++;
        }
    }
    private void LeftMouseButtonHandler(int i, int j, JLabel[][] closed_img_field, Fields fields) {
        boolean RightMouseButton = false;

        if (fields.closed_txt_field_is_CLOSED_SQUARE(i, j)) {
            if (fields.opened_txt_field_is_BOMB(i, j) && !Game_over) {
                fields.opened_txt_field_set_FAIL_BOMB(i, j);
                CheckWrongBombs(fields);
                OpenAllBlocks(closed_img_field, fields);
            }
            else SetIcons(i, j, closed_img_field, false, RightMouseButton, fields);
        } else if (!fields.closed_txt_field_is_CLOSED_SQUARE(i, j) && !fields.closed_txt_field_is_FLAG(i, j)) {
            OpenBlocksAround(i, j, closed_img_field, fields);
        }
    }
}

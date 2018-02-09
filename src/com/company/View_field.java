package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class View_field extends JPanel {
    private int SQUARE_PX;
    private boolean Game_over = false; //if Game_over == true, clicks will not work
    final private int IconsCount = 14;

    private BufferedImage[][] closed_img_field;

    private BufferedImage[] Icons = new BufferedImage[IconsCount];

    final private int CLOSED_SQUARE_ICON = 0;
    final private int BOMB_ICON = 1;
    final private int WRONG_BOMB_ICON = 2;
    final private int FLAG_ICON = 3;
    final private int FAIL_BOMB_ICON = 4;
    final private int ZERO_BOMBS_ICON = 5;
    final private int ONE_BOMBS_ICON = 6;
    final private int TWO_BOMBS_ICON = 7;
    final private int THREE_BOMBS_ICON = 8;
    final private int FOUR_BOMBS_ICON = 9;
    final private int FIVE_BOMBS_ICON = 10;
    final private int SIX_BOMBS_ICON = 11;
    final private int SEVEN_BOMBS_ICON = 12;
    final private int EIGHT_BOMBS_ICON = 13;

    private void IconsInit() {
        String FileNamePart = "res/images/" + Integer.toString(SQUARE_PX) + "x" + Integer.toString(SQUARE_PX) + " images/";

        String[] ImageName = {
                "covered_square.png", "bomb.png", "wrong_bomb.png", "flag.png", "fail_bomb.png",
                "0_bombs.png", "1_bombs.png", "2_bombs.png", "3_bombs.png", "4_bombs.png", "5_bombs.png",
                "6_bombs.png", "7_bombs.png", "8_bombs.png"
        };

        try {
            for (int i = 0; i < IconsCount; i++) {
                Icons[i] = ImageIO.read(new File(FileNamePart + ImageName[i]));
            }
        } catch (IOException ex) {
            System.out.println("Images not found, please reinstall app! Thx!");
            JLabel err_inf = new JLabel("Images not found, please reinstall app! Thx!");
            this.add(err_inf);
        }
    }

    private int flags_count;
    private int FIELD_LENGTH, FIELD_WIDTH;
    int PANEL_LENGTH, PANEL_WIDTH;


    View_field(Fields fields, StatusBar statusBar, int BOMBS_COUNT, int FIELD_LENGTH, int FIELD_WIDTH, int SQUARE_PX) {
        this.flags_count = BOMBS_COUNT;
        this.FIELD_LENGTH = FIELD_LENGTH;
        this.FIELD_WIDTH = FIELD_WIDTH;
        this.SQUARE_PX = SQUARE_PX;

        IconsInit();

        closed_img_field = new BufferedImage[FIELD_LENGTH][FIELD_WIDTH];
        InitClosed_img_field(fields);


        this.PANEL_LENGTH = SQUARE_PX * FIELD_LENGTH + 29; // I don't know, why without 29 and 6 not working
        this.PANEL_WIDTH = SQUARE_PX * FIELD_WIDTH + 6;


        System.out.print(PANEL_LENGTH);
        System.out.print(" ");
        System.out.print(PANEL_WIDTH);


        this.setBounds(0, SQUARE_PX * 2, SQUARE_PX * fields.FIELD_WIDTH, SQUARE_PX * fields.FIELD_LENGTH);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                int j = Math.round(x) / SQUARE_PX;
                int i = Math.round(y) / SQUARE_PX;

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

                if (i >= FIELD_LENGTH || i >= FIELD_WIDTH) return;

                if (SwingUtilities.isLeftMouseButton(e)) {
                    LeftMouseButtonHandler(i, j, fields);
                }
                if (SwingUtilities.isRightMouseButton(e) && !Game_over) {
                    RightMouseButtonHandler(i, j, fields, statusBar);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

    }

    private void InitClosed_img_field(Fields fields) {
        for (int i = 0; i < FIELD_LENGTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                SetIcons(i, j, false, false, true, fields);
            }
        }
    }

    private void SetIcons(int i, int j, boolean IsZeroSetIcon, boolean RightMouseButton, boolean InitClosed_img_field, Fields fields) {
        if (InitClosed_img_field) {
            closed_img_field[i][j] = Icons[CLOSED_SQUARE_ICON];
        } else if (fields.closed_txt_field_is_FLAG(i, j) && RightMouseButton) {
            closed_img_field[i][j] = Icons[CLOSED_SQUARE_ICON];
            fields.closed_txt_field_set_CLOSED_SQUARE(i, j);
        } else if (fields.closed_txt_field_is_CLOSED_SQUARE(i, j) && RightMouseButton) {
            closed_img_field[i][j] = Icons[FLAG_ICON];
            fields.closed_txt_field_set_FLAG(i, j);
        } else if (fields.opened_txt_field_is_WRONG_BOMB(i, j)) {
            closed_img_field[i][j] = Icons[WRONG_BOMB_ICON];
        } else if (IsZeroSetIcon && !RightMouseButton) {
            closed_img_field[i][j] = Icons[ZERO_BOMBS_ICON];
            fields.closed_txt_field_set_ZERO_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_ZERO_BOMBS(i, j) && !RightMouseButton) {
            OpenZeroBlocks(i, j, fields);
        } else if (fields.opened_txt_field_is_ONE_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[ONE_BOMBS_ICON];
            fields.closed_txt_field_set_ONE_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_TWO_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[TWO_BOMBS_ICON];
            fields.closed_txt_field_set_TWO_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_THREE_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[THREE_BOMBS_ICON];
            fields.closed_txt_field_set_THREE_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_FOUR_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[FOUR_BOMBS_ICON];
            fields.closed_txt_field_set_FOUR_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_FIVE_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[FIVE_BOMBS_ICON];
            fields.closed_txt_field_set_FIVE_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_SIX_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[SIX_BOMBS_ICON];
            fields.closed_txt_field_set_SIX_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_SEVEN_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[SEVEN_BOMBS_ICON];
            fields.closed_txt_field_set_SEVEN_BOMBS(i, j);
        } else if (fields.opened_txt_field_is_EIGHT_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[EIGHT_BOMBS_ICON];
            fields.closed_txt_field_set_EIGHT_BOMBS(i, j);
        } else if (fields.closed_txt_field_is_FLAG(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[FLAG_ICON];
        } else if (fields.opened_txt_field_is_BOMB(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[BOMB_ICON];
        } else if (fields.opened_txt_field_is_FAIL_BOMB(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[FAIL_BOMB_ICON];
        }
        this.repaint();
    }

    private void OpenZeroBlocks(int i, int j, Fields fields) {
        /*
        If you clicked at block, where 0 bomb around. It's fill algorithm.
         */
        if (i == -1 || i == FIELD_LENGTH || j == -1 || j == FIELD_WIDTH || fields.closed_txt_field_is_FLAG(i, j))
            return;
        if (!fields.opened_txt_field_is_ZERO_BOMBS(i, j)) {
            SetIcons(i, j, false, false, false, fields);
        }
        if (!fields.opened_txt_field_is_ZERO_BOMBS(i, j) || fields.closed_txt_field_is_ZERO_BOMBS(i, j)) return;
        if (fields.opened_txt_field_is_ZERO_BOMBS(i, j)) {
            SetIcons(i, j, true, false, false, fields);
        }
        OpenZeroBlocks(i, j - 1, fields);
        OpenZeroBlocks(i, j + 1, fields);
        OpenZeroBlocks(i - 1, j, fields);
        OpenZeroBlocks(i + 1, j, fields);
        OpenZeroBlocks(i + 1, j - 1, fields);
        OpenZeroBlocks(i + 1, j + 1, fields);
        OpenZeroBlocks(i - 1, j - 1, fields);
        OpenZeroBlocks(i - 1, j + 1, fields);
        return;
    }

    private void OpenBlocksAround(int i, int j, Fields fields) {
        /*
        if you will click at number(which shows the number of bombs around),
        and flags_count around number == number,
        blocks around number will open. If flags will not
        install at bombs, you will lose.
         */
        int flags_around = fields.opened_txt_field_get_information(i, j);

        int kmax = i + 1 < FIELD_LENGTH ? 2 : 1;
        int lmax = j + 1 < FIELD_LENGTH ? 2 : 1;

        //kmax, lmax, k and l are calculated to prevent ArrayIndexOutOfBoundsException.

        for (int k = i - 1 >= 0 ? -1 : 0; k < kmax; k++) {
            for (int l = j - 1 >= 0 ? -1 : 0; l < lmax; l++) {
                if (k != 0 || l != 0) {
                    if (fields.closed_txt_field_is_FLAG(i + k, j + l)) flags_around--;
                }
            }
        }

        if (flags_around <= 0) { // if flags_around < 0, the user made a mistake
            for (int k = i - 1 >= 0 ? -1 : 0; k < kmax; k++) {
                for (int l = j - 1 >= 0 ? -1 : 0; l < lmax; l++) {
                    if (k != 0 || l != 0) {
                        if (!fields.closed_txt_field_is_FLAG(i + k, j + l)) {
                            if (fields.opened_txt_field_is_BOMB(i + k, j + l) && !Game_over) {
                                fields.opened_txt_field_set_FAIL_BOMB(i + k, j + l);
                                CheckWrongBombs(fields);
                                OpenAllBlocks(fields);
                            }
                            SetIcons(i + k, j + l, false, false, false, fields);
                        }
                    }
                }
            }
        }
    }

    private void OpenAllBlocks(Fields fields) {
        /*
        Open all blocks if you lose
         */
        Game_over = true;
        for (int i = 0; i < FIELD_LENGTH; i++) {
            for (int j = 0; j < FIELD_LENGTH; j++) {
                if (fields.opened_txt_field_is_ZERO_BOMBS(i, j)) {
                    SetIcons(i, j, true, false, false, fields);
                } else SetIcons(i, j, false, false, false, fields);

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

    private void RightMouseButtonHandler(int i, int j, Fields fields, StatusBar statusBar) {
        if (!fields.closed_txt_field_is_FLAG(i, j) && fields.closed_txt_field_is_CLOSED_SQUARE(i, j) && flags_count != 0) {
            SetIcons(i, j, false, true, false, fields);
            flags_count--;
        } else if (fields.closed_txt_field_is_FLAG(i, j)) {
            SetIcons(i, j, false, true, false, fields);
            flags_count++;
        } else return;
        statusBar.ChangeFlagsCounter(flags_count);

    }

    private void LeftMouseButtonHandler(int i, int j, Fields fields) {
        if (fields.closed_txt_field_is_CLOSED_SQUARE(i, j)) {
            if (fields.opened_txt_field_is_BOMB(i, j) && !Game_over) {
                fields.opened_txt_field_set_FAIL_BOMB(i, j);
                CheckWrongBombs(fields);
                OpenAllBlocks(fields);
            } else SetIcons(i, j, false, false, false, fields);
        } else if (!fields.closed_txt_field_is_CLOSED_SQUARE(i, j) && !fields.closed_txt_field_is_FLAG(i, j)) {
            OpenBlocksAround(i, j, fields);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < FIELD_LENGTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                g.drawImage(closed_img_field[i][j], j * SQUARE_PX, i * SQUARE_PX, this);
            }
        }
    }
}


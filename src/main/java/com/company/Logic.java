package main.java.com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Logic {


    private int SQUARE_PX;
    private boolean Game_over = false; //if Game_over == true, clicks will not work
    final private int IconsCount = 14;


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
        String FileNamePart = "/images/" + Integer.toString(SQUARE_PX) + "x" + Integer.toString(SQUARE_PX) + " images/";

        String[] ImageName = {
                "covered_square.png", "bomb.png", "wrong_bomb.png", "flag.png", "fail_bomb.png",
                "0_bombs.png", "1_bombs.png", "2_bombs.png", "3_bombs.png", "4_bombs.png", "5_bombs.png",
                "6_bombs.png", "7_bombs.png", "8_bombs.png"
        };

        try {
            for (int i = 0; i < IconsCount; i++) {
                Icons[i] = ImageIO.read(Logic.class.getResourceAsStream(FileNamePart + ImageName[i]));
                //Icons[i] = ImageIO.read(new File(FileNamePart + ImageName[i]));
            }
        } catch (IOException ex) {
            System.out.println("Images not found, please reinstall app! Thx!");
        }
    }

    private int flags_count;

    private int BOARD_LENGTH;
    private int BOARD_WIDTH;

    private View window;

    Logic(Board board,View window,int BOARD_LENGTH, int BOARD_WIDTH, int SQUARE_PX){
        this.BOARD_LENGTH = BOARD_LENGTH;
        this.BOARD_WIDTH = BOARD_WIDTH;
        this.SQUARE_PX = SQUARE_PX;
        this.flags_count = board.BOMBS_COUNT;

        this.window = window;

        IconsInit();
    }

    public void InitClosed_img_field(Board board,BufferedImage[][] closed_img_field) {
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                SetIcons(board,closed_img_field,i, j, false, false, true);
            }
        }
    }

    private void SetIcons(Board board,BufferedImage[][] closed_img_field,int i, int j, boolean IsZeroSetIcon, boolean RightMouseButton, boolean InitClosed_img_field) {
        if (InitClosed_img_field) {
            closed_img_field[i][j] = Icons[CLOSED_SQUARE_ICON];
            return;
        } else if (board.ClosedBoard_is_FLAG(i, j) && RightMouseButton) {
            closed_img_field[i][j] = Icons[CLOSED_SQUARE_ICON];
            board.ClosedBoard_set_CLOSED_SQUARE(i, j);
        } else if (board.ClosedBoard_is_CLOSED_SQUARE(i, j) && RightMouseButton) {
            closed_img_field[i][j] = Icons[FLAG_ICON];
            board.ClosedBoard_set_FLAG(i, j);
        } else if (board.OpenedBoard_is_WRONG_BOMB(i, j)) {
            closed_img_field[i][j] = Icons[WRONG_BOMB_ICON];
        } else if (IsZeroSetIcon && !RightMouseButton) {
            closed_img_field[i][j] = Icons[ZERO_BOMBS_ICON];
            board.ClosedBoard_set_ZERO_BOMBS(i, j);
        } else if (board.OpenedBoard_is_ZERO_BOMBS(i, j) && !RightMouseButton) {
            OpenZeroBlocks(board,closed_img_field,i, j);
        } else if (board.OpenedBoard_is_ONE_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[ONE_BOMBS_ICON];
            board.ClosedBoard_set_ONE_BOMBS(i, j);
        } else if (board.OpenedBoard_is_TWO_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[TWO_BOMBS_ICON];
            board.ClosedBoard_set_TWO_BOMBS(i, j);
        } else if (board.OpenedBoard_is_THREE_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[THREE_BOMBS_ICON];
            board.ClosedBoard_set_THREE_BOMBS(i, j);
        } else if (board.OpenedBoard_is_FOUR_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[FOUR_BOMBS_ICON];
            board.ClosedBoard_set_FOUR_BOMBS(i, j);
        } else if (board.OpenedBoard_is_FIVE_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[FIVE_BOMBS_ICON];
            board.ClosedBoard_set_FIVE_BOMBS(i, j);
        } else if (board.OpenedBoard_is_SIX_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[SIX_BOMBS_ICON];
            board.ClosedBoard_set_SIX_BOMBS(i, j);
        } else if (board.OpenedBoard_is_SEVEN_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[SEVEN_BOMBS_ICON];
            board.ClosedBoard_set_SEVEN_BOMBS(i, j);
        } else if (board.OpenedBoard_is_EIGHT_BOMBS(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[EIGHT_BOMBS_ICON];
            board.ClosedBoard_set_EIGHT_BOMBS(i, j);
        } else if (board.ClosedBoard_is_FLAG(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[FLAG_ICON];
        } else if (board.OpenedBoard_is_BOMB(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[BOMB_ICON];
        } else if (board.OpenedBoard_is_FAIL_BOMB(i, j) && !RightMouseButton) {
            closed_img_field[i][j] = Icons[FAIL_BOMB_ICON];
        }
        window.BoardUpdate();
    }

    private void OpenZeroBlocks( Board board,BufferedImage[][] closed_img_field,int i, int j) {
    /*
    If you clicked at block, where 0 bomb around. It's fill algorithm.
     */
        if (i == -1 || i == BOARD_LENGTH || j == -1 || j == BOARD_WIDTH || board.ClosedBoard_is_FLAG(i, j))
            return;
        if (!board.OpenedBoard_is_ZERO_BOMBS(i, j)) {
            SetIcons(board,closed_img_field,i, j, false, false, false);
        }
        if (!board.OpenedBoard_is_ZERO_BOMBS(i, j) || board.ClosedBoard_is_ZERO_BOMBS(i, j)) return;
        if (board.OpenedBoard_is_ZERO_BOMBS(i, j)) {
            SetIcons(board,closed_img_field,i, j, true, false, false);
        }
        OpenZeroBlocks(board,closed_img_field,i, j - 1);
        OpenZeroBlocks(board,closed_img_field,i, j + 1);
        OpenZeroBlocks(board,closed_img_field,i - 1, j);
        OpenZeroBlocks(board,closed_img_field,i + 1, j);
        OpenZeroBlocks(board,closed_img_field,i + 1, j - 1);
        OpenZeroBlocks(board,closed_img_field,i + 1, j + 1);
        OpenZeroBlocks(board,closed_img_field,i - 1, j - 1);
        OpenZeroBlocks(board,closed_img_field,i - 1, j + 1);
        return;
    }
    private void OpenBlocksAround(Board board,BufferedImage[][] closed_img_field,int i, int j) {
    /*
    if you will click at number(which shows the number of bombs around),
    and flags_count around number == number,
    blocks around number will open. If flags will not
    install at bombs, you will lose.
     */
        int flags_around = board.getOpenedBoardInformation(i, j);

        int kmax = i + 1 < BOARD_LENGTH ? 2 : 1;
        int lmax = j + 1 < BOARD_LENGTH ? 2 : 1;

        //kmax, lmax, k and l are calculated to prevent ArrayIndexOutOfBoundsException.

        for (int k = i - 1 >= 0 ? -1 : 0; k < kmax; k++) {
            for (int l = j - 1 >= 0 ? -1 : 0; l < lmax; l++) {
                if (k != 0 || l != 0) {
                    if (board.ClosedBoard_is_FLAG(i + k, j + l)) flags_around--;
                }
            }
        }

        if (flags_around <= 0) { // if flags_around < 0, the user made a mistake
            for (int k = i - 1 >= 0 ? -1 : 0; k < kmax; k++) {
                for (int l = j - 1 >= 0 ? -1 : 0; l < lmax; l++) {
                    if (k != 0 || l != 0) {
                        if (!board.ClosedBoard_is_FLAG(i + k, j + l)) {
                            if (board.OpenedBoard_is_BOMB(i + k, j + l) && !Game_over) {
                                board.OpenedBoard_set_FAIL_BOMB(i + k, j + l);
                                CheckWrongBombs(board);
                                OpenAllBlocks(board,closed_img_field);
                            }
                            SetIcons(board,closed_img_field,i + k, j + l, false, false, false);
                        }
                    }
                }
            }
        }
    }
    private void OpenAllBlocks(Board board,BufferedImage[][] closed_img_field) {
    /*
    Open all blocks if you lose
     */
        Game_over = true;
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                if (board.OpenedBoard_is_ZERO_BOMBS(i, j)) {
                    SetIcons(board,closed_img_field,i, j, true, false, false);
                } else SetIcons(board,closed_img_field,i, j, false, false, false);

            }
        }
    }
    private void CheckWrongBombs(Board board) {
        window.StopTimer();
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                if (board.ClosedBoard_is_FLAG(i, j)) {
                    if (!board.OpenedBoard_is_BOMB(i, j)) {
                        board.OpenedBoard_set_WRONG_BOMB(i, j);
                    }
                }
            }
        }
    }

    public void MouseButton(Board board,View window,BufferedImage[][] closed_img_field,int MouseButton,int i, int j){
        if(!Game_over){
            if(MouseButton == 0){
                LeftMouseButtonHandler(board,closed_img_field,i,j);
            }
            else  if(MouseButton == 1){
                RightMouseButtonHandler(board,window,closed_img_field,i,j);
            }
        }
    }

    private void RightMouseButtonHandler(Board board,View window,BufferedImage[][] closed_img_field,int i, int j) {
        if (!board.ClosedBoard_is_FLAG(i, j) && board.ClosedBoard_is_CLOSED_SQUARE(i, j) && flags_count != 0) {
            SetIcons(board,closed_img_field,i, j, false, true, false);
            flags_count--;
        } else if (board.ClosedBoard_is_FLAG(i, j)) {
            SetIcons(board,closed_img_field,i, j, false, true, false);
            flags_count++;
        } else return;
        window.StatusBarUpdate(flags_count);

    }
    private void LeftMouseButtonHandler(Board board,BufferedImage[][] closed_img_field,int i, int j) {
        if (board.ClosedBoard_is_CLOSED_SQUARE(i, j)) {
            if (board.OpenedBoard_is_BOMB(i, j) && !Game_over) {
                board.OpenedBoard_set_FAIL_BOMB(i, j);
                CheckWrongBombs(board);
                OpenAllBlocks(board,closed_img_field);
            } else SetIcons(board,closed_img_field,i, j, false, false, false);
        } else if (!board.ClosedBoard_is_CLOSED_SQUARE(i, j) && !board.ClosedBoard_is_FLAG(i, j)) {
            OpenBlocksAround(board,closed_img_field,i, j);
        }
    }

}


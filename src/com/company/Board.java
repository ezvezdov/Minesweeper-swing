package com.company;

import java.util.Random;

class Board {

    final static int FIELD_LENGTH = 16;
    final static int FIELD_WIDTH = 16;
    final static int BOMBS_COUNT = (int) ((FIELD_LENGTH * FIELD_WIDTH) / 6.4);
    /*
    6.4(at default)(which was used at BOMBS_COUNT) is BombCoefficient,
    which help to find BOMBS_COUNT (at 16*16 field should be 40 bombs => 6.4 = 256/40).
    If you want to change the count of bombs , you should change BombCoefficient(6.4 at default).
    The more the BombCoefficient, the more bombs.
     */

    // FIELD_LENGTH = i; FIELD_WIDTH = j;

    enum BoardSymbols {CLOSED_SQUARE,BOMB,WRONG_BOMB,FLAG,FAIL_BOMB,ZERO_BOMBS,ONE_BOMBS,TWO_BOMBS,THREE_BOMBS,FOUR_BOMBS,FIVE_BOMBS,SIX_BOMBS,SEVEN_BOMBS,EIGHT_BOMBS};
    private BoardSymbols[][] OpenedBoard = new BoardSymbols[FIELD_LENGTH][FIELD_WIDTH];
    private BoardSymbols[][] ClosedBoard = new BoardSymbols[FIELD_LENGTH][FIELD_WIDTH];

    Board() {
        /*
        Generate all fields.
         */
        init_OpenedBoard(OpenedBoard);
        init_ClosedBoard(ClosedBoard);
    }

    private void init_OpenedBoard(BoardSymbols[][] board) {
        /*
        Initialization OpenedBoard.
         */
        clear_OpenedBoard(board);
        OpenedBoard_gen(board);
        OpenedBoard_numbers_gen(board);

    }
    private void clear_OpenedBoard(BoardSymbols[][] board) {
        /*
        Clear OpenedBoard XD...
         */
        for (int i = 0; i < FIELD_LENGTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                board[i][j] = BoardSymbols.ZERO_BOMBS;
            }
        }
    }
    private void OpenedBoard_gen(BoardSymbols[][] board) {
        /*
        Random bomb generation at OpenedBoard.
         */
        Random random = new Random();
        int i1, i2;
        for (int i = BOMBS_COUNT; i > 0; i--) {
            i1 = random.nextInt(FIELD_LENGTH);
            i2 = random.nextInt(FIELD_WIDTH);
            if (board[i1][i2] == BoardSymbols.BOMB) {
                while (board[i1][i2] == BoardSymbols.BOMB) {
                    i1 = random.nextInt(FIELD_LENGTH);
                    i2 = random.nextInt(FIELD_WIDTH);
                }
            }
            board[i1][i2] = BoardSymbols.BOMB;
        }
    }
    private void OpenedBoard_numbers_gen(BoardSymbols[][] board) {
        /*
        Generation number of bombs around
         */
        int ctn;
        for (int i = 0; i < FIELD_LENGTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                ctn = 0;
                if (OpenedBoard_is_ZERO_BOMBS(i,j)) {
                    if (i - 1 >= 0) {

                        if (j - 1 >= 0 && board[i-1][j-1] == BoardSymbols.BOMB) ctn += 1;
                        if (board[i-1][j] == BoardSymbols.BOMB) ctn += 1;
                        if (j + 1 < FIELD_WIDTH && board[i-1][j+1] == BoardSymbols.BOMB) ctn += 1;
                    }
                    if (i + 1 < FIELD_LENGTH) {
                        if (j - 1 >= 0 && board[i+1][j-1] == BoardSymbols.BOMB) ctn += 1;
                        if (board[i+1][j] == BoardSymbols.BOMB) ctn += 1;
                        if (j + 1 < FIELD_WIDTH && board[i+1][j+1] == BoardSymbols.BOMB) ctn += 1;
                    }
                    if (j - 1 >= 0 && board[i][j-1] == BoardSymbols.BOMB) ctn += 1;
                    if (j + 1 < FIELD_WIDTH && board[i][j+1] == BoardSymbols.BOMB) ctn += 1;

                    if (ctn == 0) board[i][j] = BoardSymbols.ZERO_BOMBS;
                    else if(ctn == 1)  board[i][j] = BoardSymbols.ONE_BOMBS;
                    else if(ctn == 2)  board[i][j] = BoardSymbols.TWO_BOMBS;
                    else if(ctn == 3)  board[i][j] = BoardSymbols.THREE_BOMBS;
                    else if(ctn == 4)  board[i][j] = BoardSymbols.FOUR_BOMBS;
                    else if(ctn == 5)  board[i][j] = BoardSymbols.FIVE_BOMBS;
                    else if(ctn == 6)  board[i][j] = BoardSymbols.SIX_BOMBS;
                    else if(ctn == 7)  board[i][j] = BoardSymbols.SEVEN_BOMBS;
                    else if(ctn == 8)  board[i][j] = BoardSymbols.EIGHT_BOMBS;
                }
            }
        }
    }

    private void init_ClosedBoard(BoardSymbols[][] board) {
        /*
       Initialization ClosedBoard.
         */
        for (int i = 0; i < FIELD_LENGTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                board[i][j] = BoardSymbols.CLOSED_SQUARE;
            }
        }
    }


    boolean OpenedBoard_is_BOMB(int i, int j) {
        return OpenedBoard[i][j] == BoardSymbols.BOMB;
    }

    boolean OpenedBoard_is_FAIL_BOMB(int i, int j) {
        return OpenedBoard[i][j] == BoardSymbols.FAIL_BOMB;
    }

    boolean OpenedBoard_is_WRONG_BOMB(int i, int j) {
        return OpenedBoard[i][j] == BoardSymbols.WRONG_BOMB;
    }

    boolean OpenedBoard_is_ZERO_BOMBS(int i, int j) {
        return OpenedBoard[i][j] == BoardSymbols.ZERO_BOMBS;
    }

    boolean OpenedBoard_is_ONE_BOMBS(int i, int j) {
        return OpenedBoard[i][j] == BoardSymbols.ONE_BOMBS;
    }

    boolean OpenedBoard_is_TWO_BOMBS(int i, int j) {
        return OpenedBoard[i][j] == BoardSymbols.TWO_BOMBS;
    }

    boolean OpenedBoard_is_THREE_BOMBS(int i, int j) {
        return OpenedBoard[i][j] == BoardSymbols.THREE_BOMBS;
    }

    boolean OpenedBoard_is_FOUR_BOMBS(int i, int j) {
        return OpenedBoard[i][j] == BoardSymbols.FOUR_BOMBS;
    }

    boolean OpenedBoard_is_FIVE_BOMBS(int i, int j) {
        return OpenedBoard[i][j] == BoardSymbols.FIVE_BOMBS;
    }

    boolean OpenedBoard_is_SIX_BOMBS(int i, int j) {
        return OpenedBoard[i][j] == BoardSymbols.SIX_BOMBS;
    }

    boolean OpenedBoard_is_SEVEN_BOMBS(int i, int j) {
        return OpenedBoard[i][j] == BoardSymbols.SEVEN_BOMBS;
    }

    boolean OpenedBoard_is_EIGHT_BOMBS(int i, int j) {
        return OpenedBoard[i][j] == BoardSymbols.EIGHT_BOMBS;
    }

    boolean ClosedBoard_is_ZERO_BOMBS(int i, int j) {
        return ClosedBoard[i][j] == BoardSymbols.ZERO_BOMBS;
    }

    boolean ClosedBoard_is_CLOSED_SQUARE(int i, int j) {
        return ClosedBoard[i][j] == BoardSymbols.CLOSED_SQUARE;
    }

    boolean ClosedBoard_is_FLAG(int i, int j) {
        return ClosedBoard[i][j] == BoardSymbols.FLAG;
    }

    void OpenedBoard_set_FAIL_BOMB(int i, int j) {
        OpenedBoard[i][j] = BoardSymbols.FAIL_BOMB;
    }

    void OpenedBoard_set_WRONG_BOMB(int i, int j) {
        OpenedBoard[i][j] = BoardSymbols.WRONG_BOMB;
    }

    void ClosedBoard_set_CLOSED_SQUARE(int i, int j) {
        ClosedBoard[i][j] = BoardSymbols.CLOSED_SQUARE;
    }

    void ClosedBoard_set_FLAG(int i, int j) {
        ClosedBoard[i][j] = BoardSymbols.FLAG;
    }

    void ClosedBoard_set_ZERO_BOMBS(int i, int j) {
        ClosedBoard[i][j] = BoardSymbols.ZERO_BOMBS;
    }

    void ClosedBoard_set_ONE_BOMBS(int i, int j) {
        ClosedBoard[i][j] = BoardSymbols.ONE_BOMBS;
    }

    void ClosedBoard_set_TWO_BOMBS(int i, int j) {
        ClosedBoard[i][j] = BoardSymbols.TWO_BOMBS;
    }

    void ClosedBoard_set_THREE_BOMBS(int i, int j) {
        ClosedBoard[i][j] = BoardSymbols.THREE_BOMBS;
    }

    void ClosedBoard_set_FOUR_BOMBS(int i, int j) {
        ClosedBoard[i][j] = BoardSymbols.FOUR_BOMBS;
    }

    void ClosedBoard_set_FIVE_BOMBS(int i, int j) {
        ClosedBoard[i][j] = BoardSymbols.FIVE_BOMBS;
    }

    void ClosedBoard_set_SIX_BOMBS(int i, int j) {
        ClosedBoard[i][j] = BoardSymbols.SIX_BOMBS;
    }

    void ClosedBoard_set_SEVEN_BOMBS(int i, int j) {
        ClosedBoard[i][j] = BoardSymbols.SEVEN_BOMBS;
    }

    void ClosedBoard_set_EIGHT_BOMBS(int i, int j) {
        ClosedBoard[i][j] = BoardSymbols.EIGHT_BOMBS;
    }

    int getOpenedBoardInformation(int i, int j) {
        if(OpenedBoard_is_ONE_BOMBS(i,j)) return 1;
        if(OpenedBoard_is_TWO_BOMBS(i,j)) return 2;
        if(OpenedBoard_is_THREE_BOMBS(i,j)) return 3;
        if(OpenedBoard_is_FOUR_BOMBS(i,j)) return 4;
        if(OpenedBoard_is_FIVE_BOMBS(i,j)) return 5;
        if(OpenedBoard_is_SIX_BOMBS(i,j)) return 6;
        if(OpenedBoard_is_SEVEN_BOMBS(i,j)) return 7;
        if(OpenedBoard_is_EIGHT_BOMBS(i,j)) return 8;
        return 0;
    }
}
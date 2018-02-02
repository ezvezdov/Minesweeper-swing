package com.company;

import java.util.Random;

class Fields {

    final static int FIELD_LENGTH = 16;
    final static int FIELD_WIDTH = 16;
    final static int BOMBS_COUNT = (int) ((FIELD_LENGTH * FIELD_WIDTH) / 6.4); //6.4 is const which help to find BOMBS_COUNT (at 16*16 field should be 40 bombs => 6.4 = 256/40)

    private char[][] opened_txt_field = new char[FIELD_LENGTH][FIELD_WIDTH];
    private char[][] closed_txt_field = new char[FIELD_LENGTH][FIELD_WIDTH];

    // FIELD_LENGTH = i; FIELD_WIDTH = j;

    final private char CLOSED_SQUARE_TXT = 'c'; //closed
    final private char BOMB_TXT = 'b'; // bomb
    final private char WRONG_BOMB_TXT = 'w'; //wrong
    final private char FLAG_TXT = 'f'; //flag
    final private char FAIL_BOMB_TXT = 'l'; //lose
    final private char ZERO_BOMBS_TXT = '0';
    final private char ONE_BOMBS_TXT = '1';
    final private char TWO_BOMBS_TXT = '2';
    final private char THREE_BOMBS_TXT = '3';
    final private char FOUR_BOMBS_TXT = '4';
    final private char FIVE_BOMBS_TXT = '5';
    final private char SIX_BOMBS_TXT = '6';
    final private char SEVEN_BOMBS_TXT = '7';
    final private char EIGHT_BOMBS_TXT = '8';

     Fields() {
        /*
        Generate all arrays.
         */
        init_opened_txt_field(opened_txt_field);
        init_closed_txt_field(closed_txt_field);
        print_opened_txt_field();
    }

    private void clear_opened_txt_field(char[][] field) {
        /*
        Clear opened_txt_field XD...
         */
        for (int i = 0; i < FIELD_LENGTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                field[i][j] = ZERO_BOMBS_TXT;
            }
        }
    }
    private void opened_txt_field_gen(char field[][]) {
        /*
        Random bomb generation at opened_txt_field.
         */
        Random random = new Random();
        int i1, i2;
        for (int i = BOMBS_COUNT; i > 0; i--) {
            i1 = random.nextInt(FIELD_LENGTH);
            i2 = random.nextInt(FIELD_WIDTH);
            if (field[i1][i2] == BOMB_TXT) {
                while (field[i1][i2] == BOMB_TXT) {
                    i1 = random.nextInt(FIELD_LENGTH);
                    i2 = random.nextInt(FIELD_WIDTH);
                }
            }
            field[i1][i2] = BOMB_TXT;
        }
    }
    private void opened_txt_field_numbers_gen(char field[][]) {
        /*
        Generation number of bombs around
         */
        int ctn;
        for (int i = 0; i < FIELD_LENGTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                ctn = 0;
                if (field[i][j] == ZERO_BOMBS_TXT) {
                    if (i - 1 >= 0) {

                        if (j - 1 >= 0 && field[i - 1][j - 1] == BOMB_TXT) ctn += 1;
                        if (field[i - 1][j] == BOMB_TXT) ctn += 1;
                        if (j + 1 < FIELD_WIDTH && field[i - 1][j + 1] == BOMB_TXT) ctn += 1;
                    }
                    if (i + 1 < FIELD_LENGTH) {
                        if (j - 1 >= 0 && field[i + 1][j - 1] == BOMB_TXT) ctn += 1;
                        if (field[i + 1][j] == BOMB_TXT) ctn += 1;
                        if (j + 1 < FIELD_WIDTH && field[i + 1][j + 1] == BOMB_TXT) ctn += 1;
                    }
                    if (j - 1 >= 0 && field[i][j - 1] == BOMB_TXT) ctn += 1;
                    if (j + 1 < FIELD_WIDTH && field[i][j + 1] == BOMB_TXT) ctn += 1;
                    if (ctn == 0) field[i][j] = ZERO_BOMBS_TXT;
                    else field[i][j] = (char) ('0' + ctn);
                }
            }
        }
    }
    private void print_opened_txt_field() {
        System.out.println(BOMBS_COUNT);
        for (int i = 0; i < FIELD_LENGTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                System.out.print(opened_txt_field[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private void init_opened_txt_field(char[][] field) {
        /*
        Initialization opened_txt_field.
         */
        clear_opened_txt_field(field);
        opened_txt_field_gen(field);
        opened_txt_field_numbers_gen(field);

    }

    private void init_closed_txt_field(char[][] field) {
        /*
       Initialization closed_txt_field.
         */
        for (int i = 0; i < FIELD_LENGTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                field[i][j] = CLOSED_SQUARE_TXT;
            }
        }
    }


    boolean opened_txt_field_is_BOMB(int i, int j) {
        return opened_txt_field[i][j] == BOMB_TXT;
    }
    boolean opened_txt_field_is_FAIL_BOMB(int i, int j) {
        return opened_txt_field[i][j] == FAIL_BOMB_TXT;
    }
    boolean opened_txt_field_is_WRONG_BOMB(int i, int j) {
        return opened_txt_field[i][j] == WRONG_BOMB_TXT;
    }
    boolean opened_txt_field_is_ZERO_BOMBS(int i, int j) {
        return opened_txt_field[i][j] == ZERO_BOMBS_TXT;
    }
    boolean opened_txt_field_is_ONE_BOMBS(int i, int j) {
        return opened_txt_field[i][j] == ONE_BOMBS_TXT;
    }
    boolean opened_txt_field_is_TWO_BOMBS(int i, int j) {
        return opened_txt_field[i][j] == TWO_BOMBS_TXT;
    }
    boolean opened_txt_field_is_THREE_BOMBS(int i, int j) {
        return opened_txt_field[i][j] == THREE_BOMBS_TXT;
    }
    boolean opened_txt_field_is_FOUR_BOMBS(int i, int j) {
        return opened_txt_field[i][j] == FOUR_BOMBS_TXT;
    }
    boolean opened_txt_field_is_FIVE_BOMBS(int i, int j) {
        return opened_txt_field[i][j] == FIVE_BOMBS_TXT;
    }
    boolean opened_txt_field_is_SIX_BOMBS(int i, int j) {
        return opened_txt_field[i][j] == SIX_BOMBS_TXT;
    }
    boolean opened_txt_field_is_SEVEN_BOMBS(int i, int j) {
        return opened_txt_field[i][j] == SEVEN_BOMBS_TXT;
    }
    boolean opened_txt_field_is_EIGHT_BOMBS(int i, int j) {
        return opened_txt_field[i][j] == EIGHT_BOMBS_TXT;
    }

    boolean closed_txt_field_is_ZERO_BOMBS(int i, int j) {
        return closed_txt_field[i][j] == ZERO_BOMBS_TXT;
    }
    boolean closed_txt_field_is_CLOSED_SQUARE(int i, int j) {
       return closed_txt_field[i][j] == CLOSED_SQUARE_TXT;
    }
    boolean closed_txt_field_is_FLAG(int i, int j) {
        return closed_txt_field[i][j] == FLAG_TXT;
    }

    void opened_txt_field_set_FAIL_BOMB(int i, int j) {
        opened_txt_field[i][j] = FAIL_BOMB_TXT;
    }
    void opened_txt_field_set_WRONG_BOMB(int i, int j) {
        opened_txt_field[i][j] = WRONG_BOMB_TXT;
    }

    void closed_txt_field_set_CLOSED_SQUARE(int i, int j) {
        closed_txt_field[i][j] = CLOSED_SQUARE_TXT;
    }

    void closed_txt_field_set_FLAG(int i, int j) {
        closed_txt_field[i][j] = FLAG_TXT;
    }
    void closed_txt_field_set_ZERO_BOMBS(int i, int j) {
        closed_txt_field[i][j] = ZERO_BOMBS_TXT;
    }
    void closed_txt_field_set_ONE_BOMBS(int i, int j) {
        closed_txt_field[i][j] = ONE_BOMBS_TXT;
    }
    void closed_txt_field_set_TWO_BOMBS(int i, int j) {
        closed_txt_field[i][j] = TWO_BOMBS_TXT;
    }
    void closed_txt_field_set_THREE_BOMBS(int i, int j) {
        closed_txt_field[i][j] = THREE_BOMBS_TXT;
    }
    void closed_txt_field_set_FOUR_BOMBS(int i, int j) {
        closed_txt_field[i][j] = FOUR_BOMBS_TXT;
    }
    void closed_txt_field_set_FIVE_BOMBS(int i, int j) {
        closed_txt_field[i][j] = FIVE_BOMBS_TXT;
    }
    void closed_txt_field_set_SIX_BOMBS(int i, int j) {
        closed_txt_field[i][j] = SIX_BOMBS_TXT;
    }
    void closed_txt_field_set_SEVEN_BOMBS(int i, int j) {
        closed_txt_field[i][j] = SEVEN_BOMBS_TXT;
    }
    void closed_txt_field_set_EIGHT_BOMBS(int i, int j) {
        closed_txt_field[i][j] = EIGHT_BOMBS_TXT;
    }

    int opened_txt_field_get_information(int i, int j) {
        return Character.getNumericValue(opened_txt_field[i][j]);
    }
}

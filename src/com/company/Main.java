
package com.company;

public class Main {
    // TODO: 28.01.2018 StatusBar (BOMBS_COUNT,Button(new game)),Problem with coordinatess,wrong bomb so ugly

    public static void NewGame() {
        /*
        Start new game!
         */
        Fields fields = new Fields();
        View swing = new View(fields);
    }

    public static void main(String[] args) {
        NewGame();
    }
}

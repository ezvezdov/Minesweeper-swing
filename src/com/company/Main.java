package com.company;

public class Main {
    // TODO: 03.02.2018 StatusBar (BOMBS_COUNT,Button(new game)),wrong bomb so ugly

    private static void NewGame() {
        /*
        Start new game!
         */
        Fields fields = new Fields();
        new View(fields);
    }

    public static void main(String[] args) {
        NewGame();
    }
}

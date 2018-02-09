package com.company;

public class Main {
    // TODO: 03.02.2018 StatusBar (FLAGS_COUNT,Button(new game)),wrong bomb so ugly


    public static void NewGame() {
        Fields fields = new Fields();
        new View(fields);
    }

    public static void main(String[] args) {
        NewGame();
    }

}

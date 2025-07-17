package com.deckmanager.deckmanager.adapter.in.exceptions;

public class IncorrectDeckFileLayout extends RuntimeException {
    public IncorrectDeckFileLayout() {
        super("Incorrect Deck File Layout. Layout should be as such:" +
                "1 Access Tunnel\n" +
                "1 Ajani, Sleeper Agent\n" +
                "1 Akroma's Memorial\n" +
                "1 Akroma's Will\n" +
                "1 Akroma, Angel of Wrath\n" +
                "1 Akroma, Vision of Ixidor\n" +
                "1 Alesha's Legacy\n" +
                "1 Angel of Invention");
    }
}

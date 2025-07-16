package com.deckmanager.deckmanager.domain.model;

import java.util.HashMap;

public class Card {
    private String UUID;
    private String name;
    private HashMap<Mana, Integer> manaCost;
    private String type;
}

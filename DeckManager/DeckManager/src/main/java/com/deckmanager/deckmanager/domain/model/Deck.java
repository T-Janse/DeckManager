package com.deckmanager.deckmanager.domain.model;

import java.util.*;

public class Deck {
    private UUID id;
    private String name;
    private List<String> cards;
    private DeckArchetype archetype;

    Deck(String name, List<String> cards, DeckArchetype archetype) {
        this.name = name;
        this.cards = cards;
        this.archetype = archetype;
    }

    Deck(String name, DeckArchetype archetype) {
        this.name = name;
        this.archetype = archetype;
        cards = new ArrayList<>();
    }
}
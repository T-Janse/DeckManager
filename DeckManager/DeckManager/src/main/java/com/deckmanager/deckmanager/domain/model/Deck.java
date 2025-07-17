package com.deckmanager.deckmanager.domain.model;


import com.deckmanager.deckmanager.domain.model.enums.DeckArchetype;
import lombok.Getter;

import java.util.*;

public class Deck {
    @Getter
    private UUID id;
    private String name;
    private List<String> cards;
    private DeckArchetype archetype;

    public Deck(String name, List<String> cards, DeckArchetype archetype) {
        this.name = name;
        this.cards = cards;
        this.archetype = archetype;
    }

    public Deck(String name, DeckArchetype archetype) {
        this.name = name;
        this.archetype = archetype;
        cards = new ArrayList<>();
    }

}
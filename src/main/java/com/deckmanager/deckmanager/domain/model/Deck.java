package com.deckmanager.deckmanager.domain.model;


import com.deckmanager.deckmanager.domain.model.enums.DeckArchetype;
import com.deckmanager.deckmanager.domain.model.enums.Language;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Deck {
    private UUID id;
    private String name;
    private HashMap<String, Integer> cardNames;
    private DeckArchetype archetype;
    private Language language;

    public Deck(String name, DeckArchetype archetype, Language language) {
        this.name = name;
        this.archetype = archetype;
        cardNames = new HashMap<>();
    }

    public Deck(){

    }

    /**
     * Adds a {@link Card} to an existing {@link Deck}
     *
     * @param count amount of the Card in the Deck
     * @param name Name of the Card
     */
    public void addCard(int count, String name){
        cardNames.put(name, count);
    }
}
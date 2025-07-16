package com.deckmanager.deckmanager.application.port.out;

import com.deckmanager.deckmanager.domain.model.Deck;

import java.util.UUID;

public interface DeckRepository {
    void save(Deck deck);
    Deck findById(UUID id);
}

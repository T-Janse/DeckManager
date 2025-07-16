package com.deckmanager.deckmanager.adapter.out.persistance;

import com.deckmanager.deckmanager.application.port.out.DeckRepository;
import com.deckmanager.deckmanager.domain.model.Deck;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DeckRepositoryJpa implements DeckRepository {
    private final Map<UUID, Deck> db = new HashMap<>(); // temporary memory DB

    @Override
    public void save(Deck deck) {
        db.put(deck.getId(), deck);
    }

    @Override
    public Deck findById(UUID id) {
        return db.get(id);
    }
}

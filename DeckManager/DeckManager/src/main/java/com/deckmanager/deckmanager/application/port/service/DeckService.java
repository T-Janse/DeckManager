package com.deckmanager.deckmanager.application.port.service;

import com.deckmanager.deckmanager.application.port.in.UploadDeckUseCase;
import com.deckmanager.deckmanager.application.port.out.DeckRepository;
import com.deckmanager.deckmanager.domain.model.Deck;
import com.deckmanager.deckmanager.domain.model.enums.DeckArchetype;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeckService implements UploadDeckUseCase {
    private final DeckRepository repo;

    public DeckService(DeckRepository repo) {
        this.repo = repo;
    }

    @Override
    public UUID upload(String deckText) {
        Deck deck = new Deck("test", DeckArchetype.MONO_BLACK);
        repo.save(deck);
        return deck.getId();
    }
}

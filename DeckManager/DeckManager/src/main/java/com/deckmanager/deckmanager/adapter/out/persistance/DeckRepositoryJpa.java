package com.deckmanager.deckmanager.adapter.out.persistance;

import com.deckmanager.deckmanager.adapter.out.persistance.entities.DeckCardEntity;
import com.deckmanager.deckmanager.adapter.out.persistance.entities.DeckEntity;
import com.deckmanager.deckmanager.application.port.out.DatabaseSaveUseCase;
import com.deckmanager.deckmanager.domain.model.Deck;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DeckRepositoryJpa implements DatabaseSaveUseCase {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Deck deck) {
        DeckEntity entity = toEntity(deck);
        entityManager.merge(entity);
    }

    @Override
    public Deck findById(UUID id) {
        DeckEntity entity = entityManager.find(DeckEntity.class, id);
        if (entity == null) return null;
        return toDomain(entity);
    }

    private DeckEntity toEntity(Deck deck) {
        DeckEntity entity = new DeckEntity();
        entity.setId(deck.getId());
        entity.setName(deck.getName());

        List<DeckCardEntity> cardEntities = new ArrayList<>();
        deck.getCardNames().keySet().forEach(cardName -> {
            DeckCardEntity cardEntity = new DeckCardEntity();
            cardEntity.setCardName(cardName);
            cardEntity.setCount(deck.getCardNames().get(cardName));
            cardEntity.setDeck(entity);
            cardEntities.add(cardEntity);
        });
        entity.setCards(cardEntities);
        return entity;
    }

    private Deck toDomain(DeckEntity entity) {
        throw new IllegalStateException("Not implemented yet");
    }
}

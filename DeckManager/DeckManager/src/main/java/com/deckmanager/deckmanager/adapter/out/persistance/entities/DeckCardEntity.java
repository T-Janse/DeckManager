package com.deckmanager.deckmanager.adapter.out.persistance.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DeckCardEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String cardName;
    private int count;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private DeckEntity deck;
}


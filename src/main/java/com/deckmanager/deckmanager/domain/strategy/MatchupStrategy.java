package com.deckmanager.deckmanager.domain.strategy;

import com.deckmanager.deckmanager.domain.model.Deck;

public interface MatchupStrategy {
    double estimateWinRate(Deck deckA, Deck deckB);
}

package com.deckmanager.deckmanager.application.port.out;

import com.deckmanager.deckmanager.domain.model.Card;
import com.deckmanager.deckmanager.domain.model.enums.Language;

import java.util.Optional;

public interface MtgApiRetrievalUseCase {
    String cardUrl = "/cards";
    Optional<Card> getCardByName(String name, Language lang);
}

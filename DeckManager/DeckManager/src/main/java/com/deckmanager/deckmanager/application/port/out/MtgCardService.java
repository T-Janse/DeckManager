package com.deckmanager.deckmanager.application.port.out;

import com.deckmanager.deckmanager.domain.model.Card;
import com.deckmanager.deckmanager.domain.model.enums.Language;

import java.util.Optional;

public interface MtgCardService {
    String cardUrl = "/cards";
    Optional<Card> getCardsByName(String name, Language lang);
}

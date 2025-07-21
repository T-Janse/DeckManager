package com.deckmanager.deckmanager.adapter.out.mtg_api.dto;

import com.deckmanager.deckmanager.domain.model.Card;
import com.deckmanager.deckmanager.domain.model.dto.CardDto;
import com.deckmanager.deckmanager.domain.model.enums.Language;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MtgApiResponse {

    @JsonProperty("cards")
    private List<CardDto> cards;

    /**
     * Retrieve first card of an MTG API /cards response
     *
     * @param lang The language the card should be in
     * @return First {@link Card} object in an MTG API /cards response
     */
    public Optional<Card> getFirstResultCard(Language lang) {
        if (cards == null || cards.isEmpty()) {
            return Optional.empty();
        }
        CardDto matchedCard = cards.get(0);
        return Optional.of(matchedCard.toDomain(lang));
    }

    //TODO get all names
}

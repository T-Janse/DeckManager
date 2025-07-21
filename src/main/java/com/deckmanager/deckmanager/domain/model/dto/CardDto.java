package com.deckmanager.deckmanager.domain.model.dto;

import com.deckmanager.deckmanager.domain.model.Card;
import com.deckmanager.deckmanager.domain.model.enums.Language;
import com.deckmanager.deckmanager.domain.model.enums.Mana;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDto {
    private String name;
    private String manaCost;
    private int cmc;
    private List<String> colors;
    private List<String> colorIdentity;
    private String type;
    private List<String> supertypes;
    private List<String> types;
    private List<String> subtypes;
    private String rarity;
    private String set;
    private String setName;
    private String text;
    private String artist;
    private String number;
    private String layout;
    private String imageUrl;
    private String loyalty;
    @JsonProperty("foreignNames")
    private List<ForeignCardDto> foreignNames;


    public Card toDomain(Language lang) {
        ForeignCardDto localized = null;
        if (foreignNames != null && lang != null) {
            localized = foreignNames.stream()
                    .filter(f -> f.getLanguage().equalsIgnoreCase(lang.getDisplayName()))
                    .findFirst()
                    .orElse(null);
        }
        return new Card(
                name,
                manaCost,
                cmc,
                text,
                type,
                imageUrl,
                localized
        );
    }
}

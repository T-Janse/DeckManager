package com.deckmanager.deckmanager.domain.model;

import com.deckmanager.deckmanager.domain.model.dto.ForeignCardDto;
import com.deckmanager.deckmanager.domain.model.enums.Language;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Card {

    private final String name;
    private final String manaCost;
    private final double cmc;
    private final String text;
    private final String type;
    private final String imageUrl;
    private final Language language;

    public Card(String name,
                String manaCost,
                double cmc,
                String text,
                String type,
                String imageUrl,
                ForeignCardDto localizedCard) {

        if (localizedCard != null) {
            this.name = localizedCard.getName();
            this.text = localizedCard.getText();
            this.imageUrl = localizedCard.getImageUrl();
            this.language = Language.fromString(localizedCard.getLanguage());
        } else {
            this.name = name;
            this.text = text;
            this.imageUrl = imageUrl;
            this.language = null;
        }
        this.manaCost = manaCost;
        this.cmc = cmc;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", manaCost='" + manaCost + '\'' +
                ", cmc=" + cmc +
                ", text='" + text + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return Double.compare(card.cmc, cmc) == 0 &&
                Objects.equals(name, card.name) &&
                Objects.equals(manaCost, card.manaCost) &&
                Objects.equals(text, card.text) &&
                Objects.equals(imageUrl, card.imageUrl) &&
                Objects.equals(language, card.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manaCost, cmc, text, imageUrl, language);
    }
}

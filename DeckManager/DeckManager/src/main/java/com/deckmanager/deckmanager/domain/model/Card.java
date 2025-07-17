package com.deckmanager.deckmanager.domain.model;

import com.deckmanager.deckmanager.domain.model.dto.ForeignCardDto;
import com.deckmanager.deckmanager.domain.model.enums.Language;
import com.deckmanager.deckmanager.domain.model.enums.Mana;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Card {

    private final String name;
    private final HashMap<Mana, Integer> manaCost;
    private final int cmc;
    private final String text;
    private final String type;
    private final String imageUrl;
    private final Language language;

    public Card(String name,
                String manaCost,
                int cmc,
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
        int colorLess = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int white = 0;
        int black = 0;

        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(manaCost);

        while (matcher.find()) {
            String token = matcher.group(1);
            if (token.matches("\\d+")) {
                colorLess += Integer.parseInt(token);
            } else if (token.equalsIgnoreCase("W")) {
                white++;
            }else if (token.equalsIgnoreCase("R")) {
                red++;
            }else if (token.equalsIgnoreCase("G")) {
                green++;
            }else if (token.equalsIgnoreCase("U")) {
                blue++;
            }else if (token.equalsIgnoreCase("B")) {
                black++;
            }
        }

        this.manaCost = new HashMap<>(Map.of(
            Mana.COLORLESS, colorLess,
            Mana.RED, red,
            Mana.GREEN, green,
            Mana.BLUE, blue,
            Mana.WHITE, white,
            Mana.BLACK, black
        ));
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

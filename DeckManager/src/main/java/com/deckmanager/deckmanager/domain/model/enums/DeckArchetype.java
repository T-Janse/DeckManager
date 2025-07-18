package com.deckmanager.deckmanager.domain.model.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public enum DeckArchetype {
    COLORLESS,
    RED,       // Red
    GREEN,     // Green
    BLUE,      // Blue
    WHITE,     // White
    BLACK,     // Black

    AZORIUS,   // White + Blue
    BOROS,     // Red + White
    DIMIR,     // Blue + Black
    GOLGARI,   // Black + Green
    GRUUL,     // Red + Green
    IZZET,     // Blue + Red
    ORZHOV,    // White + Black
    RAKDOS,    // Black + Red
    SELESNYA,  // Green + White
    SIMIC,     // Green + Blue

    JESKAI,    // Blue + Red + White
    MARDU,     // Red + White + Black
    SULTAI,    // Black + Green + Blue
    TEMUR,     // Green + Blue + Red
    ABZAN,     // White + Black + Green
    JUND,      // Red + Green + Black

    WITHOUT_WHITE,  // Red + Green + Blue + Black
    WITHOUT_BLUE,   // Red + White + Green + Black
    WITHOUT_BLACK,  // Red + White + Green + Blue
    WITHOUT_RED,    // White + Green + Blue + Black
    WITHOUT_GREEN,  // White + Blue + Black + Red

    WUBRG      // White + Blue + Black + Red + Green
    ;
    /**
     *
     * @return test2
     */
    public static DeckArchetype manaCostToArchetype(HashMap<Mana, Integer> manaCost) {
        Set<Mana> colorsPresent = manaCost.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getValue() > 0)
                .map(Map.Entry::getKey)
                .filter(mana -> mana != Mana.COLORLESS)
                .collect(Collectors.toSet());

        // Special case: Only colorless mana
        boolean hasOnlyColorless = manaCost.entrySet().stream()
                .allMatch(entry -> entry.getKey() == Mana.COLORLESS || entry.getValue() == null || entry.getValue() == 0);

        if (hasOnlyColorless) {
            return DeckArchetype.COLORLESS;
        }

        if (colorsPresent.size() == 1) {
            Mana only = colorsPresent.iterator().next();
            switch (only) {
                case RED: return DeckArchetype.RED;
                case GREEN: return DeckArchetype.GREEN;
                case BLUE: return DeckArchetype.BLUE;
                case WHITE: return DeckArchetype.WHITE;
                case BLACK: return DeckArchetype.BLACK;
            }
        }

        if (colorsPresent.size() == 2) {
            if (colorsPresent.containsAll(Set.of(Mana.WHITE, Mana.BLUE))) return DeckArchetype.AZORIUS;
            if (colorsPresent.containsAll(Set.of(Mana.RED, Mana.WHITE))) return DeckArchetype.BOROS;
            if (colorsPresent.containsAll(Set.of(Mana.BLUE, Mana.BLACK))) return DeckArchetype.DIMIR;
            if (colorsPresent.containsAll(Set.of(Mana.BLACK, Mana.GREEN))) return DeckArchetype.GOLGARI;
            if (colorsPresent.containsAll(Set.of(Mana.RED, Mana.GREEN))) return DeckArchetype.GRUUL;
            if (colorsPresent.containsAll(Set.of(Mana.BLUE, Mana.RED))) return DeckArchetype.IZZET;
            if (colorsPresent.containsAll(Set.of(Mana.WHITE, Mana.BLACK))) return DeckArchetype.ORZHOV;
            if (colorsPresent.containsAll(Set.of(Mana.BLACK, Mana.RED))) return DeckArchetype.RAKDOS;
            if (colorsPresent.containsAll(Set.of(Mana.GREEN, Mana.WHITE))) return DeckArchetype.SELESNYA;
            if (colorsPresent.containsAll(Set.of(Mana.GREEN, Mana.BLUE))) return DeckArchetype.SIMIC;
        }

        if (colorsPresent.size() == 3) {
            if (colorsPresent.containsAll(Set.of(Mana.BLUE, Mana.RED, Mana.WHITE))) return DeckArchetype.JESKAI;
            if (colorsPresent.containsAll(Set.of(Mana.RED, Mana.WHITE, Mana.BLACK))) return DeckArchetype.MARDU;
            if (colorsPresent.containsAll(Set.of(Mana.BLACK, Mana.GREEN, Mana.BLUE))) return DeckArchetype.SULTAI;
            if (colorsPresent.containsAll(Set.of(Mana.GREEN, Mana.BLUE, Mana.RED))) return DeckArchetype.TEMUR;
            if (colorsPresent.containsAll(Set.of(Mana.WHITE, Mana.BLACK, Mana.GREEN))) return DeckArchetype.ABZAN;
            if (colorsPresent.containsAll(Set.of(Mana.RED, Mana.GREEN, Mana.BLACK))) return DeckArchetype.JUND;
        }

        if (colorsPresent.size() == 4) {
            if (!colorsPresent.contains(Mana.WHITE)) return DeckArchetype.WITHOUT_WHITE;
            if (!colorsPresent.contains(Mana.BLUE)) return DeckArchetype.WITHOUT_BLUE;
            if (!colorsPresent.contains(Mana.BLACK)) return DeckArchetype.WITHOUT_BLACK;
            if (!colorsPresent.contains(Mana.RED)) return DeckArchetype.WITHOUT_RED;
            if (!colorsPresent.contains(Mana.GREEN)) return DeckArchetype.WITHOUT_GREEN;
        }

        if (colorsPresent.size() == 5) {
            return DeckArchetype.WUBRG;
        }

        return DeckArchetype.COLORLESS;
    }
}

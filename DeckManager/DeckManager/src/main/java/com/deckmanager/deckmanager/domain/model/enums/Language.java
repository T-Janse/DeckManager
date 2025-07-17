package com.deckmanager.deckmanager.domain.model.enums;

import java.util.Arrays;

public enum Language {
     ENGLISH,
     FRENCH,
     ITALIAN,
     SPANISH,
     JAPANESE,
     GERMAN,
     KOREAN,
     ;

     public String getDisplayName(){
        return name();
     }

     public static Language fromString(String value) {
         if (value == null) return null;

         return Arrays.stream(Language.values())
                 .filter(lang -> lang.name().equalsIgnoreCase(value))
                 .findFirst()
                 .orElse(null);
     }
 }

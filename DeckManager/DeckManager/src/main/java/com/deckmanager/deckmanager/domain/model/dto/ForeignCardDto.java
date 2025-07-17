package com.deckmanager.deckmanager.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForeignCardDto {

    private String name;
    private String text;
    private String type;
    private String flavor;
    private String imageUrl;
    private String language;
}


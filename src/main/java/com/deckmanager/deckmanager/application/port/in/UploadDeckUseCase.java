package com.deckmanager.deckmanager.application.port.in;

import com.deckmanager.deckmanager.domain.model.enums.Language;

import java.util.UUID;

public interface UploadDeckUseCase {
    UUID upload(String deckText, String Commander, Language lang);
}

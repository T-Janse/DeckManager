package com.deckmanager.deckmanager.application.port.in;

import java.util.UUID;

public interface UploadDeckUseCase {
    UUID upload(String deckText);
}

package com.deckmanager.deckmanager.adapter.in.web;

import com.deckmanager.deckmanager.application.port.in.UploadDeckUseCase;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/decks")
public class DeckController {
    private final UploadDeckUseCase deckUseCase;

    public DeckController(UploadDeckUseCase deckUseCase) {
        this.deckUseCase = deckUseCase;
    }

    //TODO realize endpoint to upload deck by textfile
    @PostMapping("/upload_file")
    public UUID upload(@RequestBody String deckText) {
        return deckUseCase.upload(deckText);
    }
}

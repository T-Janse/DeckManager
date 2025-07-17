package com.deckmanager.deckmanager.adapter.in;

import com.deckmanager.deckmanager.application.port.in.UploadDeckUseCase;
import com.deckmanager.deckmanager.domain.model.enums.Language;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public UUID upload(
            @RequestBody @RequestParam("deckFile") String deckText,
            @RequestParam("commander") String commander,
            @RequestParam("language") String languageCode) {
        return deckUseCase.upload(deckText, commander, Language.fromString(languageCode));
    }
}

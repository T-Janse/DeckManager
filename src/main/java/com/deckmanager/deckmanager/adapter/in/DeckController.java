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

    /**
     * Handles a POST request to upload a new {@link com.deckmanager.deckmanager.domain.model.Deck} from a text file.
     *
     * @param deckText the deck in plain text format (e.g. one card name per line)
     * @param commander the name of the Deck's commander
     * @param language the language of the cards in the Deck (e.g. "French")
     * @return the UUID of the newly created Deck
     */
    @PostMapping("/upload_file")
    public UUID upload(
            @RequestBody @RequestParam("deckFile") String deckText,
            @RequestParam("commander") String commander,
            @RequestParam("language") String language) {
        return deckUseCase.upload(deckText, commander, Language.fromString(language));
    }
}

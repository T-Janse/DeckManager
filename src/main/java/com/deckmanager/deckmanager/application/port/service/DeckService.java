package com.deckmanager.deckmanager.application.port.service;

import com.deckmanager.deckmanager.adapter.in.exceptions.IncorrectDeckFileLayout;
import com.deckmanager.deckmanager.adapter.out.mtg_api.MtgApiService;
import com.deckmanager.deckmanager.application.port.in.UploadDeckUseCase;
import com.deckmanager.deckmanager.application.port.out.DatabaseSaveUseCase;
import com.deckmanager.deckmanager.application.port.out.MtgApiRetrievalUseCase;
import com.deckmanager.deckmanager.domain.model.Card;
import com.deckmanager.deckmanager.domain.model.Deck;
import com.deckmanager.deckmanager.domain.model.enums.DeckArchetype;
import com.deckmanager.deckmanager.domain.model.enums.Language;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeckService implements UploadDeckUseCase, MtgApiRetrievalUseCase {
    private final DatabaseSaveUseCase repo;
    private final MtgApiService mtgApiService;

    public DeckService(DatabaseSaveUseCase repo) {
        this.repo = repo;
        this.mtgApiService = new MtgApiService();
    }

    /**
     * Implements {@link UploadDeckUseCase}
     * Upload {@link Deck} to Database
     *
     * @param deckText The Deck in Textfile format
     * @param commander The name of the Deck's commander
     * @param lang The language of the Deck in the format of 'French'
     * @return UUID of the uploaded Deck
     * @throws IncorrectDeckFileLayout deckText does not follow proper formatting
     */
    @Override
    public UUID upload(String deckText, String commander, Language lang) throws IncorrectDeckFileLayout {
        Card commanderCard = getCardByName(commander, lang).orElseThrow(IncorrectDeckFileLayout::new);
        DeckArchetype archetype = DeckArchetype.manaCostToArchetype(commanderCard.getManaCost());
        Deck deck = new Deck(commander, archetype, lang);
        deckText.lines().forEach(line -> {
            String countString = "";
            String nameString = "";

            int spaceIndex = line.indexOf(' ');
            if (spaceIndex == -1) {
                throw new IncorrectDeckFileLayout();
            } else {
                countString = line.substring(0, spaceIndex);
                nameString = line.substring(spaceIndex + 1);
            }
            try {
                deck.addCard(Integer.parseInt(countString), nameString);
            }catch (NumberFormatException e) {
                throw new IncorrectDeckFileLayout();
            }
        });
        repo.save(deck);
        return deck.getId();
    }

    /**
     * Implements {@link MtgApiRetrievalUseCase}
     * Get {@link Card} from MTG API by name
     *
     * @param name Name of the Card to retrieve
     * @param lang Language the Card should be in the format of 'French'
     * @return {@link Card} object if found by name
     */
    @Override
    public Optional<Card> getCardByName(String name, Language lang) {
        return mtgApiService.getCardByName(name, lang);
    }
}

package com.deckmanager.deckmanager.application.port.service;

import com.deckmanager.deckmanager.adapter.in.exceptions.IncorrectDeckFileLayout;
import com.deckmanager.deckmanager.adapter.out.mtg_api.MtgApiService;
import com.deckmanager.deckmanager.application.port.in.UploadDeckUseCase;
import com.deckmanager.deckmanager.application.port.out.DeckRepository;
import com.deckmanager.deckmanager.application.port.out.MtgCardService;
import com.deckmanager.deckmanager.domain.model.Card;
import com.deckmanager.deckmanager.domain.model.Deck;
import com.deckmanager.deckmanager.domain.model.enums.DeckArchetype;
import com.deckmanager.deckmanager.domain.model.enums.Language;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeckService implements UploadDeckUseCase, MtgCardService {
    private final DeckRepository repo;
    private final MtgApiService mtgApiService;

    public DeckService(DeckRepository repo) {
        this.repo = repo;
        this.mtgApiService = new MtgApiService();
    }

    @Override
    public UUID upload(String deckText, String commander, Language lang) throws IncorrectDeckFileLayout {
        Card commanderCard = getCardsByName(commander, lang).orElseThrow(IncorrectDeckFileLayout::new);
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

    @Override
    public Optional<Card> getCardsByName(String name, Language lang) {
        return mtgApiService.getCardsByName(name, lang);
    }
}

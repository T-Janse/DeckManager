package com.deckmanager.deckmanager.adapter.in.web;

import com.deckmanager.deckmanager.adapter.out.mtg_api.MtgApiService;
import com.deckmanager.deckmanager.domain.model.Card;
import com.deckmanager.deckmanager.domain.model.enums.Language;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 *  Temporary Controller during production
 */
@RestController
@RequestMapping("/api/test")
public class TestController {
    MtgApiService mtgApiService =  new MtgApiService();

    @GetMapping("/card_call")
    public String sayHello() {
        Optional<Card> result = mtgApiService.getCardsByName("Ajani", Language.FRENCH);
        return result.get().getName();
    }

}

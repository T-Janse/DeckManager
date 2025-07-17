package com.deckmanager.deckmanager.adapter.out.mtg_api;

import com.deckmanager.deckmanager.adapter.out.mtg_api.dto.MtgApiResponse;
import com.deckmanager.deckmanager.application.port.out.MtgCardService;
import com.deckmanager.deckmanager.domain.model.Card;
import com.deckmanager.deckmanager.domain.model.enums.Language;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class MtgApiService implements MtgCardService {
    private final String baseUrl = "https://api.magicthegathering.io/v1/cards";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Optional<Card> getCardsByName(String name, Language lang) {
        String url = baseUrl + cardUrl + "?name=" + name;

        try {
            ResponseEntity<MtgApiResponse> response = restTemplate.getForEntity(url, MtgApiResponse.class);
            MtgApiResponse result = response.getBody();
            if (result == null || result.getCards() == null || result.getCards().isEmpty()) {
                return Optional.empty();
            }

            return result.getFirstResultCard(lang);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }
}

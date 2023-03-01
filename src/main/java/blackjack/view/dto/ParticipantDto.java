package blackjack.view.dto;

import blackjack.domain.card.Card;
import java.util.List;

public class ParticipantDto {

    private final String name;
    private final List<Card> cards;

    public ParticipantDto(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}

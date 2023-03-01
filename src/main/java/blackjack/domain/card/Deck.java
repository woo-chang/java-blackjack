package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.remove(0);
    }
}

package blackjack.domain.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import java.util.ArrayList;
import java.util.List;

public class DeckFactory {

    private static final List<Card> TRUMP;

    static {
        final List<Card> cards = new ArrayList<>();
        for (final Suit suit : Suit.values()) {
            for (final Number number : Number.values()) {
                cards.add(new Card(suit, number));
            }
        }
        TRUMP = cards;
    }

    public static Deck createWithCount(int count) {
        final List<Card> cards = new ArrayList<>();
        while (count-- > 0) {
            cards.addAll(TRUMP);
        }
        return new Deck(cards);
    }
}

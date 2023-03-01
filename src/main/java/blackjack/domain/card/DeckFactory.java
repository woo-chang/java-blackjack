package blackjack.domain.card;

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

    public static Deck makeWithPacks(int packCount) {
        final List<Card> cards = new ArrayList<>();
        while (packCount-- > 0) {
            cards.addAll(TRUMP);
        }
        return new Deck(cards);
    }
}

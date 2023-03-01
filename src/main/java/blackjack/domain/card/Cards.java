package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards = new ArrayList<>();

    public void add(final Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = 0;
        for (final Card card : cards) {
            score += card.getScore();
        }
        return score;
    }

    public int count() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}

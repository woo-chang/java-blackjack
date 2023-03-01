package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected final Cards cards = new Cards();

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int getScore() {
        return cards.getScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    public abstract String getName();
}

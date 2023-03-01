package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Participant {

    public static final String DEALER_NAME = "딜러";

    protected final Cards cards = new Cards();

    public abstract String getName();

    public void takeCard(final Card card) {
        cards.add(card);
    }

    public boolean isDrawable(final int limit) {
        if (getScore() < limit) {
            return true;
        }
        return false;
    }

    public int getScore() {
        return cards.getScore();
    }

    public Cards getCards() {
        return cards;
    }
}

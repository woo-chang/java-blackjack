package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards = new ArrayList<>();

    public void add(final Card card) {
        cards.add(card);
    }

    //TODO: ACE인 경우 계산하는 로직 수정
    public int getScore() {
        int score = 0;
        for (final Card card : cards) {
            //card 에게 정보를 받지 않고 메시지를 던져서 할 수 없을까?
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

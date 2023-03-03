package blackjack.domain.card;

import static blackjack.domain.card.Number.ACE;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Stack;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckTest {

    @Test
    void 카드를_뽑는다() {
        final Stack<Card> cards = new Stack<>();
        final Card card = new Card(ACE, SPADE);
        cards.add(card);

        final Deck deck = new Deck(cards);

        assertThat(deck.draw()).isEqualTo(card);
    }

    @Test
    void 카드가_존재하지_않으면_예외를_던진다() {
        final Stack<Card> cards = new Stack<>();

        final Deck deck = new Deck(cards);

        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class);
    }
}